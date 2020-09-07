package com.cos.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void 해쉬암호화() {
		String encPAssword = new BCryptPasswordEncoder().encode("11234");
		System.out.println("11234 해쉬 " + encPAssword);
	}
}
