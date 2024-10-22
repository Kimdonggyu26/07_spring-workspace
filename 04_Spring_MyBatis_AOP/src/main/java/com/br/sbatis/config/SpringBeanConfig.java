package com.br.sbatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.br.sbatis.aop.AfterLoggingAdvice;
import com.br.sbatis.aop.BeforeLoggingAdvice;

@EnableAspectJAutoProxy // Aspect 어노테이션 붙어있는애들 활성화 시키기
@Configuration
public class SpringBeanConfig {
	
	@Bean
	public BeforeLoggingAdvice beforeLoggingAdvice() {
		return new BeforeLoggingAdvice();
	}
	
	@Bean
	public AfterLoggingAdvice afterLoggingAdvice() {
		return new AfterLoggingAdvice();
	}
}
