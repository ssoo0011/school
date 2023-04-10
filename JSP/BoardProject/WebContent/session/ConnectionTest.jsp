<%@ page import = "common1.JDBConnect2" %>
<%@ page import="common1.DBConnPool" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>

</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JDBC테스트1</h2>
	
	<%
			JDBConnect2 jdbc1 = new JDBConnect2();
				jdbc1.close();
				
				JDBConnect2 jdbc3 = new JDBConnect2(application);
				//어플리케이션 객체가 web.xml에 있는 애를 전달해줌~
		%>
	
	<h2>JDBC테스트2</h2>
	
	<%
			String driver = application.getInitParameter("OracleDriver");
				String url = application.getInitParameter("OracleURL");
				String id = application.getInitParameter("OracleId");
				String pwd = application.getInitParameter("OraclePwd");
				
				JDBConnect2 jdbc2 = new JDBConnect2(driver, url, id, pwd);
				jdbc2.close();
		%>
	<h3>커넥션풀 연결 테스ㅇ트</h3>
	<%
		DBConnPool pool = new DBConnPool();
		pool.close();
	%>

</body>
</html>