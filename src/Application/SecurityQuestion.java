package Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Connection.DatabaseConnection;
import Controller.Menu;
import Model.Employee;

public class SecurityQuestion {
	static Scanner sc = new Scanner(System.in);
	static Employee user = new Employee();
	
	private static String print() { //print out security question and return the user choice
		System.out.println();
		System.out.println("A.Which school do you study at?");
		System.out.println("B.What do your Parents like to do?");
		System.out.println("C.How do you see this world as?");
		System.out.print("Choice: ");
		String choice = sc.next();
		System.out.println();
		return choice;
	}
	private static boolean Issue() throws SQLException { // when user input is not right for forget pw
		System.out.println("The Results is invaild, do you want to Try again?");
		System.out.print("Y/N: ");
		String checker = (sc.next()).toLowerCase();
		System.out.println();
		boolean results;
		if (checker == "y") {results = true;}
		else {results =false;}
		return results;
	}
	
	public static void Request(Employee user) throws SQLException {
		while(true) { // running till right input is set for new registered user
		System.out.println("Please choose a security question");
		String choice= print().toLowerCase();
		System.out.print("Answer: ");
		String anw = sc.next();
		System.out.println();
		Statement state = DatabaseConnection.getConnection().createStatement();
		if (choice.equals("a")|choice.equals("b")|choice.equals("c")) { // check for input security question
			String query2 = "UPDATE Employee SET SecQues ='"+choice+"', SecAns = '"+anw+"' WHERE SerialNo ='"+user.getId()+"'";
			state.executeUpdate(query2);
			break;
		}
		else {	System.out.println("Please input a proper choice."); //user who insert wrong input
				System.out.println();
		}
		}
	}

	
	public static Employee Check() throws SQLException {
		
		int checker = 1;
		while(true) { //run till user stop or is block by 3 attempt
			System.out.println("Email: ");
			String login = sc.next();
			System.out.println("Security Question: ");
			System.out.println();
			String choice =print().toLowerCase();
			System.out.print("Answer: ");
			String anw = sc.next();
			System.out.println();
			Statement state = DatabaseConnection.getConnection().createStatement();
			String query = "SELECT * FROM Employee WHERE Email ='"+login+"'";
			ResultSet rs = state.executeQuery(query);
			while(rs.next()) { // run each sey of the query to check and verify
				if(login.equals(rs.getString("Email"))){
					if(choice.equals(rs.getString("SecQues"))) {
						if(anw.equals(rs.getString("SecAns"))) {
							user.setId(rs.getInt("SerialNo"));
							while(checker == 1) { // when user is correct go to check pw change
								System.out.println("Enter New Pwd: ");
								String newPw = sc.next();
								System.out.println("Retype Pwd: ");
								String nextPw = sc.next();
								if(newPw.equals(nextPw)) { //the update for employee table and go to security question
									String query2 = "UPDATE Employee SET Password ='"+newPw+"', NoofAttempts = 1 WHERE SerialNo ='"+user.getId()+"'";
									state.executeUpdate(query2);
									SecurityQuestion.Request(user);
									checker = 0;
									return user;
								}else {
									System.out.println("New password is not the same. Please input again:");
								}
							}
						}else { // return to menu when user decide to not continue
							if (Issue() == false) {
								Menu starting = new Menu();
							}
						}
					}else { // return to menu when user decide to not continue due to question selection failure
						if (Issue() == false) {
							Menu starting = new Menu();
						}
					}
				}else {
					System.out.println("Wrong input for Email, please retype again.");
				}
			}
			if(checker == 0) {break;}
			}
		return user;
	}

}
