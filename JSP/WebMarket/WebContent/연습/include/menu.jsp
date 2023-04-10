<%@ page import="management.CookieManager" %>
<%@page import = "java.util.ArrayList" %>
<%@page import="management.JSFunction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../../css/welcome.css" type = "text/css" rel = "stylesheet">

    <div id="nav-bar">
        <div id="page-menu">
			<a href="../UI/Welcome.jsp">Home</a>
			<a href="../UI/product.jsp">menu</a>
        </div>
        <div id="member-menu">
		
		<%
			//static 이라 생성자 안만들고 해도 됨
			String loginId = CookieManager.readCookie(request, "loginId");
			String cookieCheck = "";
			String ssId =(String)session.getAttribute("loginId");
			
			if(ssId !=null){
				
				cookieCheck = "checked";
				
				if(ssId.equals("ssoo0011")){
					%>
							<form action="../Process/LogOutProcess.jsp?id=<%=ssId%>" method="post">
								<a href="../UI/RegisterProduct.jsp">상품등록</a>
							</form>										
			<%}%>	
			<form  action="../Process/LogOutProcess.jsp?id=<%=ssId%>" method="post">
				<div id = 'LogInId'>
					<span style = 'color : gray'><%=ssId%>님 환영합니다!</span>
				</div>
				<div id = 'memberSVC'>
					<a href="../Process/LogOutProcess.jsp">logout</a>
					<a href="../Process/MemberInforProcess.jsp">회원정보</a>
				</div>

			</form>
		
		<% }else{%>								
			<form action="../UI/logIn.jsp" method="post">
				<a href="../UI/logIn.jsp?checkid=<%=ssId%>">login</a>
			</form>	

		<%} %>

        </div>
    </div>
