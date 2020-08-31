package com.liyeon.spring.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyeon.spring.users.dao.UsersDao;
import com.liyeon.spring.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao dao;

	@Override
	public void addUser(UsersDto dto) {
		dao.insert(dto);
	}//=====addUser=====
}//=======UsersServiceImpl