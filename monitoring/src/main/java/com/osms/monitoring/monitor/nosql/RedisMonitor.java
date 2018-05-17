package com.osms.monitoring.monitor.nosql;

import com.osms.monitoring.monitor.db.MysqlMonitor;
import com.osms.monitoring.util.OsmsProperties;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisMonitor {

    private static final Logger logger = LoggerFactory.getLogger(RedisMonitor.class);

    public static void runDBMonitor() throws Exception {

        long period = OsmsProperties.getPeriod();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                try {



                } catch (Throwable e) {
                    logger.error("Mysql/Mariadb monitor has an error", e);
                } finally {

                }

            }
        }, 0, period);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {

            }
        });
    }

}
