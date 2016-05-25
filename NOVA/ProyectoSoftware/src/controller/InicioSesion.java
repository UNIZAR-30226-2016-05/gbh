package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
			if (getMD5(pass).compareTo(usr.getPasswd()) != 0){
				// La contraseña no coincide
				response.sendRedirect("/ProyectoSoftware/Erasmus/signin.html?error=passwd");
			} else {
				// Usuario logeado correctamente, crear cookies
				Cookie usrMail = new Cookie("userMail", mail);
				Cookie usrName = new Cookie("userName", usr.getNombre());
				Cookie usrAdmin = new Cookie("admin", ""+usr.getAdmin());
				Cookie usrPass = new Cookie("userPass", ""+usr.getPasswd());
				// Duración de las cookies
				usrMail.setMaxAge(-1); //15 minutos
				usrName.setMaxAge(-1); //15 minutos
				usrAdmin.setMaxAge(-1); //15 minutos
				usrPass.setMaxAge(-1); //15 minutos
				// Incluirlas en la sesión
				response.addCookie(usrMail);
				response.addCookie(usrName);
				response.addCookie(usrAdmin);
				response.addCookie(usrPass);
				
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
	
	/**
	 * Metodo que dada una cadena de caracteres que representa el password
	 * de un usuario determinado, le aplica un cifrado para que el password
	 * no sea visible sin aplicarse un descifrado.
	 * 
	 * @param input	cadena de caracteres que representa el password de un usuario
	 * determinado.
	 * 
	 * @return	devuelve una cadena de caracteres que representa el password del
	 * usuario pero cifrado.
	 */
	private static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
