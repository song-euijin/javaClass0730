package com.ht.ott.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ht.ott.dto.C_dto;
import com.ht.ott.dto.MarkDTO;
import com.ht.ott.dto.Page;

@Repository
public class C_dao {
	@Autowired
	private SqlSessionTemplate sql;

	public int cListCount() {
		return sql.selectOne("Content.cListCount");
	}

	public List<C_dto> cList(Page paging, int filter) {
		if(filter == 2) {
			return sql.selectList("Content.cnameList", paging);
		}else if(filter == 3){
			return sql.selectList("Content.crecentList", paging);
		} else {
			return sql.selectList("Content.cpopularList", paging);
		}
	}

	public List<C_dto> cSearch(String keyword) {
		return sql.selectList("Content.cSearch", keyword);
	}

	public List<C_dto> cSlider(int filter) {
		if(filter == 2) {
			return sql.selectList("Content.cnameSlider");
		}else if(filter == 3){
			return sql.selectList("Content.crecentSlider");
		} else {
			return sql.selectList("Content.cpopularSlider");
		}
	}
	public String DoubleCheck(C_dto cdto) {
		return sql.selectOne("Content.DoubleCheck",cdto);
	}

	public int cAdd(C_dto content) {
		return sql.insert("Content.cAdd",content);
	}

	public C_dto cView(int CNum) {
		return sql.selectOne("Content.cView",CNum);
	}
	
	public int cDelete(int CNum) {
		return sql.delete("Content.cDelete",CNum);
	}
	
	public int cDelete2(int CNum) {
		return sql.delete("Content.cDelete2",CNum);
	}

	public int cModify(C_dto content) {
		return sql.update("Content.cModify",content);
	}

	public int cHit(int CNum) {
		return sql.update("Content.cHit",CNum);
	}

	public int kLike(MarkDTO mark) {
		return sql.selectOne("Content.kLike",mark);
	}

	public int kDisLike(MarkDTO mark) {
		return sql.selectOne("Content.kDisLike",mark);
	}

	public int kSearch(MarkDTO mark) {
		System.out.println("dao : " + mark);
		int result = sql.selectOne("Content.kSearch",mark);
		System.out.println("result : " + result);
		return result;
	}

	public int kLikeAdd(MarkDTO mark) {
		return sql.insert("Content.kLikeAdd",mark);
	}

	public int kDelete(MarkDTO mark) {
		return sql.delete("Content.kDelete",mark);
	}

	public int kDisLikeAdd(MarkDTO mark) {
		return sql.insert("Content.kDisLikeAdd",mark);
	}

	public int kAdd(MarkDTO mark) {
		return sql.insert("Content.kAdd",mark);
	}
}
