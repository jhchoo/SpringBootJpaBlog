package com.cos.blog.api;

import javax.servlet.http.HttpSession;

// import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.auth.PrincipalDetail;
import com.cos.blog.auth.PrincipalDetailService;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private PrincipalDetailService principalDetailService;

	@ApiOperation("회원가입")
	@PostMapping("/api/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println(" 회원정보 저장");
				
		// 실제로 저장하고 리턴 해주면 된다.
		userService.add(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@ApiOperation("수정")
	@PutMapping("/api/user")
	public ResponseDto<Integer> update(@RequestBody User user
			, @AuthenticationPrincipal PrincipalDetail principal
			, HttpSession session) { // json 데이터를 받으려면 @RequestBody  이걸 해야 한다.
		System.out.println(" 회원정보 저장");
				
		// 실제로 저장하고 리턴 해주면 된다.
		userService.userModify(user);
		
		/*
		// 여기서는 트렌젝션이종료되기 때문에 DB값은 변경됬음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 , 우리가 직접세션값을 변경 해줄 것임.

		// 강제로 세션에 추가 하는 방법이 예전에는 됬었는데, 지금은 안된다.
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		SecurityContext securityContext =  SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		*/
		
		// 로그인을 다시 시켜준다.
		UserDetails userDetail = principalDetailService.loadUserByUsername(user.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
		SecurityContext securityContext =  SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}




//@Autowired
//private HttpSession session; 


// 전통적인 로그인 방식이다. 다음 시간에는 스프링 시큐리티를 이용한 로그인을 만들어 보자. 
/*
 * @PostMapping("/api/user/login") public ResponseDto<Integer>
 * login(@RequestBody User user, HttpSession session ) { System.out.println(" 회원 로그인 ");
 * user.setRole(RoleType.USER); // 실제로 저장하고 리턴 해주면 된다. User principal =
 * userService.login(user);
 * 
 * if (principal != null ) { session.setAttribute("principal", principal);
 * System.out.println(" 회원 로그인 성공  "); return new
 * ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
 * 
 * return new ResponseDto<Integer>(HttpStatus.METHOD_FAILURE.value(), 1); }
 */