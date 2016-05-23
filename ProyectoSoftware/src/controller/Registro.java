package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import gateway.AsignaturaDAO;
import gateway.DestinoDAO;
import gateway.UsuarioDAO;
import objetos.Usuario;

/**
 * Servlet implementation class ServletAddDestiny
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		
		 	int tipo=Integer.parseInt(request.getParameter("tipo"));
			String correo=request.getParameter("email");
			String user=request.getParameter("username");
			String password=request.getParameter("password");
				try {
					
					Usuario usuario  = new Usuario(correo,user,password,tipo,null);
					boolean repe=UsuarioDAO.insert(usuario);
					if (repe){
						response.sendRedirect("Erasmus/signup.html?error=1");
					}
					else{
				    	response.sendRedirect("Erasmus/home.html");

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
