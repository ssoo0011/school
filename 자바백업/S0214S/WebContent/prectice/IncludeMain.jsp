<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "IncludeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		out.print("오늘 날짜 : " + today);
		out.print("<br>");
		out.print("내일 날짜 : " + tomorrow);
	%>
</body>
</html>