package com.ht.ott.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ht.ott.dto.ReviewDTO;

@Repository
public class R_dao {
	@Autowired
	private SqlSessionTemplate sql;

	public List<ReviewDTO> rList(int RCNUM) {
		List<ReviewDTO> selList = sql.selectList("Review.rList",RCNUM);
		return selList; 
	}

	public int rWrite(ReviewDTO review) {
		return sql.insert("Review.rWrite",review);
	}




}
