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

public class Delete extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Gokul@01");

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE USERID=?");
			pstmt.setInt(1, userId);

			int rowdelete = pstmt.executeUpdate();

			PrintWriter out = res.getWriter();
			if (rowdelete > 0)
				out.print(rowdelete + " data Delete Sucessfully!!");
			else
				out.print(" No data Deleted!!");
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
