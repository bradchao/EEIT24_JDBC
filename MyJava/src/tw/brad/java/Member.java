package tw.brad.java;

public class Member {
	private int id;
	private String account, realname;
	public Member(int id, 
			String account, 
			String realname) {
		this.id = id;
		this.account = account;
		this.realname = realname;
	}
	
	public int getId() {
		return id;
	}
	public String getAccount() {
		return account;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
}
