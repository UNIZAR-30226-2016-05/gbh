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
@WebServlet("/AdminPublic")
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
		System.out.println(uno);
		try {
			AsignaturaDAO.updateDelete(uno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/ProyectoSoftware/Erasmus/destino.html?idDestino=" + uno);//modificar
	}

}
