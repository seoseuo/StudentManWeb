package com.ssk.biz.admin;

import lombok.Data;

@Data
public class AdminVO {

	private String id; // 관리자 이아디 
	private String password; // 관리자 비밀번호 
	private String name; // 관리자 이름 
	private int count; //관리자가 등록 할 학생 수
}
