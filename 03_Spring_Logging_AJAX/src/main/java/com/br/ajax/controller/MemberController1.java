package com.br.ajax.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.ajax.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member1")
@RequiredArgsConstructor
@Controller
public class MemberController1 {
	
	private final MemberService memService;
	private Logger logger = LoggerFactory.getLogger(MemberController1.class);
	

	// 기존에 HttpServletResponse 방식 이용하는 방법
	/* 
	@GetMapping("/detail1.do")
	public void memberDetail(String id, String pwd, HttpServletResponse response) throws IOException {
		
		logger.debug("request id: {}, pwd: {}", id, pwd);
		
		String result = memService.selectMemberByIdPwd(id, pwd);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}
	*/
	
	/*
	 * * @ResponseBody
	 * - 비동기식으로 데이터 응답시 필요한 어노테이션
	 * - 해당 어노테이션이 붙은 메소드에서의 반환 값은
	 * 	 응답뷰가 아닌 어떤 data(text, json, xml 등)라는 걸 의미
	 */
	
	@ResponseBody
	@GetMapping(value="/detail1.do", produces="text/html; charset=utf-8")
	public String memberDetail1(String id, String pwd) {
		
		logger.debug("request id: {}, pwd: {}", id, pwd);
		
		String result = memService.selectMemberByIdPwd(id, pwd); // 홍길동
		
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="/detail2.do", produces="text/html; charset=utf-8")
	public String memberDetail2(String userId, String userPwd) {
		// String result = memService.selectMemberByIdPwd(userId, userPwd);
		return memService.selectMemberByIdPwd(userId, userPwd);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
