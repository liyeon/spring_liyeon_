package com.liyeon.spring.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		//upload 폴더에 저장된 파일 정보가 여기로 들어온다. 
		//원본 파일명
		String orgFileName = mFile.getOriginalFilename();
		//webapp/upload 폴더 까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
		//메소드를 통해서 경로를 얻어온다.
		String realPath = request.getServletContext().getRealPath("/upload");
		
		//저장할 파일의 상세경로(업로드 폴더 뒤에 붙을 파일 명을 위해서 필요하다.)
		String filePath = realPath+File.separator;
		
		//전체 경로를 가지고 있는 파일 객체를 생성해야한다.
		
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면
			upload.mkdir(); //만들어준다.
		}//====if====
		
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		
		try {
			//upload 폴더에 파일을 저장한다.
			mFile.transferTo(new File(filePath+saveFileName));// 전체 경로를 저장한다.
			System.out.println(filePath+saveFileName);//콘솔창에 저장된 파일의 전체 경로를 출력한다.
		} catch (Exception e) {
			e.printStackTrace();
		}//====try~catch====
		
		//Map에 이미지 파일의 경로를 담는다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageSrc", "/upload/"+saveFileName);
		
		return map;
	}//=====saveProfileImage=====
	
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

	

	@Override
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session) {
		
		
	}//=====loginProcess=====

	@Override
	public void getInfo(HttpSession session, ModelAndView mView) {
		
		
	}//=====getInfo=====

	@Override
	public void deleteUser(HttpSession session) {
		
		
	}//=====deleteUser=====

	@Override
	public void updateUser(HttpSession session, UsersDto dto) {
		
		
	}//=====updateUser=====

	@Override
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView) {
		
		
	}//=====updateUserPwd=====
}//=======UsersServiceImpl=======