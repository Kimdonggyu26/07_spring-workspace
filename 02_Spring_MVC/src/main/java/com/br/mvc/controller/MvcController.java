package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
	
	/*
	 * * 스프링 사용 전
	 * @WebServlet("/ 또는 /main.do")
	 * 
	 * public void doGet(HttpServletRequest rquest, HttpServletResponse response) throws Exception {
	 * 		request.getReuqestDispatcher("/WEB-INF/views/main.jsp")
	 */

	@RequestMapping(value={"/", "/main.do"}, method=RequestMethod.GET) // 기본값은 GET
	public String welcomePage() { 
		System.out.println("MvcController클래스의 welcomePage 메소드 작동됨");
		return "main";
		
		/*
		 * 반환값은 사실상 DispatcherServlet의 ViewResolver에 전달됨
		 * 이때 반환값 앞(prefix)에 "/WEB-INF/views/"가 붙고
		 * 			   뒤(suffix)에 ".jsp"가 붙으면서 응답뷰 경로가 완성됨
		 * 
		 * 기본적으로 포워딩으로 인식돼서 포워딩처리까지 자동으로 진행됨
		 * 만일 redirect 하고싶다면 반환 문자열의 앞에 redirect: 붙여주면 됨
		 */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
