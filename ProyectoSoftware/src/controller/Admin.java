package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gateway.AsignaturaDAO;
import gateway.ComentarioDAO;
import gateway.DestinoDAO;
import gateway.UsuarioDAO;
import objetos.Usuario;

/**
 * Servlet implementation class BuscaDestino
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		buscaPrivado(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @throws IOException 
	 * Busca los datos indicado en la petición y le devuelve los datos de la consulta
	 * en formato JSON
	 */
	private void buscaPrivado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int uno = 0, dos = 0, cuatro = 0, cinco=0, seis=0, siete=0;
		Usuario tres = null;
		
		String mail = "";
		String admin = "";
		String pass = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie c: cookies){
			if (c.getName().compareTo("userPass") == 0) pass = c.getValue();
			else if (c.getName().compareTo("userMail") == 0) mail = c.getValue();
			else if (c.getName().compareTo("admin") == 0) admin = c.getValue();
		}
		if (comprobadoAdmin(mail, pass, admin)){
			try {
				uno = DestinoDAO.selectNumDestinoSinValidar();
				dos = DestinoDAO.selectNumCarreraSinValidar();
				tres = UsuarioDAO.selectUsuario(mail);
				cuatro = AsignaturaDAO.selectNumAsignaturaSinValidar();
				cinco = UsuarioDAO.selectNumUsuarios();
				seis = ComentarioDAO.selectNumComentarios();
				siete = DestinoDAO.selectNumValoraciones();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String result3 = Usuario.toJSON(tres);
			result3 =result3.substring(0, result3.length()-1);
			String val1 = "\"numDestinos\": " + uno;
			String val2 = "\"numAsignaturas\": " + cuatro;
			String val3 = "\"numUsuarios\": " + cinco;
			String val4 = "\"numComentarios\": " + seis;		
			String val5 = "\"numValoraciones\": " + siete;
			String val = "\"numCarreras\": " + dos + "}";
				
			// Debug
			System.out.println(result3);
			
			String respuesta = result3 + "," + val1 + "," + val2 + "," + val3 + "," + val4 + "," + val5 +"," +val;
			
			System.out.println(respuesta);
			
			response.setContentType("application/json");
			// Get the printwriter object from response to write the required json object to the output stream      
			PrintWriter out = response.getWriter();
			// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
			out.print(respuesta);
			out.flush();
		}else {
			Usuario usr = comprobadoUser(mail, pass);
			if (usr != null) {
				// Eliminar cookies
				Cookie[] cookie = request.getCookies();
				for (Cookie c: cookie){
					// La cookie se eliminar al recibir la respuesta
					c.setMaxAge(0);
					response.addCookie(c);
				}
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
			}
		}
	}

	/**
	 * Metodo que comprueba que el usuario cuyo email es "mail" tiene el campo admin
	 * igual a "admin"
	 * @param mail
	 * @param pass
	 * @param admin
	 * @return
	 */
	private boolean comprobadoAdmin (String mail, String pass, String admin) {
		
		Usuario user = null;
		
		try {
			user = UsuarioDAO.selectUsuario(mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pass.compareTo(user.getPasswd()) == 0) {
			return admin.compareTo(user.getAdmin()+"") == 0;
		}else {
			return false;
		}
	}
	
	/**
	 * Metodo que comprueba que devuelve el usuario cuyo email es "mail" y su pass
	 * "pass"
	 * @param mail
	 * @param pass
	 * @return
	 */
	private Usuario comprobadoUser (String mail, String pass) {
		
		Usuario user = null;
		
		try {
			user = UsuarioDAO.selectUsuario(mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pass.compareTo(user.getPasswd()) == 0) {
			return user;
		}else {
			return null;
		}
	}
}
