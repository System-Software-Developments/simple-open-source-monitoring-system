package com.osms.monitoring;

import com.osms.monitoring.process.BaseValidator;
import com.osms.monitoring.util.OSValidator;
import com.osms.monitoring.util.ShellCommander;
import com.osms.monitoring.util.SystemInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsmsApplication.class, args);
		BaseValidator.start();

	}
}
