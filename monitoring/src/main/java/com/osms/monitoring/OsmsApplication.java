package com.osms.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osms.monitoring.monitor.db.DBMonitor;
import com.osms.monitoring.monitor.os2.SystemMonitor;
import com.osms.monitoring.monitor.os2.domain.FileStore;
import com.osms.monitoring.monitor.os2.domain.SolutionSystem;
import com.osms.monitoring.util.SizeUnit;
import com.sun.management.OperatingSystemMXBean;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

import oshi.hardware.CentralProcessor.TickType;
import oshi.json.SystemInfo;

import oshi.json.hardware.CentralProcessor;
import oshi.json.hardware.GlobalMemory;
import oshi.json.hardware.HardwareAbstractionLayer;
import oshi.json.software.os.OSProcess;
import oshi.json.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.json.util.PropertiesUtil;
import oshi.util.Util;


@SpringBootApplication
public class OsmsApplication {

	private static final Logger logger = LoggerFactory.getLogger(OsmsApplication.class);

	public static void main(String[] args) throws Exception{

		SpringApplication.run(OsmsApplication.class, args);
		SystemMonitor.runSystemMonitor();
		DBMonitor.runDBMonitor();

	}

}