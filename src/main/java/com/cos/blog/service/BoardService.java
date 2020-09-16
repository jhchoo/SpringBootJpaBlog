package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

// 스프링 컴포넌트를 통해 메모리에 올린다.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional    //트렌젝션을 하여  DB 유지
	public void wirte(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		System.out.println(board.getContent());
		boardRepository.save(board);
	}

}



//@Transactional(readOnly = true)    // 셀렉트 할때 여러번 해도 정합성을 유지 시켜 준다.
//public User login(User user) {
//		return userRepository.login(user.getUsername(), user.getPassword());
//}

