package lab.solo.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

//게시글
public class Board {
	protected int num, rcount, vcount;
	protected String rnum, contents,  ip, subject, writer, idate,area,file,category ;
	protected int size;
	protected String html ="N";
	protected Vector<Comment> comments;
	private MultipartFile uploadfile;
	
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	//댓글 저장할 벡터 생성
	public Board(){
		comments = new Vector<Comment>();
	}
	
	//댓글 저장
	public void addComment(Comment a){
		comments.add(a);
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
	public int getVcount() {
		return vcount;
	}
	public void setVcount(int vcount) {
		this.vcount = vcount;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Vector<Comment> getComments() {
		return comments;
	}
	public void setComments(Vector<Comment> comments) {
		this.comments = comments;
	}

	public MultipartFile getUploadfile() {
		return uploadfile;
	}


	
	
}