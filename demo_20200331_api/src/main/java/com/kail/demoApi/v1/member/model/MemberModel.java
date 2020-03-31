package com.kail.demoApi.v1.member.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("MemberModel")
@Data
public class MemberModel {
	int num;
	String phone;
	
	// member test
	private String userId;
	
	private String userPw;
	
	private String userName;
	
	private String userEmail;
	
	private String userJoinDate;
	
	private String userLoginDate;
	
	private String userSignature;
	
	private String userImg;
	
	private int userPoint;
	
}
