package lab.solo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lab.solo.service.BoardService;
import lab.solo.vo.Article;
import lab.solo.vo.Board;
import lab.solo.vo.Comment;
import lab.solo.vo.PasswordForm;
import lab.solo.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	public static int numPerPage = 10;
	public static int numPerBlock = 10;
	
	int bno=0;
	@Resource(name="downloadView")
	private View downloadView;
	
	//############################### 1. 룸메게시판 ######################################
	
	
	//로그인 성공시
	@RequestMapping("/bbs_list_Roommate_Success.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
	public ModelAndView getBoardList_Roommate(@RequestParam(value="page", required=false)String page){
		ModelAndView mav = new ModelAndView();
		int pageNo = 1;
		List<Article> headers=null;
		if(page==null){
			pageNo = service.getPageCount(numPerPage);
			headers = service.getArticles(pageNo, numPerPage);
		}else{
			pageNo = Integer.parseInt(page);
			headers = service.getArticles(pageNo, numPerPage);
		}
		
		Integer totalPage = new Integer(service.getPageCount(numPerPage));
		mav.addObject("headers",headers);
		mav.addObject("pageNo",new Integer(pageNo));
		mav.addObject("totalPage", totalPage);
		mav.setViewName("roommate_LoginSuccess");
		return mav;
		
	}
	
	@RequestMapping("/bbs_list.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
	public ModelAndView getBoardList(@RequestParam(value="page", required=false)String page){
		ModelAndView mav = new ModelAndView();
		int pageNo = 1;
		List<Article> headers=null;
		if(page==null){
			pageNo = service.getPageCount(numPerPage);
			headers = service.getArticles(pageNo, numPerPage);
		}else{
			pageNo = Integer.parseInt(page);
			headers = service.getArticles(pageNo, numPerPage);
		}
		
		Integer totalPage = new Integer(service.getPageCount(numPerPage));
		mav.addObject("headers",headers);
		mav.addObject("pageNo",new Integer(pageNo));
		mav.addObject("totalPage", totalPage);
		mav.setViewName("roommate");
		return mav;
		
	}
	
	//리스트에서 글작성 버튼 눌렀을떄
		@RequestMapping(value="/bbs_write.do", method=RequestMethod.GET)
		public String getBoardFormPage(){
			return "bbs_form";   //bbs_form으로 연결
			
		}
		
		//bbs_form에서 글쓰기저장눌렀을떄
		@RequestMapping(value="/bbs_write.do", method=RequestMethod.POST)
		public ModelAndView getBoardSave(Board board, HttpServletRequest req){
			ModelAndView mav = new ModelAndView();
			  MultipartFile uploadfile = board.getUploadfile();
			  if(uploadfile != null){
			   String fileName = uploadfile.getOriginalFilename();
			   board.setFile(fileName);
			             try {
			                 File file = new File("D:/★/spring_dev/eGovFrameDev-3.5.1-64bit/project_workspace/a_solo/src/main/webapp/image/" + fileName);
			                 uploadfile.transferTo(file);
			             } catch (IOException e) {
			                 e.printStackTrace();
			         }
			         }else{
			          board.setFile(null);
			  } 
			board.setIp(req.getRemoteAddr());
			service.insertProc(board, false);   //service.insertPorc으로 넘김->db로
			mav.setViewName("redirect:/bbs_list_Roommate_Success.do");  //
			return mav;
			
		}
		
		
		
		
	
		//글 읽기
		@RequestMapping(value="/bbs_read.do")
		public ModelAndView getReadProc(@RequestParam("num")String num,HttpServletRequest request){
			 HttpSession hs= request.getSession();
	         String id1 =(String)hs.getAttribute("userid");
	         System.out.println("-----------------------");
				System.out.println(id1);
				
			ModelAndView mav = new ModelAndView();
			bno = Integer.parseInt(num);
			Board article = null;
			
				article = service.getArticleProc(bno);
				mav.addObject("article",article);
				mav.addObject("num",bno);
				mav.addObject("id1",id1);
			mav.setViewName("bbs_read");
			return mav;
		}
		
		
		//파일 다운로드
		 @RequestMapping(value = "/fileDown.do", method = RequestMethod.GET)
		    public ModelAndView fileDown(@RequestParam("file") String fileName) {
		        ModelAndView mav = new ModelAndView();
		     mav.setView(this.downloadView);
		  String fullPath = "D:/★/spring_dev/eGovFrameDev-3.5.1-64bit/project_workspace/a_solo/src/main/webapp/image/" + fileName ;
		        File downloadFile = new File(fullPath);
		        mav.addObject("downloadFile", downloadFile);
		        return mav;
		    }

		
		//게시글 수정
		@RequestMapping(value="/bbs_modify.do")
		public ModelAndView getEditPage(){ 
			//수정페이지로 넘겨줘야됨
			ModelAndView mav = new ModelAndView();
			Board article = null;
			UserVO user=null;
	
				article = service.getArticleProc(bno);
				mav.setViewName("bbs_modify");
				mav.addObject("article",article);
				mav.addObject("num",new Integer(bno));
		
			return mav;
			
		}
		

		
		//수정한페이지 업데이트 (수정할입력다하고 수정버튼눌렀을떄)
		@RequestMapping(value="/bbs_update.do", method=RequestMethod.POST)
		public ModelAndView requestUpdate(Board board){
			
			ModelAndView mav = new ModelAndView();
				service.updateProc(board, "bbs");
				mav.setViewName("redirect:/bbs_read.do?action=read&num="
									+board.getNum()); //업데이트 완료시 뷰페이지로 넘김
				
			return mav;
		}
		
		//게시글 삭제
				@RequestMapping(value="/bbs_delete.do")
				public ModelAndView requestRemove(){ 
					//삭제페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();

						service.removeProc(bno);
						mav.setViewName("redirect:/bbs_list_Roommate_Success.do");

					return mav;
					
				}
				

				
				//댓글 쓰기
				@RequestMapping(value="/bbs_comment.do")
				public ModelAndView  commentSaveProc(Comment comment
						, @RequestParam("num")String rnum
						, HttpServletRequest req){
					
					ModelAndView mav = new ModelAndView ();
					comment.setIp(req.getRemoteAddr());
					comment.setRnum(Integer.parseInt(rnum));
					
					if (service.insertComment(comment) > 0) {			 
						mav.setViewName("redirect:/bbs_read.do?action=read&num="+rnum);
					}
					return mav;
				}
				
				//댓글삭제
				@RequestMapping(value="/delete_comment.do")
				public ModelAndView commnetRemoveProc(PasswordForm form
						       , @RequestParam("read_num")String read_num){
					ModelAndView mav = new ModelAndView ();		 
					 
					if (service.deleteComment(form) ) {
						mav.setViewName("redirect:/bbs_read.do?action=read&num="+read_num);
					}
					return mav;
				}
				
				
				//구별 리스트 뽑아오기
				//남구
				@RequestMapping("/bbs_listnamgu.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
				public ModelAndView getBoardListnamgu(@RequestParam(value="page", required=false)String page){
					ModelAndView mav = new ModelAndView();
					int pageNo = 1;
					List<Article> headers=null;
					if(page==null){
						pageNo = service.getPageCountnamgu(numPerPage);
						headers = service.getArticlesnamgu(pageNo, numPerPage);
					}else{
						pageNo = Integer.parseInt(page);
						headers = service.getArticlesnamgu(pageNo, numPerPage);
					}
					
					Integer totalPage = new Integer(service.getPageCountnamgu(numPerPage));
					mav.addObject("headers",headers);
					mav.addObject("pageNo",new Integer(pageNo));
					mav.addObject("totalPage", totalPage);
					mav.setViewName("roommate_LoginSuccess");
					return mav;
					
				}
				
					//해운대
					@RequestMapping("/bbs_listhea.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListhea(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCounthea(numPerPage);
							headers = service.getArticleshea(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticleshea(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCounthea(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("roommate_LoginSuccess");
						return mav;
						
					}	
					
					//서구
					@RequestMapping("/bbs_listseo.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListseo(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCountseo(numPerPage);
							headers = service.getArticlesseo(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticlesseo(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCountseo(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("roommate_LoginSuccess");
						return mav;
						
					}	
					//사하구
					@RequestMapping("/bbs_listsa.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListsa(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCountsa(numPerPage);
							headers = service.getArticlessa(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticlessa(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCountsa(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("roommate_LoginSuccess");
						return mav;
						
					}	
					//금정구
					@RequestMapping("/bbs_listgum.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListgum(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCountgum(numPerPage);
							headers = service.getArticlesgum(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticlesgum(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCountgum(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("roommate_LoginSuccess");
						return mav;
						
					}	
					//북구
					@RequestMapping("/bbs_listbuk.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListbuk(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCountbuk(numPerPage);
							headers = service.getArticlesbuk(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticlesbuk(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCountbuk(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("roommate_LoginSuccess");
						return mav;
						
					}	
					
					//연제구
					@RequestMapping("/bbs_listyeon.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListyeon(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCountyeon(numPerPage);
							headers = service.getArticlesyeon(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticlesyeon(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCountyeon(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("roommate_LoginSuccess");
						return mav;
						
					}	
					
					
					
				//############################### 2. 자유게시판 ######################################
					
					//로그인성공시
					@RequestMapping("/bbs_list_free.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
					public ModelAndView getBoardListfree(@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCount2(numPerPage);
							headers = service.getArticles2(pageNo, numPerPage);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticles2(pageNo, numPerPage);
						}
						
						Integer totalPage = new Integer(service.getPageCount2(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("freeboard_LoginSuccess");
						return mav;
						
					}
				@RequestMapping("/bbs_list2.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
				public ModelAndView getBoardList2(@RequestParam(value="page", required=false)String page){
					ModelAndView mav = new ModelAndView();
					int pageNo = 1;
					List<Article> headers=null;
					if(page==null){
						pageNo = service.getPageCount2(numPerPage);
						headers = service.getArticles2(pageNo, numPerPage);
					}else{
						pageNo = Integer.parseInt(page);
						headers = service.getArticles2(pageNo, numPerPage);
					}
					
					Integer totalPage = new Integer(service.getPageCount2(numPerPage));
					mav.addObject("headers",headers);
					mav.addObject("pageNo",new Integer(pageNo));
					mav.addObject("totalPage", totalPage);
					mav.setViewName("freeboard");
					return mav;
					
				}
				
				//리스트에서 글작성 버튼 눌렀을떄
				@RequestMapping(value="/bbs_write2.do", method=RequestMethod.GET)
				public String getBoardFormPage2(){
					return "freeboard_form";   //bbs_form으로 연결
					
				}
				
				//bbs_form에서 글쓰기저장눌렀을떄
				@RequestMapping(value="/bbs_write2.do", method=RequestMethod.POST)
				public ModelAndView getBoardSave2(Board board, HttpServletRequest req){
					ModelAndView mav = new ModelAndView();
					  MultipartFile uploadfile = board.getUploadfile();
					  if(uploadfile != null){
					   String fileName = uploadfile.getOriginalFilename();
					   board.setFile(fileName);
					             try {
					                 File file = new File("D:/★/spring_dev/eGovFrameDev-3.5.1-64bit/project_workspace/a_solo/src/main/webapp/image/" + fileName);
					                 uploadfile.transferTo(file);
					             } catch (IOException e) {
					                 e.printStackTrace();
					         }
					         }else{
					          board.setFile(null);
					  } 
					board.setIp(req.getRemoteAddr());
					service.insertProc2(board);   //service.insertPorc으로 넘김->db로
					mav.setViewName("redirect:/bbs_list_free.do");  //
					return mav;
					
				}
				
				
				//글 읽기
				@RequestMapping(value="/bbs_read2.do")
				public ModelAndView getReadProc2(@RequestParam("num")String num,HttpServletRequest request){
					 HttpSession hs= request.getSession();
						
			         String id1 =(String)hs.getAttribute("userid");
			         System.out.println("-----------------------");
					System.out.println(id1);
					
					ModelAndView mav = new ModelAndView();
					bno = Integer.parseInt(num);
					Board article = null;
					
						article = service.getArticleProc2(bno);
						mav.addObject("article",article);
						mav.addObject("num",bno);
						mav.addObject("id1",id1 );
					mav.setViewName("freeboard_read");
					return mav;
				}
				
				//게시글 수정
				@RequestMapping(value="/bbs_modify2.do")
				public ModelAndView getEditPage2(){ 
					//수정페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();
					Board article = null;
				
						article = service.getArticleProc2(bno); //글 읽어오기
						mav.setViewName("freeboard_modify");
						mav.addObject("article",article);
						mav.addObject("num",new Integer(bno));
				
					return mav;
					
				}
				

				
				//수정한페이지 업데이트 (수정할입력다하고 수정버튼눌렀을떄)
				@RequestMapping(value="/bbs_update2.do", method=RequestMethod.POST)
				public ModelAndView requestUpdate2(Board board){
					
					ModelAndView mav = new ModelAndView();
						service.updateProc2(board);
						mav.setViewName("redirect:/bbs_read2.do?action=read&num="
											+board.getNum()); //업데이트 완료시 뷰페이지로 넘김
						
					return mav;
				}
				
				//게시글 삭제
				@RequestMapping(value="/bbs_delete2.do")
				public ModelAndView requestRemove2(){ 
					//삭제페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();

						service.removeProc2(bno);
						mav.setViewName("redirect:/bbs_list_free.do");

					return mav;
					
				}
				
				//댓글 쓰기
				@RequestMapping(value="/bbs_comment2.do")
				public ModelAndView  commentSaveProc2(Comment comment
														, @RequestParam("num")String rnum
														, HttpServletRequest req){
					
					ModelAndView mav = new ModelAndView ();
					comment.setIp(req.getRemoteAddr());
					comment.setRnum(Integer.parseInt(rnum));
					
					if (service.insertComment2(comment) > 0) {			 
						mav.setViewName("redirect:/bbs_read2.do?action=read&num="+rnum);
					}
					return mav;
				}
				
				//댓글삭제
				@RequestMapping(value="/delete_comment2.do")
				public ModelAndView commnetRemoveProc2(PasswordForm form
						       							, @RequestParam("read_num")String read_num){
					ModelAndView mav = new ModelAndView ();		 
					 
					if (service.deleteComment2(form) ) {
						mav.setViewName("redirect:/bbs_read2.do?action=read&num="+read_num);
					}
					return mav;
				}
				//검색
				@RequestMapping(value="/search2.do", method=RequestMethod.POST)
				public ModelAndView search2(Board board, @RequestParam("text")String text,@RequestParam(value="page", required=false)String page){
						ModelAndView mav = new ModelAndView();
						int pageNo = 1;
						List<Article> headers=null;
						if(page==null){
							pageNo = service.getPageCount2(numPerPage);
							headers = service.getSearch2(text);
						}else{
							pageNo = Integer.parseInt(page);
							headers = service.getArticles2(pageNo, numPerPage);
						}
						Integer totalPage = new Integer(service.getPageCount4(numPerPage));
						mav.addObject("headers",headers);
						mav.addObject("pageNo",new Integer(pageNo));
						mav.addObject("totalPage", totalPage);
						mav.setViewName("freeboard_LoginSuccess");
						return mav;
						
					}
				
				
				//############################### 3. 공지사항 ######################################
				
				//로그인성공시
				@RequestMapping("/bbs_list_login.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
				public ModelAndView getBoardList_Login(@RequestParam(value="page", required=false)String page){
					ModelAndView mav = new ModelAndView();
					int pageNo = 1;
					List<Article> headers=null;
					if(page==null){
						pageNo = service.getPageCount3(numPerPage);
						headers = service.getArticles3(pageNo, numPerPage);
					}else{
						pageNo = Integer.parseInt(page);
						headers = service.getArticles3(pageNo, numPerPage);
					}
					
					Integer totalPage = new Integer(service.getPageCount3(numPerPage));
					mav.addObject("headers",headers);
					mav.addObject("pageNo",new Integer(pageNo));
					mav.addObject("totalPage", totalPage);
					mav.setViewName("notice_LoginSuccess");
					return mav;
					
				}
				@RequestMapping("/bbs_list3.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
				public ModelAndView getBoardList3(@RequestParam(value="page", required=false)String page){
					ModelAndView mav = new ModelAndView();
					int pageNo = 1;
					List<Article> headers=null;
					if(page==null){
						pageNo = service.getPageCount3(numPerPage);
						headers = service.getArticles3(pageNo, numPerPage);
					}else{
						pageNo = Integer.parseInt(page);
						headers = service.getArticles3(pageNo, numPerPage);
					}
					
					Integer totalPage = new Integer(service.getPageCount3(numPerPage));
					mav.addObject("headers",headers);
					mav.addObject("pageNo",new Integer(pageNo));
					mav.addObject("totalPage", totalPage);
					mav.setViewName("notice");
					return mav;
					
				}
				
				//리스트에서 글작성 버튼 눌렀을떄
				@RequestMapping(value="/bbs_write3.do", method=RequestMethod.GET)
				public String getBoardFormPage3(){
					return "notice_form";   //bbs_form으로 연결
					
				}
				
				//bbs_form에서 글쓰기저장눌렀을떄
				@RequestMapping(value="/bbs_write3.do", method=RequestMethod.POST)
				public ModelAndView getBoardSave3(Board board, HttpServletRequest req){
					ModelAndView mav = new ModelAndView();
					  MultipartFile uploadfile = board.getUploadfile();
					  if(uploadfile != null){
					   String fileName = uploadfile.getOriginalFilename();
					   board.setFile(fileName);
					             try {
					                 File file = new File("D:/★/spring_dev/eGovFrameDev-3.5.1-64bit/project_workspace/a_solo/src/main/webapp/image/" + fileName);
					                 uploadfile.transferTo(file);
					             } catch (IOException e) {
					                 e.printStackTrace();
					         }
					         }else{
					          board.setFile(null);
					  } 
					board.setIp(req.getRemoteAddr());
					service.insertProc3(board);   //service.insertPorc으로 넘김->db로
					mav.setViewName("redirect:/bbs_list_login.do");  //
					return mav;
					
				}
				
				
				//글 읽기
				@RequestMapping(value="/bbs_read3.do")
				public ModelAndView getReadProc3(@RequestParam("num")String num,HttpServletRequest request){
					HttpSession hs= request.getSession();
					 String id1 =(String)hs.getAttribute("userid");
			         System.out.println("-----------------------");
					System.out.println(id1);
					
					ModelAndView mav = new ModelAndView();
					bno = Integer.parseInt(num);
					Board article = null;
					
						article = service.getArticleProc3(bno);
						mav.addObject("article",article);
						mav.addObject("num",bno);
						mav.addObject("id1",id1 );
						
					mav.setViewName("notice_read");
					return mav;
				}
				
				//게시글 수정
				@RequestMapping(value="/bbs_modify3.do")
				public ModelAndView getEditPage3(){ 
					//수정페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();
					Board article = null;
				
						article = service.getArticleProc3(bno); //글 읽어오기
						mav.setViewName("notice_modify");
						mav.addObject("article",article);
						mav.addObject("num",new Integer(bno));
				
					return mav;
					
				}
				

				
				//수정한페이지 업데이트 (수정할입력다하고 수정버튼눌렀을떄)
				@RequestMapping(value="/bbs_update3.do", method=RequestMethod.POST)
				public ModelAndView requestUpdate3(Board board){
					
					ModelAndView mav = new ModelAndView();
						service.updateProc3(board);
						mav.setViewName("redirect:/bbs_read3.do?action=read&num="
											+board.getNum()); //업데이트 완료시 뷰페이지로 넘김
						
					return mav;
				}
				
				//게시글 삭제
				@RequestMapping(value="/bbs_delete3.do")
				public ModelAndView requestRemove3(){ 
					//삭제페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();

						service.removeProc3(bno);
						mav.setViewName("redirect:/bbs_list_login.do");

					return mav;
					
				}
				
				
				//############################### 4. 거래게시판 ######################################
				
				//로그인성공시
				@RequestMapping("/bbs_list_trade.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
				public ModelAndView getBoardList_trade(@RequestParam(value="page", required=false)String page){
					ModelAndView mav = new ModelAndView();
					int pageNo = 1;
					List<Article> headers=null;
					if(page==null){
						pageNo = service.getPageCount4(numPerPage);
						headers = service.getArticles4(pageNo, numPerPage);
					}else{
						pageNo = Integer.parseInt(page);
						headers = service.getArticles4(pageNo, numPerPage);
					}
					
					Integer totalPage = new Integer(service.getPageCount4(numPerPage));
					mav.addObject("headers",headers);
					mav.addObject("pageNo",new Integer(pageNo));
					mav.addObject("totalPage", totalPage);
					mav.setViewName("trade_LoginSuccess");
					return mav;
					
				}
				
				
				
				@RequestMapping("/bbs_list4.do")   //bbs_list.do가 들어오면 이 메쏘드가 처리하는 거
				public ModelAndView getBoardList4(@RequestParam(value="page", required=false)String page){
					ModelAndView mav = new ModelAndView();
					int pageNo = 1;
					List<Article> headers=null;
					if(page==null){
						pageNo = service.getPageCount4(numPerPage);
						headers = service.getArticles4(pageNo, numPerPage);
					}else{
						pageNo = Integer.parseInt(page);
						headers = service.getArticles4(pageNo, numPerPage);
					}
					
					Integer totalPage = new Integer(service.getPageCount4(numPerPage));
					mav.addObject("headers",headers);
					mav.addObject("pageNo",new Integer(pageNo));
					mav.addObject("totalPage", totalPage);
					mav.setViewName("trade");
					return mav;
					
				}
				
				//리스트에서 글작성 버튼 눌렀을떄
				@RequestMapping(value="/bbs_write4.do", method=RequestMethod.GET)
				public String getBoardFormPage4(){
					return "trade_form";   //bbs_form으로 연결
					
				}
				
				//bbs_form에서 글쓰기저장눌렀을떄
				@RequestMapping(value="/bbs_write4.do", method=RequestMethod.POST)
				public ModelAndView getBoardSave4(Board board, HttpServletRequest req){
					ModelAndView mav = new ModelAndView();
					  MultipartFile uploadfile = board.getUploadfile();
					  if(uploadfile != null){
					   String fileName = uploadfile.getOriginalFilename();
					   board.setFile(fileName);
					             try {
					                 File file = new File("D:/★/spring_dev/eGovFrameDev-3.5.1-64bit/project_workspace/a_solo/src/main/webapp/image/" + fileName);
					                 uploadfile.transferTo(file);
					             } catch (IOException e) {
					                 e.printStackTrace();
					         }
					         }else{
					          board.setFile(null);
					  } 
					board.setIp(req.getRemoteAddr());
					service.insertProc4(board);   //service.insertPorc으로 넘김->db로
					mav.setViewName("redirect:/bbs_list_trade.do");  //
					return mav;
					
				}
				
				//글 읽기
				@RequestMapping(value="/bbs_read4.do")
				public ModelAndView getReadProc4(@RequestParam("num")String num,HttpServletRequest request){
					 HttpSession hs= request.getSession();
					
			         String id1 =(String)hs.getAttribute("userid");
			         System.out.println("-----------------------");
					System.out.println(id1);
					ModelAndView mav = new ModelAndView();
					bno = Integer.parseInt(num);
					Board article = null;
					
						article = service.getArticleProc4(bno);
						
						mav.addObject("article",article);
						mav.addObject("num",bno);
						mav.addObject("id1",id1 );
					mav.setViewName("trade_read");
					return mav;
				}
				
				//게시글 수정
				@RequestMapping(value="/bbs_modify4.do")
				public ModelAndView getEditPage4(){ 
					//수정페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();
					Board article = null;
				
						article = service.getArticleProc4(bno); //글 읽어오기
						mav.setViewName("trade_modify");
						mav.addObject("article",article);
						mav.addObject("num",new Integer(bno));
				
					return mav;
					
				}
				

				
				//수정한페이지 업데이트 (수정할입력다하고 수정버튼눌렀을떄)
				@RequestMapping(value="/bbs_update4.do", method=RequestMethod.POST)
				public ModelAndView requestUpdate4(Board board){
					
					ModelAndView mav = new ModelAndView();
						service.updateProc4(board);
						mav.setViewName("redirect:/bbs_read4.do?action=read&num="
											+board.getNum()); //업데이트 완료시 뷰페이지로 넘김
						
					return mav;
				}
				
				//게시글 삭제
				@RequestMapping(value="/bbs_delete4.do")
				public ModelAndView requestRemove4(){ 
					//삭제페이지로 넘겨줘야됨
					ModelAndView mav = new ModelAndView();

						service.removeProc4(bno);
						mav.setViewName("redirect:/bbs_list_trade.do");

					return mav;
					
				}
				
				//댓글 쓰기
				@RequestMapping(value="/bbs_comment4.do")
				public ModelAndView  commentSaveProc4(Comment comment
														, @RequestParam("num")String rnum
														, HttpServletRequest req){
					
					ModelAndView mav = new ModelAndView ();
					comment.setIp(req.getRemoteAddr());
					comment.setRnum(Integer.parseInt(rnum));
					
					if (service.insertComment4(comment) > 0) {			 
						mav.setViewName("redirect:/bbs_read4.do?action=read&num="+rnum);
					}
					return mav;
				}
				
				//댓글삭제
				@RequestMapping(value="/delete_comment4.do")
				public ModelAndView commnetRemoveProc4(PasswordForm form
						       							, @RequestParam("read_num")String read_num){
					ModelAndView mav = new ModelAndView ();		 
					 
					if (service.deleteComment4(form) ) {
						mav.setViewName("redirect:/bbs_read4.do?action=read&num="+read_num);
					}
					return mav;
				}
				
					//검색
					@RequestMapping(value="/search.do", method=RequestMethod.POST)
					public ModelAndView search(Board board, @RequestParam("text")String text,@RequestParam(value="page", required=false)String page){
							ModelAndView mav = new ModelAndView();
							int pageNo = 1;
							List<Article> headers=null;
							if(page==null){
								pageNo = service.getPageCount4(numPerPage);
								headers = service.getSearch(text);
							}else{
								pageNo = Integer.parseInt(page);
								headers = service.getArticles4(pageNo, numPerPage);
							}
							Integer totalPage = new Integer(service.getPageCount4(numPerPage));
							mav.addObject("headers",headers);
							mav.addObject("pageNo",new Integer(pageNo));
							mav.addObject("totalPage", totalPage);
							mav.setViewName("trade_LoginSuccess");
							return mav;
							
						}
	}
				

