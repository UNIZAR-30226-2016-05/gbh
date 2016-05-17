package objetos;

import java.util.ArrayList;

import org.json.JSONObject;

public class Asignaturas {
	protected int idAsignatura;
	protected String nombre;
	protected String carrera;
	protected int creditos;
	protected int cuatrimestre;
	
	public Asignaturas (int idAsignatura, String nombre, String carrera, int creditos, int cuatrimestre){
		this.idAsignatura = idAsignatura;
		this.nombre=nombre; 
		this.carrera=carrera;
		this.creditos=creditos; 
		this.cuatrimestre=cuatrimestre;
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido del destino
	 */
	public String toJSON(){
		
		JSONObject obj = new JSONObject();
		obj.put("Id", idAsignatura);
		obj.put("Nombre", nombre);
		obj.put("Creditos", creditos);
		obj.put("Cuatrimestre", cuatrimestre);
		
		return obj.toString();
	}
	
	/**
	 * Devuelve un String en formato JSON con el contenido del destino
	 */
	public static String toJSON(ArrayList<Asignaturas> vector){
		
		String rs = "{\"asignatura\": [\n";
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
}