package lab.solo.dao;

import java.util.HashMap;
import java.util.List;

import lab.solo.vo.Article;
import lab.solo.vo.Board;
import lab.solo.vo.Comment;



import lab.solo.vo.PasswordForm;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	
	@Autowired         //스프링 컨테이너로부터 주입받아야됩
	private SqlSessionTemplate template;
	
	@Autowired
	private SqlSession sqlSession;
	//##################### 1.룸메이트게시판 #########################
	public void checkReplies(Article h, String rnum){
		List<Article> replies = null;
		int rn = Integer.parseInt(rnum);
		replies = template.selectList("lab.board.BoardMapper.checkReply",rn);
	
		if(replies!=null&&replies.size()>0){
			for(Article article: replies){
				checkReplies(article, String.valueOf(article.getNum()));
				h.addReply(article);
			}
		}
	}//checkReplies() end
	
	public List<Article> getArticleList(int start, int end){
		List<Article> lists=null;
		//mappers/BoardMapper.xml 
		HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
		hashmap.put("start", new Integer(start));
		hashmap.put("end", new Integer(end));
		lists = template.selectList("lab.board.BoardMapper.list", hashmap);
		
		for(Article article:lists){
			checkReplies(article, "-"+article.getNum());
		}          //list 페이지에서 
		System.out.println(lists);
		return lists;
		
	}//getArticleList() end
	
	public int getPageCount(){
		int max=0;
		max= template.selectOne("lab.board.BoardMapper.pageCount");
		return max;
	}//getPageCount() end
	
	
	//글쓰기
	public int insertArticle(Board form, boolean isReply){
		if(isReply){
			return template.insert("lab.board.BoardMapper.insert_bbsreply",form);
				
		}else{
			return template.insert("lab.board.BoardMapper.insert_bbs",form);
		}
	}
	
	//글 읽기
	public Board getArticle(int num){
			Board board = null;
			List<Comment> comments =null;
				//조회수 증가
				template.update("lab.board.BoardMapper.rcount_bbs",num);
				
				//글번호로 글 내용 조회
				board = template.selectOne("lab.board.BoardMapper.read_bbs",num);

				//댓글 조회, 저장
				comments = template.selectList("lab.board.BoardMapper.read_comment",num);
				for(Comment comment : comments)
					board.addComment(comment);
				
			
			return board;
		}//getArticle() end
	
	//게시글 수정
	public int updateArticle(Board form, String table){
		if(table.equals("bbs")){
			return template.update("lab.board.BoardMapper.modify_bbs",form);
			
		}else{
			return template.update("lab.board.BoardMapper.modify_bbsreply",form);
		}
	}
	


	//게시글 삭제
		public int removeArticle(int bbsid){

				return template.delete("lab.board.BoardMapper.remove_bbs",bbsid);
				
	
		}
		
		//댓글 쓰기 
		public int insertComment(Comment form) {
			 return sqlSession.insert("lab.board.BoardMapper.comment_save", form);
		}

		//댓글삭제
		public int deleteComment(PasswordForm form) {
			return sqlSession.delete("lab.board.BoardMapper.comment_remove", form); 
		}
		
		
		//구별 리스트
		//남구
		public List<Article> getArticleListnamgu(int start, int end){
			List<Article> lists=null;
			//mappers/BoardMapper.xml 
			HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
			hashmap.put("start", new Integer(start));
			hashmap.put("end", new Integer(end));
			lists = template.selectList("lab.board.BoardMapper.listnamgu", hashmap);
			
			for(Article article:lists){
				checkReplies(article, "-"+article.getNum());
			}          //list 페이지에서 
			System.out.println(lists);
			return lists;
			
		}//getArticleList() end
		
		public int getPageCountnamgu(){
			int max=0;
			max= template.selectOne("lab.board.BoardMapper.pageCountnamgu");
			return max;
		}//getPageCount() end
		
		//해운대
				public List<Article> getArticleListhea(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.listhea", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCounthea(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCounthea");
					return max;
				}//getPageCount() end
				
				//서구
				public List<Article> getArticleListseo(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.listseo", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCountseo(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCountseo");
					return max;
				}//getPageCount() end
				
				//사하구
				public List<Article> getArticleListsa(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.listsa", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCountsa(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCountsa");
					return max;
				}//getPageCount() end
				
				//금정구
				public List<Article> getArticleListgum(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.listgum", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCountgum(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCountgum");
					return max;
				}//getPageCount() end
				
				//북구
				public List<Article> getArticleListbuk(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.listbuk", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCountbuk(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCountbuk");
					return max;
				}//getPageCount() end
				
				//연제구
				public List<Article> getArticleListyeon(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.listyeon", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCountyeon(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCountyeon");
					return max;
				}//getPageCount() end
		
		//##################### 2.자유게시판 #########################

				public List<Article> getArticleList2(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.list2", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCount2(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCount2");
					return max;
				}//getPageCount() end

				//글쓰기
				public int insertArticle2(Board form) {
					return template.insert("lab.board.BoardMapper.insert2_bbs",form);
				}

				//글 읽기
				public Board getArticle2(int num){
						Board board = null;
						List<Comment> comments =null;
							//조회수 증가
							template.update("lab.board.BoardMapper.rcount2_bbs",num);
							
							//글번호로 글 내용 조회
							board = template.selectOne("lab.board.BoardMapper.read2_bbs",num);

							//댓글 조회, 저장
							comments = template.selectList("lab.board.BoardMapper.read2_comment",num);
							for(Comment comment : comments)
								board.addComment(comment);
							
						
						return board;
					}//getArticle() end
				
				//게시글 수정
				public int updateArticle2(Board form){

						return template.update("lab.board.BoardMapper.modify2_bbs",form);
						
				}
				
				//게시글 삭제
				public int removeArticle2(int bbsid){

						return template.delete("lab.board.BoardMapper.remove2_bbs",bbsid);
								
					
				}
				//댓글 쓰기 
				public int insertComment2(Comment form) {
						return sqlSession.insert("lab.board.BoardMapper.comment2_save", form);
				}

						//댓글삭제
				public int deleteComment2(PasswordForm form) {
						return sqlSession.delete("lab.board.BoardMapper.comment2_remove", form); 
				}
				
				//검색
				public List<Article> getSearch2(String text1) {
					HashMap<String,String> hashmap= new HashMap<String,String>();
					String text= "%"+text1+"%";
					hashmap.put("subject", text);
					return sqlSession.selectList("lab.board.BoardMapper.search2",hashmap);
				}
	
				//##################### 3.공지사항#########################

				public List<Article> getArticleList3(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.list3", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCount3(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCount3");
					return max;
				}//getPageCount() end

				//글쓰기
				public int insertArticle3(Board form) {
					return template.insert("lab.board.BoardMapper.insert3_bbs",form);
				}

				//글 읽기
				public Board getArticle3(int num){
						Board board = null;
						List<Comment> comments =null;
							//조회수 증가
							template.update("lab.board.BoardMapper.rcount3_bbs",num);
							
							//글번호로 글 내용 조회
							board = template.selectOne("lab.board.BoardMapper.read3_bbs",num);

				
						return board;
					}//getArticle() end
				
				//게시글 수정
				public int updateArticle3(Board form){

						return template.update("lab.board.BoardMapper.modify3_bbs",form);
						
				}
				
				//게시글 삭제
				public int removeArticle3(int bbsid){

						return template.delete("lab.board.BoardMapper.remove3_bbs",bbsid);
								
					
				}
				
				//##################### 4.거래 게시판 #########################

				public List<Article> getArticleList4(int start, int end){
					List<Article> lists=null;
					//mappers/BoardMapper.xml 
					HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
					hashmap.put("start", new Integer(start));
					hashmap.put("end", new Integer(end));
					lists = template.selectList("lab.board.BoardMapper.list4", hashmap);
					
					for(Article article:lists){
						checkReplies(article, "-"+article.getNum());
					}          //list 페이지에서 
					System.out.println(lists);
					return lists;
					
				}//getArticleList() end
				
				public int getPageCount4(){
					int max=0;
					max= template.selectOne("lab.board.BoardMapper.pageCount4");
					return max;
				}//getPageCount() end

				//글쓰기
				public int insertArticle4(Board form) {
					return template.insert("lab.board.BoardMapper.insert4_bbs",form);
				}

				//글 읽기
				public Board getArticle4(int num){
						Board board = null;
						List<Comment> comments =null;
							//조회수 증가
							template.update("lab.board.BoardMapper.rcount4_bbs",num);
							
							//글번호로 글 내용 조회
							board = template.selectOne("lab.board.BoardMapper.read4_bbs",num);

							//댓글 조회, 저장
							comments = template.selectList("lab.board.BoardMapper.read4_comment",num);
							for(Comment comment : comments)
								board.addComment(comment);
							
						
						return board;
					}//getArticle() end
				
				//게시글 수정
				public int updateArticle4(Board form){

						return template.update("lab.board.BoardMapper.modify4_bbs",form);
						
				}
				
				//게시글 삭제
				public int removeArticle4(int bbsid){

						return template.delete("lab.board.BoardMapper.remove4_bbs",bbsid);
								
					
				}
				//댓글 쓰기 
				public int insertComment4(Comment form) {
						return sqlSession.insert("lab.board.BoardMapper.comment4_save", form);
				}

						//댓글삭제
				public int deleteComment4(PasswordForm form) {
						return sqlSession.delete("lab.board.BoardMapper.comment4_remove", form); 
				}

				//검색
				public List<Article> getSearch(String text1) {
					HashMap<String,String> hashmap= new HashMap<String,String>();
					String text= "%"+text1+"%";
					hashmap.put("subject", text);
					return sqlSession.selectList("lab.board.BoardMapper.search",hashmap);
				}
	
	
}