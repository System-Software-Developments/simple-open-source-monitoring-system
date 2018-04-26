package com.osms.monitoring;


import java.util.Arrays;
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
		//BaseValidator.start();
		//OsMonitor.runOsMonitor();
		//WebMonitor.runWebMonitor();

		//OsMonitor2.runOsMonitor2();
		//DBMonitor.runDBMonitor();
		//DiskMonitor.runDiskMonitor();

		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		OperatingSystem os = si.getOperatingSystem();

		logger.info("Checking Memory...");
		printMemory(hal.getMemory());
		printCpu(hal.getProcessor());

		//Properties props = PropertiesUtil.loadProperties("conf/oshi.json.properties");
		// Pretty JSON
		//ogger.info(si.toPrettyJSON(props));

//		OSProcess process;
//		long currentTime,previousTime = 0,timeDifference;
//		double cpu;
//		int pid = 3080;
//		OperatingSystem os = si.getOperatingSystem();
//		CentralProcessor processor = si.getHardware().getProcessor();
//		int cpuNumber = processor.getLogicalProcessorCount();
//		boolean processExists = true;
//
//		while (processExists) {
//			process = os.getProcess(pid);
//			if (process != null) {
//				// CPU
//				currentTime = process.getKernelTime() + process.getUserTime();
//
//				if (previousTime != -1) {
//					// If we have both a previous and a current time
//					// we can calculate the CPU usage
//					timeDifference = currentTime - previousTime;
//					cpu = (100d * (timeDifference / ((double) 1000))) / cpuNumber;
//					logger.info("cpu : {}", cpu);
//				}
//
//				previousTime = currentTime;
//				Thread.sleep(1000);
//
//			} else {
//				processExists = false;
//			}
//		}
	}

	private static void printMemory(GlobalMemory memory) {

		float f1 =  (Float.valueOf(memory.getAvailable()) / Float.valueOf(memory.getTotal()))*100;

		logger.info("Memory percent: {}", f1);

		logger.info("Memory: " + FormatUtil.formatBytes(memory.getAvailable()) + "/"
				+ FormatUtil.formatBytes(memory.getTotal()));
		logger.info("Swap used: " + FormatUtil.formatBytes(memory.getSwapUsed()) + "/"
				+ FormatUtil.formatBytes(memory.getSwapTotal()));
	}

	private static void printCpu(CentralProcessor processor) {
		System.out.println("Uptime: " + FormatUtil.formatElapsedSecs(processor.getSystemUptime()));
		System.out.println(
				"Context Switches/Interrupts: " + processor.getContextSwitches() + " / " + processor.getInterrupts());

		long[] prevTicks = processor.getSystemCpuLoadTicks();
		System.out.println("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
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

		logger.info("totalCpu : {}", totalCpu);

		System.out.format(
				"User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%%n",
				100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
				100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu);
		//System.out.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
		//System.out.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
		double[] loadAverage = processor.getSystemLoadAverage(3);
		System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
				+ (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
				+ (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
		// per core CPU
		StringBuilder procCpu = new StringBuilder("CPU load per processor:");
		double[] load = processor.getProcessorCpuLoadBetweenTicks();
		double sum = 0;
		for (double avg : load) {
			sum += avg;
		}
		procCpu.append(String.format(" %.1f%%", sum * 100));
		System.out.println(procCpu.toString());
	}
}