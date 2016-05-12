package pruebas;

import java.sql.SQLException;
import java.util.Date;

import gateway.UsuarioDAO;
import objetos.Usuario;

public class Test {
	
	public static void main (String[] args){
		
		
		try {
			Usuario u = UsuarioDAO.selectUsuario("uno@gmail.com");
			System.out.println(u.toJSON());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
