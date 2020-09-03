package com.liyeon.spring.users.dao;

import java.util.List;

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
	
	//인자로 전달되는 id에 해당되는 사용자 정보를 리턴하는 메소드
	@Override
	public UsersDto getData(String id) {
		 //result type이 usersDto, parameter type이 string
		return session.selectOne("users.getData", id);
	}//======getData=======

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}//=======delete=======

	@Override
	public void update(UsersDto dto) {
		 session.update("users.update", dto);
	}//=======update=======

	@Override
	public void updatePwd(UsersDto dto) {
		//update문의 영향을 받은row의 갯수가 리턴된다.
		 session.update("users.updatepwd", dto);
	}//=======updatePwd=======

	@Override
	public List<UsersDto> getList(UsersDto dto) {
		return session.selectList("users.getList", dto);
	}//======getList=======
}//=========UsersDaoImpl============