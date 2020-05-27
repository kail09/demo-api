package com.kail.demoApi.v1.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	public MemberModel login(@ModelAttribute MemberModel memberModel, HttpSession session, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		String result = "";
		System.out.println("chk :: param : " + memberModel.getUserId());
		try {
			String token = memberService.loginUserInfo(memberModel, session);
			if (token != "") {
//				modelAndView.setViewName("redirect:ui/index_Admin");
				result = "/ui/index_Admin";
				request.getSession().setAttribute("userId", memberModel.getUserId());
//				session.setAttribute(HttpHeaders.AUTHORIZATION, token);
//				request.getSession().setAttribute(HttpHeaders.AUTHORIZATION, token);
				//result = userName;
			} else {
				result = "not select";
			}
		} catch (Exception e) {
			result = "failure";
		};
		
//		return memberModel;
		return memberModel;
	}
	
	@GetMapping("sqlTest")
	public String mysqlTest() {
		
		memberService.mysqlTest();
		
		return "sqlTest";
		
	}

}
