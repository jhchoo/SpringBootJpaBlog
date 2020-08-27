package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// interface 로 선언 해야 한다. 
// DAO 와 같다.
// Bean등록 된다. 자동으로.
// @Repositoy 생략가
public interface UserRepository extends JpaRepository<User, Integer>{

}
