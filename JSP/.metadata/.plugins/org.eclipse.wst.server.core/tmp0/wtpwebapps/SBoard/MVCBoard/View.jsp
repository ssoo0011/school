<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link href="../table.css" type = "text/css" rel = "stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="get">
		<c:if test = '${ empty List }'>
			리스트없
				<c:redirect url= "/MVCBoard/view.do?mode=view&idx=${param.idx}">
			</c:redirect>
		</c:if>
        <c:forEach var = 'List' items= '${ List }'>
		    <table>
		        <tr>
		            <td width = '30%'> 작성자 </td>
		            <td width = '70%'> ${ List.name } </td>
		        </tr>
		        <tr>
		            <td> 제목 </td>
		            <td> ${ List.title } </td>
		        </tr>
		        <tr>
		            <td> 내용 </td>
		            <td>  ${ List.content } </td>
		        </tr>
		        <tr>
		            <td> 첨부파일 </td>
		            <td>${ List.sfile } </td>
		        </tr>
		        <tr>
		            <td colspan="2" style="text-align: center;">
		                <a href ="./Edit.jsp?idx=${ List.idx }">수정하기</a>
		                <a href ="Pass.jsp?idx=${ List.idx }" >글삭제</a>
		                <a href = './List.jsp' >목록바로가기</a>
		            </td>
		        </tr>
		    </table>
        </c:forEach>
    </form>
</body>
</html>