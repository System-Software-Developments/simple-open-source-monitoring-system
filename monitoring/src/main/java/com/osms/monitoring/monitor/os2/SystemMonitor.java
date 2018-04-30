package com.osms.monitoring.monitor.os2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osms.monitoring.monitor.os.DiskMonitor;
import com.osms.monitoring.monitor.os2.domain.FileStore;
import com.osms.monitoring.monitor.os2.domain.SolutionSystem;
import com.osms.monitoring.util.OsmsProperties;
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
import oshi.hardware.CentralProcessor.TickType;
import oshi.json.SystemInfo;
import oshi.json.hardware.CentralProcessor;
import oshi.json.hardware.HardwareAbstractionLayer;
import oshi.json.software.os.OperatingSystem;
import oshi.json.util.PropertiesUtil;
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

                        System.out.println(result.getHardware().getProcessor().getProcessorID());

                        List<FileStore> list = result.getOperatingSystem().getFileSystem().getFileStores();

                        for (FileStore key : list){
                            logger.info("disk name : {}", key.getName());
                            logger.info("disk usableSpace : {}",SizeUnit.GB.to2(key.getUsableSpace())+"GB");
                            logger.info("disk totalSpace : {}", SizeUnit.GB.to2(key.getTotalSpace())+"GB");
                        }

                        List<Float> cpuList = result.getHardware().getProcessor().getProcessorCpuLoadBetweenTicks();
                        int i = 1;
                        for (Float key : cpuList){
                            logger.info("{} cpu % : {}%", i++, key*100);
                            //logger.info("{} CPU % : {}%", i++, Math.round(key*100*100)/100.0);
                        }

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

                        logger.info("cpu : {}%", ((double)(totalCpu - (idle+iowait))/(double)totalCpu)*100);

                    } catch (IOException e) {
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