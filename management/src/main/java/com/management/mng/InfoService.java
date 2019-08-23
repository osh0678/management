package com.management.mng;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

	private InfoRepository infoRepository;
	
	public InfoService(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}
	
	public Page<CmmInfo> cmmInfoVW(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, 10);
		
		return infoRepository.findAll(pageable);
	}
}
