<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardDAO" %>
<%@ page import="model.board.BoardDTO" %>
<%@ page import="management.JSFunction" %>
<%
	String num = request.getParameter("num");

	BoardDTO dto = new BoardDTO();
	BoardDAO dao = new BoardDAO(application);
	
	int result = dao.deletePost(num);
	
	if(result == 1){
		JSFunction.alertLocation("삭제 성공", "../UI/List.jsp", out);
		
	}else JSFunction.alertBack("삭제 실패"+num, out);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>