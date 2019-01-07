package lab.solo.vo;

public class Comment {
	private int num, rnum;
	private String writer, contents, password, idate, ip;
	
	public Comment(){}

	public Comment(int num, int rnum, String writer, String contents,
			String password, String idate, String ip) {
		super();
		this.num = num;
		this.rnum = rnum;
		this.writer = writer;
		this.contents = contents;
		this.password = password;
		this.idate = idate;
		this.ip = ip;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdate() {
		return idate;
	}

	public void setIdate(String idate) {
		this.idate = idate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
