<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.user"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>インクルード</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

氏名:<% user user = (user)session.getAttribute("user"); %>
<% if(user == null){
 }else{ %>
<%= user.getUserName() %>
<% } %>

<a href="/Management/logout-servlet" class="logout">ログアウト</a>
</body>
</html>