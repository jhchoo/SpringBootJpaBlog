package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답 HTML 파일 

// 사용자가 요청 -> 응답(Data)

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest ";

	@GetMapping("/http/lombokTest")
	public String lombokTest () {
		Member m = Member.builder().username("aaa").password("2222").email("aaa@ggg.com").build();
		System.out.println(TAG + "getter" + m.getId());
		m.setId(5000);
		System.out.println(TAG + "getter" + m.getId());
		
		return "lombok test 완";
	}
	// 인터넷 브라우저 요청은 get만 가능 하다.
	// http://localhost:8080/http/get
	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) { // id=1&username=홍길동&password=1111&email=jhchoo@gmail.com
		return "GET 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	// http://localhost:8080/http/post
	@PostMapping("/http/post")
//	public String postTest(@RequestBody String text) { // text/plane
//		return "POST 요청 : " + text;
//	}
	public String postTest(@RequestBody Member m) { // application/json,MessageConverter
		return "POST 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "PUT 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	// http://localhost:8080/http/delet
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {
		return "DELETE 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
		
}
