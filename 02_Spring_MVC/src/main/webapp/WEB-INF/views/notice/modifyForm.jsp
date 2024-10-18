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

		<form action="${ contextPath }/notice/update.do" method="POST">
			번호 : ${ n.no } <br>
			제목 : <input type="text" name="title" value="${ n.title }"> <br>
			내용 : <input type="text" name="content" value="${ n.content }"> <br>
			
			<input type="hidden" name="no" value="${ n.no }">
			<button type="submit">수정하기</button>
		</form>

</body>
</html>