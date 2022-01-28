package com.ht.ott.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class B_boardwriteDTO {
		private String BNO;
		private String BWRITER;
		private String BTITLE;
		private String BCONTENT;
		private String BFILENAME;
		private MultipartFile BFILE;
		private String BDATE;
		private int BHIT;
}
