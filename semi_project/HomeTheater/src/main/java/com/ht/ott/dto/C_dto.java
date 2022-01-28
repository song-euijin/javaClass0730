package com.ht.ott.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class C_dto {
	private int CNum;

	private String CPoster;
	private String CName;
	private String CSummery;
	private String CDate;
	private int CHit;
	private int CComment;
	
	private MultipartFile CFile;
	

}
