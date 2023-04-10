<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - import 속성</title>
</head>
<script>
	<%
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr = dateFormat.format(today);
		String dateString = "오늘 날짜 : " + todayStr;
	%> 
	var color = ['#612b3e', '#813b50', '#b35964', '#f38486', '#f6b59e']
	var i = 0;
	function date() {
		var box = document.getElementById("box");
		box.innerText = "<%= dateString %>"
		
		box.style.backgroundColor = color[i];
		i++;
		
		if(i === 4){
			i = 0
		}


	}
</script>
<style>
	#box{
		width: 200px;
		height: 200px;
		background-color: yellow;
		
	}
</style>

<body>
	<button onclick="date()">오늘 날짜</button>
	<div id = "box">
	오늘 날짜는?
	</div>

</body>
</html>