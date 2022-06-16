package ChampionJesus2022;


public class Sinapsis {// implements Cloneable{
	
	private double peso;
	private Neurona destino;
	
	public Sinapsis() {
		this.peso = Math.random();
		this.destino = null;
	}
	
	public Sinapsis(Neurona d) {
		this.peso = Math.random();
		//this.destino = d;//Modifica todo
		this.destino = new Neurona(d);	//Solo modifica su objeto
	}
	
	//Esto creo que no necesario
	public Sinapsis(Sinapsis s) {
		this.peso = s.getPeso();
		//this.destino = d;//Modifica todo
		this.destino = new Neurona(s.getDestino());	//Solo modifica su objeto
	}//*/
	
	
	/*
	@Override
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		}catch(Exception ex) {
			System.out.println("Error cloando Neurona: "+ex);
		}
		((Sinapsis)clone).setPeso(this.peso);
		((Sinapsis)clone).setDestino(new Neurona(this.destino));
		
		
		return clone;
	}//*/
	
	
	
	@Override
	public String toString() {
		return "Peso: " + this.peso + " - Neurona destino: "+ this.destino.getName();
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

	public Neurona getDestino() {
		return destino;
	}

	public void setDestino(Neurona destino) {
		this.destino = destino;
	}
	
	

}
