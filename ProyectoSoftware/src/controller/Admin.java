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
		
		int uno = 0, dos = 0, cuatro = 0;
		Usuario tres = null;
		
		String mail = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie c: cookies){
			if (c.getName().compareTo("userMail") == 0) mail = c.getValue();
		}
		
		try {
			uno = DestinoDAO.selectNumDestinoSinValidar();
			dos = DestinoDAO.selectNumCarreraSinValidar();
			tres = UsuarioDAO.selectUsuario(mail);
			cuatro = AsignaturaDAO.selectNumAsignaturaSinValidar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String result3 = Usuario.toJSON(tres);
		result3 =result3.substring(0, result3.length()-1);
		String val1 = "\"numDestinos\": " + uno;
		String val2 = "\"numAsignaturas\": " + cuatro;
		String val = "\"numCarreras\": " + dos + "}";
			
		// Debug
		System.out.println(result3);
		
		String respuesta = result3 + "," + val1 + "," + val2 + "," + val;
		
		System.out.println(respuesta);
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(respuesta);
		out.flush();
	}

}
