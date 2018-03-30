package main;

public class Children extends Users {
	private String parent1usernum;
	private String parent2usernum;
	
	public Children(String usernum, String firstname, String lastname, String imagepath, String status, int age, String gender, String parent1usernum, String parent2usernum) {
		// call the super class constructor
			super(usernum, firstname, lastname, imagepath, status, age, gender);
			this.parent1usernum = parent1usernum;
			this.parent2usernum = parent2usernum;
	}
	
	public String getparent1() {
		return parent1usernum;
		}
	public String getparent2() {
		return parent2usernum;
		}
	
}
