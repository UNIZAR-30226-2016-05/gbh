package objetos;

import java.util.ArrayList;

import org.json.*;

public class Destino {
	protected String carrera;
	protected String universidad;
	protected String pais;
	protected String ciudad;
	protected String idioma;
	protected String genero;
	protected String img;
	private int id;
	private int val;
	
	public Destino (int id, String carrera, String universidad, String ciudad, String pais, String idioma, String genero, String img){
		this.id = id;
		this.carrera = carrera;
		this.universidad=universidad; 
		this.ciudad=ciudad;
		this.pais=pais;
		this.idioma=idioma;
		this.genero=genero;
		this.img=img;
		this.val = 0;
	}
	
	public Destino (int id, String carrera, String universidad, String ciudad, 
			String pais, String idioma, String genero, String img, int v){
		this.id = id;
		this.carrera = carrera;
		this.universidad=universidad; 
		this.ciudad=ciudad;
		this.pais=pais;
		this.idioma=idioma;
		this.genero=genero;
		this.img=img;
		this.val = v;
	}
	
	public String toString(){
		return carrera+" "+universidad+" "+ciudad+" "+pais+" "+idioma+" "+genero+" "+img;
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido del destino
	 */
	public String toJSON(){
		
		JSONObject obj = new JSONObject();
		obj.put("Id", id);
		obj.put("Carrera", carrera);
		obj.put("Universidad", universidad);
		obj.put("Ciudad", ciudad);
		obj.put("Pais", pais);
		obj.put("Idioma", idioma);
		obj.put("Genero", genero);
		obj.put("Img", img);
		obj.put("Valoracion", val);
		
		return obj.toString();
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido del destino
	 */
	public static String toJSON(ArrayList<Destino> vector){
		
		String rs = "{\"destino\": [\n";
		for (Destino d: vector){
			
			rs += d.toJSON() + ",\n";
		}
		
		// Consulta vacia?
		if (!vector.isEmpty()){
			int end = rs.lastIndexOf(',');
			rs = rs.substring(0, end);	// Elimina la última coma puesta
		}
		
		return rs + "\n]}";
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido del destino
	 */
	public static String toJSON1(ArrayList<Destino> vector){
		
		String rs = "{\"carrera\": [\n";
		for (Destino d: vector){
			
			rs += d.toJSON() + ",\n";
		}
		
		// Consulta vacia?
		if (!vector.isEmpty()){
			int end = rs.lastIndexOf(',');
			rs = rs.substring(0, end);	// Elimina la última coma puesta
		}
		
		return rs + "\n]}";
	}

}