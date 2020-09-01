<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signupform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다!! </h1>
		<form action="signup.do" method="post" id="myForm" name="myForm">
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" name="id" class="form-control" />
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="text" name="pwd" class="form-control" />
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input type="text" name="email" class="form-control" />
			</div>
			<button type="submit" class="btn btn-info">가입</button>
			<button type="reset"> 취소</button>
		</form>
	</div><!-- container 종료  -->
</body>
</html>