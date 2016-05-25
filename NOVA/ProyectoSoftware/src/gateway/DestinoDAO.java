package gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				query="insert into Destinos (idDestino, Pais, Ciudad, Validado) values ("+idDestino+",'"+pais+"','"+ciudad+"',FALSE)";
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
						+ "validado) values ("+idCarrera+","+idDestino+",'"+genero+"','"+carrera+"','"+universidad+"', '"+idioma+"','"+img+"', FALSE)";
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
			
			query = "select A.idCarrera, A.Carrera, A.Universidad, D.Ciudad, D.Pais, A.Idioma, A.Rama, A.Imagen from Carrera A, Destinos D where "
					+ "(Carrera LIKE '%"+ busqueda +"%' OR Rama LIKE '%"+ busqueda + "%') "
					+ "and idDestino=Destino and A.Validado = 1 and D.Validado = 1";
			
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
				int val = selectValoracion(id);
	            Destino aux = new Destino(id,carrera, universidad, ciudad, pais,
	            		idioma, genero, img, val);
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
			
			query = "select A.idCarrera, A.Carrera, A.Universidad, D.Ciudad, D.Pais, A.Idioma, A.Rama, "
					+ "A.Imagen from Carrera A, Destinos D where idCarrera = " + busqueda + " and idDestino=Destino";
			
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
	
	public static ArrayList<Destino> selectDestinoSinValidar() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Destino> lista = new ArrayList<Destino>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "";
			
			query = "select idDestino, Pais, Ciudad from Destinos where Validado = 0";
			
			
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
				id = rs.getInt("idDestino");
				ciudad = rs.getString("Ciudad");
				pais = rs.getString("Pais");
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
	
	public static ArrayList<Destino> selectCarreraSinValidar() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Destino> lista = new ArrayList<Destino>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "";
			
			query = "select A.Destino, A.idCarrera, A.Carrera, A.Universidad, D.Ciudad, D.Pais, A.Idioma, A.Rama, "
					+ "A.Imagen from Carrera A, Destinos D where A.Validado = 0 and idDestino=A.Destino";
			
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			int id=0;
			int idCarrera=0;
			String carrera = "";
            String universidad = "";
            String ciudad = "";
            String pais = "";
            String idioma = "";
            String genero = "";
            String img = "";
			while (rs.next()) {
				id = rs.getInt("Destino");
				idCarrera = rs.getInt("idCarrera");
				carrera = rs.getString("Carrera");
				universidad = rs.getString("Universidad");
				ciudad = rs.getString("Ciudad");
				pais = rs.getString("Pais");
				idioma = rs.getString("Idioma");
				genero = rs.getString("Rama");
				img= rs.getString("Imagen");
	            Destino aux = new Destino(id,carrera, universidad, ciudad, pais, idioma, genero, img);
	            aux.setIdCarrera(idCarrera);
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
	
	/**
	 * devuelve el numero de carreras sin validar
	 * @return
	 * @throws SQLException
	 */
	public static int selectNumCarreraSinValidar() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		int d = 0;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query = "";
			
			query = "select count(*) as num from Carrera where Validado = 0 group by Validado";
			
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				// Datos de la consulta
				d = rs.getInt("num");
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
		return d;
	}
	
	/**
	 * devuelve el numero de carreras sin validar
	 * @return
	 * @throws SQLException
	 */
	public static int selectNumDestinoSinValidar() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		int d = 0;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query = "";
			
			query = "select count(*) as num from Destinos where Validado = 0 group by Validado";
			
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				// Datos de la consulta
				d = rs.getInt("num");
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
		return d;
	}
	
	/**
	 * Devuelve el usuario con el mail indicado
	 */
	public static int selectValoracion(int destino) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		Double d = 0.0;
		
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "select sum(valoracion)/count(valoracion) as Rate, Destino "
					+ "from Valoraciones WHERE destino=" + destino + " " 
					+ "group by destino";
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				// Datos de la consulta
				d = rs.getDouble("Rate");
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
		if (d == 0.0) return 0;
		else {
			return d.intValue();
		}
	}
	public static void deleteDestino(String id) throws SQLException{
		Connection conecta = AccesoBase.getDBConnection();

		
		String query = "DELETE FROM Destinos WHERE idDestino='"+id+"';";
		PreparedStatement preparedStatement = conecta.prepareStatement(query);		
		preparedStatement.execute();
		conecta.close();
	}
	
	public static void updateDestino(String id) throws SQLException{
		Connection conecta = AccesoBase.getDBConnection();

		String query = "UPDATE Destinos set Validado=1 WHERE idDestino='"+id+"';";
		PreparedStatement preparedStatement = conecta.prepareStatement(query);		
		preparedStatement.execute();
		conecta.close();
	}
	public static void deleteCarrera(String id) throws SQLException{
		Connection conecta = AccesoBase.getDBConnection();

		
		String query = "DELETE FROM Carrera WHERE idCarrera='"+id+"';";
		PreparedStatement preparedStatement = conecta.prepareStatement(query);		
		preparedStatement.execute();
		conecta.close();
	}
	
	public static void updateCarrera(String id) throws SQLException{
		Connection conecta = AccesoBase.getDBConnection();

		String query = "UPDATE Carrera set Validado=1 WHERE idCarrera='"+id+"';";
		PreparedStatement preparedStatement = conecta.prepareStatement(query);		
		preparedStatement.execute();
		conecta.close();
	}
	
	/**
	 * devuelve el numero de valoraciones
	 * @return
	 * @throws SQLException
	 */
	public static int selectNumValoraciones() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		int d = 0;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query = "";
			
			query = "select count(*) as num from Valoraciones";
			
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				// Datos de la consulta
				d = rs.getInt("num");
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
		return d;
	}
}
