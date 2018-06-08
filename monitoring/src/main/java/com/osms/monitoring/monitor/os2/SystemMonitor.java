package com.osms.monitoring.monitor.os2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osms.monitoring.monitor.os.DiskMonitor;
import com.osms.monitoring.monitor.os.util.OsCheck;
import com.osms.monitoring.monitor.os.util.OsCheck.OSType;
import com.osms.monitoring.monitor.os2.domain.FileStore;
import com.osms.monitoring.monitor.os2.domain.Process;
import com.osms.monitoring.monitor.os2.domain.SolutionSystem;
import com.osms.monitoring.util.OsmsProperties;
import com.osms.monitoring.util.ShellCommander;
import com.osms.monitoring.util.SizeUnit;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.PlatformEnum;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.json.SystemInfo;
import oshi.json.hardware.CentralProcessor;
import oshi.json.hardware.HardwareAbstractionLayer;
import oshi.json.software.os.OperatingSystem;
import oshi.json.util.PropertiesUtil;
import oshi.software.os.NetworkParams;
import oshi.json.software.os.OSProcess;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.Util;

public class SystemMonitor {

    private static final Logger logger = LoggerFactory.getLogger(SystemMonitor.class);
    private static boolean running = true;
    private static String tname = "system";

    public static void runSystemMonitor() throws Exception {

       final ExecutorService threadPools = Executors.newFixedThreadPool(1, new ThreadFactory() {

            public Thread newThread(final Runnable r) {
                Thread t = new Thread() {
                    public void run() {
                        r.run();
                    }
                };

                t.setName(tname);
                return t;
            }
        });

        threadPools.execute(new Runnable() {
            public void run() {
                while (running) {

                    SystemInfo si = new SystemInfo();
                    Properties props = PropertiesUtil.loadProperties("conf/oshi.json.properties");

                    try {

                        ObjectMapper mapper = new ObjectMapper();
                        SolutionSystem result = mapper.readValue(si.toPrettyJSON(props), SolutionSystem.class);

                        //System.out.println(result.getHardware().getProcessor().getProcessorID());

                        List<FileStore> list = result.getOperatingSystem().getFileSystem().getFileStores();

                        for (FileStore key : list){

                            long totalSpace = key.getTotalSpace();
                            if(SizeUnit.GB.to2(totalSpace) > 0) {
                                long usableSpace = key.getUsableSpace();
                                double usablePercent = ((double)usableSpace/(double)totalSpace)*100;

                                logger.info("Disk Name : {}, Usable Percent : {}%, Total Space : {}GB, Usable Space : {}GB", key.getName(),  Math.round(usablePercent*100)/100.0, SizeUnit.GB.to2(totalSpace), SizeUnit.GB.to2(usableSpace));
                            }
                        }

                        long totalMemory = result.getHardware().getMemory().getTotal();
                        long available = result.getHardware().getMemory().getAvailable();
                        long useMemory = totalMemory-available;
                        double availablePercent = ((double)available/(double)totalMemory)*100;
                        double usePercent = ((double)useMemory/(double)totalMemory)*100;

                        logger.info("Memory Total Size : {}GB, Available Size : {}GB, Available Percent : {}%, Usage Percent : {}%", SizeUnit.GB.to2(totalMemory), SizeUnit.GB.to2(available), Math.round(availablePercent*100)/100.0, Math.round(usePercent*100)/100.0);


                        //List<Float> cpuList = result.getHardware().getProcessor().getProcessorCpuLoadBetweenTicks();
                        //int i = 1;
                        //for (Float key : cpuList){
                        //    logger.info("{} cpu % : {}%", i++, key*100);
                            //logger.info("{} CPU % : {}%", i++, Math.round(key*100*100)/100.0);
                        //}

                        HardwareAbstractionLayer hal = si.getHardware();
                        CentralProcessor processor = hal.getProcessor();


                        long[] prevTicks = processor.getSystemCpuLoadTicks();
                        // Wait a second...
                        Util.sleep(1000);
                        long[] ticks = processor.getSystemCpuLoadTicks();
                        //System.out.println("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
                        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
                        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
                        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
                        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
                        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
                        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
                        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
                        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
                        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

                        double cpu = ((double)(totalCpu - (idle+iowait))/(double)totalCpu)*100;
                        //logger.info("cpu1 : {}%", cpu);

                        //  Platform 구분
                        PlatformEnum platformEnum = oshi.SystemInfo.getCurrentPlatformEnum();

                        if(PlatformEnum.LINUX == platformEnum || PlatformEnum.MACOSX == platformEnum) {
                            // 소수점 2째자리에서 반올림
                            logger.info("CPU by oshi : {}%, CPU by Linux top: {}", Math.round(cpu*100)/100.0,  ShellCommander.shellCmd("/data/osms/conf/sys-cpu.sh"));
                        }else{
                            // 소수점 2째자리에서 반올림
                            logger.info("CPU Usage oshi : {}%", Math.round(cpu*100)/100.0);
                        }

                        // TODO Network 정보
                        OperatingSystem os = si.getOperatingSystem();
                        String hostNmae = os.getNetworkParams().getHostName();
                        String domainName = os.getNetworkParams().getDomainName();
                        String dnsServer = Arrays.toString(os.getNetworkParams().getDnsServers());
                        String ipv4DefaultGateway = os.getNetworkParams().getIpv4DefaultGateway();
                        String ipv6DefaultGateway = os.getNetworkParams().getIpv6DefaultGateway();
                        logger.info("Host name : {}, Domain name : {}, DNS servers : {}, IPv4 Gateway : {}, IPv6 Gateway : {}",hostNmae, domainName, dnsServer, ipv4DefaultGateway, ipv6DefaultGateway);

                        // TODO os 종류 및 버전
                        String osVersin = result.getOperatingSystem().getVersion().getVersion();
                        String osCodeName = result.getOperatingSystem().getVersion().getCodeName();
                        String osBuild = result.getOperatingSystem().getVersion().getBuild();
                        String platform = result.getPlatform();
                        String manufacturer = result.getOperatingSystem().getManufacturer();
                        String family = result.getOperatingSystem().getFamily();
                        logger.info("OS : {} {} {} {} {}", manufacturer, family, osVersin, osCodeName, osBuild);

                        // TODO 프로세스 개수는 oshi.json.properties 설정
                        // VSZ : virtual memory size (프로세스의 가상메모리 크기)
                        // RSS : resident set size (프로세스가 사용하고 있는 물리적 메모리 크기)
                        List<Process> processList = result.getOperatingSystem().getProcesses();
                        // logger.info("   PID  %CPU %MEM       VSZ       RSS Name");
                        int i=1;
                        for (Process key : processList){
                            logger.info("No : {}, PID : {}, %CPU : {}, %MEM : {}, VSZ : {}, RSS : {}, Name : {}", i++, key.getProcessID(), 100d * (key.getKernelTime() + key.getUserTime()) / key.getUpTime(),
                                    100d * key.getResidentSetSize() / totalMemory, FormatUtil.formatBytes(key.getVirtualSize()),
                                    FormatUtil.formatBytes(key.getResidentSetSize()), key.getName());
                        }

                    } catch (Exception e) {
                        logger.error("Exception : {}", e);
                        e.printStackTrace();

                    }

                    try {
                        TimeUnit.MILLISECONDS.sleep(OsmsProperties.getPeriod());
                    } catch (InterruptedException e) {
                    }
                }
            }

        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                running = false;
                threadPools.shutdown();
            }
        });
    }

}