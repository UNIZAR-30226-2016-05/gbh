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
@WebServlet("/AdminTables")
public class AdminTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTables() {
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
		
		ArrayList<Destino> uno = null;
		ArrayList<Destino> dos = null;
		Usuario tres = null;
		ArrayList<Asignaturas> cuatro = null;
		ArrayList<Usuario> cinco = null;
		ArrayList<Comentario> seis = null;

		
		String mail = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie c: cookies){
			if (c.getName().compareTo("userMail") == 0) mail = c.getValue();
		}
		
		try {
			uno = DestinoDAO.selectDestinoSinValidar();
			dos = DestinoDAO.selectCarreraSinValidar();
			tres = UsuarioDAO.selectUsuario(mail);
			cuatro = AsignaturaDAO.selectAsignaturasSinValidar();
			cinco = UsuarioDAO.selectAllUsuarios();
			seis = ComentarioDAO.selectAllComentarios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result1 = Destino.toJSON(uno); 
		result1 =result1.substring(0, result1.length()-1);
		String result2 = Destino.toJSON1(dos);
		result2 =result2.substring(1, result2.length()-1);
		String result3 = Usuario.toJSON(tres);
		result3 =result3.substring(1, result3.length()-1);
		String result4 = Asignaturas.toJSON(cuatro);
		result4 =result4.substring(1, result4.length()-1);
		String result5 = Usuario.toJSON(cinco);
		result5 =result5.substring(1, result5.length()-1);
		String result6 = Comentario.toJSON(seis);
		result6 =result6.substring(1, result6.length());
		
			
		// Debug
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		System.out.println(result6);
		
		String respuesta = result1 + "," + result2 + "," + result3 + "," + result4 + "," + result5 + "," + result6;
		System.out.println(respuesta);
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(respuesta);
		out.flush();
	}

}
