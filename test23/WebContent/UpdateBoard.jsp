<%@page import="test23.BoardBean"%>
<%@page import="test23.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int num = Integer.parseInt(request.getParameter("num"));
   BoardDAO dao = new BoardDAO();
   BoardBean bean = dao.BoardReda(num);
%>

<center><h2>게시글 수정</h2></center>
<form method="post" action="UpdateBoardProc.jsp">
<div align="center">
<table>
<tr>
<td>제목</td>
<td><input name = "subject" value="<%=bean.getSubject()%>"></td>
</tr>
<tr>
<td>작성자</td>
<td><input name = "name" value="<%=bean.getName() %>"></td>
</tr>
<tr>
<td>이메일</td>
<td><input name = "email" value="<%=bean.getEmail() %>"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input name = "pw" type="password"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea rows="15" cols="60" name="content"><%=bean.getContent() %></textarea></td>
</tr>
<tr>
<td><input type="hidden" value="<%=num%>"></td>
<td><input type="submit" value = "작성">
	<input type="button" value = "취소" onclick="location.href='list.jsp'">
</td>
</tr>	
</table>
</div>
</form>
</body>
</html>