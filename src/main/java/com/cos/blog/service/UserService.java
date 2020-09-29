package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링 컴포넌트를 통해 메모리에 올린다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional // 트렌젝션을 하여 DB 유지
	public User find(String username) {

		User persistance = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		
		return persistance;
	}
	
	@Transactional // 트렌젝션을 하여 DB 유지
	public void add(User user) {

		String encPassword = encoder.encode(user.getPassword()); // 해쉬 변경

		user.setPassword(encPassword);
		user.setRole(RoleType.USER);

		userRepository.save(user);
	}

	@Transactional // 트렌젝션을 하여 DB 유지
	public void userModify(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화를 시키고
		// 영속화된 오브젝트를 변경하면 된다.

		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패 ");
		});

		// sns 로그인 사용자 
		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}

		// 함수종료시 자동 커밋
	}

}

//@Transactional(readOnly = true)    // 셀렉트 할때 여러번 해도 정합성을 유지 시켜 준다.
//public User login(User user) {
//		return userRepository.login(user.getUsername(), user.getPassword());
//}
