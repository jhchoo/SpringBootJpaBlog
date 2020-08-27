package com.cos.blog.test;

import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// html이아니라 데이터를 리턴 해주는 컨트롤러가 restController 이다.
@RestController
public class DummyControllerIntent {
	
	@Autowired
	private UserRepository userRepository;
	
	// save 함수는 아이디를 전달하면 insert를 한다.
	// 이메일과 패스워드를 업데이트 해보자.
	@Transactional
	@PutMapping("/dummy/user/modify/{id}")
	public String modify(@PathVariable int id, @RequestBody User requestUser) { // 아무것도 안하면 x-www-form 으로받는거다.
		
		System.out.println("id = " + id);
		System.out.println("email = " + requestUser.getEmail());
		System.out.println("pass = " + requestUser.getPassword());

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당유저는 없습니다. id : " + id); // AOP 를 사용해 에러 페이지 이동 시킨다.
		});

		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword()); // 영속성 컨텍스트 
		
		// 찾아서? 업데이트
		// 그러나 save를 인서트랑같이 쓰면 오ㄹ류 확률이 있기 때문에 사용하지 않는다.
		// 어노테이션 활용 한다. : 더티체킹  @Transactional
		// userRepository.save(user);
			
		return "수정되었습니다.";
	}
	
	
	
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한페이지당 두개의 데이터를 받아보자.
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC  ) Pageable pageable) {
		Page<User> pagingUsers = userRepository.findAll(pageable);
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	
	// {id} 이 문법은 주소를 파라미터로 받을 수 있습니다.
 	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
 		
 		// Supplier 는 제너릭 타입으로 제공한다.
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당유저는 없습니다. id : " + id); // AOP 를 사용해 에러 페이지 이동 시킨다.
//			}
//		});
		
		// 람다식 
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당유저는 없습니다. id : " + id); // AOP 를 사용해 에러 페이지 이동 시킨다.
		});
		
		// 오브젝트를 던지면 메세지 컨버터가 json 으로 바꿔준다.
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("getUserId: " + user.getId());
		System.out.println("getUsername: " + user.getUsername());
		System.out.println("getPassword: " + user.getPassword());
		System.out.println("getEmail: " + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료 되었습니다.";
	}
}
