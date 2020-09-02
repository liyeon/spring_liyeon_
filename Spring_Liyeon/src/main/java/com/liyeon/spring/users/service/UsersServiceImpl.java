package com.liyeon.spring.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
		//입력한 정보가 유효한 정보인지 여부를 저장할 지역 변수
		boolean isValid = false; // 초기값 false
		//로그인 폼에 입력한 아이디를 이용하여 DB에서select 해봄, 존재하지않으면 null 리턴
		UsersDto resultDto = dao.getData(dto.getId());
		//아이디 값으로 가져온 데이터의 null 가능성
		if(resultDto != null) {//아이디가 존재하는 경우(아이디 일치)
			//DB에 저장된 암호화된 비밀번호를 읽어온다.
			String encodedPwd=resultDto.getPwd();
			//로그인 폼에 입력한 비밀번호
			String inputPwd = dto.getPwd();
			//BCrypt 클래스의 static메소드를 이용해서 일치 여부를 얻어낸다.
			isValid = BCrypt.checkpw(inputPwd, encodedPwd);
		}
		
		if(isValid) {//만일 유효한 정보면
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
			//view페이지에서 사용할 정보
			mView.addObject("isSuccess", true);
		}else {//아니면
			mView.addObject("isSuccess", false);
		}
	}//=====loginProcess=====

	@Override
	public void getInfo(HttpSession session, ModelAndView mView) {
		//로그인 된 아이디를 session 객체를 이용해서 얻어온다.
		String id = (String)session.getAttribute("id");
		//dao를 이용해서 사용자 정보를 얻어와서
		UsersDto dto = dao.getData(id);
		//mView 객체에 담아준다.
		mView.addObject("dto", dto);
	}//=====getInfo=====

	@Override
	public void deleteUser(HttpSession session) {
		//세션에 저장된 아이디를 읽어와서
		String id = (String)session.getAttribute("id");
		//삭제
		dao.delete(id);
		//로그아웃 처리
		session.invalidate();
	}//=====deleteUser=====

	@Override
	public void updateUser(HttpSession session, UsersDto dto) {
		
		
	}//=====updateUser=====

	@Override
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView) {
		
		
	}//=====updateUserPwd=====
}//=======UsersServiceImpl=======