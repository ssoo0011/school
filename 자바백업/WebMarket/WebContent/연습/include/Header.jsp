<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./style.css" type = "text/css" rel = "stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="../../css/welcome.css" type = "text/css" rel = "stylesheet">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="../include/menu.jsp"%>
	<%!String greeting = "환영합니다";
	String tagline = "Welcome to Web Market!";%>

    <div id="title">
			<h1 class="display-3">
				<%=greeting%>
			</h1>
    </div>
	
</body>
</html>