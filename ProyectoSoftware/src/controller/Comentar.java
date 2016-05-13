package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gateway.ComentarioDAO;
import objetos.Comentario;

/**
 * Servlet implementation class Comentar
 */
@WebServlet("/Comentar")
public class Comentar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comentar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		comenta(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * 
	 */
	private void comenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String texto = request.getParameter("comentario");
		String d = request.getParameter("destino");
		int destino = Integer.parseInt(d);
		
		// Debug
		System.out.println( mail + " - " + destino + " - " + texto);
		
		// Crear el nuevo comentario
		Comentario nuevo = new Comentario(destino, mail, texto);
		
		// Introducirlo a la base
		try {
			ComentarioDAO.insert(nuevo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Redirige a la página de origen
		response.sendRedirect("/ProyectoSoftware/Erasmus/destino.html?idDestino=" + destino);
	}

}
