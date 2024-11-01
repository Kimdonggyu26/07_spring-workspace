package com.br.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.MemberDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.service.NoticeService;
import com.br.spring.util.FileUtil;
import com.br.spring.util.PagingUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;
	private final PagingUtil pagingUtil;
	private final FileUtil fileUtil;	
	
	@GetMapping("/list.do")
	public void list(@RequestParam(value="page", defaultValue="1") int currentPage
			, Model model) {
		
		int listCount = noticeService.selectNoticeListCount();
		
		PageInfoDto pi = pagingUtil.getPageInfoDto(listCount, currentPage, 5, 5);
		List<NoticeDto> list = noticeService.selectNoticeList(pi);
		
		log.debug("pi: {}", pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/regist.do")
	public void registPage() {}
	
	@PostMapping("/insert.do")
	public String regist(NoticeDto notice
					 , List<MultipartFile> uploadFiles
					 , HttpSession session
					 , RedirectAttributes rdAttributes) {
		
		// board테이블에 insert할 데이터
	    notice.setNoticeWriter( String.valueOf( ((MemberDto)session.getAttribute("loginUser")).getUserNo() ) );

		// 첨부파일 업로드 후에
		// attachment테이블에 insert할 데이터
		List<AttachDto> attachList = new ArrayList<>();
		for(MultipartFile file : uploadFiles) {
			if(file != null && !file.isEmpty()) {
				Map<String, String> map = fileUtil.fileupload(file, "notice");
				
				attachList.add(AttachDto.builder()
										.filePath(map.get("filePath"))
										.originalName(map.get("originalName"))
										.filesystemName(map.get("filesystemName"))
										.refType("N")
										.build());
			}
		}
		
		notice.setAttachList(attachList);
		
		int result = noticeService.insertNotice(notice);
		
		if(attachList.isEmpty() && result == 1
				|| !attachList.isEmpty() && result == attachList.size()) {
			rdAttributes.addFlashAttribute("alertMsg", "게시글 등록 성공");
		}else {
			rdAttributes.addFlashAttribute("alertMsg", "게시글 등록 실패");
		}	
		
		return "redirect:/notice/list.do";
	}
	
	@GetMapping("/detail.do")
	public void detail(int no, Model model) {
		
		NoticeDto n = noticeService.selectNotice(no);
		
		model.addAttribute("n", n);
	}
	
}