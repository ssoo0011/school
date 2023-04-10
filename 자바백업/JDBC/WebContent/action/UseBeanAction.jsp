<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8"); 
	%>
	<h3>액션태그로 폼값 한 번에 받기</h3>
	<jsp:useBean id="person" class='common.Person'></jsp:useBean>
	<jsp:setProperty property="*" name="person"/>
	
	이름 : <jsp:getProperty property="name" name="person"/>
	나이 : <jsp:getProperty property="age" name="person"/>
</body>
</html>