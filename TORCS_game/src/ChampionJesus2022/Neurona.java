package ChampionJesus2022;

import java.util.ArrayList;

public class Neurona implements Cloneable{
	
	private String name;
	private double umbral;
	private ArrayList<Sinapsis> conexiones;
	
	
	
	public Neurona(String n) {
		this.name = n;
		this.umbral = Math.random();
		this.conexiones = new ArrayList<>();
		//this.funcAct = new Sigmoide();
	}
	
	public Neurona(String n, ArrayList<Sinapsis> c) {
		this.name = n;
		this.umbral = Math.random();
		this.conexiones = c;
	}
	
	public Neurona(String n, double u, ArrayList<Sinapsis> c) {
		this.name = n;
		this.umbral = u;
		this.conexiones = c;
	}
	
	public Neurona(Neurona n) {
		this(n.getName(), n.getUmbral(), n.getConexiones());
	}
	
	
	public double funcion(ArrayList<Double> entradas, FuncionActivacion funcAct) {
		//Funcion Sumadora --> E(wij*xj)-Oi 
		double suma = 0;
		System.out.println("Soy "+this.name+" y en la funcion tengo "+entradas.size()+" entradas y "+this.conexiones.size()+" conexiones: "+this.conexiones);
		for (int i = 0; i < entradas.size(); i++) {
			suma += entradas.get(i) * this.conexiones.get(i).getPeso();
		}
		suma -= this.umbral;
		
		//Funcion de Activacion
		return funcAct.funcion(suma);//*/
	}
	
	
	
	
	/*
	 * GETTERS & SETTERS
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUmbral() {
		return umbral;
	}

	public void setUmbral(double umbral) {
		this.umbral = umbral;
	}

	public ArrayList<Sinapsis> getConexiones() {
		return conexiones;
	}

	public void setConexiones(ArrayList<Sinapsis> conexiones) {
		this.conexiones = conexiones;
	}
	
	/*
	//Jugar
	public void changePeso(int indice, double p) {
		//System.out.println("\t\tModNeurona--> Tengo "+this.conexiones.size()+" conexiones, Accedo a "+this.conexiones.get(indice));
		this.conexiones.get(indice).setPeso(p);
	}//*/
	
	
	
	@Override
	public String toString() {
		String s = "Neurona "+this.name+ " [";
		//String aux = "";
		
		if(this.conexiones != null && this.conexiones.size() > 0) {
			for(int i = 0; i < this.conexiones.size(); i++) {
				if(i == this.conexiones.size()-1) s += this.conexiones.get(i)+ "]";
				else s += this.conexiones.get(i) + ", ";
			}
		}else {
			s += "]";
		}
		
		return s;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		boolean iguales= false;
		Neurona n = (Neurona)obj;
		boolean samename = n.getName() == this.getName();
		boolean sameumbral = n.getUmbral() == this.getUmbral();
		boolean sameconect= true;
		if(n.getConexiones().size() == this.getConexiones().size()) {
			int i = 0;
			while(sameconect && i < n.getConexiones().size()) {
				if(n.getConexiones().get(i).getPeso() == this.getConexiones().get(i).getPeso()) {
					sameconect = false;
				}
				i++;
			}
		}else {
			sameconect= false;
		}
		
		iguales = samename && sameumbral && sameconect;
		
		return iguales;
	}
	
	@Override
	public Object clone() {
		//System.out.println("\t\tEntro en clone de Neurona");
		Neurona clone = null;
		try {
			clone = (Neurona) super.clone();
		}catch(Exception ex) {
			System.out.println("Error clonando Neurona: "+ex);
		}
		ArrayList<Sinapsis> aux = new ArrayList<>();
		for(Sinapsis s: this.getConexiones()) {
			aux.add((Sinapsis)s.clone());
		}
		clone.conexiones = aux;//*/
		//clone.conexiones = (ArrayList<Sinapsis>) clone.getConexiones().clone();
		
		return clone;
	}

}
