package ChampionJesus2022;

import java.util.ArrayList;

public class Neurona {
	
	private String name;
	private double umbral;
	private ArrayList<Sinapsis> conexiones;
	
	public Neurona(String n) {
		this.name = n;
		this.umbral = Math.random();
		this.conexiones = new ArrayList<>();
	}
	
	public Neurona(String n, ArrayList<Sinapsis> conexiones) {
		this.name = n;
		this.umbral = Math.random();
		this.conexiones = conexiones;
		//this.conexiones = (ArrayList<Sinapsis>)c.clone();
		/*this.conexiones = new ArrayList<>();
		for(Sinapsis c:conexiones) {
			this.conexiones.add(new Sinapsis(c));
		}//*/
	}
	public Neurona(Neurona n) {
		this.name = n.getName();
		this.umbral = n.getUmbral();
		this.conexiones = n.getConexiones();
	}
	
	
	/*
	@Override
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		}catch(Exception ex) {
			System.out.println("Error cloando Neurona: "+ex);
		}
		((Neurona)clone).setName(this.name);
		((Neurona)clone).setUmbral(this.umbral);
		//Funciona pero no perfecto
		//((Neurona)clone).setConexiones(new ArrayList<Sinapsis>(this.conexiones));
		ArrayList<Sinapsis> aux = new ArrayList<>();
		for(Sinapsis i: this.conexiones) {
			aux.add(new Sinapsis(i));
		}
		((Neurona)clone).setConexiones(aux);
		
		
		return clone;
	}//*/
	
	
	
	
	/*
	 * GETTERS & SETTERS
	 */
	
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
		this.conexiones = new ArrayList<>();
		for(Sinapsis c: conexiones) {
			this.conexiones.add(new Sinapsis(c));
		}
		//this.conexiones = conexiones;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	//*/
	
	

	
	public String toString() {
		String s = "Neurona "+this.name+ " --> [";
		//String aux = "";
		
		if(this.conexiones != null && this.conexiones.size() > 0) {
			for(Sinapsis sin: this.conexiones) {
				s += "\n" + sin.toString();
			}
			s += "\n]";
		}else {
			s += "]";
		}
		s += "\n["+this.umbral+"]";
		
		return s;
	}
	

}
