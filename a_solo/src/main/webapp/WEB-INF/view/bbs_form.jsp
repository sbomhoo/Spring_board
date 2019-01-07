<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" errorPage="DBError.jsp"%>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>룸메게시판 글쓰기</title>
        
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet" type="text/css" />
 		<link rel="stylesheet" media="screen and (max-width:1920px)" href="indexuser.css">
        <link rel="stylesheet" media="screen and (max-width:1024px)" href="index.css">
        <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet" type="text/css">
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
		<script type="text/javascript">
           function checking() {
            alert('수정이 완료되었습니다.');
         } {
            
         }
        </script>
    </head>

    <body>
        <div id="container">
            <div id="header">
                <header id="main_header">
                    <div id="title">
                        <a href="index_LoginSuccess.jsp">
                            <img src="./jpg/logo.jpg">
                        </a>


                    </div>
                    <nav id="main_gnb">
                        <ul>
                          	<li>  <!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#mypage">MyPage</button>
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#logout">Logout</button>
	</li>
                        </ul>
                    </nav>
                    <nav id="main_lnb">
                        <ul>
                            <li><a href="bbs_list_login.do">공지사항</a></li>
                        <li><a href="bbs_list_Roommate_Success.do">룸메찾기</a></li>
                        <li><a href="bbs_list_trade.do">트레이드</a></li>
                        <li><a href="bbs_list_free.do">자유게시판</a></li>
                        <li><a href="chatting_LoginSuccess.html">채팅</a></li>
                        </ul>
                    </nav>
                </header>
            </div>
            <!-- MyPage Modal -->
  <div class="modal fade" id="mypage" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">회원정보관리 </h4>
        </div>
        <div class="modal-body">
          <form method="post" action="modify.do" method="post">
           <fieldset>
   <div class="form-group"><input type="userid" name="userid" id="userid" value="${user.userid}" placeholder="아이디" class="form-control" readonly=""></div>
    <div class="form-group"><input type="password" name="userpw" id="userpw" value="${user.userpw}" placeholder="비밀번호" class="form-control" required=""></div>
    <div class="form-group"><input type="username" name="username" id="username" value="${user.username}" placeholder="이름" class="form-control" readonly=""></div>
    <div class="form-group"><input type="email" name="email" id="email" value="${user.email}" placeholder="이메일" class="form-control" required=""></div>
    <div class="form-group"><input type="phone" name="phone" id="phone" value="${user.phone}" placeholder="휴대전화번호/반드시 숫자로만 입력하세요." class="form-control" required=""></div>
    <div class="form-group"><input type="birth" name="birth" id="birth" value="${user.birth}" placeholder="'20000101'형식으로입력하세요." class="form-control" readonly=""></div>
    <div class="form-group"><input type="address" name="address" id="address" value="${user.address}" placeholder="주소" class="form-control"></div>          
           </fieldset>
           <br>
           <legend></legend>
           <input type="submit" class="btn btn-default" id="user" name="user" value="수정하기" style="background-color: #e2e2e2;" onclick="checking()">
           <input type="button" class="btn btn-default" name=close value="취소" onclick="window.close()">
         </form>
        </div>
      </div>
      
    </div>
  </div>     
            
             <!-- logout Modal -->
  <div class="modal fade" id="logout" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로그아웃</h4>
        </div>
        <div class="modal-body">
            <p>정말 로그아웃 하시겠습니까?</p>
            <br>
        	<legend></legend>
            <form action="logout.do" >
            	<button type="submit" class="btn btn-default">확인</button>
        	</form>
        </div>

      </div>
      
    </div>
  </div>         
            
            <div class="write">
            	<form method='post' action='bbs_write.do' enctype="multipart/form-data">
                <div class="select_local">
                    지역 &nbsp;&nbsp;&nbsp;&nbsp; 
                       <select id="area" name="area">
                        <option value="">선택</option>
                        <option value="남구">남구</option>
                        <option value="해운대구">해운대구</option>
                        <option value="서구">서구</option>
                        <option value="사하구">사하구</option>
                        <option value="금정구">금정구</option>
                        <option value="북구">북구</option>
                        <option value="연제구">연제구</option>
                    </select>
                </div>
                <br>
                <div class="title">
                    제목 &nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="text" id="title_1"  name="subject" >
                </div>
                <br>
                <div class="name">
                    작성자 &nbsp;
                    <input type="text" id="title_2" name="writer" value="${user.userid}" readonly>
                </div>
                <div class="text">
                     <br >
                    내용  &nbsp;&nbsp;&nbsp;&nbsp; 
                     <textarea id="textarea" name="contents" ></textarea>  
                </div>
                <br> 
             	
             	<div id="write_file">     
                    	파일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div> 
                    <div id="write_file_1">
                    <input type="file" name="uploadfile"/>
              	</div>
                <div>&nbsp;</div>
                
                <div id="write_form">
                <input type="button" onclick="myFunction()" value="글목록">
				<input type='submit' value="글쓰기 저장" > 
				<input type='reset' value="글쓰기 취소" >
				</div>                
              
                </form>
                <script>
					function myFunction() {
					        location.href="./bbs_list_Roommate_Success.do";
					}
				</script>
                
               
            </div>
            </div>

        <div id="footer">
            <footer id="main_footer" class="mdl-mini-footer">
                <div class="mdl-mini-footer__left-section">
                    <div class="mdl-logo">Title</div>
                    <ul class="mdl-mini-footer__link-list">
                        <li><a href="index_LoginSuccess.jsp">Main</a></li>
                        <li><a href="#top">▲Top</a></li>
                    </ul>
                </div>
            </footer>
        </div>
    </body>
</html>