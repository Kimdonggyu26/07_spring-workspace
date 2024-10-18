<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${ contextPath }/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>

	<h3>회원관리 1번 페이지</h3>
	
	<form id="mem-form">	<!-- 해당 form요소.serialize() => "userNo=10&userId=user01&userPwd=pass01" -->
	
		번호 : <input type="text" name="userNo"> <br>
		아이디 : <input type="text" name="userId"> <br>
		비밀번호 : <input type="text" name="userPwd"> <br>
		
		<button type="button" onclick="fn_ajax1();">조회1(아이디, 비번으로 이름 조회)</button>
		<button type="button" onclick="fn_ajax2();">조회2(아이디, 비번으로 이름 조회)</button>
		<button type="button" onclick="">조회3(번호로 회원전체정보 조회)</button>
		<button type="button" onclick="">전체조회</button>
		<br>
		
		<button type="button" onclick="">번외1</button>
		<button type="button" onclick="">번외2</button>
		
	</form>
	
	<hr>
	
	<div id="result">
		결과가 보여지는 영역
	</div>
	
	<script>
	
		// 조회1 버튼 : 아이디랑 비번으로 이름 조회
		function fn_ajax1(){
			
			let id = $('input[name=userId]').val();
			let pwd = $('input[name=userPwd]').val();
			
			$.ajax({
				// 요청
				url: '${contextPath}/member1/detail1.do',
				data: 'id=' + id + "&pwd=" + pwd,	// 쿼리 스트링으로 전달
				type: 'get',
				// 응답
				success: function(resData){
					$('#result').text(resData);
				},
				error: function(){
					console.log("조회1 버튼에 대한 ajax 통신 실패");
				}
			})
			
			
		}
	
		function fn_ajax2(){
			
			$.ajax({
				url: '${contextPath}/member1/detail2.do',
				type: 'get',
				data: $('#mem-form').serialize(), // form의 모든 입력요소들을 직렬화 (위와 같은 쿼리스트링으로 만들어짐)
				success: function(resData){
					$('#result').text(resData);
				},
				error: function(){
					console.log("조회2 버튼에 대한 ajax 통신 실패");
				}
			})
			
		}
	</script>
	
	
	
	
	
	
	
	

</body>
</html>