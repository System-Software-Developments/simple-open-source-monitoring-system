package com.osms.monitoring.monitor.os;


import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.osms.monitoring.util.OsmsProperties;
import com.sun.management.OperatingSystemMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by david100gom on 2018. 4. 25.
 *
 * Github : https://github.com/david100gom
 */
public class OsMonitor2 {


    private static final Logger logger = LoggerFactory.getLogger(DiskMonitor.class);
    private static boolean running = true;
    private static String tname = "os";

    public static void runOsMonitor2() throws Exception {

        //logger.info("runDiskMonitor : {}", "start");

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

                seperator(); showOSBean();
                seperator(); showThreadBean();
                seperator(); showClassLoading();
                seperator(); showMemory();
                //seperator(); showDisk();
                seperator(); showCPU();
                seperator();

                //logger.debug("send Data Disk : {}", data);

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

    /*
   * 디스크용량
   */
    private void showDisk() {
        File root = null;

        try {

            root = new File("/");
            System.out.println("Total Space: " + toMB(root.getTotalSpace()));
            System.out.println("Usable Space: " + toMB(root.getUsableSpace()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
   * cpu 사용량
   */
    private static void showCPU() {
        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runbean = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();

        long bfprocesstime = osbean.getProcessCpuTime();
        long bfuptime = runbean.getUptime();
        //long ncpus = osbean.getAvailableProcessors();

        //for (int i = 0; i < 1000000; ++i) {
        //    ncpus = osbean.getAvailableProcessors();
        //}

        long afprocesstime = osbean.getProcessCpuTime();
        long afuptime = runbean.getUptime();

        float cal = (afprocesstime - bfprocesstime) / ((afuptime - bfuptime) * 10000f);

        float usage = Math.min(99f, cal);

        logger.info("processCpuTime: {}", bfprocesstime);
        logger.info("Uptime: {}", bfuptime);
        logger.info("Calculation: {}", cal);
        logger.info("CPU Usage: {}", usage);

    }

    private void showRuntime() {
        RuntimeMXBean runbean = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();

    }

    /*
   * 메모리 사용량
   */
    private static void showMemory() {
        MemoryMXBean membean = (MemoryMXBean) ManagementFactory.getMemoryMXBean();

        MemoryUsage heap = membean.getHeapMemoryUsage();
        logger.info("Heap Memory: {}", heap.toString());

        MemoryUsage nonheap = membean.getNonHeapMemoryUsage();
        logger.info("NonHeap Memory: {}", nonheap.toString());

    }

    private static void showClassLoading() {
        ClassLoadingMXBean classbean = (ClassLoadingMXBean) ManagementFactory.getClassLoadingMXBean();

        logger.info("TotalLoadedClassCount: {}", classbean.getTotalLoadedClassCount());
        logger.info("LoadedClassCount: {}",classbean.getLoadedClassCount());
        logger.info("UnloadedClassCount: {}", classbean.getUnloadedClassCount());

    }

    private static void showThreadBean() {
        ThreadMXBean tbean = (ThreadMXBean) ManagementFactory.getThreadMXBean();

        long[] ids = tbean.getAllThreadIds();

        logger.info("Thread Count: {}", tbean.getThreadCount());

        for (long id : ids) {
            logger.info("Thread CPU Time({}) {}", id, tbean.getThreadCpuTime(id));
            logger.info("Thread User Time({}) {}", id, tbean.getThreadCpuTime(id));
        }

    }

    /**
     * OS 정보
     */
    private static void showOSBean() {

        OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        logger.info("OS Name: {}", osbean.getName());
        logger.info("OS Arch: {}", osbean.getArch());
        logger.info("Available Processors: {}", osbean.getAvailableProcessors());
        logger.info("TotalPhysicalMemorySize: {}", toMB(osbean.getTotalPhysicalMemorySize()));
        logger.info("FreePhysicalMemorySize: {}", toMB(osbean.getFreePhysicalMemorySize()));
        logger.info("TotalSwapSpaceSize: {}", toMB(osbean.getTotalSwapSpaceSize()));
        logger.info("FreeSwapSpaceSize: {}", toMB(osbean.getFreeSwapSpaceSize()));
        logger.info("CommittedVirtualMemorySize: {}", toMB(osbean.getCommittedVirtualMemorySize()));
        logger.info("SystemLoadAverage: {}", osbean.getSystemLoadAverage());

    }

    /* added cafe mocha 2009-06 */
    private static void seperator() {
        System.out.println("-----------------------------------------------");
    }
    /* added cafe mocha 2009-06 */
    private static String toMB(long size) {
        return (int)(size/(1024*1024))+"(MB)";
    }

}