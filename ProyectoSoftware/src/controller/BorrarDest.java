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
@WebServlet("/BorrarDest")
public class BorrarDest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarDest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uno=request.getParameter("asignatura");
		buscaPrivado(request, response,uno);
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
	private void buscaPrivado(HttpServletRequest request, HttpServletResponse response, String uno) throws IOException{
		String idCarrera = "";
		String mail = "";
		String admin = "";
		String pass = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c: cookies){
				if (c.getName().compareTo("idCarrera") == 0) idCarrera = c.getValue();
				else if(c.getName().compareTo("userPass") == 0) pass = c.getValue();
				else if (c.getName().compareTo("userMail") == 0) mail = c.getValue();
				else if (c.getName().compareTo("admin") == 0) admin = c.getValue();
			}
		}
		
		try {
			if (mail.compareTo("")!=0
					&& pass.compareTo("")!=0 && admin.compareTo("")!=0) {
				System.out.println(comprobadoAdmin(mail, pass, admin));
				if (comprobadoAdmin(mail, pass, admin)) {
						AsignaturaDAO.delete(uno);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cookie idCarr = new Cookie("idCarrera", "");
		idCarr.setMaxAge(0);
		response.addCookie(idCarr);
		response.sendRedirect("/ProyectoSoftware/Erasmus/destino.html?idDestino=" + idCarrera);
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
		if (pass.compareTo(user.getPasswd()) == 0 && admin.compareTo("0") != 0) {
			return admin.compareTo(user.getAdmin()+"") == 0;
		}else {
			return false;
		}
	}

}
