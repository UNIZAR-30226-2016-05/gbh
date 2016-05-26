package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

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
import objetos.Asignaturas;
import objetos.Busqueda;
import objetos.Comentario;
import objetos.Destino;
import objetos.Usuario;

/**
 * Servlet implementation class BuscaDestino
 */
@WebServlet("/SeleccionDestino")
public class SeleccionDestino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionDestino() {
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
	 * Busca los destinos indicado en la petición y le devuelve los datos de la consulta
	 * en formato JSON
	 */
	private void buscaPrivado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String carrera = request.getParameter("idDestino");
		int destino = Integer.parseInt(carrera);
		System.out.println(">" + carrera);
		
		String mail = "";
		String admin = "";
		String pass = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c: cookies){
				if (c.getName().compareTo("userPass") == 0) pass = c.getValue();
				else if (c.getName().compareTo("userMail") == 0) mail = c.getValue();
				else if (c.getName().compareTo("admin") == 0) admin = c.getValue();
			}
		}
		
		ArrayList<Destino> uno = null;
		ArrayList<Asignaturas> dos = null;
		ArrayList<Comentario> tres = null;
		int rate = 0;
		try {
			uno = DestinoDAO.buscarDestinoId(destino, null, null);
			dos = AsignaturaDAO.mostrarAsignatura(destino);
			tres = ComentarioDAO.selectByDestino(destino);
			rate = DestinoDAO.selectValoracion(destino);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = Destino.toJSON(uno); 
		result =result.substring(0, result.length()-1);
		String result2 = Asignaturas.toJSON(dos);
		result2 =result2.substring(1, result2.length()-1);
		String val = "\"Valoracion\": " + rate;
		String result3 = Comentario.toJSON(tres);
		result3 =result3.substring(1, result3.length());
		
		String respuesta = result + "," + result2 + ", " + val
				+ ", " + result3;
		
		if (mail.compareTo("")!=0
				&& pass.compareTo("")!=0 && admin.compareTo("")!=0) {
			if (!comprobadoAdmin(mail, pass, admin)) {
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
				respuesta = result + "," + result2 + ", " + val
						+ "}";
			}
		}
			
		// Debug
		System.out.println(respuesta);
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(respuesta);
		out.flush();
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
