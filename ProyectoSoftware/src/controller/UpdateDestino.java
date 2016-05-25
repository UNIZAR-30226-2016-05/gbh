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
@WebServlet("/UpdateDestino")
public class UpdateDestino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDestino() {
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
		String idCarrera = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c: cookies){
				if (c.getName().compareTo("idCarrera") == 0) idCarrera = c.getValue();
			}
		}
		
		int numRemoved=Integer.parseInt(request.getParameter("numRemoved"));
		int countRemoved=0;
		String subjectName="";
		int creditNumber=0;
		int cuatriNumber=0;
		String creditN="";
		String cuatri="";
		int numSubject=1;
		subjectName=request.getParameter("subjectName"+numSubject);
		while(request.getParameter("subjectName"+numSubject) != null || countRemoved < numRemoved){
			if(request.getParameter("subjectName"+numSubject) != null && 
					request.getParameter("cuatriNumber"+numSubject) != null
					&& request.getParameter("creditNumber"+numSubject) != null){
				subjectName=request.getParameter("subjectName"+numSubject);
				creditN=request.getParameter("creditNumber"+numSubject);
				if(isNumeric(creditN)){
					creditNumber=Integer.parseInt(request.getParameter("creditNumber"+numSubject));
				}
				cuatri=request.getParameter("cuatriNumber"+numSubject);
				if (cuatri.contains("OtoÃ±o") || cuatri.contains("Otoño")){
					cuatriNumber = 1;
				}
				else if (cuatri.contains("Primavera")){
					cuatriNumber = 2;
				}
				if(isNumeric(creditN) || cuatriNumber==1 || cuatriNumber==2 || subjectName!=""){
					try {
						AsignaturaDAO.insertAsignatura(subjectName,Integer.parseInt(idCarrera),creditNumber,cuatriNumber);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else{
				countRemoved++;
			}
			numSubject++;
		}
		Cookie idCarr = new Cookie("idCarrera", "");
		idCarr.setMaxAge(0);
		response.addCookie(idCarr);
		response.sendRedirect("Erasmus/destino.html?idDestino=" + idCarrera);
	}
	
	private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
	
	/**
	 * @throws IOException 
	 * Busca los destinos indicado en la petición y le devuelve los datos de la consulta
	 * en formato JSON
	 */
	private void buscaPrivado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String id = request.getParameter("Id");
		int destino = Integer.parseInt(id);
		Cookie idCarrera = new Cookie("idCarrera", id);
		idCarrera.setMaxAge(-1);
		response.addCookie(idCarrera);
		
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
		
		ArrayList<Asignaturas> dos = null;
		try {
			dos = AsignaturaDAO.mostrarAsignatura(destino);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result2 = Asignaturas.toJSON(dos);
		result2 =result2.substring(0, result2.length());
		
		String respuesta = result2;
		
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
					idCarrera = new Cookie("idCarrera", id);
					idCarrera.setMaxAge(-1);
					response.addCookie(idCarrera);
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
				respuesta = result2;
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
