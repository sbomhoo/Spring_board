package lab.solo.service;

import java.util.List;

import lab.solo.dao.BoardDAO;
import lab.solo.vo.Article;
import lab.solo.vo.Board;
import lab.solo.vo.Comment;
import lab.solo.vo.PasswordForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;
	

	//#########################1. 룸메이트게시판 #######################
	
	@Override
	public List<Article> getArticles(int page, int numPerPage) {
		int start = (page-1)*numPerPage;
		int end = page*numPerPage;
		List<Article> articles = null;
		articles = dao.getArticleList(start, end);
		
		for(Article header: articles){
			//답변글이 있는지 여부를 확인
			
		}
		return articles;
	}
	
	
	public int getPageCount(int numPerPage){
		int pageCount = (int)Math.ceil(dao.getPageCount()/(double)numPerPage); //Math.ceil 반올림
		//글이하나도 없을때 1리턴
		pageCount = Math.max(pageCount, 1);
		return pageCount;
	}


	//게시글 쓰기
		public int insertProc(Board form, boolean isReply){
			String contents = form.getContents();
			if(form.getHtml().equals("N")){
				form.setContents(contents.replaceAll("<", "&lt;"));
			}
			if(isReply){
				String rnum = form.getRnum();
				form.setRnum("-"+rnum);
			}
			return dao.insertArticle(form, isReply);
		}
		
		//글 읽기
		public Board getArticleProc(int num){
			return dao.getArticle(num);
		}
		
		//게시글 수정
		public int updateProc(Board form, String table) {
		
			return dao.updateArticle(form,table);
		}

		//게시글삭제
		public int removeProc(int bbsid) {
			return  dao.removeArticle(bbsid);
		}


		//댓글 쓰기
		public int insertComment(Comment form) {
			return dao.insertComment(form);
		}


		//댓글 삭제
		public boolean deleteComment(PasswordForm form) {
			boolean success = false;
			   if(dao.deleteComment(form)>0){
				   success = true;
			   }
			   return success;
		}
		
		//구별 리스트
		//남구
		@Override
		public List<Article> getArticlesnamgu(int page, int numPerPage) {
			int start = (page-1)*numPerPage;
			int end = page*numPerPage;
			List<Article> articles = null;
			articles = dao.getArticleListnamgu(start, end);
			
			for(Article header: articles){
				//답변글이 있는지 여부를 확인
				
			}
			return articles;
		}
		
		
		public int getPageCountnamgu(int numPerPage){
			int pageCount = (int)Math.ceil(dao.getPageCountnamgu()/(double)numPerPage); //Math.ceil 반올림
			//글이하나도 없을때 1리턴
			pageCount = Math.max(pageCount, 1);
			return pageCount;
		}
		
		//해운대
				@Override
				public List<Article> getArticleshea(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleListhea(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
				
				public int getPageCounthea(int numPerPage){
					int pageCount = (int)Math.ceil(dao.getPageCounthea()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
				
				//서구
				@Override
				public List<Article> getArticlesseo(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleListseo(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
				
				public int getPageCountseo(int numPerPage){
					int pageCount = (int)Math.ceil(dao.getPageCountseo()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
				//사하구
				@Override
				public List<Article> getArticlessa(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleListsa(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
				
				public int getPageCountsa(int numPerPage){
					int pageCount = (int)Math.ceil(dao.getPageCountsa()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
				
				//금정구
				@Override
				public List<Article> getArticlesgum(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleListgum(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
				
				public int getPageCountgum(int numPerPage){
					int pageCount = (int)Math.ceil(dao.getPageCountgum()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
				
				//북구
				@Override
				public List<Article> getArticlesbuk(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleListbuk(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
				
				public int getPageCountbuk(int numPerPage){
					int pageCount = (int)Math.ceil(dao.getPageCountbuk()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
				
				//연제구
				@Override
				public List<Article> getArticlesyeon(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleListyeon(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
				
				public int getPageCountyeon(int numPerPage){
					int pageCount = (int)Math.ceil(dao.getPageCountyeon()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
		
		//#########################2. 자유게시판 #######################
		@Override
		public List<Article> getArticles2(int page, int numPerPage) {
			int start = (page-1)*numPerPage;
			int end = page*numPerPage;
			List<Article> articles = null;
			articles = dao.getArticleList2(start, end);
			
			for(Article header: articles){
				//답변글이 있는지 여부를 확인
				
			}
			return articles;
		}


		@Override
		public int getPageCount2(int numPerPage) {
			int pageCount = (int)Math.ceil(dao.getPageCount2()/(double)numPerPage); //Math.ceil 반올림
			//글이하나도 없을때 1리턴
			pageCount = Math.max(pageCount, 1);
			return pageCount;
		}

		public int insertProc2(Board form) {
			String contents = form.getContents();
			if(form.getHtml().equals("N")){
				form.setContents(contents.replaceAll("<", "&lt;"));
			}
			return dao.insertArticle2(form);
			
		
		}
		
		//글 읽기
		public Board getArticleProc2(int num){
				return dao.getArticle2(num);
		}
		
		//게시글 수정
		public int updateProc2(Board form) {
				
				return dao.updateArticle2(form);
		}
		//게시글삭제
		public int removeProc2(int bbsid) {
				return  dao.removeArticle2(bbsid);
		}
		//댓글 쓰기
		public int insertComment2(Comment form) {
				return dao.insertComment2(form);
		}


		//댓글 삭제
		public boolean deleteComment2(PasswordForm form) {
				boolean success = false;
			if(dao.deleteComment2(form)>0){
						   success = true;
					   }
					   return success;
		}
		
		//검색
		@Override
		public List<Article> getSearch2(String text) {
			List<Article> articles = null;
			articles = dao.getSearch2(text);
			
			for(Article header: articles){
				//답변글이 있는지 여부를 확인
				
			}
			return articles;
		}
		
		//#########################3. 공지사항 #######################
				@Override
				public List<Article> getArticles3(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleList3(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}


				@Override
				public int getPageCount3(int numPerPage) {
					int pageCount = (int)Math.ceil(dao.getPageCount3()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}

				public int insertProc3(Board form) {
					String contents = form.getContents();
					if(form.getHtml().equals("N")){
						form.setContents(contents.replaceAll("<", "&lt;"));
					}
					return dao.insertArticle3(form);
					
				
				}
				
				//글 읽기
				public Board getArticleProc3(int num){
						return dao.getArticle3(num);
				}
				
				//게시글 수정
				public int updateProc3(Board form) {
						
						return dao.updateArticle3(form);
				}
				//게시글삭제
				public int removeProc3(int bbsid) {
						return  dao.removeArticle3(bbsid);
				}
				
				//######################### 4. 거래게시판 #######################
				@Override
				public List<Article> getArticles4(int page, int numPerPage) {
					int start = (page-1)*numPerPage;
					int end = page*numPerPage;
					List<Article> articles = null;
					articles = dao.getArticleList4(start, end);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}


				@Override
				public int getPageCount4(int numPerPage) {
					int pageCount = (int)Math.ceil(dao.getPageCount4()/(double)numPerPage); //Math.ceil 반올림
					//글이하나도 없을때 1리턴
					pageCount = Math.max(pageCount, 1);
					return pageCount;
				}
				
				public int insertProc4(Board form) {
					String contents = form.getContents();
					if(form.getHtml().equals("N")){
						form.setContents(contents.replaceAll("<", "&lt;"));
					}
					return dao.insertArticle4(form);
					
				
				}
				
				//글 읽기
				public Board getArticleProc4(int num){
						return dao.getArticle4(num);
				}
				
				//게시글 수정
				public int updateProc4(Board form) {
						
						return dao.updateArticle4(form);
				}
				//게시글삭제
				public int removeProc4(int bbsid) {
						return  dao.removeArticle4(bbsid);
				}
				//댓글 쓰기
				public int insertComment4(Comment form) {
						return dao.insertComment4(form);
				}


				//댓글 삭제
				public boolean deleteComment4(PasswordForm form) {
						boolean success = false;
					if(dao.deleteComment4(form)>0){
								   success = true;
							   }
							   return success;
				}


				//검색
				@Override
				public List<Article> getSearch(String text) {
					List<Article> articles = null;
					articles = dao.getSearch(text);
					
					for(Article header: articles){
						//답변글이 있는지 여부를 확인
						
					}
					return articles;
				}
				
}
