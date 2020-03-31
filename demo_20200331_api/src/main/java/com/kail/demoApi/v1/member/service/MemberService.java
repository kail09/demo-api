package com.kail.demoApi.v1.member.service;

import javax.servlet.http.HttpSession;

import com.kail.demoApi.v1.member.model.MemberModel;

public interface MemberService {
	
	public void test();

	public void mysqlTest();

	void insertUserInfo(MemberModel memberModel) throws Exception;

	String loginUserInfo(MemberModel memberModel, HttpSession session) throws Exception;

	String loginUserInfo(MemberModel memberModel);

}
