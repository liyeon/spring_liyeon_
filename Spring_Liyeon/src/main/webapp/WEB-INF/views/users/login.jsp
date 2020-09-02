<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/login</title>
</head>
<body>
	<script>
	<%--requestScope. --%>
	<%--sessionScope / requestScope.url, encodedUrl // ModelAndView--%>
	if(${isSuccess }){
		alert("${id }님이 로그인 되었습니다.");
		location.href="${url }";
	}else{
		alert("아이디 혹은 비밀번호를 다시 확인해 주십시오");
		location.href="loginform.do?url=${encodedUrl }";
	}
	</script>
	
</body>
</html>