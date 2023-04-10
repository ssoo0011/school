<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<link href="../table.css" type = "text/css" rel = "stylesheet">
<table border = "1" width = "90%">
	<tr>
		<td align = "center">
			<% if (session.getAttribute("UserId") == null){%>
			<a href="/BoardProject/session/LoginForm.jsp">로그인</a>
			<%}else{ %>
			<a href="/BoardProject/session/Logout.jsp">로그아웃</a>
			<%} %>
			
			&nbsp;&nbsp;&nbsp;
			<a href="/BoardProject/UI/List.jsp">게시판(페이징x)</a>
			&nbsp;&nbsp;&nbsp;
			<a href="pageList.jsp">게시판(페이징0)</a>
		</td>
	</tr>

</table>