package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import gateway.AsignaturaDAO;
import gateway.DestinoDAO;

/**
 * Servlet implementation class ServletAddDestiny
 */
@WebServlet("/ServletAddDestiny")
public class ServletAddDestiny extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		//try {
			//Connection conecta = (Connection) AccesoBase.getDBConnection();
			String pais=request.getParameter("countryName");
			if(pais.equals("EspaÃ±a")){
				pais="España";
			}
			String ciudad=request.getParameter("cityName");
			String universidad=request.getParameter("universityName");
			String language=request.getParameter("language");
			String carrera=request.getParameter("degreeName");
			String imag=request.getParameter("degreeImg");
			if(language.equals("EspaÃ±ol")){
				language="Español";
			}
			String rama=request.getParameter("rama");
			int numRemoved=Integer.parseInt(request.getParameter("numRemoved"));
			int countRemoved=0;
			
			try {
				DestinoDAO.insertDestino(carrera, universidad, ciudad, pais,language, rama, imag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int idDestino=DestinoDAO.getIdDestino(pais, ciudad);
			int idCarrera=DestinoDAO.getIdCarrera(idDestino, rama,carrera,universidad,language);
			String subjectName="";
			int creditNumber=0;
			int cuatriNumber=0;
			String cuatri="";
			int numSubject=1;
			subjectName=request.getParameter("subjectName"+numSubject);
			while(request.getParameter("subjectName"+numSubject) != null || countRemoved < numRemoved){
				if(request.getParameter("subjectName"+numSubject) != null && 
						request.getParameter("cuatriNumber"+numSubject) != null
						&& request.getParameter("creditNumber"+numSubject) != null){
					subjectName=request.getParameter("subjectName"+numSubject);
					creditNumber=Integer.parseInt(request.getParameter("creditNumber"+numSubject));
					cuatri=request.getParameter("cuatriNumber"+numSubject);
					
					if (cuatri.contains("OtoÃ±o") || cuatri.contains("Otoño")){
						cuatriNumber = 1;
					}
					else if (cuatri.contains("Primavera")){
						cuatriNumber = 2;
					}
					
					try {
						AsignaturaDAO.insertAsignatura(subjectName,idCarrera,creditNumber,cuatriNumber);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				else{
					countRemoved++;
				}
				numSubject++;
			}
	    	response.sendRedirect("Erasmus/home.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
