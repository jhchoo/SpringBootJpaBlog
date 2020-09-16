package com.cos.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepositoy;
		
	// 스프링이 로그인 요청을 가로챌 때, 
	// password 부분 처리는 알아서 함
	// username 이 DB에 있는지만 확인 한다. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepositoy.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" + username);
				});

		return new PrincipalDetail(principal); // 시큐리티 세션에 유저가 저장 된다.
	}

}
