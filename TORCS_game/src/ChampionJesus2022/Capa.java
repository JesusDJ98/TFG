package ChampionJesus2022;

import java.util.ArrayList;

public class Capa implements Cloneable {

	private String name;
	private ArrayList<Neurona> Neuronas;
	private ArrayList<Double> salidas;
	private ArrayList<Double> entradas;
	//private ArrayList<Double> sigmas;
	//private FuncionActivacion funcAct;
	
	public Capa(String n) {
		this.name = n;
		this.Neuronas = new ArrayList<>();
		this.salidas = new ArrayList<>();
		this.entradas = new ArrayList<>();
	}
	public Capa(String name,ArrayList<Neurona> e) {//, FuncionActivacion act) {
		this.name = name;
		this.Neuronas = e;
		this.salidas = new ArrayList<>();
		this.entradas = new ArrayList<>();
	}
	
	public Capa(Capa c) {
		this(c.getName(), c.getNeuronas());
	}
	
	public ArrayList<Double> salidasCapa(ArrayList<Double> entrada, FuncionActivacion funcAct){
		ArrayList<Double> salida = new ArrayList<>();
		setEntradas(entrada);//Guardo mis entradas recibadas
		for(Neurona e: this.Neuronas) {
			salida.add(e.funcion(entrada, funcAct));
		}
		setSalidas(salida);//Guardamos la salida generada
		
		return salida;
	}
	
	
	public void Modificar_PesoNeurona(int iNeurona, int iconexion, double p) {
		//System.out.println("\tModCapa--> Tengo "+this.Neuronas.size()+" neuronas, Accedo a "+this.Neuronas.get(iNeurona));
		this.Neuronas.get(iNeurona).changePeso(iconexion, p);
	}
	
	
	
	public ArrayList<Neurona> getNeuronas() {
		return Neuronas;
	}
	public void setNeuronas(ArrayList<Neurona> entradas) {
		this.Neuronas = entradas;
	}
	public ArrayList<Double> getSalidas() {
		return salidas;
	}
	public void setSalidas(ArrayList<Double> salidas) {
		this.salidas = salidas;
	}
	public ArrayList<Double> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Double> entradas) {
		this.entradas = entradas;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public Object clone() {
		//System.out.println("\tEntro en clone de Capa");
		Capa clone = null;
		try {
			clone = (Capa) super.clone();
		}catch(Exception ex) {
			System.out.println("Error clonando Capa: "+ex);
		}
		ArrayList<Neurona> aux = new ArrayList<>();
		for(Neurona n: clone.getNeuronas()) {
			aux.add((Neurona)n.clone());
		}
		clone.Neuronas = aux;
		
		return clone;
	}
	
	
	@Override
	public String toString() {
		String s = "Capa " + this.name+" [\n\t[";
		ArrayList<Double> umbrales = new ArrayList<>();
		//Recorremos los pesos
		if(this.Neuronas.size() > 1) {
			for(int i = 0; i < this.Neuronas.size(); i++) {
				umbrales.add(this.Neuronas.get(i).getUmbral());
				if(i == 0) s += this.Neuronas.get(i) +"\n";
				else if(i == this.Neuronas.size()-1) s += "\t"+ this.Neuronas.get(i) +"\n\t], umbrales [";	//La primera va seguida
				else s += "\t" + this.Neuronas.get(i) +"\n";		//Las demas tienen una separacion
				
			}
		}else {
			s += this.Neuronas.get(0)+"\n\t], umbrales [";
			umbrales.add(this.Neuronas.get(0).getUmbral());
		}
		//Recorremos los umbrales
		for(int i = 0; i < umbrales.size(); i++) {
			if(i == umbrales.size()-1) s += umbrales.get(i)+"]\n]";
			else s += umbrales.get(i)+", ";
		}
		return s;
	}
	
}
