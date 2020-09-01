<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liyeon's Home!</title>
<link rel="stylesheet" href="resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>스프링 연습 중입니다!!</h1>
	<c:choose>
		<%--로그인 안 된 상태 sessionScope.id 가 비어있는상태  --%>
		<c:when test="${empty id }">
			<a href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
			<a href="${pageContext.request.contextPath }/users/signupform.do">회원가입</a>
		</c:when>
		<%--로그인이 된 상태 sessionScope.id 가 비어있지 않는 상태--%>
		<c:otherwise>
			<a href="${pageContext.request.contextPath }/users/private/info.do"><strong>${id }</strong>님이 접속 중입니다.</a>
			<a href="${pageContext.request.contextPath }/users/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>
	<ul>
		<li>목록쓰 </li>
	</ul>
</body>
</html>