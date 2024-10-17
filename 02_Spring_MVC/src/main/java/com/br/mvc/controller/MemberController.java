package com.br.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.mvc.dto.MemberDto;

@RequestMapping("/member")
@Controller
public class MemberController {

	// ======= 요청시 전달되는 값 (parameter) 처리하는 방법 =======
	/*
	 * 1. HttpServletRequest 방법
	 * 2. @RequestParam 이용하는 방법
	 * 3. 커맨드 객체 이용하는 방법
	 */

	/*
	 * 1. HttpServletRequest 방법
	 */
	@RequestMapping("/detail.do")
	public String memberDetail(HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("조회할 회원번호: " + no);
		
		
		return "main";
	}
	
	//@RequestMapping(value="/enroll1.do", method=RequestMethod.GET) // 405 에러 (요청전송방식이 맞지 않을 경우)
	//@RequestMapping(value="/enroll1.do", method=RequestMethod.POST)
	@PostMapping("/enroll1.do") // Spring 버전업 되면서 @GetMapping, @PostMapping 지원
	public String memberEnroll1(HttpServletRequest request) {
		
		// request.setCharacterEncoding("utf-8"); // => 스프링에서 제공하는 인코딩 필터 등록
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
		System.out.println("주소: " + address);
		
		return "main";
	}
	
	/*
	 * 2. @RequestParam 이용하는 방법
	 * 	  request.getParmaeter()를 자동으로 해주는 어노테이션
	 * 
	 * 	  1) 요청시 전달값을 받기 위한 변수를 매개변수로 작성하기
	 * 	  2) 요청시 전달값의 키값과 담고자하는 변수명이 다를경우
	 * 	  	 또는 어노테이션의 속성을 활용하고자 할 경우 어노테이션 기술
	 */
	
	@GetMapping("/detail2.do")
	public String memberDetail2(int no) {
		System.out.println("조회할 회원 번호 : " + no);
		return "main";
		
	}
	
	@PostMapping("/enroll2.do")
	public String memberEnroll2(/*@RequestParam(value="name")*/ String name // 키값과 매개변수명이 동일한 경우 생략 가능
							  , @RequestParam(value="age", defaultValue="10") int age	// 내부적으로 파싱돼서 담기는데 값이 없을 경우 400 에러
							  , @RequestParam(value="address") String addr) {
	
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
		System.out.println("주소: " + addr);
	
		MemberDto mem = new MemberDto();
		mem.setName(name);
		//mem.setAge(age);
		mem.setAddr(addr);
		
		System.out.println(mem);
		
		return "main";
	}
	
	/*
	 * 3. 커맨드 객체 방법
	 * 	  요청 파라미터들을 각 필드에 담고자 하는 객체를 커맨드 객체라고 함
	 * 	  즉, 전달값들을 특정 dto객체에 바로 담고자 할 경우 사용하면 됨
	 * 	
	 *    1) 메소드의 매개변수로 전달값들을 담고자하는 객체를 작성
	 *    2) 요청파라미터의 키값을 담고자하는 객체의 필드명으로 작성하면 됨
	 *    
	 *    > 내부적으로 실행되는 내용
	 *    	/member/enroll3.do?name=홍길동&age=20&addr=서울 url요청시
	 *      매개변수로 있는 Dto객체를 기본생성자로 생성함
	 *      => 파라미터의 키값가지고 setter메소드를 찾아 실행시켜줌
	 */
	
	@PostMapping("/enroll3.do")
	public String memberEnroll3(MemberDto mem) {
		
		System.out.println(mem);
		
		return "main";
	}

	
	
	
	
	
	
	
	
}
