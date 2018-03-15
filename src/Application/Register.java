package Application;

import java.sql.*;
import java.util.Scanner;

import Connection.DatabaseConnection;
import Model.Employee;

public class Register {
	static Scanner sc = new Scanner(System.in);
	public static void Request() throws SQLException {
		int checker = 0;
		String nric="";
		String dob="";
		String email="";
		String mobile="";
		
		while(true) { //this is to run and check the user input for each field, insert to Employee after.
			System.out.println("Register New User:");
			System.out.println("Name: ");
		
			String name = sc.next();
		
			while(checker == 0) {
				System.out.println("Nric: ");
				nric = sc.next();
				if(nric.matches("^[STFG]\\d{7}[A-Z]$")) {
					checker = 1;
				}
			}checker = 0;
		
			while(checker == 0) {
				System.out.println("Date of Birth: dd/mm/yyy");
				dob = sc.next();
				if(dob.matches("\\d{2}[/]\\d{2}[/]\\d{4}$")) {
					checker = 1;
				}
			}checker = 0;
		
			while(checker == 0) {
				System.out.println("Email: ");
				email = sc.next();
				if(email.matches("\\w*[.]\\w*[@]\\w*[.]\\w*")) {
					checker = 1;
				}
			}checker = 0;
		
		
			while(checker == 0) {
				System.out.println("Mobile: ");
				mobile = sc.next();
				if(mobile.matches("\\d{8,16}")) {
					checker = 1;
				}
			}checker = 0;
		
			String temp_pw = nric.substring(1, 5)+mobile.substring(4,8);
			Employee user = new Employee();
			Statement state = DatabaseConnection.getConnection().createStatement();
			String query = "Insert into Employee (" + 
					"   Name," + 
					"   Nric," + 
					"   Email," + 
					"   DOB," + 
					"   Role," + 
					"   Mobile," + 
					"   Password," + 
					"   Status," + 
					"   NoofAttempts)" + 
					"   values ('"+name+"','"+nric+"','"+email+"','"+dob+"','user','"+mobile+"','"+temp_pw+"','Active',0)";
			if (state.executeUpdate(query) == 1) { // check if the query to Employee has been complete.
				System.out.println();
				System.out.println("User "+name+" have been registered.");
				System.out.println();
				SendEmail.SendEmail(email,temp_pw,name); // send email to the new Employee that has been registered
				break;
			}else {
				System.out.println("Error occur, please input again.");
			}
			
		}
		
	}

}
