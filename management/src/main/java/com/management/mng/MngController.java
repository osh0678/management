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
import org.springframework.web.bind.annotation.RequestParam;
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
	
//	@Autowired
//	private LogRepository logRepository;
	
	@Autowired 
	private SeqRepository seqRepository;
	
	public MngController(InfoService infoService, LogService logService) {
		this.infoService = infoService;
		this.logService = logService;
	}
	
	@GetMapping("/cmmlog.vw")
	public String cmmlogVw(@PageableDefault Pageable pageable, Model model){
		Page<CmmLog> logPage = logService.cmmLogVW(pageable);
		model.addAttribute("list", logPage);
		System.out.println("[cmmlog.vw] pageable : " + pageable);
		System.out.println("총 Element 수 : " + logPage.getTotalElements());
		System.out.println("전체 page 수 : " + logPage.getTotalPages());
		System.out.println("페이지에 표시할 element 수 : " + logPage.getSize());
		System.out.println("현재 페이지 index : " + logPage.getNumber());
		System.out.println("현재 페이지의 element 수 :" + logPage.getNumberOfElements());
		
		return "mng/cmmlog";
	}
	

	/**
	 * 상담이력검색
	 * @param pageable
	 * @param model
	 * @param cmmLog
	 * @return
	 */
	@RequestMapping(value="/cmmlog.do", method = RequestMethod.GET)
	public String searchLogDo(@PageableDefault Pageable pageable,Model model, @RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
		System.out.println("검색어 : " + keyword);
		if(keyword == "" || keyword == null) {
			return "redirect:/mng/cmmlog.vw";
		}
		Page<CmmLog> logPage = logService.searchLog(pageable, keyword);
		model.addAttribute("list", logPage);
		
		System.out.println("[cmmlog.do] pageable : " + pageable);
		System.out.println("총 Element 수 : " + logPage.getTotalElements());
		System.out.println("전체 page 수 : " + logPage.getTotalPages());
		System.out.println("페이지에 표시할 element 수 : " + logPage.getSize());
		System.out.println("현재 페이지 index : " + logPage.getNumber());
		System.out.println("현재 페이지의 element 수 :" + logPage.getNumberOfElements());
		
		redirectAttributes.addAttribute("keyword", keyword);
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
	public String addLogDo(CmmLog cmmLog,MasterSeq masterSeq, CmmInfo cmmInfo) {
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
		
		//고객 이력 추가
		logService.insertLog(cmmLog); 
		
		return "redirect:/mng/cmmlog.vw";
	}

	/**
	 * 고객정보 뷰 페이지
	 * @param pageable
	 * @param model
	 * @return
	 */
	@GetMapping("/cmmInfo.vw")
	public String cmmInfoVw(@PageableDefault Pageable pageable, Model model) {
		Page<CmmInfo> infoPage = infoService.cmmInfoVW(pageable);
		model.addAttribute("list", infoPage);
		return "mng/cmmInfo";
	}
	
	/**
	 * 고객 정보 추가 페이지
	 * @param masterSeq
	 * @param cmmInfo
	 * @return
	 */
	@RequestMapping(value="/addInfo.do", method = RequestMethod.GET)
	public String addInfoDo(MasterSeq masterSeq, CmmInfo cmmInfo) {
		Optional<MasterSeq> infoSeq = seqRepository.findById(Const.CMM_INFO_SEQ);
		
		int cmmSeq = infoSeq.get().getSeqCnt();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date todayDate = new Date();
		String today = dateFormat.format(todayDate);
		
		System.out.println("오늘 날짜 : " + today);
		System.out.println("인포 시퀀스 : " + cmmSeq);
		
		System.out.println("=============VaildCheck=============");
		/* 기존 고객 검증 로직 */
		Optional<CmmInfo> infoCheck = infoRepository.findByUserNameAndPhoneId(cmmInfo.getUserName(), cmmInfo.getPhoneId());
		System.out.println("data checking !");
		if(infoCheck.isPresent()) {
			System.out.println("infoCheck is not null");
			if(cmmInfo.getUserName().equals(infoCheck.get().getUserName()) && cmmInfo.getPhoneId().equals(infoCheck.get().getPhoneId())) {
				System.out.println("이미 등록된 고객입니다.");
			}
		}else {
			//기존  고객 정보에 없을 시, 신규 고객키 생성
			cmmInfo.setUserNo(infoService.makeUserSeq(masterSeq));
			
			cmmInfo.setUserNo(cmmInfo.getUserNo());
			cmmInfo.setUserName(cmmInfo.getUserName());
			cmmInfo.setPhoneId(cmmInfo.getPhoneId());
			cmmInfo.setRegUser("webadmin");
			cmmInfo.setRegDt(today);
			
			infoRepository.save(cmmInfo);
		}
		
		return "redirect:/mng/cmmInfo.vw";
	}
	
}
