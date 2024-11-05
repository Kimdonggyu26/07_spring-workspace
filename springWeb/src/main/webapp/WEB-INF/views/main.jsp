<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container p-3">

    <!-- Header, Nav start -->
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <!-- Header, Nav end -->

    <!-- Section start -->
    <section class="row m-3" style="min-height: 500px">

      <div class="container border p-5 m-4 rounded">
        <h2 class="m-4">해당 페이지의 내용이 보여져야되는 자리</h2>
        
      	<ol>
      		<li>웹 소켓 관련 라이브러리 추가 : pom.xml</li>
      		<li>DispatcherServlet등록하는 구문에 비동기 작업에 대한 구문 추가 : web.xml</li>
      		<li>
      			EchoHandler 구성
      			클라이언트가 서버에 연결되었을 때,
      			서버로 데이터가 전송되었을때,
      			클라이언트와의 연결이 끊겼을 때,
      			처리할 구문을 작성할 클래스
      		</li>
      		<li>webSocket관련 등록구문, EchoHandler 등록구문 작성 : servlet-context.xml</li>
      		<li>웹소켓 사용을 위한 화면 구성(자바스크립트의 sockjs 이용해서 웹소켓 사용)</li>
      	</ol>
      	
      	<c:if test="${ not empty loginUser }">
      		<a class="btn btn-secondary" href="${ contextPath }/chat/room.do">채팅방 입장</a>
      	</c:if>
        
      
      </div>
      
      

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <!-- Footer end -->

  </div>
</body>
</html>c