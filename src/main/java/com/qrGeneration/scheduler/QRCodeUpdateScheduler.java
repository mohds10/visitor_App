package com.qrGeneration.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qrGeneration.service.QRService;

@Component
public class QRCodeUpdateScheduler {
	@Autowired
	private QRService qrService;

	// @Scheduled(fixedRate = 3600000) // Run every 1 hour (in milliseconds)
	@Scheduled(cron = "0 * * * * *") // Executes every minute
	public synchronized void updateDynamicQR() {
		qrService.updateDynamicQR();
	}
}
