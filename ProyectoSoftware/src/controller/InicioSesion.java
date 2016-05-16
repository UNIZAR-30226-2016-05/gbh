package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gateway.UsuarioDAO;
import objetos.Usuario;

/**
 * Servlet implementation class InicioSesion
 */
@WebServlet("/InicioSesion")
public class InicioSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		signIn(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Trata la peticion del inicio de sesion de un usuario
	 */
	protected void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String pass = request.getParameter("passwd");
		Usuario usr = null;
		
		// Debug
		System.out.println(mail + " - " + pass);
		
		// Comprobar si el usuario esta en la base
		try {
			usr = UsuarioDAO.selectUsuario(mail);
			System.out.println(usr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (usr == null){
			response.sendRedirect("/ProyectoSoftware/Erasmus/signin.html?error=nulo");
		} else {
			// El usuario se encuentra en la base
			if (pass.compareTo(usr.getPasswd()) != 0){
				// La contraseña no coincide
				response.sendRedirect("/ProyectoSoftware/Erasmus/signin.html?error=passwd");
			} else {
				// Usuario logeado correctamente, crear cookies
				Cookie usrMail = new Cookie("userMail", mail);
				Cookie usrName = new Cookie("userName", usr.getNombre());
				Cookie usrAdmin = new Cookie("admin", ""+usr.getAdmin());
				// Duración de las cookies
				usrMail.setMaxAge(60*15); //15 minutos
				usrName.setMaxAge(60*15); //15 minutos
				usrAdmin.setMaxAge(60*15); //15 minutos
				// Incluirlas en la sesión
				response.addCookie(usrMail);
				response.addCookie(usrName);
				response.addCookie(usrAdmin);
				
				// Inicio de sesión de administrador
				if (usr.getAdmin() > 0){
					response.sendRedirect("/ProyectoSoftware/Erasmus/admin.html");
				} else {
					// Usuario común
					response.sendRedirect("/ProyectoSoftware/Erasmus/home.html");
				}
			}
		}
		
	}

}
