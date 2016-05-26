package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import gateway.AsignaturaDAO;
import gateway.ComentarioDAO;
import gateway.DestinoDAO;
import gateway.UsuarioDAO;

/**
 * Servlet implementation class Grafica
 */
@WebServlet("/Grafica")
public class Grafica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] month = {"January","Febuary","March",
			"April","May","June","July","August","September",
			"October","November","December"};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Grafica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int yy =  Calendar.getInstance().get(Calendar.YEAR);
		try {
			int [] destinos = DestinoDAO.selectCountDestino(yy);
			int [] carreras = DestinoDAO.selectCountCarrera(yy);
			int [] asignaturas = AsignaturaDAO.selectCount(yy);
			int [] usuarios = UsuarioDAO.selectCount(yy);
			int [] comentarios = ComentarioDAO.selectCount(yy);
			int [] valoraciones = DestinoDAO.selectCountValoracion(yy);
			String datos = "{\"data\": [";
			for (int i=0; i<12; i++){
				int j = i + 1;
				JSONObject obj = new JSONObject();
				String mes = "" + i;
				if (j < 10) mes = "0" + j;
				obj.put("period", yy + "-" + mes);	// Periodo
				obj.put("Destinos", destinos[i]);	// destinos
				obj.put("Carreras", carreras[i]);	// carreras
				obj.put("Asignaturas", asignaturas[i]);	// asignaturas
				obj.put("Usuarios", usuarios[i]);	// usuarios
				obj.put("Comentarios", comentarios[i]);	// comentarios
				obj.put("Valoraciones", valoraciones[i]);	// valoraciones
				
				// Añadir a datos
				datos += obj.toString() + ",\n";				
			} 
			int end = datos.lastIndexOf(',');
			datos = datos.substring(0, end) + "\n]}";	// Elimina la última coma puesta y termina
			
			// Debug
			System.out.println(datos);
			
			response.setContentType("application/json");
			// Get the printwriter object from response to write the required json object to the output stream      
			PrintWriter out = response.getWriter();
			// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
			out.print(datos);
			out.flush();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("grafica");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
