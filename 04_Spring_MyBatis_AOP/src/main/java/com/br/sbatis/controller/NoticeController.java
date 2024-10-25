package com.br.sbatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.sbatis.dto.NoticeDto;
import com.br.sbatis.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;

	@GetMapping("/list.do") // /notice/list.do
	public void noticeList(Model model) {
		
		List<NoticeDto> list = noticeService.selectNoticeList();
		
		log.debug("list: {}", list);
		model.addAttribute("list", list);
		
		//return "notice/list";
	}
	
	@GetMapping("/detail.do") // /notice/detail.do
	public void noticeDetail(int no, Model model) {
		
		NoticeDto n = noticeService.selectNoticeByNo(no);
		
		model.addAttribute("n", n);
		
	}
	
	@GetMapping("/enroll.do") // /notice/enroll.do
	public void noticeEnroll() {}
	
	@PostMapping("/insert.do")
	public String noticeInsert(NoticeDto n) {
		
		int result = noticeService.insertNotice(n);
		
		if(result > 0) { // 성공 => 리스트 페이지
			return "redirect:/notice/list.do";
		}else { // 실패 => 메인페이지
			return "redirect:/";
		}
	}
	
	@GetMapping("/modify.do") // /notice/modify.do
	public void noticeModify(int no, Model model) {
		model.addAttribute("n", noticeService.selectNoticeByNo(no));
	}
	
	@PostMapping("/update.do") // /notice/update.do
	public String noticeUpdate(NoticeDto n) {
		
		int result = noticeService.updateNotice(n);
		
		if(result > 0) { // 성공 => 상세페이지
			return "redirect:/notice/detail.do?no=" + n.getNo();
		}else { // 실패 => 목록페이지
			return "redirect:/notice/list.do";
		}
		
	}
	
	@PostMapping("/delete.do")
	public String noticeDelete(String[] deleteNo) {
		// delete from notice where no in (xx, xx, xx) 쿼리가 실행될 수 있도록
		// service 메소드 수정, dao 메소드 만들기, mapper에 쿼리 작성
		int result = noticeService.deleteNotice(deleteNo);
		// 성공했을경우 => 목록페이지
		// 실패했을경우 => 메인페이지
		if(result == deleteNo.length) {
			return "redirect:/notice/list.do";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/txtest.do")
	public String transactionTest() {
		
		noticeService.transactionTest();
		
		return "redirect:/";
	}
	
	
}
