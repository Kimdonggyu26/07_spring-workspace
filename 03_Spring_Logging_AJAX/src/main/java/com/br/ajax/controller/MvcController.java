package com.br.ajax.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {

	private Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping(value= {"/", "/main.do"})
	public String mainPage() {
		
		// 출력문은 성능저하를 야기시킴 => 로그 출력 권장
		// System.out.println("MvcController의 mainPage 메소드 작동됨");
		
		/*
		logger.trace("trace msg");
		logger.debug("debug msg");
		logger.info("info msg");
		logger.warn("warn msg");
		logger.error("error msg");
		*/
		
		
		
		return "main";
	}
	
	@GetMapping("/member/manage1.do")
	public void memberManagePage1() {}
	
	@GetMapping("/member/manage2.do")
	public void memberManagePage2() {}
	
	
	
	
	
	
	
	
	
	
}
