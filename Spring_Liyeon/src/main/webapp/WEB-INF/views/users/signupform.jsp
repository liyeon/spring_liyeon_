<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signupform.jsp</title>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
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
<body ng-app="myApp">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script>
	//myApp이라는 모듈 만들기
	var myApp=angular.module("myApp", []);
	//formCtrl 이라는 컨트롤러 만들기
	myApp.controller("formCtrl", function($scope, $http){//angularjs가 초기화 될 때 최초 한번만 호출된다.
		$scope.canUseId=false; //입력한 아이디가 사용 가능한지 여부
		//ng-model에 입력한 id의 값
		$scope.idChanged=function(){
			$http({
				url:"checkid.do",
				method:"get",
				params:{inputId:$scope.id}
			}).success(function(data){
				//data => {isExist:true} or {isExist:false} 인 object 이다.
				//입력한 아이디가 DB에 존재하지 않아야지 사용 할 수 있다.
				$scope.canUseId=!data.isExist;
				$scope.myForm.id.$valid= !data.isExist;//값을 강제로 집어넣을 수 있다.
				$scope.myForm.id.$invalid= data.isExist;;
			});//success 종료
		};//idChanged 종료
		//비밀번호 입력란을 입력했을 때 호출되는 함수
		$scope.isPwdEqual=true;
		$scope.pwdChanged=function(){
			$scope.isPwdEqual = $scope.pwd==$scope.pwd2;
			//비밀번호를 같게 입력 했는지 여부를 모델로 관리
			$scope.myForm.pwd.$valid=$scope.pwd==$scope.pwd2;//같은지
			$scope.myForm.pwd.$invalid=$scope.pwd!=$scope.pwd2;//다른지
			$scope.myForm.pwd2.$valid=$scope.pwd==$scope.pwd2;//같은지
			$scope.myForm.pwd2.$invalid=$scope.pwd!=$scope.pwd2;//다른지
		}//pwdChanged
	});//myApp.controller 종료
</script>
	<div class="container" ng-controller="formCtrl">
		<h1>회원가입 폼 입니다!! </h1>
		<p>아이디중복여부 : {{canUseId}}</p>
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
		<form action="signup.do" method="post" id="myForm" name="myForm" novalidate>
			<input type="hidden" name="profile" id="profile" value=""/>
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" name="id" class="form-control" 
					   ng-model="id"
					   ng-required="true"
					   ng-pattern="/^[a-z].{3,9}$/"
					   ng-class="{'is-invalid': myForm.id.$invalid && myForm.id.$dirty,
				   				  'is-valid': myForm.id.$valid}"
				   	   ng-change="idChanged()"
				/>
				<small class="form-text text-muted">영문자 소문자로 시작하고 최소 4글자~10글자까지 입력가능합니다.</small>
				<div class="invalid-feedback">사용 할 수 없는 아이디 입니다.</div>
				<div class="valid-feedback">사용 가능한 아이디 입니다.</div>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd" class="form-control"
					   ng-model="pwd"
					   ng-required="true"
					   ng-minlength="4"
					   ng-maxlength="10"
					   ng-class="{'is-invalid':myForm.pwd.$invalid  && myForm.pwd.$dirty,
					   			  'is-valid':myForm.pwd.$valid}"
					   ng-change="pwdChanged()"
				/>
				<small class="form-text text-muted">최소 4글자~10글자이내로 입력하세요!</small>
				<div class="invalid-feedback">하단의 비밀번호를 확인해주세요!</div>
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input type="password" name="pwd2" id="pwd2" class="form-control"
					   ng-model="pwd2"
					   ng-required="true"
					   ng-minlength="4"
					   ng-maxlength="10"
					   ng-class="{'is-invalid':myForm.pwd2.$invalid && myForm.pwd2.$dirty,
					   			  'is-valid':myForm.pwd2.$valid}"
					   ng-change="pwdChanged()"
				/>
				<div class="valid-feedback">비밀번호가 동일합니다.</div>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input type="text" name="email" id="email" class="form-control" 
					   ng-model="email"
					   ng-required="true"
					   ng-pattern="/@/"
					   ng-class="{'is-invalid':myForm.email.$invalid && myForm.email.$dirty,
					   			 'is-valid':myForm.email.$valid}"
				/>
				<!-- /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 이메일 유효성 검증 i는 대소문자 구분x -->
				<div class="invalid-feedback">이메일 형식에 맞게 입력해주세요</div>
				<div class="valid-feedback">이메일 형식에 맞게 입력하셨습니다.</div>
			</div>
			<button type="submit" ng-disabled="myForm.$invalid || !canUseId || !isPwdEqual" class="btn btn-info">가입</button>
			<button type="reset" class="btn btn-danger"> 취소</button>
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