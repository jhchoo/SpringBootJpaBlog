package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
//@DynamicInsert // insert 시에 null  인 필드를 제외 시켜 준다.
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스 auto_increament

	@Column(nullable = false, length = 100, unique=true)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") // 문자라는것을 알려주기위해 작은 따옴표가 안에 있다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // enum 을 쓰는게 좋다.도메인을 정할 수 있다. 오타방지 가능.

	@Column(length = 50)
	private String oauth; // kakao, google
	
	@CreationTimestamp
	private Timestamp createDate;
}
