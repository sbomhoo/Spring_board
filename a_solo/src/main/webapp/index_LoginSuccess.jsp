<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" errorPage="DBError.jsp"%>
<%request.setCharacterEncoding("UTF-8");%>

<html lang="en">

<head>
        <meta charset="UTF-8">
        <title>Sponsor Main</title>
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
					<img src="jpg/logo.jpg">
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
                        <li><a href="chatting_LoginSuccess.jsp" >채팅</a></li>
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
   
        <div id="content">
            <div id="sections">
				
                <section id="main_section1">
                <a href="bbs_list_login.do" style="text-decoration:none">
                    <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                        <div class="mdl-card__title1" >
                            <h2 class="mdl-card__title-text">공지사항</h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            Everyone makes mistakes. if you can't forgive others, don't expect others 
                            to forgive you.
                        </div>
                    </div>
                    </a>
                </section>

                <section id="main_section2">
                <a href="bbs_list_Roommate_Success.do" style="text-decoration:none">
                    <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                        <div class="mdl-card__title2" >
                            <h2 class="mdl-card__title-text">룸메찾기</h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            Even if you treat people kindly, honestly still hate you 
                            for some reason you can't understand. 
                        </div>
                    </div>
                    </a>
                </section>

                <section id="main_section3">
                <a href="bbs_list_trade.do" style="text-decoration:none">
                    <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                        <div class="mdl-card__title3">
                            <h2 class="mdl-card__title-text">거래게시판</h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            It's nice to have someone in your life who can make you smile even when they're not around.
                        </div>
                    </div>
                    </a>
                </section>

                <section id="main_section4">
                 <a href="bbs_list_free.do" style="text-decoration:none">
                    <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                        <div class="mdl-card__title4">
                            <h2 class="mdl-card__title-text">자유게시판</h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            The Best Feeling in the world, is to know that your parents are smiling 
                            because of you.
                        </div>
                    </div>
                    </a>
                </section>

            </div>
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