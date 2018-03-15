package Application;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Connection.DatabaseConnection;
import Model.Employee;

public class FirstTime { // when the user login for the first time.
	static Scanner sc = new Scanner(System.in);
	public static void Request(Employee user) throws SQLException {
		Statement state = DatabaseConnection.getConnection().createStatement();
		String query = "SELECT * FROM Employee WHERE SerialNo =" + user.getId();
		ResultSet rs = state.executeQuery(query);
		int checker = 1;
		while (rs.next()){ // for each set of results from query to employee
			while (checker == 1) {  // run untill proper input is made by new user.
				System.out.println("Hi "+rs.getString("Name")+", the system need to get info for first login attempt");
				System.out.println("Enter Temp Pwd: ");
				String tempPw = sc.next();
				if(tempPw.equals(rs.getString("Password"))) { //password confirmation
					System.out.println("Enter New Pwd: ");
					String newPw = sc.next();
					System.out.println("Retype Pwd: ");
					String nextPw = sc.next();
					if(newPw.equals(nextPw)) { // when verify, set it to the Employee db
						String query2 = "UPDATE Employee SET Password ='"+newPw+"', NoofAttempts = 1 WHERE SerialNo ='"+user.getId()+"'";
						state.executeUpdate(query2);
						SecurityQuestion.Request(user); // goes to ask user to key in their security question and answer
						checker = 0;
					}else {  // the return if password is not the same at confirmation
						System.out.println("New password is not the same. Please input again:");
						System.out.println();
					}
				}else { //wrong temp password
					System.out.println("Wrong password input, please type again.");
					System.out.println();
				}
			}if(checker == 0) {  // when all updates are done.
				System.out.println("Password and Security Check have been updated.");
				System.out.println();
				break;
			}
		}
	}

}
