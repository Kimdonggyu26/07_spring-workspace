package com.br.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhoneController2 {
	
	/*
	 * * 직접 Service 객체 생성할 경우 (스프링 사용 전)
	 *		private PhoneService pService 
	 *	 // = new PhoneServiceWebImpl();	-- 웹 개발 당시
	 *	 // = new PhoneServiceMobileImpl(); -- 모바일 개발 당시
	 *	
	 * > 개발자가 직접 new 객체 생성시 "결합도가 높음"의 문제 발생
	 */
	
	@Autowired
	private PhoneService pService;
	
	
	
	
	
	@RequestMapping("/list.ph")
	public void selectList() {
		pService.selectList();
	}
	@RequestMapping("/insert.ph")
	public void insertPhone() {
		pService.insertPhone();
	}

}
