package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail princlipal) {
		// application 에 설정을 해서 파일네임만 적으면 된다.
		if (princlipal != null) {
			System.out.println("로그인 사용자 아이디 : " + princlipal.getUsername() );
		} else {
			System.out.println("로그인 사용자 없다 " );
		} 
		
		return "index";
	}
	
	// USER 권한이 필요.
	@GetMapping("/board/saveForm")
	public String saveForm() {
		
		return "board/saveForm";
	}
	
	
}
