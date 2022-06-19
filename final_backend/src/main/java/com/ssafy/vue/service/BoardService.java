package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Reply;

public interface BoardService {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public void updateHit(int articleno);
	public boolean writeBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean deleteBoard(int articleno);
	public List<Board> searchById(String userid);
	public List<Board> searchBySubject(String subject);
	public List<Board> searchByContent(String content);
	public List<Reply> getReply(int articleno);
	public void newReply(Reply r);
}
