package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gateway.AsignaturaDAO;
import gateway.ComentarioDAO;
import gateway.DestinoDAO;
import objetos.Asignaturas;
import objetos.Busqueda;
import objetos.Comentario;
import objetos.Destino;

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
		
		ArrayList<Destino> uno = null;
		ArrayList<Asignaturas> dos = null;
		ArrayList<Comentario> tres = null;
		try {
			uno = DestinoDAO.buscarDestinoId(destino, null, null);
			dos = AsignaturaDAO.mostrarAsignatura(destino);
			tres = ComentarioDAO.selectByDestino(destino);
			//tres = ComentarioDAO
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = Destino.toJSON(uno); 
		result =result.substring(0, result.length()-1);
		String result2 = Asignaturas.toJSON(dos);
		result2 =result2.substring(1, result2.length()-1);
		String result3 = Comentario.toJSON(tres);
		result3 =result3.substring(1, result3.length());
			
		// Debug
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
		
		String respuesta = result + "," + result2 + ", " + result3;
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(respuesta);
		out.flush();
	}

}
