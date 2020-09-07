package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// interface 로 선언 해야 한다. 
// DAO 와 같다.
// Bean등록 된다. 자동으로.
// @Repositoy 생략가
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
}





// 로그인을 위한 기능을 하나 만든다.
// JPA 네이밍 전략./
// 이걸 사용하면 쿼리를 자동으로 만들어 주는데.....
// 실제 프로젝트에서는 단순 쿼리가 거의 없기 때문에 직접 쿼리를 하는게 좋을 것이다.
// SELECT * FROM user WHERE username = ?1 AND password ?2;
// User findByUsernameAndPassword(String username, String passord);

//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//User login(String username, String passord);