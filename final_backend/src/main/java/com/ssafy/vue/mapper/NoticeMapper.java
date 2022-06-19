package com.ssafy.vue.mapper;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
	public List<Board> selectBoard();
	public Board selectBoardByNo(int articleno);
	public void updateHit(int articleno);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int articleno);
	public List<Board> searchById(String userid);
	public List<Board> searchBySubject(String subject);
	public List<Board> searchByContent(String content);
	public List<Reply> getReply(int articleno);
	public void newReply(Reply r);
}