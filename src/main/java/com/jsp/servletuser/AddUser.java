package com.jsp.servletuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AddUser extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		int userId = Integer.parseInt(req.getParameter("userId"));
		String userName = req.getParameter("userName");
		String userEmail = req.getParameter("userEmail");
		String userAddress = req.getParameter("userAddress");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Gokul@01");

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user Values(?,?,?,?)");
			pstmt.setInt(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			pstmt.setString(4, userAddress);

			int rowinserted = pstmt.executeUpdate();

			PrintWriter out = res.getWriter();
			if (rowinserted > 0)
				out.print(rowinserted + " data Inserted Sucessfully!!");
			else
				out.print("<h2 style=\" font-size: 30px ; color: red;\">"+"No data Inserted!!"+" </h2>\" ");
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
