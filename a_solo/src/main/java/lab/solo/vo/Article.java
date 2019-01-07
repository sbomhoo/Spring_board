package lab.solo.vo;

import java.util.List;
import java.util.Vector;

public class Article {
	private String subject, writer, idate,area,category ;
	private int num, rcount, rn; //rn ->순차적으로 보이는 글번호 글삭제해도 글번호 순차적으로
	private List<Article> replies;
	
	public Article(){
		replies = new Vector<Article>();
	}
	
	

	
	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public Article(String subject, String writer, String idate, String area,String category,
			int num, int rcount, int rn, List<Article> replies) {
		super();
		this.subject = subject;
		this.writer = writer;
		this.idate = idate;
		this.area = area;
		this.num = num;
		this.rcount = rcount;
		this.rn = rn;
		this.replies = replies;
		this.category = category;
		replies = new Vector<Article>();
	}




	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public int getRn() {
		return rn;
	}


	public void setRn(int rn) {
		this.rn = rn;
	}


	public int getSize(){ return replies.size();}
	
	public void addReply(Article r){
		replies.add(r);
	}



	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public List<Article> getReplies() {
		return replies;
	}
	public void setReplies(List<Article> replies) {
		this.replies = replies;
	}
	
}
