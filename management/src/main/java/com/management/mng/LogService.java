package com.management.mng;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.com.Const;
import com.management.com.MasterSeq;
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
	
	/**
	 * 고객이력페이지 뷰
	 * @param pageable
	 * @return
	 */
	public Page<CmmLog> cmmLogVW(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - Const.PAGE_FIRST_PAGE); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, Const.PAGE_MAX_ELEMENT);
		
		return logRepository.findAll(pageable);
	}
	
	
	/**
	 * 고객이력페이지 검색
	 * @param pageable
	 * @return
	 */
	public Page<CmmLog> searchLog(Pageable pageable, String	keyword){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - Const.PAGE_FIRST_PAGE); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, Const.PAGE_MAX_ELEMENT);
		
		return logRepository.findByUserNameOrLogDtOrRetryCallOrRmkOrExistCash(keyword, keyword, keyword, keyword, keyword, pageable);
//		return logRepository.findAll(pageable);
	}
	
	
	/** 상담이력 생성
	 * @param cmmLog
	 */
	public void insertLog(CmmLog cmmLog) {
		System.out.println("InsertLog : " + cmmLog.getUserNo());
		logRepository.save(cmmLog);
		System.out.println("finish insert");
	}
	
	/**
	 * 신규 상담 이력 키 생성
	 * @return seq 
	 */
	public int makeLogSeq(MasterSeq masterSeq) {
		Optional<MasterSeq> infoSeq = seqRepository.findById(Const.CMM_LOG_SEQ);
		int cmmSeq = infoSeq.get().getSeqCnt();
		
		masterSeq.setSeqName(Const.CMM_LOG_SEQ);
		masterSeq.setSeqCnt(Const.UPDATE_SEQ + cmmSeq);
		System.out.println(masterSeq.getSeqCnt());
		seqRepository.save(masterSeq);
		
		System.out.println("신규 상담 이력 키 증가");
		return cmmSeq;
	}
}
