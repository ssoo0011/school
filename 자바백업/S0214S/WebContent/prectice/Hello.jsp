<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<% String a = "���ҿ�";
String b = "¯¯";%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function oh() {
		
		var box = document.getElementById("box");
		var color = box.style.backgroundColor  = "yellow";
	}
</script>
<style>
	#box{
	width : 100px;
	height : 100px;
	background-color : #ffb351;
	}
</style>
<body>
	<div id = "box">box</div>
	<button onclick="oh()">��ư</button>
</body>
</html>