<%@page import="membership.MemberDAO"%>
<%@page import="membership.MemberDTO"%>
<%@ page import="management.CookieManager" %>
<%@page import="management.JSFunction" %>
<%@page import="java.util.ArrayList"%>
<%@page import="product.Product"%>
<%@ page import="DB.JDBConnect"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8"); 
	
	String url ="";
	
	if(request.getParameter("index")==null){
		//인덱스 없으면 웰컴페이지로 
		url = "../UI/Welcome.jsp";
	}else{
		//인덱스 받아오면 상품페이지로
		int index = Integer.parseInt(request.getParameter("index"));
		url = "../UI/salePage.jsp?index="+index;
	}
	
	//아이디 비번 받아오기(로그인 페이지에서)
	String id = request.getParameter("id");
	String pw = request.getParameter("pwd");
	String save_check = request.getParameter("save_check");
	
	
	JDBConnect jdbc = new JDBConnect();
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = dao.getMemberDTO(id, pw);
	
	dao.close();
	
	//아이디가 db에 있으면 
	if(dto.getId() != null){
		
		//아이디 저장 체크 했을 대
		if(save_check != null){
			session.setAttribute("loginId", id);
			CookieManager.makeCookie(response, "loginId", id, 86400);
			
			//체크 안했을 때
		}else{
			session.setAttribute("loginId", id);
		}
		//로그인 성공 후 넘어가는 페이지
		JSFunction.alertLocation("로그인 성공",url, out);
		
	}else{
		JSFunction.alertBack("로그인 실패", out);
		request.setAttribute("LoginErrMsg", "로그인 오류");
		request.getRequestDispatcher("../UI/login.jsp").forward(request, response);
	}
	

%>
