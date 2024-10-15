package com.br.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PhoneController1 {

	/*
	 * * Container에 등록된 Bean을 가져오는 방법
	 * 
	 * > 가장 근본적인 방법 : 빈들이 담겨있는 Spring 컨테이너 객체로부터 getBean으로 가져오기
	 * > DI 관련 어노테이션을 이용하는 방법
	 * 	- @Inject	  : 등록된 Bean들 중 타입(class)이 일치하는 Bean 객체를 주입
	 * 	- @Resource   : 등록된 Bean들 중 이름(id)이 일치하는 Bean 객체를 주입
	 * 	- @Autowired  : 등록된 Bean들 중 타입(class)이 일치하는 Bean 객체를 주입 (주로 사용)
	 */
	
	// * @Autowired
	// 1) 필드를 이용한 방법
	/*
	@Autowired
	private Phone a; // 기본적으로 타입을 가지고 빈을 탐색함
	
	
	@RequestMapping("/test1") // localhost:8888/spring/test1
	public void diTest1() {
		System.out.println(a);
	}
	*/
	
	/*
	 * 2) 생성자를 이용해서 주입받기
	 * 	  필드를 초기화하는 매개변수 생성자 작성후
	 * 	  해당 생성자 상단에 @Autowired 어노테이션 기술
	 */
	/*
	private Phone a;
	
	@Autowired
	public PhoneController1(Phone a) { // 매개변수에 생성된 객체 주입
		this.a = a;
	}
	
	@RequestMapping("/test1") // localhost:8888/spring/test1
	public void diTest1() {
		System.out.println(a);
	}
	*/
	
	/*
	 * 3) 메소드를 이용하여 주입
	 * 	  객체 전달받아서 필드에 대입을 시켜주는 용도의 메소드 작성하고
	 * 	  상단에 @Autowired 기술 (생략불가)
	 * 	  통상 setter메소드 처럼 작성함
	 */
	/*
	private Phone a;
	
	@Autowired
	public void setA(Phone a) {
		this.a = a;
	}
	
	@RequestMapping("/test1")
	public void diTest1() {
		System.out.println(a);
	}
	*/
	
	/*
	 * * 동일한 타입의 Bean이 여러개 등록되어있을 경우
	 *   @Autowired는 기본적으로 타입으로 빈을 탐색함
	 *   동일한 타입의 빈이 여러개일 경우 오류를 유발시킴
	 *   
	 *   단, 해당 필드명을 내가 주입받고자 하는 빈의 이름으로 작성시
	 *   이름으로 빈을 찾아서 주입해줌
	 *   
	 *   즉, 탐색 순서가 타입(class) => 이름(id)
	 */
	
	// 1) 필드
	
	/*
	@Autowired
	private Phone phone1;
	@Autowired
	private Phone phone2;
	
	@RequestMapping("/test1")
	public void diTest1() {
		System.out.println(phone1);
		System.out.println(phone2);
	}
	*/
	
	// 2) 생성자
	/*
	private Phone a;
	private Phone b;
	
	public PhoneController1(Phone phone1, Phone phone2) {
		this.a = phone1;
		this.b = phone2;
	}
	
	@RequestMapping("/test1")
	public void diTest1() {
		System.out.println(a);
		System.out.println(b);
	}
	*/
	
	// 3) 메소드
	private Phone a;
	private Phone b;
	
	@Autowired
	public void setA(Phone phone1) {
		this.a = phone1;
	}
	
	@Autowired
	public void setB(Phone phone2) {
		this.b = phone2;
	}
	
	@RequestMapping("/test1")
	public void diTest1() {
		System.out.println(a);
		System.out.println(b);
	}
}
