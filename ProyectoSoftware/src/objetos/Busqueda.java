package objetos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gateway.AccesoBase;

public class Busqueda {
	
	public static ArrayList<Destino> Destino(String query) throws SQLException {
		Connection conecta = null;
		Statement stmt = null;
		ArrayList<Destino> lista = new ArrayList<Destino>();

		try {
			conecta = AccesoBase.getDBConnection();
			
			stmt = conecta.createStatement();
			
			// execute query
			ResultSet rs = stmt.executeQuery(query);
			
			String carrera = "";
            String universidad = "";
            String ciudad = "";
            String pais = "";
            String idioma = "";
            String genero = "";
            String img = "";
			while (rs.next()) {
				carrera = rs.getString("Carrera");
				universidad = rs.getString("Universidad");
				ciudad = rs.getString("Ciudad");
				pais = rs.getString("Pais");
				idioma = rs.getString("Idioma");
				genero = rs.getString("Rama");
				img= rs.getString("Imagen");
	            Destino aux = new Destino(0, carrera, universidad, ciudad, pais, idioma, genero, img); // Id provisional
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

	public static ArrayList<Destino> DestinoPorUni(String nombre) throws SQLException {
		String query = "";
		if (nombre==null) query= "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where idDestino=Destino";
		query = "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where Universidad='"+nombre+"' and "
				+ "idDestino=Destino";
		return Destino(query);
		
	}
	
	public static ArrayList<Destino> DestinoPorCarrera(String nombreCarrera) throws SQLException {
		String query = "";
		if (nombreCarrera==null) query= "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where idDestino=Destino";
		query = "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where Carrera='"+nombreCarrera+"' and "
				+ "idDestino=Destino";
		return Destino(query);
		
	}
	
	public static ArrayList<Destino> DestinoPorCiudad(String nombre) throws SQLException {
		String query = "";
		if (nombre==null) query= "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where idDestino=Destino";
		query = "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where Ciudad='"+nombre+"' and "
				+ "idDestino=Destino";
		return Destino(query);
		
	}
	
	public static ArrayList<Destino> DestinoPorPais(String nombre) throws SQLException {
		String query = "";
		if (nombre==null) query= "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where idDestino=Destino";
		query = "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where Pais='"+nombre+"' and "
				+ "idDestino=Destino";
		return Destino(query);
		
	}
	public static ArrayList<Destino> DestinoPorIdioma(String nombre) throws SQLException {
		String query = "";
		if (nombre==null) query= "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where idDestino=Destino";
		query = "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where Idioma='"+nombre+"' and "
				+ "idDestino=Destino";
		return Destino(query);
		
	}
	public static ArrayList<Destino> DestinoPorGenero(String nombre) throws SQLException {
		String query = "";
		if (nombre==null) query= "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where idDestino=Destino";
		query = "select Carrera, Universidad, Ciudad, Pais, Idioma, Rama, Imagen from "
				+ "Carrera, Destinos where Rama LIKE '%"+nombre+"%' and "
				+ "idDestino=Destino";
		return Destino(query);
		
	}
}
