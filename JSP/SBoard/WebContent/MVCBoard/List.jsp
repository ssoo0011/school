<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../table.css" type = "text/css" rel = "stylesheet">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action = 'Write.jsp'>
		<c:if test = '${ empty List }'>
			<c:redirect url= "/MVCBoard/list.do">
			</c:redirect>
		</c:if>
		<table>
			<th width="5%">NO</th>
			<th width="12%">글쓴이</th>
			<th width="55%">제목</th>
			<th width="15%">작성일</th>
			<th width="13%">조회수</th>
			<c:forEach var = 'List' items= '${ List }'>
			<tr>
				<td>${ List.idx }</td>
				<td>${ List.name }</td>
				<td><a href="View.jsp?idx=${ List.idx }">${ List.title }</a></td>
				<td>${ List.postdate }</td>
				<td>${ List.visitcount }</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5">
				<button onclick = "Write.jsp">글쓰기</button>
				</td>
			<tr>
		</table>
	</form>
</body>
</html>