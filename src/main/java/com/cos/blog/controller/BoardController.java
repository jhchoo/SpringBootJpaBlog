package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC  ) Pageable pageable) {
		// application 에 설정을 해서 파일네임만 적으면 된다.
//		if (princlipal != null) {
//			System.out.println("로그인 사용자 아이디 : " + princlipal.getUsername() );
//		} else {
//			System.out.println("로그인 사용자 없다 " );
//		} 
		
		model.addAttribute("boards", boardService.list(pageable));
		
		return "index"; // viewResolver 작동.
	}
	
	// USER 권한이 필요.
	@GetMapping("/board/saveForm")
	public String saveForm() {
		
		return "board/saveForm";
	}
	
	
}
