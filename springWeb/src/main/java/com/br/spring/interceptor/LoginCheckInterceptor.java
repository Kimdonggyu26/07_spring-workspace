package com.br.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LoginCheckInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") != null) { // 로그인이 되어있을 경우
			return true; // 정상적으로 Controller 실행
		}else { // 로그인이 되어있지 않을 경우
			
			// alert 메세지와 함께 메인페이지가 다시 보여지도록
			
			// RedirectAttributes의 대안
			FlashMap flashMap = new FlashMap();
			flashMap.put("alertMsg", "로그인 후 이용가능한 서비스입니다.");
			RequestContextUtils.getFlashMapManager(request).saveOutputFlashMap(flashMap, request, response);
			
			
			response.sendRedirect(request.getContextPath());
			
			return false; // Controller 실행되지 않도록
		}
	}

}
