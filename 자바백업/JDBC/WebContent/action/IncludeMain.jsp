<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String out1 = "OuterPage1.jsp";
	String out2 = "OuterPage2.jsp";
	
	pageContext.setAttribute("pAttr", "동명왕");
	request.setAttribute("rAttr", "온조왕");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>지시어와 액션태그</h2>
	<h3>지시어로 페이지 포함학</h3>
	<%@ include file = "OuterPage1.jsp" %>
	<p>외부 파일에 선언한 변수 : <%= newVar1 %></p>
	
	<h3>액션태그로 페이지 포함학</h3>
	<jsp:include page = "OuterPage2.jsp" />
	<jsp:include page = "<%= out2 %>" />
	<p>외부 파일에 선언한 변수 : 사용모태</p>
</body>
</html>