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

import io.swagger.annotations.ApiOperation;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;

	@ApiOperation("딜러 목록 전체 요청")
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.wirte(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@ApiOperation("설정된 범위의 딜러 검색")
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		System.out.println("deleteById");
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@ApiOperation("딜러 조건 검색")
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		System.out.println("deleteById");
		boardService.update(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@ApiOperation("딜러 조건 검색sdfsdaf")
	@PostMapping("/api/board/{boardid}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardid, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.replyWirte(boardid, reply, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@ApiOperation("딜러 조건 검색asdfsadfsad")
	@DeleteMapping("/api/board/{boardid}/reply/{replyid}")
	public ResponseDto<Integer> replyDelete(@PathVariable int boardid, @PathVariable int replyid) {
		System.out.println("boardid" + boardid);
		System.out.println("replyid" + replyid);
		
		boardService.replyDelete(replyid);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}