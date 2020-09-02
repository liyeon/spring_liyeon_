package com.liyeon.spring.users.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liyeon.spring.users.dto.UsersDto;
import com.liyeon.spring.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	
	//아이디 중복 및 존재하는지 여부
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){
		//service가 리턴해주는 Map객체를 리턴한다.
		return service.isExistId(inputId);
	}//====checkid====
	
	//회원가입 폼 요청처리
	@RequestMapping("/users/signupform")
	public String signupForm() {
		//WEB-INF/views/users/signupform.js 페이지 forward 이동해서 응답한다.
		return "users/signupform";
	}//====signupForm====
	
	//회원 가입 요청 처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {
		//service객체를 이용해서 사용자 정보를 추가한다.
		service.addUser(dto);
		//view 페이지로 forward 이동해서 응답한다.
		mView.setViewName("users/signup");
		return mView;
	}//====signip====
	
	//ajax로 프로필 사진 업로드 요청 처리
	@RequestMapping("/users/private/profile_upload")
	@ResponseBody
	public Map<String, Object> profileUpload(HttpServletRequest request,
			@RequestParam MultipartFile image){
		//service 객체를 이용하여 이미지를 upload 폴더에 저장하고 Map을 리턴받는다.
		Map<String, Object> map = service.saveProfileImage(request, image);
		//servlet-context에 bean 설정 해주기ㅠㅜ
		//{"imageSrc","/upload/xxx.jpg"}형식의 JSON문자열을 출력하기 위해 Map을 @ResponseBody로 리턴해준다.
		return map;
	}//====profileUpload====
	
}//========UsersController=======