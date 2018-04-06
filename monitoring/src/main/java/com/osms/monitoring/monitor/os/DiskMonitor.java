package com.osms.monitoring.monitor.os;

import com.osms.monitoring.util.OsmsProperties;
import com.osms.monitoring.util.OsmsUtils;
import java.io.File;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskMonitor {

    private static final Logger logger = LoggerFactory.getLogger(DiskMonitor.class);
    private static boolean running = true;
    private static String tname = "disk";

    public static void runDiskMonitor() throws Exception {

        logger.info("runDiskMonitor : {}", "start");

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

                    String drive;                // 드라이브 명
                    double size;                 // 드라이브의 최대 크기 = 용량

                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);                                   // 소숫점 2자리까지만 보이게 변환

                    File[] roots = File.listRoots();
                    Map<String, String> data = new HashMap<String, String>();

                    for (File root : roots) {
                        drive = root.getAbsolutePath();
                        size = root.getTotalSpace() / Math.pow(1024, 3);

                        if(size > 0) {
                            long freeSpace = new File(drive).getFreeSpace();
                            double freeSize = freeSpace/Math.pow(1024, 3);      // 사용 가능 용량 (GB)
                            data.put(OsmsUtils.getDevicename("disk")+OsmsProperties.getConcatenator()+drive, nf.format(freeSize));
                        }
                    }

                    logger.debug("send Data Disk : {}", data);

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
