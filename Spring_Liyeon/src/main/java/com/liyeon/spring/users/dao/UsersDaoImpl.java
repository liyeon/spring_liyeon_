package com.liyeon.spring.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liyeon.spring.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	@Autowired
	private SqlSession session;
	@Override
	public void insert(UsersDto dto) {
		session.insert("users", dto);
	}//=======insert=======
	
}//=========UsersDaoImpl============