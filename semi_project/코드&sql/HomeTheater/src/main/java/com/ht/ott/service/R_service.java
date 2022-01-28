package com.ht.ott.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.ott.dao.R_dao;
import com.ht.ott.dto.ReviewDTO;

@Service
public class R_service {
	
	@Autowired
	private R_dao rdao;

	
	
	List<ReviewDTO> rList = new ArrayList<ReviewDTO>();
	
	public List<ReviewDTO> rList(int RCNUM) {
		rList = rdao.rList(RCNUM);
		return rList;
	}

	public List<ReviewDTO> rWrite(ReviewDTO review) {
		List<ReviewDTO> reviewList = null;
		int result = rdao.rWrite(review);
		
		if(result>0) {
			reviewList = rdao.rList(review.getRCNUM());
		} else {
			reviewList = null;
		}
		return reviewList;
	}


}
