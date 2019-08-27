package com.management.mng;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	private LogRepository logRepository;
	
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	public Page<CmmLog> cmmInfoVW(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, 10);
		
		return logRepository.findAll(pageable);
	}
}
