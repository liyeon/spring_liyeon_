<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/private/update</title>
</head>
<body>
<script>
	if(${isSuccess}){
		alert("${id }님 비밀번호를 수정했습니다.");
		location.href="info.do";
	}else{
		alert("기존 비밀번호가 일치하지 않습니다.");
		location.href="pwd_updateform.do";
	}
</script>
</body>
</html>