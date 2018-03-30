package main;

public abstract class Users {
	private String usernum;
	private String firstname;
	private String lastname;
	private String imagepath;
	private String status;
	private int age;
	private String gender;


	public Users(String usernum, String firstname, String lastname, String imagepath, String status, int age, String gender) {
		this.usernum=usernum;
		this.firstname=firstname;
		this.lastname=lastname;
		this.imagepath=imagepath;
		this.status=status;
		this.age=age;
		this.gender=gender;
	}
	
	public String getusernum() {
		return usernum;
	}

	public String getfirstName() {
		return firstname;
	}

	public String getlastName() {
		return lastname;
	}

	public int getage() {
		return age;
	}

	public String getimagepath() {
		return imagepath;
	}

	public String getstatus() {
		return status;
	}
	public String getgender() {
		return gender;
	}
	
}	
