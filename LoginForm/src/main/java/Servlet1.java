import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Servlet1")

public class Servlet1 extends HttpServlet {

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		// making an jdbc connection 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "root";
			String password1 = "password";
			Connection con = DriverManager.getConnection(url, user, password1);
			
			PreparedStatement preparedStatement = con.prepareStatement("insert into login (username, password) values (?, ?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			
			int rowsAffected = preparedStatement.executeUpdate();
			PrintWriter pw = response.getWriter();
			
			if(rowsAffected != 0) {
				pw.println("<h1> Login Succefully </h1>");
			}
			else {
				pw.println("<h1> Failed ! </h1>");
			}
			preparedStatement.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
