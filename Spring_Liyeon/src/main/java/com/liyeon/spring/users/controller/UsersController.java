package com.liyeon.spring.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {
		//url파라미터가 넘어오는지 읽어와보기
    	String url = request.getParameter("url");
    	if(url==null){//목적지 정보가 없다면
    		String cPath=request.getContextPath();
    		url = cPath+"/home.do"; //로그인 후 인덱스 페이지로 가도록하기 위해서
    	}
    	//request에 담는다.
    	request.setAttribute("url", url);
		return "users/loginform";
	}//=====loginform=====
	
	@RequestMapping("/users/login")
	public ModelAndView login(UsersDto dto, ModelAndView mView,
			HttpSession session, HttpServletRequest request) {
		//로그인 후 가야하는 목적지 정보
    	String url = request.getParameter("url");
    	//목적지 정보도 미리 인코딩 해 놓는다.
    	String encodedUrl=URLEncoder.encode(url);
    	//view 페이지에 전달하기 위해 ModelAndView 객체에 담아준다.
    	mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		//service 객체를 이용해서 로그인 관련 처리를 한다.
		service.loginProcess(dto, mView, session);;
		mView.setViewName("users/login");
		return mView;
	}//=====login=====
	
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.do";
		//return "users/logout";
	}//=====logout=====
}//========UsersController=======