package com.liyeon.spring.users.service;

import java.util.Map;

import com.liyeon.spring.users.dto.UsersDto;

public interface UsersService {
	public void addUser(UsersDto dto); //회원 추가
	public Map<String, Object> isExistId(String inputId);//아이디 중복 및 존재하는지 여부
}