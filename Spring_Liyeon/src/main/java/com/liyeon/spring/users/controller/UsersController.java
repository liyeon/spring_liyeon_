package com.liyeon.spring.users.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	}
	//회원가입 폼 요청처리
	@RequestMapping("/users/signupform")
	public String signupForm() {
		//WEB-INF/views/users/signupform.js 페이지 forward 이동해서 응답한다.
		return "users/signupform";
	}
	//회원 가입 요청 처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {
		//service객체를 이용해서 사용자 정보를 추가한다.
		service.addUser(dto);
		//view 페이지로 forward 이동해서 응답한다.
		mView.setViewName("users/signup");
		return mView;
	}
	
}//========UsersController=======