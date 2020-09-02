<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/private/info</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	.profile {width : 200px; height : 200px; text-align : center;display : block;border-radius : 50%; border : 1px solid #000;}
	#profileImage {width : 100%; height : 100%;object-fit : cover;}
	svg#profileImage{width : 80%; height : 94%; color : #868686;	}
	img#profileImage{border-radius : 50%;	}
</style>
</head>
<body>
	<div class="container mt-4">
	<h1>개인정보 페이지</h1>
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<td>${dto.id }</td>
			</tr>
			<tr>
				<th>프로필 이미지</th>
				<td>
				<div class="profile">
				<c:choose>
				<%--빈 문자열도 true,null도 true로 인식 --%>
					<c:when test="${empty dto.profile}">
					<svg id="profileImage" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
					</svg>
					</c:when>
					<c:otherwise>
					<img id="profileImage"
					src="${pageContext.request.contextPath }${dto.profile }" />
					</c:otherwise>
				</c:choose>
				</div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><a href="pwd_updateform.do">수정하기</a></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email }</td>
			</tr>
			<tr>
				<th>가입일</th>
				<td>${dto.regdate }</td>
			</tr>
		</table>
		<a href="updateform.do">개인정보 수정</a>
		<a href="javascript:deleteConfirm()">탈퇴</a>
		
		
	</div>
	<script>
		function deleteConfirm(){
			var isDelete = confirm("${id } 회원님 탈퇴하시겠습니까?");
			if(isDelete){
				location.href="delete.do?id=${id}";
			}
			
		}
	</script>
</body>
</html>