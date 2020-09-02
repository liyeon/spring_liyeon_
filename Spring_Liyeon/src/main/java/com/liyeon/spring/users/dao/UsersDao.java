package com.liyeon.spring.users.dao;

import com.liyeon.spring.users.dto.UsersDto;

public interface UsersDao {
	public boolean isExist(String inputId); //입력한 아이디의 존재 여부 
	public void insert(UsersDto dto); //회원 정보 추가
	public UsersDto getData(String id); // 한명의 회원 정보를 가져옴
	public void delete(String id);//회원정보 삭제 
	public void update(UsersDto dto);//회원 정보 수정
	public void updatePwd(UsersDto dto); //비밀번호 수정
}