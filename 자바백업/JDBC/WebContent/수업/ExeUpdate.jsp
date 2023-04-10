<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="common.JDBConnect" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 추가 테스트(ExecuteUpdate())</h2>
	<%
		JDBConnect jdbc = new JDBConnect();
			String id = "test1";
			String pw = "1111";
			String name = "회원1";
			
			String sql = "INSERT INTO member VALUES(?,?,?,sysdate)";
			
			PreparedStatement pstmt = jdbc.con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			
			int inResult = pstmt.executeUpdate();
			out.println(inResult + "행 입력");
			
			jdbc.close();
	%>
</body>
</html>