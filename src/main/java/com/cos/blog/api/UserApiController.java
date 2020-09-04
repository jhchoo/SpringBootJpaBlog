package com.cos.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session; 

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println(" 회원정보 저장");
		user.setRole(RoleType.USER);
		// 실제로 저장하고 리턴 해주면 된다.
		userService.add(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// 전통적인 로그인 방식이다. 다음 시간에는 스프링 시큐리티를 이용한 로그인을 만들어 보자. 
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user) {
		System.out.println(" 회원 로그인 ");
		user.setRole(RoleType.USER);
		// 실제로 저장하고 리턴 해주면 된다.
		User principal = userService.login(user);
		
		if (principal != null ) {
			session.setAttribute("principal", principal);
			System.out.println(" 회원 로그인 성공  ");
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		}
		
		return new ResponseDto<Integer>(HttpStatus.METHOD_FAILURE.value(), 1);
	}
}
