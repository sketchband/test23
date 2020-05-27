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
   String pw = request.getParameter("pw");
   BoardDAO dao = new BoardDAO();
   
   if(pw.equals(dao.PwCheck(num))){
	   dao.BoardDelete(num);
	   %>
	   <script>
	   alert("게시글이 삭제 되었습니다.");
	   location.href="list.jsp";
	   </script>
   <%}else{
	   %>
	   <script>
	   alert("비밀번호가 틀렸습니다.");
	   history.go(-1);
	   </script>
	   <%
}%>


</body>
</html>