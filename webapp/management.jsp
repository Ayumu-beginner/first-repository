<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<form action="/Management/chg-pass-servlet" method="post">
  社員ID:<input type="text" name="id" required><br>
  パスワード:<input type="password" name="password" required><br><br>
  <input type="submit" value="パスワード変更">
</form>

<% String errorMsg = (String)request.getAttribute("errorMsg"); %>
<% if(errorMsg == null){%>
<% }else{%>
	<p class="errorMsg"><%= errorMsg %></p>
<% }%>

</body>
</html>