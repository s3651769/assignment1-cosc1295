package main;
import java.util.*;

public class Adult extends Users {
	private boolean spouse;
	private boolean dependents;
	private Scanner input = new Scanner(System.in);
	
	public Adult(String usernum, String firstname, String lastname, String imagepath, String status, int age, String gender, boolean spouse, boolean dependents){
		// call the super class constructor
			super(usernum, firstname, lastname, imagepath, status, age, gender);
			this.spouse = spouse;
			this.dependents = dependents;
	}
	 
	public boolean havespouse() {
		return spouse;
	}
	
	public void newspouse() {
		spouse = true;
	}
	
	public boolean havedependents() {
		return dependents;
	}
	

}
