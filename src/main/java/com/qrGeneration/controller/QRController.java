package com.qrGeneration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qrGeneration.service.QRService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/qrcode")
public class QRController {

	@Autowired
	private QRService qrService;

	@GetMapping("/latest")
	public ResponseEntity<String> getLatestQR() {
		try {
			String latestQR = qrService.getLatestQR();
			return ResponseEntity.ok(latestQR);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to retrieve latest QR: " + e.getMessage());
		}
	}

	@GetMapping("/check-url")
	public ResponseEntity<String> validateQR(@RequestParam String url) {
		try {
			if (qrService.isValidURL(url)) {
				return ResponseEntity.ok("Success: URL is valid");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed: URL is not valid");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error validating URL: " + e.getMessage());
		}
	}

}
