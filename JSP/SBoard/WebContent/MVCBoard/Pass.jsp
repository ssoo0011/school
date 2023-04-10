<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../table.css" type = "text/css" rel = "stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/MVCBoard/pass.do" method="post">
		<table>
			<tr>
				<td>비밀번호 입력</td>
				<td><input type= 'password' name = 'pass'></td>
			</tr>
			<tr>
			<td><input style = 'display: none' type = 'text' value = '${ param.idx }' name = 'idx'>
				<input style = 'display: none' type = 'text' value = 'delete' name = 'mode'></td>
		 		
				<td><button type = 'submit'>삭제하기</button></td>
			</tr>
		</table>
	</form>
</body>
</html>