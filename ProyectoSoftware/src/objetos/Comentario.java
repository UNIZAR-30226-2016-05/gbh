package objetos;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;

public class Comentario {
	
	private int id;
	private int destino;
	private String usuario;
	private String texto;
	private Date time;
	
	/**
	 * Método constructor
	 */
	public Comentario(int idComentario, int destino, String usuario,
			String texto, Date time){
		this.id = idComentario;
		this.destino = destino;
		this.usuario = usuario;
		this.texto = texto;
		this.time = time;
		
	}
	
	/**
	 * Método constructor sin identificador
	 */
	public Comentario(int destino, String usuario,
			String texto, Date time){
		this.id = 0;
		this.destino = destino;
		this.usuario = usuario;
		this.texto = texto;
		this.time = time;
		
	}
	
	/**
	 * Devuelve un String en formato JSON con el 
	 * contenido de un usuario
	 */
	public String toJSON(){
		JSONObject obj = new JSONObject();
		
		obj.put("Id", id);
		obj.put("Destino", destino);
		obj.put("Usuario", usuario);
		obj.put("Texto", texto);
		// Fecha en formato especial para la web
		obj.put("Time", Fecha.getFechaToWeb(time));
		
		return obj.toString();
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido de
	 * los usuarios
	 */
	public static String toJSON(ArrayList<Asignaturas> vector){
		
		String rs = "{\"comentarios\": [\n";
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

	public int getId() {
		return id;
	}

	public int getDestino() {
		return destino;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getTexto() {
		return texto;
	}

	public Date getTime() {
		return time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

}
