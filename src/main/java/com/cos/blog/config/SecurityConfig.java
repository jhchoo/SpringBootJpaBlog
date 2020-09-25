package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.auth.PrincipalDetailService;

//  빈등록 : 스프링 컨테이터에서 관리 하도록 하는것 
@Configuration // 빈등록 
@EnableWebSecurity // 시큐리티 필터추가 // 설정을 여기에서 하겠다고 지정 하는것 
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소를 접근하면 권한을 미리 체크 한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	 
	@Bean // IoC 가 되요. 
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 중요 패스워드 비
	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	// 해당 password 가 뭘로 해쉬가 되어 회원 가입이 되었는지 알아야 
	// 같은 해쉬암호화 해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화, 테스트시에는 이렇게 한다. 
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**","/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 대신 로그인 해준다 
				.defaultSuccessUrl("/");
	}
}
