package lab.solo.service;

import java.util.List;

import lab.solo.vo.Article;
import lab.solo.vo.Board;
import lab.solo.vo.Comment;
import lab.solo.vo.PasswordForm;

public interface BoardService {
	//1.룸메이트게시판
	public List<Article> getArticles(int page, int numPerPage);
	public int getPageCount(int numPerPage);
	public int insertProc(Board form, boolean isReply); //게시글쓰기
	public Board getArticleProc(int num); //글 읽기
	public int updateProc(Board form, String table);  //게시글 수정
	public int removeProc(int bbsid);
	public int insertComment(Comment form);
	public boolean deleteComment(PasswordForm form);
	
	//1-1 구별 리스트
	//남구
	public List<Article> getArticlesnamgu(int page, int numPerPage);
	public int getPageCountnamgu(int numPerPage);
	//해운대구
	public List<Article> getArticleshea(int page, int numPerPage);
	public int getPageCounthea(int numPerPage);
	//서구
	public List<Article> getArticlesseo(int page, int numPerPage);
	public int getPageCountseo(int numPerPage);
	//사하구
	public List<Article> getArticlessa(int page, int numPerPage);
	public int getPageCountsa(int numPerPage);
	//금정구
	public List<Article> getArticlesgum(int page, int numPerPage);
	public int getPageCountgum(int numPerPage);	
	//북구
	public List<Article> getArticlesbuk(int page, int numPerPage);
	public int getPageCountbuk(int numPerPage);	
	//연제구
	public List<Article> getArticlesyeon(int page, int numPerPage);
	public int getPageCountyeon(int numPerPage);	
	
	//2.자유게시판
		public List<Article> getArticles2(int page, int numPerPage);
		public int getPageCount2(int numPerPage);
		public int insertProc2(Board form);
		public Board getArticleProc2(int num); //글 읽기
		public int updateProc2(Board form);  //게시글 수정
		public int removeProc2(int bbsid);
		public int insertComment2(Comment form);
		public boolean deleteComment2(PasswordForm form);
		public List<Article> getSearch2(String text);
		
		//3.공지사항
		public List<Article> getArticles3(int page, int numPerPage);
		public int getPageCount3(int numPerPage);
		public int insertProc3(Board form);
		public Board getArticleProc3(int num); //글 읽기
		public int updateProc3(Board form);  //게시글 수정
		public int removeProc3(int bbsid);
		
		//4.거래게시판
		public List<Article> getArticles4(int page, int numPerPage);
		public int getPageCount4(int numPerPage);
		public int insertProc4(Board form);
		public Board getArticleProc4(int num); //글 읽기
		public int updateProc4(Board form);  //게시글 수정
		public int removeProc4(int bbsid);
		public int insertComment4(Comment form);
		public boolean deleteComment4(PasswordForm form);
		public List<Article> getSearch(String text);

}
