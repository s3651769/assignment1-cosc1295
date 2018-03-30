package main;
import java.util.*;

public class Menu {
 private Scanner input = new Scanner(System.in);
 private int menuselection = 0;
 Driver driver = new Driver();
 
 //hard coded menu options
private void printmenu() {
		System.out.println("Menu Options: ");
		System.out.println("Type 1 to select an user");
		System.out.println("Type 2 to select an user and print all relationships");
		System.out.println("Type 3 to add new user");
		System.out.println("Type 4 to add new relationship");
		System.out.println("Type 5 to delete user");
		System.out.println("Type 0 to exit program");
		System.out.println();
		
		 
	}
 
public void selectmenu() {
	do {
		printmenu();
		
		 boolean done = false;
	     do {
	        try {
	       	System.out.println("Please enter your selection from the menu:");
	       	menuselection = input.nextInt();
	           done = true;
	        }
	        catch(InputMismatchException ex){
	           System.out.println("Invalid input. Enter again : ");
	           input.nextLine();
	        }
	     	} while (!done); 
	     

	     switch(menuselection) {
	     case 0:
	         System.out.println("Exit");
	         break;
	     case 1:
	         System.out.println("You have chosen to select an user profile");
	         driver.displayprofile(); // accesses driver to show user profile
	         System.out.println();
	         System.out.println();
	         System.out.println("Returning to Menu");
	         System.out.println();
	         break;
	     case 2:
	         System.out.println("You have chosen to select an user profile and show their relationships");
	         driver.displayrelationship(); // accesses driver to show relationships with selected person
	         System.out.println();
	         System.out.println();
	         System.out.println("Returning to Menu");
	         System.out.println();
	         break;
	     case 3:
	         System.out.println("You have chosen to add a new user"); // accesses driver to add a new user to array
	         driver.adduser();
	         System.out.println();
	         System.out.println();
	         System.out.println("Returning to Menu");
	         System.out.println();
	         break;
	     case 4:
	         System.out.println("You have chosen to add a new relationship"); // accesses driver to add a new relationship to array
	         driver.addrelationship();
	         System.out.println();
	         System.out.println();
	         System.out.println("Returning to Menu");
	         System.out.println();
	         break;
	     case 5:
	         System.out.println("You have chosen to delete an user"); // accesses driver to remove user from array
	         driver.deleteuser();
	         System.out.println();
	         System.out.println();
	         System.out.println("Returning to Menu");
	         System.out.println();
	         break;
	     default:
	         System.out.println("Not an available option");
	 };
	} while (menuselection !=0);
	

}
 


}