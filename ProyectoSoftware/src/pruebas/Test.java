package pruebas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import gateway.ComentarioDAO;
import gateway.UsuarioDAO;
import objetos.Comentario;
import objetos.Usuario;

public class Test {
	
	public static void main (String[] args){
		test2(1);
		test2(3);
	}
	
	private static void test1(int d){
		ArrayList<Comentario> c = null;
		try {
			c = ComentarioDAO.selectByDestino(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Comentario i: c){
			System.out.println(i.toJSON());
		}
	}
	
	private static void test2(int d){
		try {
			UsuarioDAO.insertValoracion("uno@gmail.com", 2, d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
