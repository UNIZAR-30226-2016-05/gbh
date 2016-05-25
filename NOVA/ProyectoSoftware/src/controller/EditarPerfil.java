package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gateway.UsuarioDAO;
import objetos.Usuario;

/**
 * Clase correspondiente a la capa de presentacion, concretamente esta clase
 * implementa al servlet que se encarga de gestionar las peticiones de editar el perfil
 * de los usuarios.
 * 
 * @author Grupo 1 - Arquitectura Software. Universidad de Zaragoza.
 */
@WebServlet("/EditarPerfil")
public class EditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cookieName = "userMail";
		String userMail = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) 
		{
		    for(int i=0; i<cookies.length; i++) 
		    {
		        Cookie cookie = cookies[i];
		        if (cookieName.equals(cookie.getName())) 
		        {
		        	userMail = (cookie.getValue());
		        }
		    }
		}	
		
		try {
			UsuarioDAO.delete(userMail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Erasmus/home.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cookieName = "userMail";
		String userMail = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) 
		{
		    for(int i=0; i<cookies.length; i++) 
		    {
		        Cookie cookie = cookies[i];
		        if (cookieName.equals(cookie.getName())) 
		        {
		        	userMail = (cookie.getValue());
		        }
		    }
		}		
		String user=request.getParameter("username");
		String password=request.getParameter("password");
		Usuario usuario=null;
		
		try {
			usuario=UsuarioDAO.selectUsuario(userMail);
			int count=0;
			
			if(user.compareTo("")==0){
				user=usuario.getNombre();
				count++;
			}
			if(password.compareTo("")==0){
				password=usuario.getPasswd();
				count++;
			}
			else if (password.compareTo("")!=0) {
				password = getMD5(password);
			}
			if(count!=2){
				Usuario updated = new Usuario(userMail,user,password,usuario.getAdmin(),usuario.getFecha());
				UsuarioDAO.update(updated);
			}		
			response.sendRedirect("Erasmus/home.html");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
