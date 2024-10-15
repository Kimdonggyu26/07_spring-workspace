package com.br.spring.ioc.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration
 * - Spring Bean Configuration File의 역할을 대신해줄 Java클래스에 부여하는 어노테이션
 */
@Configuration
public class SpringBeanConfig {

	/*
	 * @Bean
	 * - Java에서 Bean을 등록할 때 사용하는 어노테이션
	 * - @Configuration 클래스 내에 메소드 하나당 빈 하나를 생성
	 * 	메소드명 == 빈 이름(id)
	 * 	반환형   == 빈 타입(class)
	 */
	
	@Bean
	public Music music1() {
		Music m = new Music();
		
		m.setTitle("Love wins all");
		m.setGenre("발라드");
		
		return m;
	}
	
	@Bean
	public Singer singer1() {
		Singer s = new Singer();
		
		s.setName("아이유");
		s.setMusic(music1());
		
		return s;
	}
	
	// * constructor Injection 예시
	@Bean(name="music2") // name 속성으로 별도로 빈 이름 지정 가능
	public Music abcd() {
		return new Music("EASY", "K-POP");
	}
	
	@Bean(name="singer2")
	public Singer defg() {
		return new Singer("르세라핌", abcd());
	}
	
	
	
}
