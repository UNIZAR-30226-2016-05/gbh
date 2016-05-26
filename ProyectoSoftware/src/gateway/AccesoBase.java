package gateway;



import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import objetos.Destino;

public class AccesoBase {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1/mydb";//Poner el que sea
	private static final String DB_USER = "root";//Poner el que sea
	private static final String DB_PASSWORD = "ssii2015";//Poner el que sea
	
	
	/*private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://127.4.156.2:3306/";	// Server remoto
	//private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/erasmus";	// Server local
    private static final String DB_USER = "adminSK9mUZc";
    private static final String DB_PASSWORD = "3RfwtQGkiFqX";*/
	
	/**
	 * Establece una conexion con la bbdd
	 */
	public static Connection getDBConnection() {
		

		Connection dbConnection = null;

		/*
		try {

			Class.forName(DB_DRIVER).newInstance();

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
		*/
		try {
			dbConnection = getConnectionFromPool();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbConnection;
		

	}
	
	/**
	 * Devuevle una conexión con la base de datos a
	 * través de un pool de conexiones
	 * @throws SQLException error durante la conexión
	 * @throws NamingException el pool de conexiones no se
	 * ha configurado correctamente
	 * @return conexión con la base de datos
	 */
	public static Connection getConnectionFromPool() throws SQLException, NamingException{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/Erasmus");
		
		return ds.getConnection();
	}

}
