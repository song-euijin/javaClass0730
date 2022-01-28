package com.ht.ott.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ht.ott.dao.B_dao;
import com.ht.ott.dto.B_boardwriteDTO;
import com.ht.ott.dto.Page;

@Service
public class B_service {

	@Autowired
	private B_dao dao;

	private ModelAndView mav = new ModelAndView();

	private static final int PAGE_LIMIT = 10; 
	private static final int BLOCK_LIMIT = 5; 

	// bWrite : 글쓰기 
	public ModelAndView bWrite(B_boardwriteDTO board) throws IllegalStateException, IOException {
		System.out.println("[2]service : board -> " + board);

		// 파일 이름 불러오기
		MultipartFile bFile = board.getBFILE();
		String bFileName = bFile.getOriginalFilename();

		if (bFile.isEmpty()) {
			board.setBFILENAME("NULL");
		} else {
			// 파일 저장경로 설정하기 : [1]마지막에 '/' 꼭 써주기! [2] +bFileName 해주기!

			String savePath = "C:/Users/djand/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/HomeTheater/src/main/webapp/resources/board/"
					+ bFileName;

			board.setBFILENAME(bFileName);
			bFile.transferTo(new File(savePath));
		}

		int result = dao.bWrite(board);
		
		System.out.println("[4]service : board -> " + board);

		if (result > 0) {
			mav.setViewName("redirect:/B_board");
		} else {
			mav.setViewName("index");
		}

		return mav;
	}

	// bList : 글 리스트
	public ModelAndView bList(int page) {
		Page paging = new Page();

		// 실행 : dao.bListCount()
		// 결과 : listCount
		// 게시글에 작성된 글 수
		int listCount = dao.bListCount();
		System.out.println(listCount);
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;

		// Math.ceil() : 올림
		// Math.floor() : 버림
		// Math.round() : 반올림
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

		// 실행 : dao.bList(paging)
		// 결과 : boardList
		// 등록된 게시물 내용 모두 불러오기
		List<B_boardwriteDTO> boardList = dao.bList(paging);
				
		// boardList와 paging의 데이터를 B_board.jsp로 넘진다.
		mav.addObject("boardList", boardList);
		mav.addObject("paging", paging);
		mav.setViewName("B_Board");

		return mav;
	}

	// bSearch : 글 검색
	public ModelAndView bSearch(String selectVal, String keyword) {
		List<B_boardwriteDTO> sList = dao.bSearch(selectVal, keyword);
		
		mav.addObject("sList", sList);
		mav.setViewName("B_Search");
		
		return mav;
	}
	
	// bView : 글 상세
	public ModelAndView bView(int page, int BNO) {
		B_boardwriteDTO board = dao.bView(BNO);

		mav.addObject("view", board);
		mav.addObject("page", page);
		mav.setViewName("B_View");

		return mav;
	}

	// bModiForm : 글 수정 (불러오기)
	public ModelAndView bModiForm(int page, int BNO) {

		B_boardwriteDTO board = dao.bModiForm(BNO);

		mav.addObject("modify", board);
		mav.addObject("page", page);
		mav.setViewName("B_Modify");

		return mav;
	}

	// bModify : 글 수정 (등록하기)
	public ModelAndView bModify(int page, B_boardwriteDTO board) throws IllegalStateException, IOException {

		// 파일 이름 불러오기
		MultipartFile bFile = board.getBFILE();
		String bFileName = bFile.getOriginalFilename();
		System.out.println("2. " + bFile + " 2. " + bFileName);
		// 파일 저장경로 설정하기 : [1]마지막에 '/' 꼭 써주기! [2] +bFileName 해주기!
		String savePath = "C:/Users/djand/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/HomeTheater/src/main/webapp/resources/board/"
				+ bFileName;
		System.out.println("3. " + bFile + " 3. " + bFileName);

		// 업로드 한 파일이 있을 경우
		
		if (!bFileName.isEmpty()) {
			board.setBFILENAME(bFileName);
			bFile.transferTo(new File(savePath));
		}else {
			board.setBFILENAME("NULL");

		}
		System.out.println("4. " + bFile + " 4. " + bFileName);

		// bFileName을 board객체에 추가하기
		int result = dao.bModify(board);

		if (result > 0) {
			mav.setViewName("redirect:/B_view?BNO=" + board.getBNO());
		} else {
			mav.setViewName("redirect:/B_board");
		}
		System.out.println("5. " + bFile + " 5. " + bFileName);

		return mav;
	}

	// bDelete : 글 삭제
	public ModelAndView bDelete(int page, int BNO) {
		int result = dao.bDelete(BNO);

		if (result > 0) {
			mav.setViewName("redirect:/B_board");
		} else {
			mav.setViewName("B_Board");
		}

		return mav;
	}


}
