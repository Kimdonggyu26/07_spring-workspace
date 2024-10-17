package com.br.mvc.service;

import java.util.List;

import com.br.mvc.dto.NoticeDto;

public interface NoticeService {

	// 목록조회
	List<NoticeDto> selectNoticeList();
	// 상세조회
	NoticeDto selectNoticeByNo(int noticeNo);
	// 수정
	int updateNotice(NoticeDto n);
}
