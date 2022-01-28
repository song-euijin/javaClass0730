package com.ht.ott.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ht.ott.dto.C_dto;
import com.ht.ott.dto.MarkDTO;
import com.ht.ott.service.C_service;

@Controller
public class C_controller {
	@Autowired
	private C_service csvc;

	private ModelAndView mav = new ModelAndView();

	// 기본 : 인덱스로
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView cList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "filter", required = false, defaultValue = "1") int filter) {
		mav = csvc.cList(page, filter);
		return mav;
	}

	// 인덱스로
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "filter", required = false, defaultValue = "1") int filter) {
		mav = csvc.cList(page, filter);
		return mav;
	}

	// C_search : 제목 검색
	@RequestMapping(value = "C_search", method = RequestMethod.GET)
	public ModelAndView bSearch(@RequestParam("keyword") String keyword) {
		System.out.println("keyword : " + keyword);

		mav = csvc.cSearch(keyword);
		return mav;
	}
	
	// C_addForm : 콘텐츠 추가 페이지 이동
	@RequestMapping(value = "/C_addForm", method = RequestMethod.GET)
	public String cAddForm() {
		return "C_Add";
	}
	

	// DoubleCheck : 제목 + 개봉일 중복체크
	@RequestMapping(value = "/DoubleCheck",method = RequestMethod.POST)
	public @ResponseBody String DoubleCheck(@RequestParam(value = "Title") String Title,@RequestParam(value = "CODate") String CODate) {
		C_dto content = new C_dto();
		content.setCName(Title);
		content.setCDate(CODate);
		String result = csvc.DoubleCheck(content);
		return result;
		
	}
	
	// C_add : 콘텐츠 추가
	@RequestMapping(value = "/C_add", method = RequestMethod.POST)
	public ModelAndView cAdd(@ModelAttribute C_dto content) throws IllegalStateException, IOException {
		mav = csvc.cAdd(content);
		return mav;
	}
	
	// C_view : 콘텐츠 정보
	@RequestMapping(value = "/C_view", method = RequestMethod.GET)
	public ModelAndView cView(@RequestParam("CNum") int CNum) {
		mav = csvc.cView(CNum);
		return mav;
	}
	
	// C_modiForm : 콘텐츠 수정 페이지 이동
	@RequestMapping(value = "/C_modiForm", method = RequestMethod.GET)
	public ModelAndView cModiForm(@RequestParam("CNum") int CNum) {
		mav = csvc.cModiForm(CNum);
		return mav;
	}
	
	// C_modify : 콘텐츠 수정
	@RequestMapping(value = "/C_modify", method = RequestMethod.POST)
	public ModelAndView cModify(@ModelAttribute C_dto content) throws IllegalStateException, IOException {
		mav = csvc.cModify(content);
		return mav;
	}
		
	// C_delete: 콘텐츠 삭제
	@RequestMapping(value = "/C_delete", method = RequestMethod.GET)
	public ModelAndView cDelete(@RequestParam("CNum") int CNum) {
		mav = csvc.cDelete(CNum);
		return mav;
	}

	// C_like : 좋아요
	@RequestMapping(value = "/C_like",method = RequestMethod.POST)
	public @ResponseBody int cLike(@ModelAttribute MarkDTO mark) {
		int result = csvc.cLike(mark);
		return result;
		
	}
	
	// C_dislike : 싫어요
	@RequestMapping(value = "/C_dislike",method = RequestMethod.POST)
	public @ResponseBody int cDisLike(@ModelAttribute MarkDTO mark) {
		int result = csvc.cDisLike(mark);
		return result;
		
	}
	
	
	
}



