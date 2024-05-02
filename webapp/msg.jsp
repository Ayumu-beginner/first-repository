<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>msg</title>
</head>
<body>

<% String errorMsg = (String)request.getAttribute("errorMsg"); %>
<% if(errorMsg == null){%>
<% }else{%>
	<p class="errorMsg"><%= errorMsg %></p>
<% }%>

</body>
</html>