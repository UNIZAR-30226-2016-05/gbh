package gateway;



import java.sql.*;
import java.util.ArrayList;

import objetos.Destino;

public class AccesoBase {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/Erasmus";//Poner el que sea
	private static final String DB_USER = "root";//Poner el que sea
	private static final String DB_PASSWORD = "ssii2015";//Poner el que sea
	
	/**
	 * Establece una conexion con la bbdd
	 */
	public static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER).newInstance();
			System.out.println("Registro exitoso");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}
