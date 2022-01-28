package com.ht.ott.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ht.ott.dto.B_boardwriteDTO;
import com.ht.ott.service.B_service;

@Controller
public class B_controller {
	
	@Autowired
	private B_service bsvc;

	private ModelAndView mav = new ModelAndView();

	@Autowired
	HttpSession session;

	// B_writeForm : 글 작성 페이지 이동
	@RequestMapping(value = "B_writeForm", method = RequestMethod.GET)
	public String write() {
		return "B_Write"; // WEB-INF/views/B_Write.jsp
	}

	// B_write : 글 작성
	@RequestMapping(value = "B_write", method = RequestMethod.POST)
	public ModelAndView bWrite(@ModelAttribute B_boardwriteDTO board) throws IllegalStateException, IOException {
		System.out.println("[1]controller : board -> " + board);

		mav = bsvc.bWrite(board);
		System.out.println("[5]controller : mav -> " + mav);

		return mav;
	}

	// B_board : 글 리스트 
	@RequestMapping(value = "B_board", method = RequestMethod.GET) // 페이지 기본 = [1]
	public ModelAndView bList(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		// 혹시나의 오류 잡기 - page가 1보다 작은 경우
		if (page <= 0) {
			page = 1;
		}
		// 실행 : B_service.bList(page)
		// 결과 : mav
		mav = bsvc.bList(page);
		return mav;
	}

	// B_search : 게시글 검색
	@RequestMapping(value = "B_search", method = RequestMethod.GET)
	public ModelAndView bSearch(@RequestParam("selectVal") String selectVal, @RequestParam("keyword") String keyword) {
		System.out.println("selectVal : " + selectVal);
		System.out.println("keyword : " + keyword);
		
		mav = bsvc.bSearch(selectVal, keyword);
		
		return mav;
	}
	
	// B_view : 글 상세보기
	@RequestMapping(value = "B_view", method = RequestMethod.GET)
	public ModelAndView bView(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("BNO") int BNO) {
		mav = bsvc.bView(page, BNO);
		return mav;
	}

	// B_modiForm : 게시글 수정페이지 이동
	@RequestMapping(value = "B_modiForm", method = RequestMethod.GET)
	public ModelAndView bModiForm(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("BNO") int BNO) {
		
		mav = bsvc.bModiForm(page, BNO);
		return mav;
	}

	// B_modify : 게시글 수정
	@RequestMapping(value = "B_modify", method = RequestMethod.POST)
	public ModelAndView bModify(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@ModelAttribute B_boardwriteDTO board) throws IllegalStateException, IOException {
		mav = bsvc.bModify(page, board);
		return mav;
	}

	// B_delete : 게시글 삭제
	@RequestMapping(value = "B_delete", method = RequestMethod.GET)
	public ModelAndView bDelete(@RequestParam(value="page", required=false, defaultValue="1") int page, @RequestParam("BNO") int BNO) {
		mav = bsvc.bDelete(page,BNO);
		return mav;
	}
	


}

	
