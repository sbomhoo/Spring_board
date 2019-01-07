<%@page import="java.util.List"%>
<%@page import="lab.solo.vo.Article"%>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" errorPage="DBError.jsp"%>
<%@ page contentType="text/html;charset=utf-8" %>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<%!
	int numPerPage = 10;
	int numPerBlock = 10;
	
	void printReplies(JspWriter o, Article h, String rootNum, 
		int depth) throws java.io.IOException {
		List<Article>  v = h.getReplies();
		int n = v.size();
		for(int i=0; i < n; i++) {
			Article r =  v.get(i);
			o.print("<tr id='" + rootNum + "_" + h.getNum() + 
			        "' style='display:none'>");
			o.print("<td>&nbsp;</td> <td>");
			for(int k =0; k < depth; k++) {
				o.print("<img src=./image/blank.gif>");
			}			
			if(r.getSize() > 0) {
				o.print("<a id=link" + h.getNum() + "_" + r.getNum() + 
				" href='javascript:show(\"" + h.getNum() + "_" + 
				r.getNum() + "\");'>+</a>");
			} else {
				o.print("&nbsp;");
			}
			o.print(" <a href=/bbs_list.do?action=read_r&num=" + r.getNum() + 
			        ">" + r.getSubject() + "</a></td>");
			o.print("<td>" + r.getWriter() + "</td>");
			o.print("<td>" + r.getIdate() + "</td>");
			o.print("<td style='text-align:right'>" + 
			         r.getRcount() + "</td>");
			o.println("</tr>");
			printReplies(o, r, rootNum, depth+1);
		}
	}
%>
<head>
<script type="text/javascript">
         function idCheck(userid) {
/*              f.action = "idCheck.do?"+userid; 
             f.submit(); */
            wx=600;
            wy=400;
            sx=screen.width;
            sy=screen.height;
            x=(sx-wx)/2;
            y=(sy-wy)/2;
             url="idCheck.jsp?userid=" + userid; 
             wr = window.open(url,"아이디검색","width=400,height=200");
            wr.moveTo(x, y);

         }      
         function digit_check(evt){
             var code = evt.which?evt.which:event.keyCode;
             if(code < 48 || code > 57){
             return false;
             }
             }
      
      </script>
    <meta charset="UTF-8">
    <title>룸메이트 게시판</title>
    <script>
	function show(id) {
		var d = document.getElementsByName(id);
		var len = d.length;
		
		for(i = 0; i < len; i++) {
			d[i].style.display = 'block';
		}
		var link = 'link' + id;
		
		var a = document.getElementById('link' + id);
		a.childNodes[0].nodeValue = '-';
		a.href = "javascript:hide('" + id + "')";
	}
	
	function hide(id) {
		var d = document.getElementsByName(id);
		var len = d.length;
		
		for(i = 0; i < len; i++) {
			d[i].style.display = 'none';
		}
		var a = document.getElementById('link' + id);
		a.childNodes[0].nodeValue = '+';
		a.href = "javascript:show('" + id + "')";
	}
</script>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" media="screen and (max-width:1920px)" href="styleuser.css">
     <link rel="stylesheet" media="screen and (max-width:1024px)" href="style.css">
    <link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
    <script src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
     <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!--초기화 코드-->
</head>

<body>
    <div id="container">
        <div id="header">
            <header id="main_header">
                <div id="title">
                   <a href="index.jsp">
                            <img src="./jpg/logo.jpg">
                        </a>
                </div>
                <nav id="main_gnb">
                    <ul>
                        <li>  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#login">Login</button>
  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#join">Join</button>
</li>
                    </ul>
                </nav>
                <nav id="main_lnb">
                    <ul>
                        <li><a href="./bbs_list3.do">공지사항</a></li>
                        <li><a href="./bbs_list.do" id="roommate">룸메찾기</a></li>
                        <li><a href="./bbs_list4.do">트레이드</a></li>
                        <li><a href="./bbs_list2.do">자유게시판</a></li>
                        <li><a href="chatting.jsp">채팅</a></li>
                    </ul>
                </nav>
            </header>
        </div>
          <!-- login Modal -->
  <div class="modal fade" id="login" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로그인</h4>
        </div>
        <div class="modal-body">
          <form action="login.do" method="post">
          <div class="form-group"><input type="userid" name="userid" id="userid" value="" placeholder="아이디" class="form-control" required=""></div>
      
          <div class="form-group"><input type="password" name="userpw" id="userpw" value="" placeholder="비밀번호" class="form-control" required=""></div>
          			  <br>
			  <legend></legend>
          <button type="submit" class="btn btn-default">로그인</button>     
