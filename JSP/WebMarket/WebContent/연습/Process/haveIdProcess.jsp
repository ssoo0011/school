<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="management.CookieManager" %>
<%@ page import="management.JSFunction" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="DB.JDBConnect"%>
    
<%

	JDBConnect jdbc = new JDBConnect();
	String url;

	
	if(request.getParameter("id") != ""){
		
		String id = request.getParameter("id");
		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
		PreparedStatement pstmt = jdbc.con.prepareStatement(sql);
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			url = "../UI/Regist.jsp";
			JSFunction.alertLocation("존재하는 아이디", url, out);
		}else{
			url = "../UI/Regist.jsp?newid="+id;
			JSFunction.alertLocation("사용가능한 아이디", url, out);
		}
		jdbc.close();
	}else{
		url = "../UI/Regist.jsp";
		JSFunction.alertLocation("아이디를 입력해주세요", url, out);
	}


%>