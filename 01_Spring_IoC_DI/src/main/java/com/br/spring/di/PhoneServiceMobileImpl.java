package com.br.spring.di;

public class PhoneServiceMobileImpl implements PhoneService {

	@Override
	public void selectList() {
		System.out.println("모바일용 폰 전체 조회 서비스 실행");
	}

	@Override
	public void insertPhone() {
		System.out.println("모바일용 폰 등록 서비스 실행");
	}

}
