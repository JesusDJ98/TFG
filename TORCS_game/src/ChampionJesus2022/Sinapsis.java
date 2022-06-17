package ChampionJesus2022;


public class Sinapsis implements Cloneable{
	
	private double peso;
	private double delta;
	
	public Sinapsis() {
		this.peso = Math.random();
		this.delta = 0;
	}
	
	public Sinapsis(double p) {
		this.peso = p;
		this.delta = 0;
	}
	
	
	
	
	/*
	 * GETTERS & SETTERS
	 */

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	
	
	@Override
	public Object clone() {
		//System.out.println("\t\t\tEntro en clone de Sinapsis");
		Sinapsis clone = null;
		try {
			clone = (Sinapsis) super.clone();
		}catch(Exception ex) {
			System.out.println("Error clonando Sinapsis: "+ex);
		}
		
		return clone;
	}//*/
	
	
	
	@Override
	public String toString() {
		//String s = "Peso: "+this.peso;
		String s = ""+this.peso;
		return s;
	}
	

}
