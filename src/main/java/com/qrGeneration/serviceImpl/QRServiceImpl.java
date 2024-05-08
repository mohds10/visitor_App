package com.qrGeneration.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrGeneration.encyptDecrypt.AesEncryptionServiceImpl;
import com.qrGeneration.entity.QR;
import com.qrGeneration.repository.QRRepository;
import com.qrGeneration.service.QRService;

import jakarta.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QRServiceImpl implements QRService {

	@Autowired
	private QRRepository qrRepository;

	@Override
	public String getLatestQR() {
		QR latestQR = qrRepository.findTopByOrderByCreationTimeDesc();
		return (latestQR != null) ? latestQR.getUrl() : null;
	}

	@Override
	@Transactional
	public synchronized void updateDynamicQR() {
		String dynamicQR = generateDynamicQR();
		deleteDynamicQR();
		saveDynamicQR(dynamicQR);
	}

	private String generateDynamicQR() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String currentDateTime = dateFormat.format(new Date());
		return "https://hanzapps.oc.holcim.net/vima/" + currentDateTime;
	}

	private void saveDynamicQR(String dynamicQR) {
		QR qr = new QR();
		qr.setUrl(dynamicQR);
		qr.setCreationTime(new Date());
		qrRepository.save(qr);
	}

	public void deleteDynamicQR() {
		qrRepository.deleteByCreationTimeBefore();
	}

	@Override
	public boolean isValidURL(String url) {
		// String actualUrl = aesEncryptionServiceImpl.decrypt(url);

		if (qrRepository.existsByUrl(url)) {
			return true;
		}
		return false;
	}

}
