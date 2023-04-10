<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../table.css" type = "text/css" rel = "stylesheet">
</head>
<body>
	<c:if test = '${ empty List }'>
		리스트없
		<c:redirect url= "/MVCBoard/pass.do?mode=no&idx=${param.idx}">
		</c:redirect>
	</c:if>
    <c:forEach var = 'List' items= '${ List }'>
		<form method="post" action="<%= request.getContextPath() %>/MVCBoard/pass.do">
			    <table>
			        <tr>
			            <td width = '30%'> 작성자 </td>
			            <td width = '70%'>
			            	${ List.name }'
			            </td>
			        </tr>
			        <tr>
			            <td> 제목 </td>
			            <td>
			            	<input type = 'text' name = 'title' value = '${ List.title }'> 
			            </td>
			        </tr>
			        <tr>
			            <td> 내용 </td>
			            <td>  <textarea name = 'content' rows="15" cols="80">${ List.content }</textarea> </td>
			        </tr>
			        <tr>
			            <td> 첨부파일 </td>
			            <td>${ List.sfile } </td>
			        </tr>
			        <tr>
			            <td colspan="2" style="text-align: center;">
			                <button type= 'submit'>수정완료</button>
			                <a href ='./List.jsp' >목록바로가기</a>
			            </td>
			        </tr>
			    </table>
			        <input style = 'display: none' type = 'text' value = 'edit' name = 'mode'>
			        <input style = 'display: none' type = 'text' value = '${ List.idx }' name = 'idx'>
	    </form>
    </c:forEach>
</body>
</html>