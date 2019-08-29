package com.management.mng;


import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.com.Const;
import com.management.com.MasterSeq;
import com.management.com.SeqRepository;

@Service
public class LogService {

	private LogRepository logRepository;
	private InfoRepository infoRepository;
	private SeqRepository seqRepository;
	
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	public Page<CmmLog> cmmInfoVW(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, 10);
		
		return logRepository.findAll(pageable);
	}
	
	public String makeUserSeq() {
		Optional<MasterSeq> infoSeq = seqRepository.findById(Const.CMM_INFO_SEQ);
		int cmmSeq = Integer.parseInt(infoSeq.get().getSeqCnt());
		String seq= Const.CUSTOMER_TYPE + String.format("%09d",cmmSeq);
		
		return seq;
	}
}
