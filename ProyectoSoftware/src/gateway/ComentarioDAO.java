package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import objetos.Comentario;
import objetos.Fecha;
import objetos.Usuario;

public class ComentarioDAO {

	/**
	 * Inserta un usuario en la BBDD
	 * Devuelve true si lo inserta, false si no
	 */
	public static void insert(Comentario com) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query="insert into Comentarios(Destino, Usuario, Comentario) "
					+ "values (" + com.getDestino() +",'"+ com.getMail()
					+ "','" + com.getTexto() +"')";
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
	
	/**
	 * Devuelve el usuario con el mail indicado
	 */
	public static ArrayList<Comentario> selectByDestino(int destino) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Comentario> result = new ArrayList<Comentario>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			String query = "SELECT Correo, Nombre, Contraseña, Admin, B.Time as tUsr, "
					+ "idComentario, Destino, Comentario, A.Time as tComment "
					+ "FROM Comentarios A, Usuarios B where "
					+ "correo = usuario AND destino= " + destino 
					+ " ORDER BY A.Time DESC";
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// Datos del usuario
				String nombre = rs.getString("Nombre");
				String mail = rs.getString("Correo");
				String passwd = rs.getString("Contraseña");
				int admin = rs.getInt("Admin");
				String d = rs.getString("tUsr");
				
				Date f = Fecha.mySQLtoDate(d);
				Usuario usr = new Usuario(mail, nombre, passwd, admin, f);
				
				
				// Datos del comentario
				int id = rs.getInt("idComentario");
				int dest = rs.getInt("Destino");
				String texto = rs.getString("Comentario");
				String dComment = rs.getString("tComment");
				// Parse fecha
				Date fecha = Fecha.mySQLtoDate(dComment);
				
				Comentario c = new Comentario(id, destino, usr, texto, fecha);
				result.add(c);
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
	
}
