<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int num = Integer.parseInt(request.getParameter("num")); %>
<center><h2>본인확인</h2></center>
<div align="center">
<form method="post" action="delCheckProc.jsp">
<table>
<tr>
<td>비밀번호</td>
<td><input type="password" name="pw"></td>
</tr>
<td></td>
<td><input type="submit" value="확인">
	<input type="button" value="취소" onclick="history.go(-1)">
	<input type="hidden" name="num" value="<%=num%>">
</table>
</form>
</div>
</body>
</html>