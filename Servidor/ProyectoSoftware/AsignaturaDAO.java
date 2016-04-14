package gateway;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objetos.Asignaturas;


public class AsignaturaDAO {
	
	public static void insertAsignatura(String nombre, int carrera, int creditos, int cuatrimestre) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query="select max(idAsignatura) i from Asignaturas";
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			while (rs.next()) {
				i = rs.getInt(1)+1;
	        }
			//Comprobar si existe la asignatura
			query="select * from Asignaturas where Carrera="+carrera+" and Nombre='"+nombre+"';";
			rs = stmt.executeQuery(query);
			boolean existeAsignatura=false;
			while (rs.next()) {
				existeAsignatura=true;
	        }
			
			if(!existeAsignatura){
				query="insert into Asignaturas(idAsignatura, Carrera, Nombre, Creditos, Cuatrimestre, Validado)"
					+ "values ("+i+","+carrera+",'"+nombre+"',"+creditos+","+cuatrimestre+",TRUE)";
				stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				stmt.close();
			}

			if (conecta != null) {
				conecta.close();
			}

		}
	}
	
	public static ArrayList<Asignaturas> mostrarAsignatura(String carrera) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Asignaturas> lista = new ArrayList<Asignaturas>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "select carrera from "
					+ "Carrera where idCarrera="+carrera;
			// execute query
			ResultSet rs = stmt.executeQuery(query);
            String nCarrera = "";
			while (rs.next()) {
				nCarrera = rs.getString("carrera");
	        }
			
			query = "select idAsignatura, Nombre, Creditos, Cuatrimestre from "
					+ "Asignaturas where Carrera="+carrera;
			// execute query
			rs = stmt.executeQuery(query);
			int id=0;
            String nombre = "";
            int cuatrimestre=0, creditos=0;
			while (rs.next()) {
				id = rs.getInt("idAsignatura");
				nombre= rs.getString("Nombre");
				creditos = rs.getInt("Creditos");
				cuatrimestre = rs.getInt("Cuatrimestre");
	            Asignaturas aux = new Asignaturas(id, nombre, nCarrera, creditos, cuatrimestre);
	            lista.add(aux);
	            System.out.println(aux.toString());
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				stmt.close();
			}

			if (conecta != null) {
				conecta.close();
			}

		}
		return lista;
	}
}
