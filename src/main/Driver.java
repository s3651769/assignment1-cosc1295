package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	//network path for storage of prior users
	   private Path file_name_user = Paths.get("userdb.txt").toAbsolutePath();
	 //network path for storage of prior relationships
	   private Path file_name_relationship = Paths.get("relationshipdb.txt").toAbsolutePath();
	   private int selectionnumber = 0; // to match with the user table
	   private String usernum = "na"; // to match with relationships table
	   //array to reference for users in system   
	   private ArrayList<Users> userdatabase = new ArrayList<Users>();
	   private ArrayList<Relationships> relationshipdatabase = new ArrayList<Relationships>();
	   private Scanner input = new Scanner(System.in);
	   private File file = new File(file_name_user.toString()); {
		
	   //error handling when the network user file cannot be found
		   try {
			   input = new Scanner(file);
			   input.useDelimiter(",");
		   } catch (FileNotFoundException e) {
			   System.out.println("Cannot access network - Try Again Later");
			   e.printStackTrace();
		   }
		   
		//read in users from database  
		   while(input.hasNext()) {
			 String usernumber = input.next();
	         String firstName = input.next();
	         String lastName = input.next();
	         String imagepath = input.next();
	         String status = input.next();
	         int age = input.nextInt();
	         String gender = input.next();
	   //differentiating children when read in
	         if (age >= 16) {
	        	boolean spouse = input.nextBoolean();
	         	boolean dependents = input.nextBoolean();
	        	 userdatabase.add(new Adult(usernumber,firstName,lastName,imagepath, status, age, gender, spouse, dependents));
	        	 }
	         else {
	        	 String parent1usernum = input.next();
	        	 String parent2usernum = input.next();
	        	 userdatabase.add(new Children(usernumber,firstName,lastName,imagepath, status, age, gender, parent1usernum, parent2usernum));	
	        	 }
		   }	
	}
	   //error handling when the network relationship file cannot be found
	   private File file2 = new File(file_name_relationship.toString()); {
		   
		   //error handling when the network user file cannot be found
			   try {
				   input = new Scanner(file2);
				   input.useDelimiter(",");
			   } catch (FileNotFoundException e) {
				   System.out.println("Cannot access network - Try Again Later");
				   e.printStackTrace();
			   }
			   
			//read in names, age, image path and status   
			   while(input.hasNext()) {
				 String user1 = input.next();
		         String user2 = input.next();
		         String relationshiptype = input.next();
		         relationshipdatabase.add(new Relationships(user1, user2, relationshiptype));
			   }	
		}
	   
	   
	   // method to select users - reuse for displaying profile - showing relationships - etc.
	   private void selection() {
		   input = new Scanner(System.in);
		   String selectionname; // input to match with first or last name
		   
		   System.out.println("Please input the first or last name of the person you wish to select");
		   
		   selectionname = input.next();
		   for (int i=0; i < userdatabase.size(); i++) { // loop through to match with array names that contains selection
			   if (userdatabase.get(i).getfirstName().contains(selectionname)) {
				   System.out.println(userdatabase.get(i).getusernum()+ ": "+userdatabase.get(i).getfirstName() + " " + userdatabase.get(i).getlastName());
				   selectionnumber=i;
				   usernum = userdatabase.get(i).getusernum();
			   }
			   else if (userdatabase.get(i).getlastName().contains(selectionname)) {
				   System.out.println(userdatabase.get(i).getusernum()+ ": "+userdatabase.get(i).getfirstName() + " " + userdatabase.get(i).getlastName());
				   selectionnumber=i;
				   usernum = userdatabase.get(i).getusernum();
			   	}
			   }
		   };
		   
		   public void displayprofile() {
			   selection();
			   // selection to ask whether this profile should be displayed
			   input = new Scanner(System.in);
			   int userprofile = 0; // option 1 or 2
			 System.out.println("Would you like to display this users profile?");
			 	System.out.println();
				System.out.println("Type 1 for yes");
				System.out.println("Type 2 to return to menu");
				// error checking preventing mismatch and also ensuring only valid options are chosen
			 boolean done = false;
			      do {
			    	  try {
			        	System.out.println("Please enter in the space below");
			        	userprofile = input.nextInt();
			        	if (userprofile == 1 | userprofile ==2) {
			        		done = true;
			        	}
			        	else {
			        		System.out.println("Invalid input. Enter again : ");
			        	}
			         }
			    	  catch(InputMismatchException ex){
			            System.out.println("Invalid input. Enter again : ");
			            input.nextLine();
			    	  }
			      	} while (!done);
				// printing out profile 
			      if(userprofile==1) {
					System.out.println("User profile: " + userdatabase.get(selectionnumber).getfirstName() + " " + userdatabase.get(selectionnumber).getlastName());
					System.out.println("Age: " + userdatabase.get(selectionnumber).getage());
					System.out.println("Image: " + userdatabase.get(selectionnumber).getimagepath());
					System.out.println("Status: " + userdatabase.get(selectionnumber).getstatus());
					System.out.println("Gender: " + userdatabase.get(selectionnumber).getgender());
				}
			   
		 }
		   
		   public void displayrelationship() {
			   selection(); // select user you want to show relationships for
			   for (int i=0; i<relationshipdatabase.size(); i++)
			   if(relationshipdatabase.get(i).getuser1().equals(usernum)) {
				  String usernum2 = relationshipdatabase.get(i).getuser2(); 
				  for (int j=0; j<userdatabase.size(); j++) {
					  if(userdatabase.get(j).getusernum().equals(usernum2)) {
						  System.out.println(userdatabase.get(selectionnumber).getfirstName()+" "+userdatabase.get(selectionnumber).getlastName()+"'s "+
								  relationshipdatabase.get(i).relationshiptype()+" is "+userdatabase.get(j).getfirstName()+" "+userdatabase.get(j).getlastName());
					  }
				  }
			   }
		   }
		   
		   public void adduser() {
			 input = new Scanner(System.in);
			 int userprofile = 0; // option 1 or 2
			 String gender = "did not disclose";
			 String imagepath;
			 String status;
			 System.out.println("Please begin creating a new user profile by entering a first name");
			 String newfirstname = input.next();
			 System.out.println("Enter last name");
			 String newlastname = input.next();
			 System.out.println("Enter age");
			 boolean done = false;
			 int newage = 0;
		      do {
		    	  try {
		        	System.out.println("Please enter in the space below");
		        	newage = input.nextInt();
		        		done = true;
		         }
		    	  catch(InputMismatchException ex){
		            System.out.println("Invalid input. Enter again : ");
		            input.nextLine();
		    	  }
		      	} while (!done);
		      		if (newage<16) {
		    	  System.out.println("As user is under 16, parents on the network are required");
		    	  System.out.println("To proceed to select the parent users type 1, to return to the menu type 2");
		    	// error checking preventing mismatch and also ensuring only valid options are chosen
					done = false;
					      do {
					    	  try {
					        	System.out.println("Please enter in the space below");
					        	userprofile = input.nextInt();
					        	if (userprofile == 1 | userprofile ==2) {
					        		done = true;
					        	}
					        	else {
					        		System.out.println("Invalid input. Enter again : ");
					        	}
					         }
					    	  catch(InputMismatchException ex){
					            System.out.println("Invalid input. Enter again : ");
					            input.nextLine();
					    	  }
					      	} while (!done);
						// selecting parents from existing users
					      if(userprofile==1) {
					    	 selection();
					    	 String newparent1 = userdatabase.get(selectionnumber).getusernum();
					    	 System.out.println("Parent 1: " + userdatabase.get(selectionnumber).getfirstName()+" "+ userdatabase.get(selectionnumber).getlastName());
					    	 String newparent2 = "na";
					    	 done = false;
					    	 do {
					    		 selection();
						    	 newparent2 = userdatabase.get(selectionnumber).getusernum();
						    	 if (newparent1==newparent2) { // Preventing duplication of parents
						    		 System.out.println("Both parents are the same, try inputting the other parent");
						    	 }
						    	 else {
						    		 done=true;
						    	 }
					    	 }
					    	 while (!done);
					    	 System.out.println("Parent 2: " + userdatabase.get(selectionnumber).getfirstName()+" "+ userdatabase.get(selectionnumber).getlastName());
					    	 
					    	 
					    	 String usernum = "C"+(userdatabase.size());
					    	 System.out.println("Please select gender from the following options: ");
					    	 System.out.println("Type 1 for female, type 2 for male and type 3 for do not with to disclose");
					    	// error checking preventing mismatch and also ensuring only valid options are chosen
					    	 userprofile = 0;
					    	 done = false;
							      do {
							    	  try {
							        	System.out.println("Please enter in the space below");
							        	userprofile = input.nextInt();
							        	if (userprofile == 1 | userprofile ==2 | userprofile ==3) {
							        		done = true;
							        	}
							        	else {
							        		System.out.println("Invalid input. Enter again : ");
							        	}
							         }
							    	  catch(InputMismatchException ex){
							            System.out.println("Invalid input. Enter again : ");
							            input.nextLine();
							    	  }
							      	} while (!done);
							      // Consistency across gender entry
							      if(userprofile == 1) {
							    	  gender = "female";
							      }
							      else if (userprofile == 2) {
							    	  gender = "male";
							      }
							      else gender = "did not disclose";
							      System.out.println("Please select whether you would like an image associated with the profile : ");
							    	 System.out.println("Type 1 for yes and then the image path, or 2 for no");
							    	// error checking preventing mismatch and also ensuring only valid options are chosen
							    	 userprofile = 0;
							    	 done = false;
									      do {
									    	  try {
									        	System.out.println("Please enter in the space below");
									        	userprofile = input.nextInt();
									        	if (userprofile == 1 | userprofile ==2) {
									        		done = true;
									        	}
									        	else {
									        		System.out.println("Invalid input. Enter again : ");
									        	}
									         }
									    	  catch(InputMismatchException ex){
									            System.out.println("Invalid input. Enter again : ");
									            input.nextLine();
									    	  }
									      	} while (!done);
									      // Consistency across gender entry
									      if(userprofile == 1) {
									    	  System.out.println("Please type in file path including extension (.jpg/.png) :");
									    	  imagepath = input.next();
									      }
									      else {
									    	  imagepath = "Not Available";
									      }
										      System.out.println("Please select whether you would like an status associated with the profile : ");
										    	 System.out.println("Type 1 for yes and then the status, or 2 for no");
										    	// error checking preventing mismatch and also ensuring only valid options are chosen
										    	 userprofile = 0;
										    	 done = false;
												      do {
												    	  try {
												        	System.out.println("Please enter in the space below");
												        	userprofile = input.nextInt();
												        	if (userprofile == 1 | userprofile ==2) {
												        		done = true;
												        	}
												        	else {
												        		System.out.println("Invalid input. Enter again : ");
												        	}
												         }
												    	  catch(InputMismatchException ex){
												            System.out.println("Invalid input. Enter again : ");
												            input.nextLine();
												    	  }
												      	} while (!done);
												      // Consistency across gender entry
												      if(userprofile == 1) {
												    	  System.out.println("Please type in a short status");
												    	  status = input.next();
												      }
												      else {
												    	  status = "Not Available";}
							      userdatabase.add(new Children(usernum,newfirstname,newlastname,imagepath, status, newage, gender, newparent1, newparent2));
							      relationshipdatabase.add(new Relationships(newparent1, usernum, "dependent"));
							      relationshipdatabase.add(new Relationships(usernum, newparent1, "parent"));
							      relationshipdatabase.add(new Relationships(newparent2, usernum, "dependent"));
							      relationshipdatabase.add(new Relationships(usernum, newparent2, "parent"));
							      }	
		   }
		      		else {
		      			String usernum = "A"+(userdatabase.size());
		      			System.out.println("Please select gender from the following options: ");
		      			System.out.println("Type 1 for female, type 2 for male and type 3 for do not with to disclose");
		      			// error checking preventing mismatch and also ensuring only valid options are chosen
		      				userprofile = 0;
		      				done = false;
		      				do {
		      					try {
		      						System.out.println("Please enter in the space below");
		      						userprofile = input.nextInt();
		      							if (userprofile == 1 | userprofile ==2 | userprofile ==3) {
		      								done = true;
		      							}
		      							else {
		      								System.out.println("Invalid input. Enter again : ");
		      							}
		      					}
		      					catch(InputMismatchException ex){
		      						System.out.println("Invalid input. Enter again : ");
		      						input.nextLine();
		      					}
					      	} while (!done);
					      // Consistency across gender entry
					      if(userprofile == 1) {
					    	  gender = "female";
					      }
					      else if (userprofile == 2) {
					    	  gender = "male";
					      }
					      else gender = "did not disclose";
					      System.out.println("Please select whether you would like an image associated with the profile : ");
					    	 System.out.println("Type 1 for yes and then the image path, or 2 for no");
					    	// error checking preventing mismatch and also ensuring only valid options are chosen
					    	 userprofile = 0;
					    	 done = false;
							      do {
							    	  try {
							        	System.out.println("Please enter in the space below");
							        	userprofile = input.nextInt();
							        	if (userprofile == 1 | userprofile ==2) {
							        		done = true;
							        	}
							        	else {
							        		System.out.println("Invalid input. Enter again : ");
							        	}
							         }
							    	  catch(InputMismatchException ex){
							            System.out.println("Invalid input. Enter again : ");
							            input.nextLine();
							    	  }
							      	} while (!done);
							      // Consistency across gender entry
							      if(userprofile == 1) {
							    	  System.out.println("Please type in file path including extension (.jpg/.png) :");
							    	  imagepath = input.next();
							      }
							      else {
							    	  imagepath = "Not Available";
							      }
								      System.out.println("Please select whether you would like an status associated with the profile : ");
								    	 System.out.println("Type 1 for yes and then the status, or 2 for no");
								    	// error checking preventing mismatch and also ensuring only valid options are chosen
								    	 userprofile = 0;
								    	 done = false;
										      do {
										    	  try {
										        	System.out.println("Please enter in the space below");
										        	userprofile = input.nextInt();
										        	if (userprofile == 1 | userprofile ==2) {
										        		done = true;
										        	}
										        	else {
										        		System.out.println("Invalid input. Enter again : ");
										        	}
										         }
										    	  catch(InputMismatchException ex){
										            System.out.println("Invalid input. Enter again : ");
										            input.nextLine();
										    	  }
										      	} while (!done);
										      // Consistency across gender entry
										      if(userprofile == 1) {
										    	  System.out.println("Please type in a short status");
										    	  status = input.next();
										      }
										      else {
										    	  status = "Not Available";}
					      userdatabase.add(new Adult(usernum,newfirstname,newlastname,imagepath, status, newage, gender, false, false));
		      		}
		   }
		   
		   public void addrelationship() {
			   selection();

			   if (userdatabase.get(selectionnumber).getage()<=2) {
				   System.out.println("Due to this persons age you cannot add any relationships");
			   }
			   else if (userdatabase.get(selectionnumber).getage()<16) {
				 String user1 = usernum;
				 int user1age = userdatabase.get(selectionnumber).getage();
				 System.out.println("Select the next user that they wish to become friends with");
			    	 boolean done = false;
			    	 String user2 = "na";
			    	 int user2age = 0;
			    	 do {
			    		 selection();
				    	 user2 = userdatabase.get(selectionnumber).getusernum();
				    	 user2age = userdatabase.get(selectionnumber).getage();
				    	 if (user1==user2) { // Preventing relationship with self
				    		 System.out.println("Both user are the same, try inputting the other user");
				    	 }
				    	 else {
				    		 done=true;
				    	 }
			    	 }
			    	 while (!done);
			    	 if ((user1age-2)>user2age & (user1age+2)<user2age) { // cannot be friends with user 2 years older or younger
			    		 System.out.println("You cannot be friends with this user");
			    	 }
			    	 else if (user2age < 2 | user2age > 16) { // cannot be friends with users under 2 or over 16
			    		 System.out.println("You cannot be friends with this user");
			    	 }
			    	 else {
			    		 relationshipdatabase.add(new Relationships(user1, user2, "friend")); // else add friend
			    		 relationshipdatabase.add(new Relationships(user2, user1, "friend"));
			    	 }

			   }
			   else {
				   String user1 = usernum;
				   int user1i = selectionnumber;
				   System.out.println("Please enter what type of relationship you would like to add for this user");
				   System.out.println("Type 1 for new spouse, type 2 for new friend and type 3 to return to menu");
				// error checking preventing mismatch and also ensuring only valid options are chosen
     				int userprofile = 0;
     				boolean done = false;
     				do {
     					try {
     						System.out.println("Please enter in the space below");
     						userprofile = input.nextInt();
     							if (userprofile == 1 | userprofile ==2 | userprofile ==3) {
     								done = true;
     							}
     							else {
     								System.out.println("Invalid input. Enter again : ");
     							}
     					}
     					catch(InputMismatchException ex){
     						System.out.println("Invalid input. Enter again : ");
     						input.nextLine();
     					}
			      	} while (!done);
			      // Consistency across types of relationships
			      if(userprofile == 1) {
			    	  int user2i = -1;
			    	  if(((Adult) userdatabase.get(selectionnumber)).havespouse()==true) {
			    		  System.out.println("This user already has a spouse");
			    	  }
			    	  else {
						System.out.println("Select the next user that is their spouse");
					    	 done = false;
					    	 String user2 = "na";
					    	 do {
					    		 selection();
						    	 user2 = userdatabase.get(selectionnumber).getusernum();
						    	 user2i = selectionnumber; //array index 
						    	 if (user1==user2) { // Preventing relationship with self
						    		 System.out.println("Both user are the same, try inputting the other user");
						    	 }
						    	 else {
						    		 done=true;
						    	 }
					    	 } while (!done);
					    	 ((Adult) userdatabase.get(user1i)).newspouse();
					    	 ((Adult) userdatabase.get(user2i)).newspouse();
				    		 relationshipdatabase.add(new Relationships(user1, user2, "spouse")); // add spouse
				    		 relationshipdatabase.add(new Relationships(user2, user1, "spouse"));
			    	  }
			      }
			      else if (userprofile == 2) {
						System.out.println("Select the next user that is their friend");
				    	 done = false;
				    	 String user2 = "na";
				    	 do {
				    		 selection();
					    	 user2 = userdatabase.get(selectionnumber).getusernum();
					    	 if (user1==user2) { // Preventing relationship with self
					    		 System.out.println("Both user are the same, try inputting the other user");
					    	 }
					    	 else {
					    		 done=true;
					    	 }
				    	 } while (!done);
			    		 relationshipdatabase.add(new Relationships(user1, user2, "friend")); // else add friend
			    		 relationshipdatabase.add(new Relationships(user2, user1, "friend"));
			      }
			   }
		   }
		   
		   public void deleteuser() {
			   selection();
			   System.out.println("Are you sure you want to delete user?");
			   System.out.println("Type 1 to proceed, type 2 to return to menu");
			   int userprofile = 0;
			   boolean done = false;
				      do {
				    	  try {
				        	System.out.println("Please enter in the space below");
				        	userprofile = input.nextInt();
				        	if (userprofile == 1 | userprofile ==2) {
				        		done = true;
				        	}
				        	else {
				        		System.out.println("Invalid input. Enter again : ");
				        	}
				         }
				    	  catch(InputMismatchException ex){
				            System.out.println("Invalid input. Enter again : ");
				            input.nextLine();
				    	  }
				      	} while (!done);
				   if (userprofile==1) {
					   userdatabase.remove(selectionnumber);
					  for (int i=0; i<relationshipdatabase.size(); i++) {
						  if (relationshipdatabase.get(i).getuser1()==usernum) {
							  relationshipdatabase.remove(i);
						  }
						  if (relationshipdatabase.get(i).getuser2()==usernum) {
							  relationshipdatabase.remove(i);
						  }
					  }
				   }
		   }

}


  


