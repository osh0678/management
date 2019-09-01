package com.management.mng;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.com.Const;
import com.management.com.MasterSeq;
import com.management.com.SeqRepository;

@Service
public class InfoService {

	private InfoRepository infoRepository;
	private SeqRepository seqRepository;
	public InfoService(InfoRepository infoRepository, SeqRepository seqRepository) {
		this.infoRepository = infoRepository;
		this.seqRepository = seqRepository;
	}
	
	public Page<CmmInfo> cmmInfoVW(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, 10);
		
		return infoRepository.findAll(pageable);
	}
	
	public void insertInfo(CmmInfo cmmInfo) {
		System.out.println("InsertLog : " + cmmInfo.getUserNo());
		infoRepository.save(cmmInfo);
		System.out.println("finish insert");
	} 
	
	/**
	 * 신규 고객키 생성
	 * @return seq 
	 */
	public String makeUserSeq(MasterSeq masterSeq) {
		Optional<MasterSeq> infoSeq = seqRepository.findById(Const.CMM_INFO_SEQ);
		int cmmSeq = infoSeq.get().getSeqCnt();
		String seq= Const.CUSTOMER_TYPE + String.format("%09d",cmmSeq);
		
		masterSeq.setSeqName(Const.CMM_INFO_SEQ);
		masterSeq.setSeqCnt(Const.UPDATE_SEQ + cmmSeq);
		System.out.println(masterSeq.getSeqCnt());
		seqRepository.save(masterSeq);
		
		return seq;
	}
	
}
