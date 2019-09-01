package com.management.mng;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.com.SeqRepository;

//import com.management.com.SeqRepository;

@Service
public class LogService {

	private LogRepository logRepository;
//	private InfoRepository infoRepository;
	private SeqRepository seqRepository;
	
	public LogService(LogRepository logRepository, SeqRepository seqRepository) {
		this.logRepository = logRepository;
		this.seqRepository = seqRepository;
	}
	
	public Page<CmmLog> cmmInfoVW(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, 10);
		
		return logRepository.findAll(pageable);
	}
	
	public void insertLog(CmmLog cmmLog) {
		System.out.println("InsertLog : " + cmmLog.getUserNo());
		logRepository.save(cmmLog);
		System.out.println("finish insert");
	}
}
