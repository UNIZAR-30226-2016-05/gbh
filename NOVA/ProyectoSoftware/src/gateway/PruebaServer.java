package gateway;

import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Destino;

public class PruebaServer {

	public static void main (String[] args){
			ArrayList<Destino> uno = null;
		try {
			uno = DestinoDAO.buscarDestino("medicina", null, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = Destino.toJSON(uno); 
			
		System.out.println(result);
	}
	
}
