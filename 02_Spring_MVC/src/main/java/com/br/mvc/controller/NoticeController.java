package com.br.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.mvc.dto.NoticeDto;
import com.br.mvc.service.NoticeService;

import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/notice")
@Controller
public class NoticeController {

	// private NoticeService noticeService = new NoticeServiceImpl(); // 직접 생성하면 결합도가 높아지는 문제 발생
	
	// 1) 필드 주입
	/*
	@Autowired
	private NoticeService noticeService;
	*/
	
	// 2) 메소드 주입
	/*
	private NoticeService noticeService;
	
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	*/
	
	// 3) 생성자 주입
	/*
	private NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	*/
	
	private String name;
	private int no;
	private final NoticeService noticeService;	//  앞으로 사용할 DI 방식 
	
	// ====== 포워딩할 응답페이지에 필요한 데이터 담는 방법 ======
	
	@GetMapping("/list.do")
	public String noticeList(Model model) {
		List<NoticeDto> list = noticeService.selectNoticeList(); // 응답페이지에 필요한 데이터
		model.addAttribute("list", list);
		
		return "notice/list";
	}
	
	/*
	 * 2. ModelAndView 객체 이용하기
	 * 	  Model과 View가 합쳐져있는 형태
	 *    Model은 데이터를 담는 객체
	 *    View는 응답뷰에 대한 정보를 담는 객체
	 *    
	 *    ModelAndView 객체에 데이터와 응답뷰에 대해 담고 해당 객체를 반환
	 */
	@GetMapping("/detail.do")
	public ModelAndView noticeDetail(int no, ModelAndView mv) {
		// NoticeDto n = noticeService.selectNoticeByNo(no);	// 응답페이지에 필요한 데이터
		// return "notice/detail";
		
		mv.addObject("notice", noticeService.selectNoticeByNo(no)).setViewName("notice/detail");
		
		return mv;
	}
	
	@GetMapping("/modifyForm.do")
	public String noticeModify(int no, Model model) {
		
		NoticeDto n = noticeService.selectNoticeByNo(no);
		
		model.addAttribute("n", n);
		
		return "notice/modifyForm";
	}
	
	
	
	// ========= redirect시 그때 포워딩 되는 페이지에 필요한 데이터 담는 방법 ==========
	/*
	 * Model 영역은 requestScope이기 때문에 당장 포워딩되는 jsp에서만 사용 가능함
	 * 즉, redirect로 다른 controller가 실행되는 순간 현재 만들어진 Model은 소멸됨
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	@PostMapping("/update.do")
	public String noticeUpdate(NoticeDto n, RedirectAttributes ra) {
		
		System.out.println(n);
		
		int noticeNo = n.getNo();	// url 재요청 할때 사용
		
		int result = noticeService.updateNotice(n);
		
		if(result > 0) {
		 // model.addAttribute("alertMsg", "성공적으로 수정되었습니다.");
			ra.addFlashAttribute("alertMsg", "성공적으로 수정되었습니다");
			return "redirect:detail.do?no=" + noticeNo;
		}else {
			return "redirect:/";
		}
	}
	
 	
	
	
	
	
	
	
}
