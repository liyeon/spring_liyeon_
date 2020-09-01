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
		session.insert("users.insert", dto);
	}//=======insert=======
	
	@Override
	public boolean isExist(String inputId) {
		//입력한 아이디가 존재하는지 id를 select해본다.
		String id = session.selectOne("users.isExist", inputId);
		if(id==null) { //select시 id가 존재하지 않으면 리턴하지 않는다.
			return false;
		}else { //select시 id가 존재한다면 리턴한다.
			return true;
		}
	}//=======isExist=======
	
}//=========UsersDaoImpl============