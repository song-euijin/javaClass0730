package com.ht.ott.dto;

import lombok.Data;

@Data
public class Page {
	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;
}
