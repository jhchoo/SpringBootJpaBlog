package com.cos.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
		
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.wirte(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		System.out.println("deleteById");
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		System.out.println("deleteById");
		boardService.update(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/board/{boardid}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardid, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.replyWirte(boardid, reply, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardid}/reply/{replyid}")
	public ResponseDto<Integer> replyDelete(@PathVariable int boardid, @PathVariable int replyid) {
		System.out.println("boardid" + boardid);
		System.out.println("replyid" + replyid);
		
		boardService.replyDelete(replyid);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}