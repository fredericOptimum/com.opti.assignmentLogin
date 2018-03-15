package Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Connection.DatabaseConnection;

public class Viewer {
	static Scanner sc = new Scanner(System.in);
	
	private static int Insert(String stat, String nric, Statement state, ResultSet rs) throws SQLException{
		//change user status
		String query2 = "UPDATE Employee SET Status ='"+stat+"' WHERE Nric ='"+nric+"'";
		return state.executeUpdate(query2);
	}

	private static void print(String stat) {
		//print the change
		System.out.println("Status is now "+stat);
		System.out.println();
	}
	
	public static void Request() throws SQLException {
		Statement state = DatabaseConnection.getConnection().createStatement();
		String query = "SELECT * FROM Employee";
		ResultSet rs = state.executeQuery(query);
		while (true){ // run till admin stop the viewer process
			System.out.println();
			rs.first(); 
			while (rs.next()){ // for each query set... print out
				System.out.println("User: "+rs.getString("Name")+", Nric: "+rs.getString("Nric")+" is "+ rs.getString("Status"));
			}
			System.out.println();
			System.out.println("Type a User Nric to change their status: Active/Deactive");
			String nric = sc.next();
			System.out.println();
			rs.first();
			while(rs.next()) { //print out all Employee
				if(rs.getString("Nric").equals(nric)) { // when admin select the employee with ic
					String stat = "Active";
					if(stat.equals(rs.getString("Status"))) { // change status 
						stat = "Deactived";
						if(Insert(stat,nric,state,rs)==1) {
							print(stat);
						}
					}
					else { // change status
						stat = "Active";
						if(Insert(stat,nric,state,rs)==1) {
							print(stat);
						}
					}
					
				}
			} //check for admin to continue
			System.out.println("Do you want to continue? Y/N");
			String userInput = sc.next().toLowerCase();
			if (userInput == "y") {
				break;
			}
		}
		
	}

}
