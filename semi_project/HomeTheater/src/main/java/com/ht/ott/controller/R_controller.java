package com.ht.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.ott.dto.ReviewDTO;
import com.ht.ott.service.R_service;

@Controller
public class R_controller {
		
	@Autowired
	private R_service rscv;
	
	// 댓글 불러오기
	@RequestMapping(value = "/R_list", method = RequestMethod.POST)
	public @ResponseBody List<ReviewDTO> cList(@RequestParam(value = "RCNUM") int RCNUM) {	
		List<ReviewDTO> rList = rscv.rList(RCNUM);
		
		return rList;
	}
	
	// 댓글 작성
	@RequestMapping(value = "/R_write", method = RequestMethod.POST)
	public @ResponseBody List<ReviewDTO> cWrite(@ModelAttribute ReviewDTO review) {	
		List<ReviewDTO> reviewList = rscv.rWrite(review);
		return reviewList;
	}
	
	
}
