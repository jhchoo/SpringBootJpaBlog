package com.cos.blog.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;

@RestController
public class DummyControllerIntent {

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("getUsername: " + user.getUsername());
		System.out.println("getPassword: " + user.getPassword());
		System.out.println("getEmail: " + user.getEmail());
		return "회원가입이 완료 되었습니다.";
	}
}
