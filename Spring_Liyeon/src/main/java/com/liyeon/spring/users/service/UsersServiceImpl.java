package com.liyeon.spring.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.liyeon.spring.users.dao.UsersDao;
import com.liyeon.spring.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao dao;

	@Override
	public void addUser(UsersDto dto) {
		//dto 객체에 비밀번호를 암호화 하여 넣어준다.
		String inputPwd = dto.getPwd();
		// Spring security 이용 
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(); // 회원가입 form에 입력한 비밀번호
		String encodedPwd = encoder.encode(inputPwd);//암호화된 비밀번호
		//암호화된 비밀번호를 dto 객체에 다시 넣어준다.
		dto.setPwd(encodedPwd);
		
		//dao 객체를 이용하여 새로운 사용자 정보를 DB에 저장한다.
		dao.insert(dto);
	}//=====addUser=====

	@Override
	public Map<String, Object> isExistId(String inputId) {
		//dao를 이용하여 아이디의 존재여부를 알아낸다.
		boolean isExist = dao.isExist(inputId);
		//아이디가 존재하는지의 여부를
		Map<String, Object> map = new HashMap<String, Object>();
		//Map에 key, value로 담아 리턴해준다.
		map.put("isExist", isExist);
		return map;
	}//=====isExistId=====
}//=======UsersServiceImpl