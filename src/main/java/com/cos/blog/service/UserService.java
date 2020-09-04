package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링 컴포넌트를 통해 메모리에 올린다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional    //트렌젝션을 하여  DB 유지
	public void add(User user) {
		//try {
			userRepository.save(user);
			
			// 구현하지 않아도 
			// GlobalExceptionHandler 이 동작하여
			// 에러를 표시한다.
			
		//} catch (Exception e) {
		//	e.printStackTrace();
		//	System.out.println("회원가입 오류 ");
		//}
	}

	@Transactional(readOnly = true)    // 셀렉트 할때 여러번 해도 정합성을 유지 시켜 준다.
	public User login(User user) {
			return userRepository.login(user.getUsername(), user.getPassword());
	}
}
