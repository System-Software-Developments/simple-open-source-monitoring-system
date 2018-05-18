package com.osms.monitoring;

import com.osms.monitoring.monitor.db.MysqlMonitor;
import com.osms.monitoring.monitor.nosql.RedisMonitor;
import com.osms.monitoring.monitor.os2.SystemMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OsmsApplication {

	private static final Logger logger = LoggerFactory.getLogger(OsmsApplication.class);

	public static void main(String[] args) throws Exception{
		SpringApplication.run(OsmsApplication.class, args);
		SystemMonitor.runSystemMonitor();
		MysqlMonitor.runDBMonitor();
        RedisMonitor.runDBMonitor();
    }

}