package com.ssafy.vue.mapper;

import java.sql.SQLException;

import com.ssafy.vue.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int duplicateCheck(String userid) throws SQLException;
	public void registerMember(MemberDto m) throws SQLException;
	public void dropMember(String userid) throws SQLException;
	public String getPassword(String userid) throws SQLException;
	public String getEmail(String userid) throws SQLException;
	public void updateInfo(MemberDto m) throws SQLException;
	public int mailCheck(String userid, String email) throws SQLException;
}