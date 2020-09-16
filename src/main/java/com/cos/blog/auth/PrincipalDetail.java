package com.cos.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가. 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
// 해당 타입의 오브젝트를 시큐리티 고유한 저장소에 저장 해준다.
@Getter
public class PrincipalDetail implements UserDetails {
	private User user; //

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료 되었는지 확인 한.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
// 계정 잠김 여부 
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료 여부 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정의 활성화 여부를 리턴한다.
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 가지고 있는 권한 목록을 리턴 한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
//		collectors.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				// TODO Auto-generated method stub
//				return "ROLE_"+user.getRole(); // prefix붙여야 한다.
//			}
//		});
		
		// 람다식으로 넣어 보자. 
		collectors.add(()-> {return  "ROLE_"+user.getRole(); });
		
		// TODO Auto-generated method stub
		return collectors; 
	}
	
}
