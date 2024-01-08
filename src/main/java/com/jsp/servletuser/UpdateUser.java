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

public class UpdateUser extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		String userEmail = req.getParameter("userEmail");
		String userAddress = req.getParameter("userAddress");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Gokul@01");

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE user SET USEREMAIL=? ,USERADDRESS=? WHERE USERID=?");
			pstmt.setInt(3, userId);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userAddress);

			int rowupdate = pstmt.executeUpdate();

			PrintWriter out = res.getWriter();
			if (rowupdate > 0)
				out.print(rowupdate + " data Update Sucessfully!!");
			else
				out.print(" No data Updated!!");
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
