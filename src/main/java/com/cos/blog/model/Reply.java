package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
public class Reply {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Lob // 대용량 데이
	private String content;

	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@CreationTimestamp
	private Timestamp createDate;
	
}
