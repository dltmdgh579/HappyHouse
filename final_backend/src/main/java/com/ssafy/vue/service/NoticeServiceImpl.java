package com.ssafy.vue.service;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Reply;
import com.ssafy.vue.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
	
    @Autowired
	private NoticeMapper noticeMapper;

    @Override
	public List<Board> retrieveBoard() {
		return noticeMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return noticeMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		updateHit(articleno);
		return noticeMapper.selectBoardByNo(articleno);
	}
	
	@Override
	public void updateHit(int articleno) {
		noticeMapper.updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return noticeMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return noticeMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public List<Board> searchById(String userid) {
		return noticeMapper.searchById(userid);
	}

	@Override
	public List<Board> searchBySubject(String subject) {
		return noticeMapper.searchBySubject(subject);
	}

	@Override
	public List<Board> searchByContent(String content) {
		return noticeMapper.searchByContent(content);
	}

	@Override
	public List<Reply> getReply(int articleno) {
		return noticeMapper.getReply(articleno);
	}

	@Override
	public void newReply(Reply r) {
		noticeMapper.newReply(r);
		return;
	}
}