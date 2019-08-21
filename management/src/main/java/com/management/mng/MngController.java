package com.management.mng;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mng")
public class MngController {

	private List<CmmLog> cmmlog = new ArrayList<CmmLog>();
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@GetMapping("/cmmlog.vw")
	public String cmmlogVw(Model model){
		model.addAttribute("list", logRepository.findAll());
		
		return "mng/cmmlog";
	}
	
	@GetMapping("/cmmInfo.vw")
	public String cmmInfoVw(Model model){
		model.addAttribute("list", infoRepository.findAll());
		
		return "mng/cmmInfo";
		
	}
}
