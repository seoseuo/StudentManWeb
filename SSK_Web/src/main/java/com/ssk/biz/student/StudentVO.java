package com.ssk.biz.student;

import lombok.Data;

@Data
public class StudentVO {

	private int num; // 학번 
	private String name; // 이름 
	private String major; // 전공 
	private String phone; // 전화번호 
	private String adminId; // 해당 학생의 관리자 아이
}
