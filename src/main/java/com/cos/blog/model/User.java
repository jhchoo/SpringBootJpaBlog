package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity // User 클래스가 MySQL에 테이블이 생성된다.
public class User {
	
	@Id // pk 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스 auto_increament
	
	@Column(nullable = false, length = 30)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
	private String role; // enum 을 쓰는게 좋다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
