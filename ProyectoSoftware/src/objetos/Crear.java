package objetos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gateway.AccesoBase;

public class Crear {
	
	public static void crearDestino(String carrera, String universidad, String ciudad, String pais, String idioma, String genero, String img) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query=("select idDestino from Destinos where ciudad='"+ciudad+"' and pais='"+pais+"'");
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			int idDestino=0;
			while (rs.next()) {
				idDestino = rs.getInt("idDestino");
				i++;
	        }
			if (i==0){//Nueva ciudad-pais
				//Comprobar máximo idDestino
				query="select max(idDestino) i from Destinos";
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					idDestino = rs.getInt("i")+1;
		        }
				query="insert into Destinos (idDestino, Pais, Ciudad, Validado) values ("+idDestino+",'"+pais+"','"+ciudad+"',TRUE)";
				stmt.executeUpdate(query);
			}
			//Comprobar si existe la rama
			i=0;
			query="select * from Rama where idRama='"+genero+"'";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				i++;
	        }
			if (i==0){//Nueva rama
				query="insert into Rama (idRama) values ('"+genero+"')";
				stmt.executeUpdate(query);
			}
			// Comprobar máximo idCarrera
			int idCarrera=0;
			query="select max(idCarrera) id from Carrera";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				idCarrera = rs.getInt("id")+1;
	        }
			query="insert into Carrera (idCarrera, destino, rama, carrera, universidad, idioma, imagen, "
						+ "validado) values ("+idCarrera+","+idDestino+",'"+genero+"','"+carrera+"','"+universidad+"', '"+idioma+"','"+img+"', TRUE)";
			System.out.println(query);
			stmt.executeUpdate(query);
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
}
