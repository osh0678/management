package com.management.mng;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/mng")
public class MngController {
	private InfoService infoService;
	
	public MngController(InfoService infoService) {
		this.infoService = infoService;
	}
	
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
	public String cmmInfoVw(@PageableDefault Pageable pageable, Model model) {
		Page<CmmInfo> infoPage = infoService.cmmInfoVW(pageable);
		model.addAttribute("list", infoPage);
		return "mng/cmmInfo";
	}
}
