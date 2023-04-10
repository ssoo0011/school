<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.board.BoardDAO" %>
<%@ page import="model.board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDAO dao = new BoardDAO(application);

	Map<String, Object> param = new HashMap<String, Object>();
	String searchField = request.getParameter("searchField");
	String searchWord = request.getParameter("searchWord");
	
	if(searchWord != null){
		param.put("searchField", searchField);
		param.put("searchWord", searchWord);
	}
	
	int totalCount = dao.selectCount(param);
	List<BoardDTO> boardLists = dao.selectList(param);
	dao.close();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../table.css" type = "text/css" rel = "stylesheet">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/Link.jsp"></jsp:include>
	<form method="get">
		<table border = "1" width = "90%">
			<th><h2>목록보기 (List)</h2></th>
			<tr>
				<td align="center">
					<select name = "searchField">
						<option value = "title"> 제목 </option>
						<option value = "content"> 내용 </option>
					</select>
					<input type = 'text' name = "searchWord">
					<input type = 'submit' value = '검색하기'>
				</td>

			</tr>

		</table>
	</form>
	
		<table border = "1" width = "90%">
			<tr>
				<th width = '10%'> 번호 </th>
				<th width = '50%'> 제목 </th>
				<th width = '15%'> 작성자 </th>
				<th width = '10%'> 조회수 </th>
				<th width = '15%'> 작성일 </th>
			</tr>
			
			<%
				if(boardLists.isEmpty()){ //리스트가 비었으면
			%>
			
			<tr>
				<td colspan = '5' align="center">
					등록된 게시글 없음
				</td>
			</tr>
			
			<%
				}else{
					int virtualNum = 0;
					for(BoardDTO dto : boardLists){
						virtualNum = totalCount--;
			%>
				
				<tr align="center">
					<td><%= virtualNum %></td>
					<td align="left"><a href="View.jsp?num=<%=dto.getNum()%>"><%= dto.getTitle()%></a></td>
					<td><%= dto.getId() %></td>
					<td><%= dto.getVisitcount() %></td>
					<td><%= dto.getPostdate() %></td>
			
			<%
					}
				}
			%>			
		</table>
		
		<table border = "1" width = "90%">
			<tr align="right">
				<td>
					<input type = 'submit' value = '글쓰기' onclick = "location.href = 'Write.jsp'">
				</td>
		</table>

</body>
</html>