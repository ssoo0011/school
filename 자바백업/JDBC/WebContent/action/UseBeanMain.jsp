<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자바빈즈 생성</h2>
	<jsp:useBean id="person" class='common.Person' scope="request"></jsp:useBean>
	
	<h2>자바빈즈 속성 지정</h2>
	<jsp:setProperty property="person" name="name" value = '임꺾정'/>
	<jsp:setProperty property="person" name="age" value = '41'/>
	
	<h2>자바빈즈 속성 읽기</h2>
	이름 : <jsp:getProperty property="person" name="name"/>
	나이 : <jsp:getProperty property="person" name="age"/>
</body>
</html>