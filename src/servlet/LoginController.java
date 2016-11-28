package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.MysqlConnect;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String n = request.getParameter("username");
		String p = request.getParameter("password");
		
		MysqlConnect c = MysqlConnect.getDbCon();
		ResultSet rs = null;
		try{
			rs = c.query("SELECT * FROM cliente WHERE usuario = ' " + n + " ' AND contrasena = ' " + p + " ' ");
			if (rs.next()){ //Existe usuario
				request.setAttribute("variable", "usuario");
				request.getRequestDispatcher("html/perfil.jsp").forward(request, response);
			}
			else{ //No existe usuario
				response.sendRedirect("index.html");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		doGet(request, response);
	}

}
