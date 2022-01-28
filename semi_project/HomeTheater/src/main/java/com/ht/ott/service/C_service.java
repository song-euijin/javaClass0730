package com.ht.ott.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ht.ott.dao.C_dao;

import com.ht.ott.dto.C_dto;
import com.ht.ott.dto.MarkDTO;
import com.ht.ott.dto.Page;


@Service
public class C_service {
	@Autowired
	private C_dao dao;

	
	private ModelAndView mav = new ModelAndView();
	
	private static final int PAGE_LIMIT = 8; // 한 페이지당 글 개수
	private static final int BLOCK_LIMIT = 10; // 한 페이지당 페이지 개수

	// 글목록
	public ModelAndView cList(int page, int filter) {
		Page paging = new Page();
		
		int listCount = dao.cListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		// Math.ceil() : 올림
		// Math.floor() : 내림
		// Math.round() : 반올림
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setPage(page);
		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		List<C_dto> contentList = dao.cList(paging, filter);
		List<C_dto> contentslider = dao.cSlider(filter);


		
		mav.addObject("paging", paging);
		mav.addObject("contentList", contentList);
		mav.addObject("contentslider", contentslider);

		mav.setViewName("index");
		String category = "인기순";
		if(filter == 2) {
			category = "이름순";
		} else if(filter == 3) {
			category = "최신 개봉순";
		}
		mav.addObject("filter", filter);
		mav.addObject("category", category);
		return mav;
	}
	
	// 검색
	public ModelAndView cSearch(String keyword) {
		List<C_dto> sList = dao.cSearch(keyword); 

		mav.addObject("sList", sList);
		mav.addObject("keyword", keyword);
		mav.setViewName("CS_List");
		
		return mav;
	}
	public String DoubleCheck(C_dto cdto) {
		String DCheck = dao.DoubleCheck(cdto);
		String result = null;
		if(DCheck==null) {
			result = "OK";
		} else {
			result = "NO";
		}
		return result;
	}

	public ModelAndView cAdd(C_dto content) throws IllegalStateException, IOException{
		MultipartFile CFile= content.getCFile();
		String CPoster = CFile.getOriginalFilename();
		
		// 파일 저장 경로 설정 : 마지막에 '/' 꼭 써주기
		String savePath = "C:/Users/djand/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/HomeTheater/src/main/webapp/resources/CPoster/" + CPoster;
		
		if(!CPoster.isEmpty()) {
			content.setCPoster(CPoster);
			CFile.transferTo(new File(savePath));
		}
		
		int result = dao.cAdd(content);
        
		if(result > 0) {
			mav.setViewName("redirect:/index");
		} else {
			mav.setViewName("redirect:/index");
		}
		
		return mav;
	}

	public ModelAndView cView(int CNum) {
		C_dto content = dao.cView(CNum);
		dao.cHit(CNum);
		MarkDTO mark = new MarkDTO();
		mark.setKNUM(CNum);
		int like = dao.kLike(mark);
		int dislike = dao.kDisLike(mark);
		mav.addObject("CView",content);
		mav.addObject("like",like);
		mav.addObject("dislike",dislike);
		mav.setViewName("C_View");
		return mav;
	}

	public ModelAndView cModiForm(int CNum) {
		C_dto content = dao.cView(CNum);
		mav.addObject("CModi",content);
		mav.setViewName("C_Modify");
		return mav;
	}

	public ModelAndView cModify(C_dto content) throws IllegalStateException, IOException {
		MultipartFile CFile= content.getCFile();
		String CPoster = CFile.getOriginalFilename();
		
		// 파일 저장 경로 설정 : 마지막에 '/' 꼭 써주기
		String savePath = "C:/Users/djand/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/HomeTheater/src/main/webapp/resources/CPoster/" + CPoster;
		
		if(!CPoster.isEmpty()) {
			content.setCPoster(CPoster);
			CFile.transferTo(new File(savePath));
		}
		
		int result = dao.cModify(content);
        
		if(result > 0) {
			mav.setViewName("redirect:/index");
		} else {
			mav.setViewName("index");
		}
		
		return mav;
	}

	public int cLike(MarkDTO mark) {
		dao.kAdd(mark);
		int SearchLike = dao.kSearch(mark);
		int result = 0;
		if(SearchLike==-1) {
			dao.kDelete(mark);
			dao.kLikeAdd(mark);
		} else if (SearchLike==1) {
			dao.kDelete(mark);
		} else {
			dao.kLikeAdd(mark);
		}
		result = dao.kLike(mark);
		return result;
	}

	public int cDisLike(MarkDTO mark) {
		dao.kAdd(mark);
		int SearchLike = dao.kSearch(mark);
		int result = 0;
		if(SearchLike==1) {
			dao.kDelete(mark);
			dao.kDisLikeAdd(mark);
		} else if (SearchLike==-1) {
			dao.kDelete(mark);
		} else {
			dao.kDisLikeAdd(mark);
		}
		result = dao.kDisLike(mark);
		return result;
	}

	public ModelAndView cDelete(int CNum) {
		int result = dao.cDelete(CNum);
		System.out.println(result);
		int result2 = dao.cDelete2(CNum);
		System.out.println(result2);

		if(result2>0) {
			mav.setViewName("redirect:/index");
		} else {
			mav.setViewName("redirect:/cView?CNum="+CNum);
		}
		return mav;
	}
}
