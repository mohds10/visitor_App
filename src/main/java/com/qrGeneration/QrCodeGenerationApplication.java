package com.qrGeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QrCodeGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrCodeGenerationApplication.class, args);
	}
}
