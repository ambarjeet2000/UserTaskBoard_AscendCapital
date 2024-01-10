package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String e1 = request.getParameter("email");
		String p = request.getParameter("password");
		PrintWriter out = response.getWriter();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql:///codenera_sql3","root","kavita@012");
	PreparedStatement ps = con.prepareStatement("select email from user where email = ? and password = ?");
	ps.setString(1, e1);
	ps.setString(2, p);
	ResultSet rs =  ps.executeQuery();
	
		 if(rs.next())
		 {
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
		 }
		 else
		 {
			 out.println("<font colour = red size =18 >Login failed !!!<br>");
			 Thread.sleep(1000);
			 RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				 
		 }
		 
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
