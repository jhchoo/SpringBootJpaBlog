package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// 메이븐 경로 변경전 테스트 
//	@GetMapping("/temp/home")
//	public String tempHome() {
//		System.out.println("tempHome()");
//	 	return "/home.html";
//	}
	
	@GetMapping("/temp/jsp")
	public String tempJSP() {
		System.out.println("tempJSP()");
		return "test";
	}
}
