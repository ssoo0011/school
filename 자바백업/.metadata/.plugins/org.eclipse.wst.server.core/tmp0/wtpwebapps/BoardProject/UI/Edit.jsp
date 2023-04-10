<%@ page import="model.board.BoardDAO" %>
<%@ page import="model.board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String num = request.getParameter("num");
	BoardDAO dao = new BoardDAO(application);
	
	BoardDTO dto = dao.selectView(num);
	dao.close();
	
%>
<!DOCTYPE html>
<html>
<script type="text/javascript">

	function validateForm(form) {
	
		if(form.title.value == ""){
			alert("제목을 입력하세요");
			form.title.focus();
			return false;
		}
		if(form.content.value == ""){
			alert("내용을 입력하세요");
			form.content.focus();
			return false;
		}
	}
	
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../common/Link.jsp"></jsp:include>
<body>
	<form name = 'writeFrm' method="post" action = '../Process/EditProcess.jsp' 
		onsubmit="retrun validateForm(this)">
				<table>
			<tr>
				<td> 제목 </td>
				<td>
					<input type = 'text' name = 'title' style = 'width : 90%' value=<%=dto.getTitle()%>>
					<input type = 'text' name = 'num' style = 'display: none' value=<%=dto.getNum()%>>
				</td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td>
					<textarea name = 'content' style = 'width : 90%; height : 100%'>
						<%=dto.getContent()%>
						
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan = '2' align="center">
					<button type = 'button' onclick = "location.href = './List.jsp'">목록보기</button>
					<button type = 'submit'>수정완료</button>
					<button type = 'reset'>다시입력</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>