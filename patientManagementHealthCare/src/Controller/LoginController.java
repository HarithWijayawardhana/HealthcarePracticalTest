package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");

		// Connect to mysql and verify username password

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// loads driver
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", ""); // gets a
																												// new
																												// connection

			System.out.println("Success");

			PreparedStatement ps = c
					.prepareStatement("select userName,password from user where userName=? and password=?");
			ps.setString(1, un);
			ps.setString(2, pw);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				response.sendRedirect("register.jsp");

				return;
			}
			response.sendRedirect("error.jsp");
			return;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
