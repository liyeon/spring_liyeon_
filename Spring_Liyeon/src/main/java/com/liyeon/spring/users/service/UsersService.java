package com.liyeon.spring.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liyeon.spring.users.dto.UsersDto;

public interface UsersService {
	public void addUser(UsersDto dto); //회원 추가
	public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile); // 프로필 이미지
	public Map<String, Object> isExistId(String inputId);//아이디 중복 및 존재하는지 여부
	
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session);//로그인
	public void getInfo(HttpSession session, ModelAndView mView);//회원정보
	public void deleteUser(HttpSession session);//회원 삭제
	public void updateUser(HttpSession session, UsersDto dto);//회원정보 수정
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView);//비밀번호 수정
}