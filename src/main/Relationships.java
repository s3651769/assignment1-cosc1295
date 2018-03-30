package main;

public class Relationships {
	private String usernum1;
	private String usernum2;
	private String relationshiptype;
	
	public Relationships(String usernum1, String usernum2, String relationshiptype) {
		this.usernum1=usernum1;
		this.usernum2=usernum2;
		this.relationshiptype=relationshiptype;
	}
	
	public String getuser1() {
		return usernum1;
	}
	
	public String getuser2() {
		return usernum2;
	}
	
	public String relationshiptype() {
		return relationshiptype;
	}
}
