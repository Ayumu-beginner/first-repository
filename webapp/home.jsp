<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" type="text/css" href="style.css">
<!-- 現在時刻表示のJavaScript -->
<script>
	function updateClock() {
		var now = new Date();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds();
		hours = ('0' + hours).slice(-2);
		minutes = ('0' + minutes).slice(-2);
		document.getElementById('clock').innerHTML = hours + ':' + minutes;
	}
	setInterval(updateClock, 1000);
	window.onload = updateClock;
</script>
</head>
<body>

  <jsp:include page="include.jsp" />
  <% String beginsTime = (String)session.getAttribute("beginsTime"); %>
  <% String endsTime = (String)session.getAttribute("endsTime"); %>
 


    <br>
	<br>
	
	<span id="clock"></span>

	<br>
	
	<table border="1">
	  <tr>
	    <td>
	      <form class="btn" action="/Management/time-begins-servlet" method="post">
	      <input type="submit" value="出勤">
	      </form>
	    </td>
	    <td><% if(beginsTime != null){%>
	    <%= beginsTime %>
	    <% } %>
	    </td>
	  </tr>
	  <tr>
	    <td>
	      <form class="btn" action="/Management/time-ends-servlet" method="post">
	      <input type="submit" value="退勤">
	      </form>
	    </td>
	    <td>
	    <% if(endsTime != null){%>
	       <%= endsTime %>
	    <% } %>


	    </td>
	  </tr>
	</table>
	
<jsp:include page="msg.jsp" />

</body>
</html>