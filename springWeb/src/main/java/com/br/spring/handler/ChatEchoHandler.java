package com.br.spring.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.br.spring.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatEchoHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception { // 웹소켓에 클라이언트가 연결되었을 때 처리할 내용 정의
		/*
		log.debug("========= websocket 연결됨 ============");
		log.debug("WebSocketSession 객체: {}", session);
		log.debug("session id: {}", session.getId());
		log.debug("session Attributes: {}", session.getAttributes()); // {sessionId=XXXX, loginUser=MemberDto객체}
		log.debug("현재 채팅방에 참가한 로그인한 회원: {}", session.getAttributes().get("loginUser"));
		*/
		
		sessionList.add(session);
		
		for(WebSocketSession sss : sessionList) {
			String msg = "entry|" + ((MemberDto)session.getAttributes().get("loginUser")).getUserId() + "님이 입장하였습니다.";
			sss.sendMessage(new TextMessage(msg));
		}
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception { // 웹소켓으로 데이터(텍스트, 파일 등)가 전송되었을 경우 처리할 내용
		/*
		log.debug("========== 메세지 들어옴 =========");
		log.debug("WebSocketSession 객체: {}", session);
		log.debug("WebSocketMessage 객체: {}", message);
		log.debug("메세지 내용 : {}", message.getPayload());
		*/
		
		// * 현재 해당 웹소켓에 연결되어있는 모든 클라이언트들(작성자본인포함)에게 현재 들어온 메세지 재발송
		for(WebSocketSession sss : sessionList) {
			// 메세지유형(chat/entry/exit) | 채팅방에 띄워주고자 하는 메세지 내용 | 발신자아이디 | 등등....
			String msg = "chat|" + message.getPayload() + "|" + ((MemberDto)session.getAttributes().get("loginUser")).getUserId();
			sss.sendMessage(new TextMessage(msg)); // * room.jsp 에서 onMessage 함수가 자동 실행
		}
		

	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception { // 웹소켓에 클라이언트가 연결이 끊겼을 때 처리할 내용 정의
		/*
		log.debug("========= websocket 연결끊김 ============");
		log.debug("WebSocketSession 객체: {}", session);
		log.debug("session id: {}", session.getId());
		log.debug("현재 채팅방에서 나간 회원: {}", session.getAttributes().get("loginUser"));
		*/
		
		
		sessionList.remove(session);
		
		for(WebSocketSession sss : sessionList) {
			String msg = "exit|" + ((MemberDto)session.getAttributes().get("loginUser")).getUserId() + "님이 퇴장하였습니다.";
			sss.sendMessage(new TextMessage(msg));
		}
	}

}
