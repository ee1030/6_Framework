<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% String ctxPath = request.getContextPath(); %>
	<form name="loginForm" action="<%= ctxPath%>/login.do" method="POST">
	   <input type="text" name="id">
	   <input type="password" name="pwd">
	   <button>로그인</button>
	</form>
</body>
</html>