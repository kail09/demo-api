package com.kail.demoApi.v1.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kail.demoApi.v1.member.model.MemberModel;
import com.kail.demoApi.v1.member.service.MemberService;


@RestController
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("test")
	public String test() {
		
		memberService.test();
		
		return "chk";
		
	}
	
	@PostMapping("login")
	public String login(@ModelAttribute MemberModel memberModel, HttpSession session) {
		String result = "";
		
		try {
			String userName = memberService.loginUserInfo(memberModel, session);
			if (userName != "") {
				result = "forward:/index_Admin";
				result = userName;
			} else {
				result = "not select";
			}
		} catch (Exception e) {
			result = "failure";
		};
		
		return result;
	}
	
	@GetMapping("sqlTest")
	public String mysqlTest() {
		
		memberService.mysqlTest();
		
		return "sqlTest";
		
	}

}
