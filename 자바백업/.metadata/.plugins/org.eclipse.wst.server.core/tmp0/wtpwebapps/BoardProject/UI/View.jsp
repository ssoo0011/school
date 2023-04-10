<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.board.BoardDAO" %>
<%@ page import="model.board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String num = request.getParameter("num");
	BoardDAO dao = new BoardDAO(application);
	dao.updateVisitCount(num);
	
	BoardDTO dto = dao.selectView(num);
	dao.close();

				
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../table.css" type = "text/css" rel = "stylesheet">
<title>Insert title here</title>
<script type="text/javascript">

	function deletePost(num) {
		
		var confir = confirm("삭제하시겠습니까?");
		
		if(confir){
			var form = document.writeFrm;
			form.method = "post";
			form.action='../Process/DeleteProcess.jsp?num='+num;
			form.submit();
		}
	}
</script>

</head>
<body>
	<table>
		<tr>
			<td colspan = '3' width = 100% style = 'text-align: center'>
				<h1 name = 'title'><%= dto.getTitle() %></h1>
			</td>
			
		</tr>
	</table>
	<table width = "90%">
		<tr>
			<th width = 20% style = "font-size : 7px">작성자 : <%= dto.getId() %></th>
			<th width = 20% style = "font-size : 7px">작성 날짜 :<%= dto.getPostdate() %></th>
			<th width = 20% style = "font-size : 7px">조회수 :<%= dto.getVisitcount() %></th>
		</tr>
		<tr>
			<td height= 100% width = 100%>
			<p><%= dto.getContent() %></p>
			</td>
		</tr>
		<tr>
			<td colspan = '2' align="center">
				<button type = 'button' onclick = "location.href = './List.jsp'">목록보기</button>
				<%
					if(dto.getId().equals((String)session.getAttribute("UserId"))){
				
				%>
				<button type = 'button' onclick = "location.href = './Edit.jsp?num=<%=dto.getNum()%>'">수정하기</button>
				<form name = 'writeFrm'>
					<button type = 'button' onclick = 'deletePost(<%=dto.getNum()%>);'>삭제하기</button>
				</form>
				<%
					}
				%>
	</table>

</body>
</html>