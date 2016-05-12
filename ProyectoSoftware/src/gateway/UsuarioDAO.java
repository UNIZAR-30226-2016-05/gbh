package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import objetos.Asignaturas;
import objetos.Fecha;
import objetos.Usuario;

public class UsuarioDAO {
	
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
				return !existe;
			}

			if (conecta != null) {
				conecta.close();
				return !existe;
			}

		}
		return false;
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
	

}
