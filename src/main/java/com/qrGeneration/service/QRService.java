package com.qrGeneration.service;

public interface QRService {

	void deleteDynamicQR();

	String getLatestQR();

	void updateDynamicQR();

	boolean isValidURL(String url); // New method for URL validation

}
