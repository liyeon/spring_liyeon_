<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/private/updateform</title>
<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body ng-app="myApp">
<!-- angularjs 로딩 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script>
	//myApp이라는 모듈 만들기
	var myApp=angular.module("myApp", []);
	//formCtrl 이라는 컨트롤러 만들기
	myApp.controller("formCtrl", function($scope){//angularjs가 초기화 될 때 최초 한번만 호출된다.
		//비밀번호 입력란을 입력했을 때 호출되는 함수
		$scope.isPwdEqual=true;
		$scope.pwdChanged=function(){
			$scope.isPwdEqual = $scope.newPwd==$scope.newPwd2;
			//비밀번호를 같게 입력 했는지 여부를 모델로 관리
			$scope.myForm.newPwd.$valid=$scope.newPwd==$scope.newPwd2;//같은지
			$scope.myForm.newPwd.$invalid=$scope.newPwd!=$scope.newPwd2;//다른지
			$scope.myForm.newPwd2.$valid=$scope.newPwd==$scope.newPwd2;//같은지
			$scope.myForm.newPwd2.$invalid=$scope.newPwd!=$scope.newPwd2;//다른지
		}//pwdChanged
	});//myApp.controller 종료
</script>
	<div class="container" ng-controller="formCtrl">
	<h1>비밀번호 수정 폼!</h1>
		<!-- 비밀번호 수정 업로드폼 -->
		<form action="pwd_update.do" method="post" id="myForm" name="myForm" novalidate>
			<div class="form-group">
				<label for="pwd">기존 비밀번호</label>
				<input type="password" id="pwd" name="pwd" class="form-control" 
					   ng-model="pwd"
					   ng-required="true"
				/>
			</div>
			<div class="form-group">
				<label for="newPwd">변경할 비밀번호</label>
				<input type="password" id="newPwd" name="newPwd" class="form-control" 
					   ng-model="newPwd"
					   ng-required="true"
					   ng-minlength="4"
					   ng-maxlength="10"
					   ng-class="{'is-invalid':myForm.newPwd.$invalid  && myForm.newPwd.$dirty,
					   			  'is-valid':myForm.newPwd.$valid}"
					   ng-change="pwdChanged()"
				/>
			</div>
			<div class="form-group">
				<label for="newPwd2">변경할 비밀번호 확인</label>
				<input type="password" id="newPwd2" name="newPwd2" class="form-control" 
					   ng-model="newPwd2"
					   ng-required="true"
					   ng-minlength="4"
					   ng-maxlength="10"
					   ng-class="{'is-invalid':myForm.newPwd2.$invalid  && myForm.newPwd2.$dirty,
					   			  'is-valid':myForm.newPwd2.$valid}"
					   ng-change="pwdChanged()"
				/>
				<div class="valid-feedback">변경할 비밀번호가 동일합니다.</div>
			</div>
			<button type="submit" ng-disabled="myForm.$invalid || !isPwdEqual" class="btn btn-info">수정</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</form>
	</div><!-- container -->
</body>
</html>