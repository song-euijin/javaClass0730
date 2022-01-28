package com.ht.ott.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ht.ott.dto.M_dto;
import com.ht.ott.dto.Page;

@Repository
public class M_dao {

	@Autowired
	private SqlSessionTemplate sql;

	public int mJoin(M_dto member) {
		
		return sql.insert("Member.mJoin", member);
	}
	
	public M_dto mLogin(M_dto member) {
		return sql.selectOne("Member.mLogin", member);
	}

	public int mListCount() {
		return sql.selectOne("Member.mListCount");
	}
	
	public List<M_dto> mList(Page paging) {
		return sql.selectList("Member.mList",paging);
	}

	public M_dto mView(String mId) {
		
		return sql.selectOne("Member.mView", mId);
	}

	public int mModify(M_dto member) {
		return sql.update("Member.mModify", member);
	}

	public int mDelete(String mId) {
		return sql.delete("Member.mDelete", mId);
	}

	public String idOverlap(String mId) {
		return sql.selectOne("Member.idOverlap", mId);
	}

	public String phoneOverlap(String mPhone) {
		return sql.selectOne("Member.phoneOverlap", mPhone);
	}
	
	
}