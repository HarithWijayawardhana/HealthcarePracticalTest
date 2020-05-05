package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class patient {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String insertPatient(String name, String password, String mobile, String email, String address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into user (`userId`,`userName`,`password`,`mobile`,`email`,`address`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, mobile);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, address);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readPatient();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPatient() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>userName</th><th>password</th><th>mobile</th><th>email</th>" + "<th>Address</th> <th>Update</th><th>Remove</th></tr>";
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String patientId = Integer.toString(rs.getInt("userId"));
				String username = rs.getString("userName");
				String password = rs.getString("password");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String address = rs.getString("address");
				// Add into the html table
				output += "<tr><td><input id= 'hidItemIDUpdate' name= 'hidItemIDUpdate' type='hidden' value='"
						+ patientId + "'>" + username + "</td>";
				output += "<td>" + password + "</td>";

				output += "<td>" + mobile + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> " + " <td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + patientId + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePatient(String id, String name, String password, String mobile, String email, String address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE user SET userName=?,password=?,mobile=?,email=?,address=?	 WHERE userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, mobile);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, address);
			preparedStmt.setInt(6, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			 String newItems = readPatient();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}"; 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePatient(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user where userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			 String newItems = readPatient();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
			 
			 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
