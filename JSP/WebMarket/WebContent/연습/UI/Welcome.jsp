<%@ page import= "product.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<style>
a{
    text-decoration: none;
    color: white;
    font-size: 30xp;
}
</style>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="../../css/welcome.css" type = "text/css" rel = "stylesheet">
<meta charset="UTF-8">
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
    <div id="content">
			<h3>
				<%=tagline%>
			</h3>
			<%
				response.setIntHeader("Refresh", 5);
				Date day = new java.util.Date();
				String am_pm;
				int hour = day.getHours();
				int minute = day.getMinutes();
				int second = day.getSeconds();
				if (hour / 12 == 0) {
					am_pm = "AM";
				} else {
					am_pm = "PM";
					hour = hour - 12;
				}
				String CT = hour + ":" + minute + ":" + second + " " + am_pm;
				out.println("현재 접속  시각: " + CT + "\n");
			%>
    </div>
		<hr>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>