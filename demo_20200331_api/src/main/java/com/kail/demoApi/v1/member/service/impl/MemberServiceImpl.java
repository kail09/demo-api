package com.kail.demoApi.v1.member.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.kail.demoApi.v1.member.dao.MemberDao;
import com.kail.demoApi.v1.member.model.MemberModel;
import com.kail.demoApi.v1.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void test() {
		MemberModel memberModel = new MemberModel();
		memberModel.setNum(2);
		System.out.println("run chk ");
		String a = memberDao.test("2");
		System.out.println("mapper ok"+a);
		log.debug("chk debug");
		log.info("chk info ");
		log.info(messageSource.getMessage("debug.test", null, LocaleContextHolder.getLocale()));
	}

	@Override
	public void insertUserInfo(MemberModel memberModel) throws Exception {
		
		// 기존 userId 조회
		
		long cntUserId = memberDao.countUserId(memberModel.getUserId());
		if (cntUserId > 0) {
			log.error("[ERROR-100] Exception : "+ "Duplicate userId");
			throw new Exception();
		}
		
		try {
			memberDao.insertUserInfo(memberModel);
		} catch (Exception e) {
			log.error("[ERROR-101] Exception : " + e.getMessage());
		}
	}
	
	@Override
	public String loginUserInfo(MemberModel memberModel, HttpSession session) throws Exception {
		
		// 기존 userId 조회
		try {
			String userName = memberDao.loginUserInfo(memberModel);
			session.setAttribute("userId", memberModel.getUserId());
			session.setAttribute("userName", userName);
			memberModel.setUserName(userName);
		} catch (Exception e) {
			log.error("[ERROR-200] Exception : " + e.getMessage());
		}
		return memberModel.getUserName();
	}
	
	@Override
	public void mysqlTest() {
		
		Connection conn = null;

		try{
			// 1) 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2) 연결하기
			String url = "jdbc:mysql://localhost/demoDb";		

			conn = DriverManager.getConnection(url, "root", "qwe123!@#");
			System.out.println("연결 성공");

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
	}
	
	@Override
	public String loginUserInfo(MemberModel memberModel) {
		String userName = memberDao.loginUserInfo(memberModel);
		System.out.println("chk memberserviceimpl data :  "+ memberModel.getUserId());
		System.out.println("chk memberserviceimpl data userNm :  "+ userName);
		log.debug("data : " + memberModel.getUserId() ); 
		return userName;
	}
}
