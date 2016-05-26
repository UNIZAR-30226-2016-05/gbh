package gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import objetos.Asignaturas;
import objetos.Comentario;
import objetos.Fecha;
import objetos.Usuario;

public class UsuarioDAO {
	
	/**
	 * Devuelve el numero de asignaturas introducidas en un mes
	 * de un determinado año
	 */
	public static int[] selectCount(int year) throws SQLException{
		int [] result = new int [12];
		for (int i=0; i< result.length; i++){
			result[i] = 0;
		}
		Connection conecta = null;
		Statement stmt = null;
		
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query="SELECT Count(*) as num, Month(Time) as mes "
					+ "FROM Usuarios where year(Time)=" + year
					+ " group by mes";
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int mes = rs.getInt("mes");
				int count = rs.getInt("num");
				result[mes-1] = count;
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
		
		
		return result;
	}
	
	/**
	 * Inserta un usuario en la BBDD
	 * Devuelve true si lo inserta, false si no
	 */
	public static boolean insert(Usuario usr) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		boolean existe=false;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			//Comprobar si existe 
			String query="select * from Usuarios where Correo='"+ usr.getMail() +"';";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				existe=true;
	        }
			
			if(!existe){
				query="insert into Usuarios(Correo, Nombre, Contraseña, Admin)"
					+ "values ('"+usr.getMail()+"','"+usr.getNombre()+"','"+
						usr.getPasswd()+"',"+usr.getAdmin()+")";
				stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				stmt.close();
				return existe;
			}

			if (conecta != null) {
				conecta.close();
				return existe;
			}

		}
		return existe;
	}
	
	/**
	 * Modifica un usuario en la BBDD
	 * Devuelve true si lo modifica, false si no
	 */
	public static boolean update(Usuario usr) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		boolean existe=false;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			//Comprobar si existe 
			String query="select * from Usuarios where Correo='"+ usr.getMail() +"';";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				existe=true;
	        }
			
			if(existe){
				query="update Usuarios SET "
						+ "Nombre='" + usr.getNombre() 
						+ "', Contraseña='" + usr.getPasswd()
						+ "' WHERE Correo='"+ usr.getMail()+"'";
					stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				stmt.close();
				return existe;
			}

			if (conecta != null) {
				conecta.close();
				return existe;
			}

		}
		return existe;
	}
	
	
	/**
	 * Inserta o actualiza una valoración
	 */
	public static void insertValoracion(String usr, int destino, int val)
			throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		boolean existe=false;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			//Comprobar si existe 
			String query="select * from Valoraciones where "
					+ "Usuario='"+ usr +"' AND Destino=" + destino;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				existe=true;
	        }
			
			if(!existe){
				query="insert into Valoraciones (Usuario, Destino, Valoracion)"
					+ "values ('"+usr+"',"+ destino +","+ val + ")";
				stmt.executeUpdate(query);
			} else {
				query="update Valoraciones SET "
						+ "Valoracion=" + val + " "
						+ "WHERE Usuario='"+ usr+"' AND "
						+ "Destino=" + destino;
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
	
	/**
	 * Devuelve el usuario con el mail indicado
	 */
	public static Usuario selectUsuario(String mail) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		Usuario usuario = null;

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "select * from "
					+ "Usuarios where Correo='"+ mail + "'";
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nombre = rs.getString("Nombre");
				String passwd = rs.getString("Contraseña");
				int admin = rs.getInt("Admin");
				String date = rs.getString("Time");
				
				Date fecha = Fecha.mySQLtoDate(date);
				
				usuario = new Usuario(mail, nombre, passwd, admin, fecha);
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
		return usuario;
	}
	
	/**
	 * Devuelve el usuario con el mail indicado
	 */
	public static ArrayList<Usuario> selectAllUsuarios() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		Usuario usuario = null;
		ArrayList<Usuario> result = new ArrayList<Usuario>();


		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "select * from Usuarios";
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String mail = rs.getString("Correo");
				String nombre = rs.getString("Nombre");
				String passwd = rs.getString("Contraseña");
				int admin = rs.getInt("Admin");
				String date = rs.getString("Time");
				
				Date fecha = Fecha.mySQLtoDate(date);
				
				usuario = new Usuario(mail, nombre, passwd, admin, fecha);
				result.add(usuario);
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
		return result;
	}
	
	public static void delete(String id) throws SQLException{
		Connection conecta = AccesoBase.getDBConnection();

		
		String query = "DELETE FROM Usuarios WHERE Correo='"+id+"';";
		PreparedStatement preparedStatement = conecta.prepareStatement(query);		
		preparedStatement.execute();
		conecta.close();
	}
	
	/**
	 * devuelve el numero de usuarios
	 * @return
	 * @throws SQLException
	 */
	public static int selectNumUsuarios() throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		int d = 0;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			String query = "";
			
			query = "select count(*) as num from Usuarios";
			
			
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
