<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/private/andmin.do</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<h1>전체 회원의 정보를 가져옵니다.</h1>
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>가입날짜</th>
				<th>프로필 링크</th>
			</tr>
			<c:forEach var="tmp" items="${list }">
			<tr>
				<td>${tmp.id }</td>
				<td>${tmp.email }</td>
				<td>${tmp.regdate }</td>
				<td>${tmp.profile }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>