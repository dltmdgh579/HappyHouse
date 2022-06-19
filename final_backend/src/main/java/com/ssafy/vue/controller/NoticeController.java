package com.ssafy.vue.controller;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Reply;
import com.ssafy.vue.service.BoardService;
import com.ssafy.vue.service.NoticeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notice")
//@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;


	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Board>>(noticeService.retrieveBoard(), HttpStatus.OK);
	}


	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<Board>(noticeService.detailBoard(articleno), HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board board) {
		logger.debug("writeBoard - 호출");
		if (noticeService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		logger.debug("updateBoard - 호출");
		
		if (noticeService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (noticeService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@GetMapping("/searchid/{userid}")
	public List<Board> searchById(@PathVariable String userid){
		return noticeService.searchById(userid);
	}
	@GetMapping("/searchsubject/{subject}")
	public List<Board> searchBySubject(@PathVariable String subject){
		return noticeService.searchBySubject(subject);
	}
	@GetMapping("/searchcontent/{content}")
	public List<Board> searchByContent(@PathVariable String content){
		return noticeService.searchByContent(content);
	}

	@GetMapping("/reply/{articleno}")
	public List<Reply> getReply(@PathVariable int articleno){
		return noticeService.getReply(articleno);
	}

	@PostMapping("/reply")
	public void newReply(@RequestBody Reply r){
		noticeService.newReply(r);
		return;
	}
}