package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
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
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스 auto_increament
	
	@Column(nullable = false, length = 30)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'") // 문자라는것을 알려주기위해 작은 따옴표가 안에 있다.
	private String role; // enum 을 쓰는게 좋다.도메인을 정할 수 있다. 오타방지 가능.
	
	@CreationTimestamp
	private Timestamp createDate;
}
