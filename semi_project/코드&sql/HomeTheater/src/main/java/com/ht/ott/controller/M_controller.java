package com.ht.ott.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ht.ott.dto.M_dto;
import com.ht.ott.service.M_service;

@Controller
public class M_controller {

	@Autowired
	private M_service msvc;

	@Autowired
	private HttpSession session;

	@Autowired
	JavaMailSender mailsender;
	
	private ModelAndView mav = new ModelAndView();

	// M_loginForm : 로그인 페이지 이동
	@RequestMapping(value = "/M_loginForm", method = RequestMethod.GET)
	public String loginform() {
		return "M_Login";
	}

	// M_joinForm : 회원가입 페이지로 이동
	@RequestMapping(value = "/M_joinForm", method = RequestMethod.GET)
	public String JoinForm() {
		return "M_Join";
	}

	// MJoin : 회원가입
	@RequestMapping (value="/M_join", method = RequestMethod.POST)
	public ModelAndView  mJoin(@ModelAttribute M_dto member) throws IllegalStateException, IOException {
		mav = msvc.mJoin(member);
	return mav;
	}
	
	// MLogin : 로그인
	@RequestMapping (value="/M_login", method = RequestMethod.POST)
	public ModelAndView  mLogin(@ModelAttribute M_dto member) {
		
		mav =msvc.mLogin(member);
		
		return mav;
	}
	
	// MLogout : 로그아웃
	@RequestMapping (value="/M_logout", method = RequestMethod.GET)
	public String mLogout() {
		session.invalidate();
		return "index";
	}
	
	// MList : 회원목록
	@RequestMapping (value="/M_list", method = RequestMethod.GET)
	public ModelAndView mList(@RequestParam(value="page", required= false, defaultValue="1") int page) {
		if(page<=0) {
			page = 1;
		}

		mav = msvc.mList(page);

		return mav;
	}
	
	// MView : 회원정보보기
	@RequestMapping (value="/M_view", method = RequestMethod.GET)
	public ModelAndView  mView(@RequestParam("MId") String mId) {
		
		mav =msvc.mView(mId);
		
		return mav;
	}
	
	// MmodiForm : 회원수정 페이지 이동
	@RequestMapping (value="/M_modiForm", method = RequestMethod.GET)
	public ModelAndView  mModiForm(@RequestParam("MId") String mId) {
		
		mav =msvc.mModiForm(mId);
		
		return mav;
	}
	
	// MModify : 회원수정
	@RequestMapping (value="M_modify", method = RequestMethod.POST)
	public ModelAndView  mModify(@ModelAttribute M_dto member) throws IllegalStateException, IOException {
	
		mav = msvc.mModify(member);
		
		return mav;
	}
	
	// MDelete : 회원삭제
	@RequestMapping (value="/M_delete", method = RequestMethod.GET)
	public ModelAndView  mDelete(@RequestParam("MId") String mId) {
		mav = msvc.mDelete(mId);
	return mav;
	}
	
	
	// A_idoverlap : 아이디 중복 검사
	@RequestMapping (value="/A_idoverlap", method = RequestMethod.POST)
	public @ResponseBody  String idOverlap(@RequestParam("MId") String mId) {
	String result = msvc.idOverlap(mId);
	
	return result;
	
	}
	
	// A_emailConfirm : 이메일 인증번호 발송
	@RequestMapping(value = "/A_emailConfirm", method = RequestMethod.GET)
	public @ResponseBody String emailConfirm(@RequestParam("MEmail") String MEmail) {
		
		String emailKey = emailKey(false, 8);
		
		MimeMessage mail = mailsender.createMimeMessage();
		
		String massage = "<h2>안녕하세요. 인천일보 아카데미 입니다</h2>"
						+ "<br/><p>인증번호는 [" + emailKey + "] 입니다.</p>"
						+ "<p>인증번호를 인증번호 입력란에 입력해주세요</p>"
						+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다.)";
		
		try {
			mail.setSubject("[본인인증] 인천일보 아카데미 인증메일입니다.", "utf-8");
			mail.setText(massage, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(MEmail));
			mailsender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return emailKey;
		
	}
	
		// 난수를 이용한 키 생성
		private boolean lowerCheck;
		private int size;
		
		public String emailKey(boolean lowerCheck, int size) {
			this.lowerCheck = lowerCheck;
			this.size = size;
			
			return init();
		}
		
		// 난수를 만드는 메소드
		private String init() {
			Random ran = new Random();
			StringBuffer sb = new StringBuffer();
			
			int num = 0;
			
			do {
				num = ran.nextInt(75) + 48;
				
				if((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
					sb.append((char)num);
				} else {
					continue;
				} 
					
			} while(sb.length()< size);
			
			if(lowerCheck) {
				return sb.toString().toLowerCase();
			}
			
			
			
			return sb.toString();
		}
		
	
	// 연락처 중복검사
	@RequestMapping (value="/A_phoneoverlap", method = RequestMethod.POST)
	public @ResponseBody  String phoneOverlap(@RequestParam("MPhone") String mPhone) {
	String result = msvc.phoneOverlap(mPhone);			
	return result;
		
	}	

}