<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>포워드 결과</h2>
	page영역 : <%= pageContext.getAttribute("pAttr") %>
	request영역 : <%= request.getAttribute("rAttr") %>
	<input type="text" name="val" placeholder=" 저소영" disabled />
</body>
</html>