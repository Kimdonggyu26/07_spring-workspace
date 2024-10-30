package com.br.spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.spring.dto.MemberDto;



/*
 * * JUnit
 * - Java 개발시 가장 많이 사용되는 테스팅 기반 프레임워크
 * - 독립된 단위 테스트를 지원함
 */

@RunWith(SpringJUnit4ClassRunner.class)	// JUnit4를 이용하겠다는걸 의미
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
								, "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드의 이름순으로 테스트를 수행하겠다는걸 의미
public class MemberDaoUnitTest_JUnit4 {
	
	@Autowired
	private MemberDao memberDao;
	
	
	
	/*
	 * 테스트 케이스 작성 패턴
	 * 
	 * given (준비) : 어떤 데이터를 가지고
	 * when  (실행) : 어떤 메소드 실행시
	 * then  (검증) : 어떤 결과가 나와야 되는지
	 * 			ㄴ assertEquals	 (예상값, 실행값) : 실행값이 예상값과 일치하는지 확인해주는 메소드
	 * 			ㄴ assertNotNull (실행값)		  : 실행값이 null이 아닌지 확인해주는 메소드
	 * 			ㄴ assertTrue	 (조건)			  : 해당 조건이 참인지를 확인해주는 메소드
	 * 
	 */
	
	@Test
	public void test01_로그인테스트() {
		// given
		MemberDto m = MemberDto.builder()
							   .userId("admin01")
							   .userPwd("1234")
							   .build();
		
		// when
		MemberDto result = memberDao.selectMember(m);
		
		// then
		assertNotNull(result);
	}
	
	@Ignore // 이거 빼고 테스트 진행
	@Test
	public void test02_회원가입테스트() {
		
		// given
		MemberDto m = MemberDto.builder()
							   .userId("test01")
							   .userPwd("1234")
							   .userName("테스트")
							   .email("test@br.or.kr")
							   .phone("010-1111-2222")
							   .address("서울시 강서구 마곡동")
							   .build();
							 
		// when
		int result = memberDao.insertMember(m);
		
		// then
		assertEquals(1, result);
		
	}
	
	@Test
	public void test03_일치하는아이디수조회테스트() {
		
		// given
		String checkId = "user01";
		
		// when
		int result = memberDao.selectUserIdCount(checkId);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	public void test04_회원정보변경테스트() {
		
		// given
		MemberDto m = MemberDto.builder()
							   .userId("user01")
							   .userName("변경테스트")
							   .email("updatetest@br.com")
							   .phone("010-0000-0000")
							   .address("서울시 강남구")
							   .gender("F")
							   .build();
		
		// when
		int result = memberDao.updateMember(m);
		
		// then
		assertEquals(1, result);
							   
	}
	
	@Test
	public void test05_회원탈퇴테스트() {
		
		// given
		String userId = "user02";
		
		// when
		int result = memberDao.deleteMember(userId);
		
		// then
		assertEquals(1, result);
		
	}

}
