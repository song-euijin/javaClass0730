package com.ht.ott.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ht.ott.dto.*;

@Repository
public class B_dao {

	@Autowired
	private SqlSessionTemplate sql;

	// bWrite : 글쓰기 
	public int bWrite(B_boardwriteDTO board) {
		System.out.println("[3]dao : board -> " + board);
		return sql.insert("Board.bWrite", board);
	}

	// bListCount : 등록된 글 갯수
	public int bListCount() {
		return sql.selectOne("Board.bListCount");
	}

	// bList : 글 리스트
	public List<B_boardwriteDTO> bList(Page paging) {
		// System.out.println("[3-1]dao : paging -> " + paging);
		return sql.selectList("Board.bList", paging);
	}
	
	// bView : 글 상세
	public B_boardwriteDTO bView(int BNO) {
		sql.update("Board.bHit", BNO);
		return sql.selectOne("Board.bView", BNO);
	}
	
	// bModiForm : 글 수정 (불러오기)
	public B_boardwriteDTO bModiForm(int BNO) {
		return sql.selectOne("Board.bView", BNO);
	}
	
	// bModify : 글 수정 (등록하기)
	public int bModify(B_boardwriteDTO board) {
		return sql.update("Board.bModify", board);
	}
	
	// bDelete : 글 삭제
	public int bDelete(int BNO) {
		return sql.delete("Board.bDelete", BNO);
	}

	// bSearch : 글 검색
	public List<B_boardwriteDTO> bSearch(String selectVal, String keyword) {
		
		if(selectVal.equals("BWRITER")) {
			return sql.selectList("Board.bsWriter", keyword);
		} else if(selectVal.equals("BTITLE")) {
			return sql.selectList("Board.bsTitle", keyword);
		} else {
			return sql.selectList("Board.bsContent", keyword);
		}
	}



}

