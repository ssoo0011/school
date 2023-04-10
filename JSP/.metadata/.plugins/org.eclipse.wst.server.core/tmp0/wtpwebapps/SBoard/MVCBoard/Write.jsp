<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = 'Board.BoardDTO' %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../table.css" type = "text/css" rel = "stylesheet">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="<%= request.getContextPath() %>/MVCBoard/write.do">
    <table>
        <tr>
            <td width = '30%'> 작성자 </td>
            <td width = '70%'> <input type="text" name = 'name'> </td>
        </tr>
        <tr>
            <td > 제목 </td>
            <td> <input type="text" name = 'title' placeholder="제목을 입력하세요"> </td>
        </tr>
        <tr>
            <td> 내용 </td>
            <td> <textarea cols = '80' rows = '30' name = 'content' placeholder="내용을 입력하세요"></textarea> </td>
        </tr>
        <tr>
            <td> 첨부파일 </td>
            <td> <input type="file" name = 'ofile'> </td>
        </tr>
        <tr>
            <td width = '30%'> 비밀번호 </td>
            <td width = '70%'> <input type="password" name = 'pass'> </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <button>작성 완료</button>
                <button>다시 작성</button>
                <button type="button" onclick ="location.href = './List.jsp'" >목록 바로가기</button>
            </td>
        </tr>
    </table>
	</form>
</body>
</html>