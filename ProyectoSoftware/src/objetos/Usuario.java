package objetos;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;

public class Usuario {
	
	private String mail;
	private String nombre;
	private String passwd;
	private int admin;
	private Date fecha;
	
	/**
	 * Método constructor
	 */
	public Usuario(String mail, String nombre, String passwd, 
			int admin, Date fecha){
		this.mail = mail;
		this.nombre = nombre;
		this.passwd = passwd;
		this.admin = admin;
		this.fecha = fecha;
	}
	
	/**
	 * Devuelve un String en formato JSON con el 
	 * contenido de un usuario
	 */
	public String toJSON(){
		JSONObject obj = new JSONObject();
		
		obj.put("Mail", mail);
		obj.put("Nombre", nombre);
		//obj.put("Password", passwd);
		//obj.put("Fecha", fecha);
		
		return obj.toString();
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido de
	 * los usuarios
	 */
	public static String toJSON(ArrayList<Asignaturas> vector){
		
		String rs = "{\"usuarios\": [\n";
		for (Asignaturas d: vector){
			
			rs += d.toJSON() + ",\n";
		}
		
		// Consulta vacia?
		if (!vector.isEmpty()){
			int end = rs.lastIndexOf(',');
			rs = rs.substring(0, end);	// Elimina la última coma puesta
		}
		
		return rs + "\n]}";
	}

	// Getters & setters
	public String getMail() {
		return mail;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPasswd() {
		return passwd;
	}

	public int getAdmin() {
		return admin;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
