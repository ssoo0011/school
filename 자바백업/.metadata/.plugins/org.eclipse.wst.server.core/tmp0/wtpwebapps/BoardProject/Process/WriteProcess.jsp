<%@ page import="model.board.BoardDAO" %>
<%@ page import="model.board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = './IsLoggedIn.jsp' %>
<%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardDTO dto = new BoardDTO();
	dto.setTitle(title);
	dto.setContent(content);
	
	dto.setId(session.getAttribute("UserId").toString());
	//세션 값 toString으로 형변환해서 보내주기
	
	BoardDAO dao = new BoardDAO(application);
	int iResult = dao.insertWrite(dto);
	dao.close();
	
	if(iResult == 1){
		response.sendRedirect("/BoardProject/UI/List.jsp");
	}else{
		JSFunction.alertBack("게시물 작성 실패", out);
	}
	
	
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