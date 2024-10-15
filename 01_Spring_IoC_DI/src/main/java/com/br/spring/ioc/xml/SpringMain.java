package com.br.spring.ioc.xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
	
	public static void main(String[] args) {
		
		// * Spring 사용 전 (개발자가 필요한 객체를 직접 생성해서 사용)
		// Calculator calc = new Calculator();
		
		// * Spring 사용 후 (프레임워크가 미리 생성해둔 빈을 가져다 씀)
		// > 컨테이너에 저장된 빈을 가져다 쓸때 사용되는 클래스 == 컨테이너 객체
		// - GenericXmlApplicationContext 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:xml/appCtx.xml");
		// ctx == 컨테이너 == 빈(객체)들이 담겨있음
		//Calculator calc = (Calculator)ctx.getBean("calculator");
		Calculator calc = ctx.getBean("calculator", Calculator.class);
		
		calc.plus(2, 3);
		calc.minus(4, 2);
		
		Student stu1 = ctx.getBean("student1", Student.class);
		Student stu2 = ctx.getBean("student2", Student.class);
		Student stu3 = ctx.getBean("student3", Student.class);
		
		System.out.println(stu1);
		System.out.println(stu2);
		System.out.println(stu3);
		
		System.out.println("============================================");
		
		/*
		 * * Spring 사용 전
		 * 	 Service에서 Dao측 메소드 호출을 위해
		 *   직접 Dao 객체를 new 생성 해서 호출함
		 *   
		 *   => 매 요청마다 새로운 dao 객체 생성되고 소멸됨(매번 주소값이 다름)
		 *   	=> 메모리 사용이 빈번함
		 *   
		 *   ==> 자주 사용될 객체는 메모리상에 한번만 생성해두고 재사용하는걸 권장 (싱글톤 패턴)
		 */
		
		/*
		StudentDao dao1 = new StudentDao();
		dao1.selectStudentList();
		System.out.println(dao1);
		
		StudentDao dao2 = new StudentDao();
		dao2.insertStudent();
		System.out.println(dao2);
		*/
		
		/*
		 * * Spring 사용 후
		 * 	 Dao 클래스를 스프링이 관리하도록 빈으로 등록하고
		 *   필요할때마다 불러서 쓰면 됨
		 */
		StudentDao dao1 = ctx.getBean("studentDao", StudentDao.class);
		dao1.selectStudentList();
		System.out.println(dao1);
		
		StudentDao dao2 = ctx.getBean("studentDao", StudentDao.class);
		dao2.insertStudent();
		System.out.println(dao2);
		
		
		
	}

}
