package com.management.mng;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<CmmInfo, String> {
	Page<CmmInfo> findAll(Pageable request);
	Page<CmmInfo> findByUserNameOrPhoneIdOrWorkNameOrLocation(String userName, String phoneId, String workName, String location, Pageable page);

	Optional<CmmInfo> findByUserNameAndPhoneId(String userName, String phoneId);
	Optional<CmmInfo> findByUserNo(String userNo);
}
