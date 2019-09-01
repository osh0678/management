package com.management.mng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.com.Const;
import com.management.com.MasterSeq;
import com.management.com.SeqRepository;
import com.management.mng.CmmLog;

@Controller
@RequestMapping("/mng")
public class MngController {
	private InfoService infoService;
	private LogService logService;
	@Autowired
	private InfoRepository infoRepository;
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired 
	private SeqRepository seqRepository;
	
	public MngController(InfoService infoService, LogService logService) {
		this.infoService = infoService;
		this.logService = logService;
	}
	
	@GetMapping("/cmmlog.vw")
	public String cmmlogVw(@PageableDefault Pageable pageable, Model model){
		Page<CmmLog> logPage = logService.cmmInfoVW(pageable);
		model.addAttribute("list", logPage);
		
		return "mng/cmmlog";
	}
	
	/**
	 * 상담 이력 추가
	 * @param cmmLog
	 * @param masterSeq
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/addLog.do", method = RequestMethod.GET)
	public String addLogDo(CmmLog cmmLog,MasterSeq masterSeq, CmmInfo cmmInfo,RedirectAttributes redirectAttributes) {
		Optional<MasterSeq> logSeq = seqRepository.findById(Const.CMM_LOG_SEQ);
		Optional<MasterSeq> infoSeq = seqRepository.findById(Const.CMM_INFO_SEQ);
		
		int cmmSeq = infoSeq.get().getSeqCnt();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date todayDate = new Date();
		String today = dateFormat.format(todayDate);
		
		System.out.println("오늘 날짜 : " + today);
		System.out.println("로그 시퀀스 : " + logSeq.get().getSeqCnt());
		System.out.println("인포 시퀀스 : " + infoSeq.get().getSeqCnt());
		
		System.out.println("=============VaildCheck=============");
		/* 기존 고객 검증 로직 */
		Optional<CmmInfo> infoCheck = infoRepository.findByUserNameAndPhoneId(cmmLog.getUserName(), cmmLog.getPhoneId());
		System.out.println("data checking !");
		if(infoCheck.isPresent()) {
			System.out.println("infoCheck is not null");
			if(cmmLog.getUserName().equals(infoCheck.get().getUserName()) && cmmLog.getPhoneId().equals(infoCheck.get().getPhoneId())) {
				cmmLog.setUserNo(infoCheck.get().getUserNo());
			}
		}else {
			//기존  고객 정보에 없을 시, 신규 고객키 생성
			cmmLog.setUserNo(infoService.makeUserSeq(masterSeq));
			
			cmmInfo.setUserNo(cmmLog.getUserNo());
			cmmInfo.setUserName(cmmLog.getUserName());
			cmmInfo.setPhoneId(cmmLog.getPhoneId());
			cmmInfo.setRegUser("webadmin");
			cmmInfo.setRegDt(today);
			
			infoRepository.save(cmmInfo);
		}
		System.out.println("setting Data");
		cmmLog.setLogDt(today);
		cmmLog.setLogNo(logService.makeLogSeq(masterSeq));
		
		// log로 	추후 업데이트
		System.out.println("getUserNo : " + cmmLog.getUserNo());
		System.out.println("getLogNo : " + cmmLog.getLogNo());
		System.out.println("getUserName : " + cmmLog.getUserName());
		System.out.println("getRetryCall : " + cmmLog.getRetryCall());
		System.out.println("getRmk : " + cmmLog.getRmk());
		System.out.println("getExistCash : " + cmmLog.getExistCash());
		System.out.println("setLogCount : " + logSeq.get().getSeqCnt());
		System.out.println("setInfoCount : " + infoSeq.get().getSeqCnt());
		System.out.println(Const.CUSTOMER_TYPE + String.format("%09d",cmmSeq));
		
		System.out.println(cmmLog.toString());
		
		logService.insertLog(cmmLog); //insert 기능
		
		return "redirect:/mng/cmmlog.vw";
	}
	
	@GetMapping("/cmmInfo.vw")
	public String cmmInfoVw(@PageableDefault Pageable pageable, Model model) {
		Page<CmmInfo> infoPage = infoService.cmmInfoVW(pageable);
		model.addAttribute("list", infoPage);
		return "mng/cmmInfo";
	}
	
}
