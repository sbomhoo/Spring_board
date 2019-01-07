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
<script type="text/javascript">
function checkDo(userid){
   t.action = "idCheck.do?"+userid; 
   t.submit();
}

</script>
</head>
<body bgcolor="#FFFFFF">
<br>
<center>
<form action="" name=t>
<input type="userid" name="userid" id="userid" value="<%=userid%>" placeholder="아이디" class="form-control" required=""><input type="button" value="ID중복체크" onclick="checkDo(this.form.userid.value)">
</form>
<input type="button" class="btn btn-default" name=close value="닫기" onclick="window.close()">
</center>
</body>
</html>