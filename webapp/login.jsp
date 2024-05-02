<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<h2>ログイン</h2>

<form action="/Management/login-servlet" method="post">

社員ID <input class="id" type="text" name="id" required><br>
パスワード <input type="password" name="password" required><br><br>
<input type="submit" value="ログイン">

</form>

<jsp:include page="msg.jsp" />

</body>
</html>