<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signupform.jsp</title>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<style>
.profile {
	width: 200px;
	height: 200px;
	text-align: center;
	display: block;
	border-radius: 50%;
	border: 1px solid #000;
}

#profileImage {
	cursor: pointer;
	width: 100%;
	height: 100%;
	object-fit: cover;
}

img#profileImage {
	border-radius: 50%;
}

svg#profileImage {
	width: 80%;
	height: 94%;
	color: #868686;
}
/*프로필 업로드 폼을 화면에서 숨긴다.*/
#profileForm {
	display: none;
}
</style>
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다!! </h1>
		<div class="profile">
			<a id="profileLink" href="javascript:">
				<i class="fas fa-user" id="profileImage"></i>
			</a>
		</div>
		<!-- 프로필을 업로드 하기 위한 폼 -->
		<form id="profileForm" action="private/profile_upload.do" method="post" enctype="multipart/form-data">
			<input type="file" id="image" name="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG, .gif, .GIF" />
		</form>
		<!-- 회원가입 폼  -->
		<form action="signup.do" method="post" id="myForm" name="myForm">
			<input type="hidden" name="profile" id="profile" value=""/>
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
	
<!-- jquery 로딩 -->
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<!-- jquery 플러그인 로딩 -->
<script src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script>
	//프로필 이미지를 클릭했을 때 실행 할 함수 등록
	$("#profileLink").on("click", function(){
		//input type="file"을 강제 클릭한다.
		$("#image").click();
	});
	
	//이미지를 선택 했을 때 실행할 함수 등록 
	$("#image").on("change", function(){
		//폼을 강제로 제출 함
		$("#profileForm").submit();
	});
	
	//폼이 ajax로 제출 될 수 있도록 jquery form 플러그인을 동작 시킨다. data는 서버로부터 결과를 받는다.
	$("#profileForm").ajaxForm(function(data){
		
		//기존의 프로필 요소를 제거한다.
		$("#profileImage").remove();
		
		//새로 img 요소를 만들어서 #profileLink에 추가한다.
		$("<img/>").attr("id", "profileImage")
		.attr("src", "${pageContext.request.contextPath }"+data.imageSrc)
		.appendTo("#profileLink");
		
		//회원 정보 수정폼이 전송 될 때 같이 전송 되도록 한다.
		$("#profile").val(data.imageSrc);//input type="hidden"의 value값
	});
</script>
</body>
</html>