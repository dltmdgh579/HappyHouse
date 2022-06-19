package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<Board> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		updateHit(articleno);
		return boardMapper.selectBoardByNo(articleno);
	}
	
	@Override
	public void updateHit(int articleno) {
		boardMapper.updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public List<Board> searchById(String userid) {
		return boardMapper.searchById(userid);
	}

	@Override
	public List<Board> searchBySubject(String subject) {
		return boardMapper.searchBySubject(subject);
	}

	@Override
	public List<Board> searchByContent(String content) {
		return boardMapper.searchByContent(content);
	}

	@Override
	public List<Reply> getReply(int articleno) {
		return boardMapper.getReply(articleno);
	}

	@Override
	public void newReply(Reply r) {
		boardMapper.newReply(r);
		return;
	}
}