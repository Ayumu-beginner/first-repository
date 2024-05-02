<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>打刻</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

  <jsp:include page="include.jsp" />
  
  	<form class="btn" action="/Management/time-begins-servlet" method="post">
	  <input type="submit" value="出勤">
	</form>
	
	<form class="btn" action="/Management/time-ends-servlet" method="post">
	  <input type="submit" value="退勤">
	</form>

</body>
</html>