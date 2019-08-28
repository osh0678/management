package com.management.mng;

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
	
	
	public MngController(InfoService infoService, LogService logService) {
		this.infoService = infoService;
		this.logService = logService;
	}
	
	@GetMapping("/cmmlog.vw")
	public String cmmlogVw(@PageableDefault Pageable pageable, Model model,CmmLog cmmLog){
		Page<CmmLog> logPage = logService.cmmInfoVW(pageable);
		model.addAttribute("list", logPage);
		
		return "mng/cmmlog";
	}
	
	@RequestMapping(value="/addLog.do", method = RequestMethod.GET)
	public String addLogDo(CmmLog cmmLog, RedirectAttributes redirectAttributes) {
		System.out.println("addLog");
		// log로 추후 업데이트
		System.out.println("getUserName : " + cmmLog.getUserName());
		System.out.println("getRetryCall : " + cmmLog.getRetryCall());
		System.out.println("getRmk : " + cmmLog.getRmk());
		System.out.println("getExistCash : " + cmmLog.getExistCash());
		
		//logRepository.save(cmmLog); insert 기능
		
		redirectAttributes.addFlashAttribute("cmmLog", cmmLog);
		return "redirect:/mng/cmmlog.vw";
	}
	
	

	@GetMapping("/cmmInfo.vw")
	public String cmmInfoVw(@PageableDefault Pageable pageable, Model model) {
		Page<CmmInfo> infoPage = infoService.cmmInfoVW(pageable);
		model.addAttribute("list", infoPage);
		return "mng/cmmInfo";
	}
	
}
