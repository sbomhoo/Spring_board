<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
   <%   String userid = request.getParameter("userid");

         %>   
<%--    <%   String userid = request.getParameter("userid");
         boolean check = memMgr.idCheck(userid);
         %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID중복체크</title>

</head>
<body bgcolor="#FFFFFF">
<br>
<center>
<h3><%=userid%>는 사용가능한 아이디입니다.</h3>
<input type="button" class="btn btn-default" name=close value="닫기" onclick="window.close()">
</center>
</body>
</html>