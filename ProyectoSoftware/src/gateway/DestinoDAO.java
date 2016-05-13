package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objetos.Destino;

public class DestinoDAO {
	
	public static void insertDestino(String carrera, String universidad, String ciudad, String pais, String idioma, String genero, String img) throws SQLException {
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
	
	public static ArrayList<Destino> buscarDestino(String busqueda, ArrayList<String> carac, String orderby) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Destino> lista = new ArrayList<Destino>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			int j=0;
			String opciones="";
			if (carac != null){
				for (String i: carac){
					if (j==0){
						opciones="where "+i+" ";
						j++;
					}
					else{
						opciones+="or "+i+" ";
					}
				}
			}
			String query = "";
			
			// if (carac == null);
			
			query = "select idCarrera, Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from Carrera, Destinos where "
					+ "(Carrera LIKE '%"+ busqueda +"%' OR Rama LIKE '%"+ busqueda + "%') "
					+ "and idDestino=Destino ";
			
			if (orderby != null) query = query + " order by " + orderby;
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			int id=0;
			String carrera = "";
            String universidad = "";
            String ciudad = "";
            String pais = "";
            String idioma = "";
            String genero = "";
            String img = "";
			while (rs.next()) {
				id = rs.getInt("idCarrera");
				carrera = rs.getString("Carrera");
				universidad = rs.getString("Universidad");
				ciudad = rs.getString("Ciudad");
				pais = rs.getString("Pais");
				idioma = rs.getString("Idioma");
				genero = rs.getString("Rama");
				img= rs.getString("Imagen");
	            Destino aux = new Destino(id,carrera, universidad, ciudad, pais, idioma, genero, img);
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
	
	public static ArrayList<Destino> buscarDestinoId(int busqueda, ArrayList<String> carac, String orderby) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Destino> lista = new ArrayList<Destino>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query = "";
			
			// if (carac == null);
			
			query = "select idCarrera, Carrera, Universidad, Ciudad, Pais, Idioma, Rama, "
					+ "Imagen from Carrera, Destinos where idCarrera = " + busqueda + " and idDestino=Destino";
			
			if (orderby != null) query = query + " order by " + orderby;
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			int id=0;
			String carrera = "";
            String universidad = "";
            String ciudad = "";
            String pais = "";
            String idioma = "";
            String genero = "";
            String img = "";
			while (rs.next()) {
				id = rs.getInt("idCarrera");
				carrera = rs.getString("Carrera");
				universidad = rs.getString("Universidad");
				ciudad = rs.getString("Ciudad");
				pais = rs.getString("Pais");
				idioma = rs.getString("Idioma");
				genero = rs.getString("Rama");
				img= rs.getString("Imagen");
	            Destino aux = new Destino(id,carrera, universidad, ciudad, pais, idioma, genero, img);
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
	
	public static int getIdDestino(String pais, String ciudad){
		Connection conecta = null;
		Statement stmt = null;
		int idDestino=-1;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query=("select idDestino from Destinos where ciudad='"+ciudad+"' and pais='"+pais+"'");
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			while (rs.next()) {
				idDestino = rs.getInt("idDestino");
				i++;
	        }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conecta != null) {
				try {
					conecta.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return idDestino;
	}
	
	public static int getIdCarrera(int idDestino, String rama,String carrera,String universidad, String idioma){
		Connection conecta = null;
		Statement stmt = null;
		int idCarrera=-1;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query=("select idCarrera from Carrera where Destino="+idDestino+" and Rama='"+rama+"'and Carrera='"+carrera+"' and Universidad='"+universidad+"' and Idioma='"+idioma+"';");
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				idCarrera = rs.getInt("idCarrera");
	        }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conecta != null) {
				try {
					conecta.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return idCarrera;
	}
}
