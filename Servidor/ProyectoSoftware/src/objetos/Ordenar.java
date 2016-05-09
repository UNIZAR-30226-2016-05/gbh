package objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ordenar {

	public static ArrayList<Destino> OrdenPorUnimM(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o1.universidad.compareTo(o2.universidad);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorUniMm(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o2.universidad.compareTo(o1.universidad);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorCarreramM(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o1.carrera.compareTo(o2.carrera);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorCarreraMm(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o2.carrera.compareTo(o1.carrera);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorCiudadmM(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o1.ciudad.compareTo(o2.ciudad);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorCiudadMm(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o2.ciudad.compareTo(o1.ciudad);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorPaismM(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o1.pais.compareTo(o2.pais);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorPaisMm(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o2.pais.compareTo(o1.pais);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorIdiomamM(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o1.idioma.compareTo(o2.idioma);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorIdiomaMm(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o2.idioma.compareTo(o1.idioma);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorGeneromM(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o1.genero.compareTo(o2.genero);
			}
			
			
		});
		return lista;
	}
	
	public static ArrayList<Destino> OrdenPorGeneroMm(ArrayList<Destino> lista) {
		Collections.sort(lista, new Comparator<Destino>(){
			public int compare(Destino o1, Destino o2) {
				return o2.genero.compareTo(o1.genero);
			}
			
			
		});
		return lista;
	}
}
