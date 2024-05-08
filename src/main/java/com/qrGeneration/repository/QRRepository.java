package com.qrGeneration.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.qrGeneration.entity.QR;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

public interface QRRepository extends JpaRepository<QR, Long> {
	public QR findTopByOrderByCreationTimeDesc();

	// Delete old records before the cutoff date
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE qr", nativeQuery = true)
	void deleteByCreationTimeBefore();

	boolean existsByCreationTimeBefore(Date date);

	boolean existsByUrl(String url);

}