</form>
        </div>


      </div>
      
    </div>
  </div>
  <!-- join Modal -->
  <div class="modal fade" id="join" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">회원가입 </h4>
        </div>
        <div class="modal-body">
          <form method="post" action="join.do" method="post" name=f>
           <fieldset>
   <div class="form-group"><input type="userid" name="userid" id="userid" value="" placeholder="아이디" class="form-control" required="" style="width: 78%;float: left;" >&nbsp&nbsp&nbsp&nbsp<input type="button" class="btn btn-default" value="ID중복체크" onclick="idCheck(this.form.userid.value)"style="width: 18%;background-color: #e2e2e2;"></div>
    <div class="form-group"><input type="password" name="userpw" id="userpw" value="" placeholder="비밀번호" class="form-control" required=""><!-- <input type="button" value="ID중복체크" onclick="idCheck(this.form.userid.value)"> --></div>
    <div class="form-group"><input type="username" name="username" id="username" value="" placeholder="이름" class="form-control" required=""></div>
    <div class="form-group"><input type="email" name="email" id="email" value="" placeholder="이메일" class="form-control" required=""></div>
    <div class="form-group"><input type="number" name="phone" id="phone" value="" placeholder="휴대전화번호/숫자로 입력하세요." class="form-control" required="" style="ime-mode:disabled;"onkeypress="return digit_check(event)"></div>
    <div class="form-group"><input type="number" name="birth" id="birth" value="" placeholder="'20000101'형식으로입력하세요." class="form-control" required=""style="ime-mode:disabled;"onkeypress="return digit_check(event)"></div>
    <div class="form-group"><input type="address" name="address" id="address" value="" placeholder="주소" class="form-control"></div>         
           </fieldset>
           
           
           <br>
           <legend></legend>
           <input type="submit" class="btn btn-default" id="user" name="user" value="회원가입" style="background-color: #e2e2e2;">
         </form>
        </div>
      </div>
      
    </div>
  </div>    


<div id="content">

   <div class="row row-offcanvas" id="main-row">
   	  <div id="category">
   	  	 <select name="local" onchange="window.open(value,'_self');" >
   							 <option value="" >지역선택</option>
   							 <option value="./bbs_listnamgu.do">남구</option>
    						 <option value="./bbs_listhea.do">해운대구</option>
   							 <option value="./bbs_listseo.do">서구</option>
							<option value="./bbs_listsa.do">사하구</option>
							<option value="./bbs_listgum.do">금정구</option>
							<option value="./bbs_listbuk.do">북구</option>
							<option value="./bbs_listyeon.do">연제구</option>	
								
								</select>
                        
                    </div>
   <br><br>
      <article id="content_table" class="content panel panel-default col " itemscope="" itemtype="http://schema.org/Article">
                      <table class="table table-striped" id="main_table">
  <tr><th id="num_">글번호</th><th id="cago_">카테고리</th><th id="title_">제목</th>
  <th id="name_">작성자</th><th id="date_">작성일</th><th id="count_">조회수</th></tr>
  <tr><th colspan="6"></th></tr>
  
<c:forEach var="row" items="${headers}">
	<tr><td>${row.num} </td>
		<td>${row.area}</td>
		<td>
		<c:if test='${row.size > 0}'>
		<a id='link${row.num}' 
		   href='javascript:show("${row.num}");' >+</a>
		</c:if>
		<a href=index.jsp onclick="loginrequest()">${row.subject}</a>
		<script>
			function loginrequest(){
				alert("로그인 후 이용해주세요.");
				
			}
		
		</script>

		</td>
		<td>${row.writer}</td>
		<td>${row.idate}</td>
		<td style='text-align:right'>${row.rcount}</td>

</c:forEach>
           
</table>
<div style='text-align:right'><br>
	<input type="button" onclick="mywrite()" value="글쓰기">

</div>
<script>
function mywrite() {
	alert("로그인 후 이용해주세요.");
	
    location.href="index.jsp";
}
</script>
<!-- 페이지 번호 -->
	<div style="text-align:center">	
<%	
	Integer p = (Integer) request.getAttribute("pageNo");
	int mypage = p.intValue();
    int currentBlock = (int)Math.ceil(mypage / (double)numPerBlock);
	Integer tp = (Integer) request.getAttribute("totalPage");
	double totalPage = tp.intValue();
	int totalBlock = (int)Math.ceil(totalPage / numPerBlock);
	if(totalBlock > currentBlock) { 
		int togo = (currentBlock + 1) * numPerBlock;
		if(togo > totalPage)
			togo = (int) totalPage; %>
		<a href=./bbs_list.do?page=<%=togo%>> << </a>
<%	}
	for(int i = numPerBlock; i > 0; i--) {
		int pn = numPerBlock * (currentBlock-1) + i;
		if(pn > totalPage)
			continue;
		if(pn == mypage) { %>
		<a href=./bbs_list.do?page=<%=pn%>>
		<span style="text-decoration:underline"><%=pn%></span></a>&nbsp;
<%		} else { %>
		<a href=./bbs_list.do?page=<%=pn%>><%=pn%></a>&nbsp		
<%		}
	}
	if(currentBlock > 1) { %>
		<a href=./bbs_list.do?page=<%= (currentBlock-1)*numPerBlock %>> >> </a>
<%	} %> </div> 
</div>                           
<div class="clearfix"></div>
      </article>
</div>


        </div>
    </div>
      <div id="footer">
            <footer id="main_footer" class="mdl-mini-footer">
                <div class="mdl-mini-footer__left-section">
                    <div class="mdl-logo">Title</div>
                    <ul class="mdl-mini-footer__link-list">
                        <li><a href="index.jsp">Main</a></li>
                        <li><a href="#top">▲Top</a></li>
                    </ul>
                </div>
            </footer>
        </div>





</body>

</html>