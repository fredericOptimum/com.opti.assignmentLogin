package Application;

import java.sql.*;
import java.util.Scanner;


import Connection.DatabaseConnection;
import Controller.Menu;
import Model.Employee;

public class Login extends Exception{
	static Scanner sc = new Scanner(System.in);
	
	private static boolean returnCheck() { // to not duplicate when user vaildation fail
		System.out.println("Invaild User, please key in a vaild user.");
		System.out.println();
		boolean results = false;
		System.out.println("Do you want to login again? Y/N");
		String input = sc.next().toLowerCase();
		System.out.println();
		if (input.equals("n")) {
			results = true;
		}
		return results;
	}
	
	public static Employee Request() throws SQLException, ClassCastException {
		int checker = 0;
		Employee user= new Employee();
		String email;
		while(checker ==0) { // this is to run and only stop once the email and password is correct.
			System.out.println("Email: ");
			String login = sc.next();
			System.out.println("Password: ");
			String pw = sc.next(); 
			Statement state = DatabaseConnection.getConnection().createStatement();
			String query = "SELECT * FROM Employee";
		
			ResultSet rs = state.executeQuery(query);
			System.out.println();
			while (rs.next())
			{ //for each set of results from sql query at Employee
				String Email = rs.getString("Email");
				if(Email.equals(login) && rs.getString("Status").equals("Active") && rs.getInt("LoginAttempt")!=3) {
					//when email is accepted
					String Password = rs.getString("Password");
					if (Password.equals(pw)) { // when both email and password are accepted
						String name = rs.getString("Name");
						user.setId(rs.getInt("SerialNo"));
						checker = 1;
						System.out.println("Welcome "+name);
						return user;
					}else {//add LoginAttempt 
						String query2 = "UPDATE Employee SET LoginAttempt ='"+(rs.getInt("LoginAttempt")+1)+"' WHERE Email ='"+rs.getString("Email")+"'";
						Statement state2 = DatabaseConnection.getConnection().createStatement();
						state2.executeUpdate(query2);
						System.out.println("issue");
					}
					}else if(Email.equals(login) && rs.getInt("LoginAttempt")==3){ 
						// when there is 3 attempt on that email
						System.out.println("This account has be lock after 3 attempts, Please contact the admin for help.");
						new Menu();
					}
				}
				if (rs.last()) {
					if(returnCheck()) {// return to menu when user select to not continue
						new Menu();
					};
				} 
			}
		System.out.println(user.getId());
	
		return user;
	}
}
