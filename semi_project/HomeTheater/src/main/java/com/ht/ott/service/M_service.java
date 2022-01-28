package com.ht.ott.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ht.ott.dao.M_dao;
import com.ht.ott.dto.M_dto;
import com.ht.ott.dto.Page;

@Service
public class M_service {
	
	@Autowired
	private M_dao dao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BCryptPasswordEncoder pwEnc;

	private ModelAndView mav = new ModelAndView();

	private static final int PAGE_LIMIT = 5; // 한 페이지당 글 개수
	private static final int BLOCK_LIMIT = 5; // 한 페이지당 페이지 개수
	
	public ModelAndView mList(int page) {


		Page paging = new Page();

		int listCount = dao.mListCount();


		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;

		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setPage(page);
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		////////////////////////////////////////////////////////////////////////////

		List<M_dto> memberList = dao.mList(paging);


		mav.addObject("memberList", memberList);
		mav.addObject("paging", paging);
		mav.setViewName("M_List");

		return mav;
	}


	
	// 회원가입 메소드
	public ModelAndView mJoin(M_dto member) {
		
		
		member.setMPw(pwEnc.encode(member.getMPw()));
		
		int result = dao.mJoin(member);
		
		if (result>0) {
			mav.setViewName("M_Login");
		} else {
			mav.setViewName("index");
		}
		
		return mav;
	}

	public ModelAndView mLogin(M_dto member) {
		// [1] 입력한 ID로 암호화 된 password를 검색하고
		M_dto encPw = dao.mView(member.getMId());
		
		// [2] db에 저장된 password와 입력한 password를 match해서 일치여부 확인  
		if(pwEnc.matches(member.getMPw(), encPw.getMPw())) {
			session.setAttribute("loginId", encPw.getMId());
			mav.setViewName("redirect:/index");
		} else {
			mav.setViewName("M_Login");
		}
		
		return mav;
	}

	public ModelAndView mView(String mId) {
		M_dto member = dao.mView(mId);
		mav.addObject("view", member);
		mav.setViewName("M_View");
		
		
		return mav;
	}

	public ModelAndView mModiForm(String mId) {
		M_dto member = dao.mView(mId);
		mav.addObject("modify", member);
		mav.setViewName("M_Modify");
		
		return mav;
	}
	

	public ModelAndView mModify(M_dto member) {
		
		member.setMPw(pwEnc.encode(member.getMPw()));
		
		int result = dao.mModify(member);
		
		if(result>0) {
			mav.setViewName("M_Login");
			
		} else {
			mav.setViewName("index");
		}
		
		return mav;
	}
	
	public ModelAndView mDelete(String mId) {
		int result = dao.mDelete(mId);
		
		if(result>0) {
			mav.setViewName("redirect:/M_list");
		} else {
			mav.setViewName("index");
		}
		return mav;
	}

	public String idOverlap(String mId) {
		String idCheck = dao.idOverlap(mId);
		String result = null;
		
		if(idCheck==null) {
			result = "OK";
		} else {
			result = "NO";
		}
		
		return result;
	}

	public String phoneOverlap(String mPhone) {
		String phoneCheck = dao.phoneOverlap(mPhone);
		String result = null;
		
		if(phoneCheck==null) {
			result = "OK";
		} else {
			result = "NO";
		}
		return result;
	}
	
	
	
}
