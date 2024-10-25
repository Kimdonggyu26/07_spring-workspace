package com.br.file.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.br.file.dto.AttachDto;
import com.br.file.dto.BoardDto;
import com.br.file.service.BoardService;
import com.br.file.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	private final FileUtil fileUtil;

	@PostMapping("/insert1.do")
	public String insertOneFileBoard(BoardDto board, MultipartFile uploadFile) {
		
		// 라이브러리 추가 후 빈 등록을 해야만 값이 잘 담김**
		log.debug("board: {}", board);
		log.debug("attach: {}", uploadFile);
		
		AttachDto attach = null;
		if(uploadFile != null && !uploadFile.isEmpty()) { // 첨부파일이 존재할 경우 => 업로드
			
			// 전달된 파일 업로드 처리
			
			// FileUtil 클래스의 fileupload 호출 (uploadFile 넘기면서)
			Map<String, String> map = fileUtil.fileupload(uploadFile);
			
			// 4) db에 기록할 정보를 자바객체(AttachDto)에 세팅
			attach = AttachDto.builder()
							  .filePath(map.get("filePath"))
							  .originalName(map.get("originalName"))
							  .filesystemName(map.get("filesystemName"))
							  .build();
		}
		
		int result = boardService.insertOneFileBoard(board, attach);
		
		if(result > 0) {
			log.debug("게시글 등록 성공");
		}else {
			log.debug("게시글 등록 실패");
		}
		
		
		
		
		
		return "redirect:/";
	}
	
	@PostMapping("/insert2.do")
	public String insertManyFileBoard(BoardDto board, List<MultipartFile> uploadFile) {
		
		List<AttachDto> list = new ArrayList<>();
		for( MultipartFile file : uploadFile) {
			
			if(file != null && !file.isEmpty()) {	// 파일이 존재할 경우
				
				Map<String, String> map = fileUtil.fileupload(file);
				
				list.add(AttachDto.builder()
								  .filePath(map.get("filePath"))
								  .originalName(map.get("originalName"))
								  .filesystemName(map.get("filesystemName"))
								  .build());
			}
			
		}
		
		int result = boardService.insertManyFileBoard(board, list);
		
		// log.debug("result: {}", result);
		// log.debug("list size: {}", list.size());
		
		
		if(list.isEmpty() && result == 1 || !list.isEmpty() && result == list.size()) {
			log.debug("다중 첨부파일 게시글 등록 성공");
		}else {
			log.debug("다중 첨부파일 게시글 등록 실패");
		}
		
		
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/ajaxInsert.do")
	public String insertAjaxFileBoard(BoardDto board, MultipartFile uploadFile) {
		
		AttachDto attach = null;
		if(uploadFile != null && !uploadFile.isEmpty()) {
			Map<String, String> map = fileUtil.fileupload(uploadFile);
			attach = AttachDto.builder()
							  .filePath(map.get("filePath"))
							  .originalName(map.get("originalName"))
							  .filesystemName(map.get("filesystemName"))
							  .build();
		}
		
		int result = boardService.insertOneFileBoard(board, attach);
		
		if(result > 0) {
			log.debug("ajax로 첨부파일 게시글 등록 성공");
			return "SUCCESS";
		}else {
			log.debug("ajax 첨부파일 게시글 등록 실패");
			return "FAIL";
		}
		
	}
	
	@ResponseBody
	@GetMapping(value="/atlist.do", produces="application/json")
	public List<AttachDto> selectAttachList() {
		
		return boardService.selectAttachList();
		
		// Jackson라이브러리의 역할로 인해 json String으로 자동 변환됨
	}
	
	
	
	
	
	
	
	
	
}
