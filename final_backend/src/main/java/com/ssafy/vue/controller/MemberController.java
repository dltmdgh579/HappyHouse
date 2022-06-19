package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/info")
	public HttpStatus updateInfo(@RequestBody MemberDto m){
		HttpStatus httpstatus = null;

		try{
			memberService.updateInfo(m);
			httpstatus=HttpStatus.OK;
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("회원정보수정 오류 발생");
			httpstatus=HttpStatus.BAD_REQUEST;
		}
		return httpstatus;
	}

	@GetMapping("/register/{userid}")
	public int DuplicateCheck(@PathVariable String userid) {
		int cnt = 0;
		try {
			cnt = memberService.duplicateCheck(userid);
		} catch (Exception e) {
			logger.error("아이디중복체크 오류");
		}
		System.out.println(cnt);
		return cnt;
	}

	@PostMapping("/register")
	public void registerMember(@RequestBody MemberDto m){
		try{
			memberService.registerMember(m);
		}catch (Exception e) {
			System.out.println("회원가입 오류");
			e.printStackTrace();
		}
		return;
	}

	@DeleteMapping("/{userid}")
	public void dropMember(@PathVariable String userid){
		System.out.println(userid);
		try {
			memberService.dropMember(userid);
		} catch (Exception e) {
			System.out.println("탈퇴 예외 발생");
		}
	}
	@GetMapping("/findpwd/{userid}")
	public void findPwd(@PathVariable String userid){
		try {
			memberService.Findpwd(userid);
		} catch (Exception e) {
			System.out.println("비밀번호찾기 오류");
			e.printStackTrace();
		}
	}

	@GetMapping("/mailcheck/{userid}/{email}")
	public int mailCheck(@PathVariable String userid, @PathVariable String email) throws Exception {
		return memberService.mailCheck(userid, email);
	}
}
