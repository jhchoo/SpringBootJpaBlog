package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//  빈등록 : 스프링 컨테이터에서 관리 하도록 하는것 
@Configuration // 빈등록 
@EnableWebSecurity // 시큐리티 필터추가 // 설정을 여기에서 하겠다고 지정 하는것 
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소를 접근하면 권한을 미리 체크 한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean // IoC 가 되요. 
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}
