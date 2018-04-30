package com.osms.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osms.monitoring.domain.FileStore;
import com.osms.monitoring.domain.SolutionSystem;
import com.osms.monitoring.util.SizeUnit;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OsmsApplicationTests {

	private final static Logger logger = LoggerFactory.getLogger(OsmsApplicationTests.class);

	@Test
	public void contextLoads() {

//		try {
//			// Heartbeat Test like ping...
//			CloseableHttpClient client = HttpClientBuilder.create().build();
//			HttpGet request = new HttpGet("https://www.naver.com");
//			HttpResponse response = client.execute(request);
//
//			BufferedReader bufReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//			StringBuilder builder = new StringBuilder();
//
//			String line;
//
//			while ((line = bufReader.readLine()) != null) {
//				builder.append(line);
//				builder.append(System.lineSeparator());
//			}
//
//			System.out.println(builder);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		OperatingSystem os = si.getOperatingSystem();

		printCpu(hal.getProcessor());

		Properties props = PropertiesUtil.loadProperties("conf/oshi.json.properties");
		// Pretty JSON

		System.out.println(si.toPrettyJSON(props));


//		try {
//
//			ObjectMapper mapper = new ObjectMapper();
//			SolutionSystem result = mapper.readValue(si.toPrettyJSON(props), SolutionSystem.class);
//
//			System.out.println(result.getHardware().getProcessor().getProcessorID());
//
//			List<FileStore> list =  result.getOperatingSystem().getFileSystem().getFileStores();
//
//			for (FileStore key : list){
//				System.out.println("name :"+key.getName());
//				//System.out.println("volume :"+key.getVolume());
//				System.out.println("usableSpace : "+ SizeUnit.GB.to2(key.getUsableSpace())+"GB");
//				System.out.println("totalSpace : "+ SizeUnit.GB.to2(key.getTotalSpace())+"GB");
//			}
//
//
//
//		} catch (IOException e) {
//			e.printStackTrace();
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
		System.out.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
		System.out.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
		double[] loadAverage = processor.getSystemLoadAverage(3);
		System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
				+ (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
				+ (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
		// per core CPU
//		StringBuilder procCpu = new StringBuilder("CPU load per processor:");
//		double[] load = processor.getProcessorCpuLoadBetweenTicks();
//		double sum = 0;
//		for (double avg : load) {
//			sum += avg;
//		}
//		procCpu.append(String.format(" %.1f%%", sum * 100));
//		System.out.println(procCpu.toString());
		// per core CPU
		StringBuilder procCpu = new StringBuilder("CPU load per processor:");
		double[] load = processor.getProcessorCpuLoadBetweenTicks();
		for (double avg : load) {
			procCpu.append(String.format(" %.1f%%", avg * 100));
		}
		//System.out.println(procCpu.toString());

		logger.info(procCpu.toString());

		//logger.info("user:{}, nice:{}, sys:{}, idle:{}, iowait:{}, irq:{}, softirq:{}, steal:{}", user,nice,sys,idle,iowait,irq,softirq,steal);
		System.out.println("user: "+user);

		System.out.println(((double)(totalCpu - (idle+iowait))/(double)totalCpu)*100);
	}

}
