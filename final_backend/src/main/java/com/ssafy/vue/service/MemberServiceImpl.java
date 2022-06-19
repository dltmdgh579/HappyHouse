package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.mapper.MemberMapper;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public int duplicateCheck(String userid) throws Exception {
		return memberMapper.duplicateCheck(userid);
	}

	@Override
	public void registerMember(MemberDto m) throws Exception {
		memberMapper.registerMember(m);
		return;
	}
	public void dropMember(String userid) throws  Exception {
		memberMapper.dropMember(userid);
		return;
	}

	@Override
	public String getPassword(String userid) throws Exception {
		return memberMapper.getPassword(userid);
	}

	@Override
	public String getEmail(String userid) throws Exception {
		return memberMapper.getEmail(userid);
	}

	@Override
	public void updateInfo(MemberDto m) throws Exception {
		memberMapper.updateInfo(m);
	}

	@Override
	public int mailCheck(String userid, String email) throws Exception {
		return memberMapper.mailCheck(userid, email);
	}

	@Override
	public void Findpwd(String userid) throws Exception {
		String recipient = getEmail(userid);
		String pwd = getPassword(userid);

		final String user = "happyhouse14ad@gmail.com";
		final String password = "ssafy7!!";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


		Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(user, password);
			}
		});
		MimeMessage message = new MimeMessage(session);
		try{
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(userid+ "님의 비밀번호입니다.");
			message.setText("안녕하세요 HappyHouse입니다.\n\n"+userid+"님의 비밀번호는 " +pwd+" 입니다.");
			Transport.send(message);
		} catch (Exception e) {
			System.out.println("메일발송 에러 발생");
			e.printStackTrace();
		}



	}
}
