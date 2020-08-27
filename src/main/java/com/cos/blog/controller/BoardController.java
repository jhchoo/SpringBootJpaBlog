package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index() {
		// application 에 설정을 해서 파일네임만 적으면 된다.
		return "index";
	}
}
