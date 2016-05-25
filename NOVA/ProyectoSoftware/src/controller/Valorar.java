package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gateway.ComentarioDAO;
import gateway.UsuarioDAO;
import objetos.Comentario;

/**
 * Servlet implementation class Valorar
 */
@WebServlet("/Valorar")
public class Valorar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Valorar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		valorar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Inseta o actualiza una valoración y devuelve al usuario a la página en la que estaba
	 */
	private void valorar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String v = request.getParameter("rating");
		int val = Integer.parseInt(v);
		String d = request.getParameter("destino");
		int destino = Integer.parseInt(d);
		
		// Debug
		System.out.println(mail + " - " + destino + " - " + val);
		
		// Introducirlo a la base
		try {
			UsuarioDAO.insertValoracion(mail, destino, val);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Redirige a la página de origen
		response.sendRedirect("/ProyectoSoftware/Erasmus/destino.html?idDestino=" + destino);
	}

}
