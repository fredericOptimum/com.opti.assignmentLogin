package Controller;

import java.sql.*;
import java.util.Scanner;

import Application.FirstTime;
import Application.Login;
import Application.Register;
import Application.SecurityQuestion;
import Application.Viewer;
import Connection.DatabaseConnection;
import Model.Employee;

public class Menu { // used when user login
	Scanner sc = new Scanner(System.in);
	Employee user= new Employee();
	
	private int Logout(Employee name) { // logout function
		int results = 1;
		System.out.println("Logout: Y/N ");
		String res = sc.next();
		if (res.toLowerCase().equals("y")) {
			results = 0;
			System.out.println("Goodbye "+ name.getName());
			System.out.println();
		}
		return results;
		
	}
	
	public Menu() throws SQLException{ //Main menu where all the work in menu proceed and implement together
		
		Statement state = DatabaseConnection.getConnection().createStatement();
		String query = "SELECT * FROM Employee";
		ResultSet rs = state.executeQuery(query);
		int check = 1;
		while(true) { //run till user end it's life ("It runs like the wind")
			System.out.println("Welcome to Assignment4");
			System.out.println("1.Login");
			System.out.println("2.Forget PW");
			System.out.print("Type in the option number:");
			String start = sc.next();
			System.out.println();
			if(Integer.parseInt(start) == 1) { //when option 1 is selected(Enter, enter mission~)
				user = Login.Request();
				while (rs.next()){ //once employee is taken in, loop with all employee to find info
					user.setName(rs.getString("Name"));
					if(user.getId() == rs.getInt("SerialNo")) { //checker for user for correct informartion
						if (rs.getInt("NoofAttempts") == 0) { 
							// move to the first login attempt just after registering.
							FirstTime.Request(user);
						}
						while(check == 1) { //While user is login
							if(rs.getString("Role").equals("user")) { // users/Employee menu
								check = Logout(user);
							}
							else { // Admin menu
								System.out.println("1.Register New User");
								System.out.println("2.View User List");
								System.out.println("3.Logout");
								String taker = sc.next();
								try {
								int over = Integer.parseInt(taker);
								if( over == 1|over == 2|over == 3) { // checker for select option
									if(over == 1) { //register new employee
										Register.Request();
									}
									else if(over == 2) {  //viewer for admins to change employee to active 
										Viewer.Request();
									}else {
										check = Logout(user);
									}
								}else { //int invaild option
									System.out.println("Please input a vaild option.");
									System.out.println();
								}
								}catch(Exception e) { // if users type in something different from option
									System.out.println("Please input a vaild option.");
									System.out.println();
								}
							}
						
						}
					}
				}
			}
			else if(Integer.parseInt(start) == 2) { //when option 2 is selected(Enter, enter mission~)
				user = SecurityQuestion.Check();
			
			}
		}
	}
}
