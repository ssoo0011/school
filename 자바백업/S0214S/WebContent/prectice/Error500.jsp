<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage = "IsErrorPage.jsp" %>
	<!--오류 났을 때 처리하는 페이지 지정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<input type="text" id="age" > 
	<input type="submit"></input>
	
	<%
	int myAge = Integer.parseInt(request.getParameter("age")) + 10;
	out.println("10년 후 당신의 나이는 " + myAge+ "입니다.");
	%>
</body>
</html>