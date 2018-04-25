package com.osms.monitoring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

import oshi.json.SystemInfo;

import oshi.json.hardware.GlobalMemory;
import oshi.json.hardware.HardwareAbstractionLayer;
import oshi.json.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.json.util.PropertiesUtil;


@SpringBootApplication
public class OsmsApplication {

	private static final Logger logger = LoggerFactory.getLogger(OsmsApplication.class);

	public static void main(String[] args) throws Exception{
		SpringApplication.run(OsmsApplication.class, args);
		//BaseValidator.start();
		//OsMonitor.runOsMonitor();
		//WebMonitor.runWebMonitor();

		//OsMonitor2.runOsMonitor2();
		//DBMonitor.runDBMonitor();
		//DiskMonitor.runDiskMonitor();

		SystemInfo si = new SystemInfo();

		//HardwareAbstractionLayer hal = si.getHardware();
		//OperatingSystem os = si.getOperatingSystem();

		logger.info("Checking Memory...");
		//printMemory(hal.getMemory());

		Properties props = PropertiesUtil.loadProperties("conf/oshi.json.properties");
		// Pretty JSON
		logger.info(si.toPrettyJSON(props));
	}

	private static void printMemory(GlobalMemory memory) {
		logger.info("Memory: " + FormatUtil.formatBytes(memory.getAvailable()) + "/"
				+ FormatUtil.formatBytes(memory.getTotal()));
		logger.info("Swap used: " + FormatUtil.formatBytes(memory.getSwapUsed()) + "/"
				+ FormatUtil.formatBytes(memory.getSwapTotal()));
	}
}