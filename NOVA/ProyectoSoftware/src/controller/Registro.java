package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		String repassword=request.getParameter("repassword");
		if (password.compareTo(repassword) == 0){
			password = getMD5(password);
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
		}else{
			response.sendRedirect("Erasmus/signup.html?error=1");
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
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
