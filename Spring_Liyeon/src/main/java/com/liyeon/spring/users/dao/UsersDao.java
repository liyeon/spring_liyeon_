package com.liyeon.spring.users.dao;

import com.liyeon.spring.users.dto.UsersDto;

public interface UsersDao {
	public boolean isExist(String inputId);
	public void insert(UsersDto dto);
}