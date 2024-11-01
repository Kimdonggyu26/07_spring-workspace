package com.br.spring.service;

import java.util.List;

import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

public interface NoticeService {

	// 공지사항 목록 조회 (페이징)
	int selectNoticeListCount();
	List<NoticeDto> selectNoticeList(PageInfoDto pi);
	
	// 공지사항 등록
	int insertNotice(NoticeDto n);
	
	// 공지사항 상세 조회
	NoticeDto selectNotice(int noticeNo);
}
