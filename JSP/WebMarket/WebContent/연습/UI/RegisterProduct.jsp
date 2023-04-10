<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../css/RegistProduct.css" type = "text/css" rel = "stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/menu.jsp"%>
		<%!String greeting = "상품등록";%>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				<%=greeting%>
			</h1>
		</div>
	</div>
	<body>
	<form action="../Process/RegisterProductProcess.jsp" method="post">
	    <div id="wrap">
	        <ul>
	            <li>상품명</li><input type="text" name = "prdctName"></input>
	            <li>상세정보</li><input type="text" name = "details"></input>
	            <li>상품코드</li><input type="text"name = "code"></input>
	            <li>제조사</li><input type="text" name = "prfrm"></input>
	            <li>분류</li><input type="text" name = "category"></input>
	            <li>재고</li><input type="text" name = "stock"></input>
	            <li>가격</li><input type="text" name = "price"></input>
	        </ul>
	        <button>상품등록</button>
	    </div>
    </form>
</body>
</body>
</html>