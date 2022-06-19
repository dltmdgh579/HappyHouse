package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public int duplicateCheck(String userid) throws Exception;
	public void registerMember(MemberDto m) throws Exception;
	public void dropMember(String userid) throws Exception;
	public String getPassword(String userid) throws Exception;
	public String getEmail(String userid) throws Exception;
	public void Findpwd(String userid) throws Exception;
	public void updateInfo(MemberDto m) throws Exception;
	public int mailCheck(String userid, String email) throws Exception;
}
