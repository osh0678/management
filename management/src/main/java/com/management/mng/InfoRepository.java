package com.management.mng;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<CmmInfo, String> {
	Page<CmmInfo> findAll(Pageable request);
}
