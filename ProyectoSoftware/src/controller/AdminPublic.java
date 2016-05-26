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
public class AdminPublic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPublic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uno=request.getParameter("tipo");
		String dos=request.getParameter("accion");
		String tres=request.getParameter("id");
		buscaPrivado(request, response,uno,dos,tres);
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
	private void buscaPrivado(HttpServletRequest request, HttpServletResponse response, String uno,
			String dos, String tres) throws IOException{
		System.out.println(uno);
		System.out.println(dos);
		System.out.println(tres);
		try {
			/* uno = 0 -> destino, uno = 1 -> carrera, uno = 2 -> asignatura, uno = 3 -> usuario,
			 *  uno = 4 -> comentario*/
			if(uno.compareTo("0")==0){
				if(dos.compareTo("0")==0){
					//publicar
					DestinoDAO.updateDestino(tres);
				}
				else if(dos.compareTo("1")==0){
					//borrar
					DestinoDAO.deleteDestino(tres);
				}
			}
			else if(uno.compareTo("1")==0){
				if(dos.compareTo("0")==0){
					//publicar
					DestinoDAO.updateCarrera(tres);
				}
				else if(dos.compareTo("1")==0){
					//borrar
					DestinoDAO.deleteCarrera(tres);
				}
			}
			else if(uno.compareTo("2")==0){
				if(dos.compareTo("0")==0){
					//publicar
					AsignaturaDAO.update(tres);
				}
				else if(dos.compareTo("1")==0){
					//borrar
					AsignaturaDAO.delete(tres);
				}
			}
			else if(uno.compareTo("3")==0){
				//borrar
				UsuarioDAO.delete(tres);
			}
			else if(uno.compareTo("4")==0){
				//borrar
				ComentarioDAO.delete(tres);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	response.sendRedirect("Erasmus/tables.html");		
	}

}
