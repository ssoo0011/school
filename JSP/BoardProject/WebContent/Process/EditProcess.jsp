<%@page import="management.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardDAO" %>
<%@ page import="model.board.BoardDTO" %>

<%
	request.setCharacterEncoding("UTF-8");
	String num = request.getParameter("num");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardDTO dto = new BoardDTO();
	dto.setNum(num);
	dto.setTitle(title);
	dto.setContent(content);
	
	BoardDAO dao = new BoardDAO(application);
	int result = dao.updateEdit(dto);//리턴값으로 인트형 있음
	dao.close();
	
	if(result == 1){
		response.sendRedirect("../UI/View.jsp?num="+dto.getNum());
		out.print(num+title+content);
	}else{
		JSFunction.alertBack("수정 실패"+num, out);
	}
%>
