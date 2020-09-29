package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

// 46강 
// 인증이 안된 사용자들이 출입할 수 있는 경로를 auth 경로를 사용해서 허
// 그냥 주소가 / 면 index 허용
// static 이하 에 있는 js, css, image 허용


@Controller
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	UserService userService;

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		// Retrofit2
		// OkHttp
		// RestTemplate
		
		// POST 방식으로 key=value 데이터 요청
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "bf1c33b6c7145ec9481e129ec6114ffa");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);
		
		ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class
				);
		
		// Gson, Json Simple, ObjectMapper
		// 
		ObjectMapper objectMapper =  new ObjectMapper();
		OAuthToken oAuthToken = null; 
		try {
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//---
		//--- 카카오 프로파일 요
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);
		
		ResponseEntity<String> response2 = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest2,
					String.class
				);
		
		ObjectMapper objectMapper2 =  new ObjectMapper();
		KakaoProfile kakaoProfile = null; 
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// USER오브젝트를 만들어 로그인 한다. username, password, email
		System.out.println("카카오 아이디 : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		// UUID garvagePassword = UUID.randomUUID();
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getProperties().getNickname()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		User originUser = userService.find(kakaoUser.getUsername());
		if ( originUser.getUsername() == null ) {
			userService.add(kakaoUser);
		}
		
		// 로그인 처리 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
}
