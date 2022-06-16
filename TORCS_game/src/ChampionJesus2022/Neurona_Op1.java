package ChampionJesus2022;

public class Neurona_Op1 {
	
	//private String name;//No necesario
	
	//private double[] entradas;	//Creo que no necesarios
	private double[] pesos;
	//private FuncionActivacion funcAct;
	private double activacion;
	//private double umbral;		//Segundo array del tf.keras.models get_weights (Pesos, umbral)
	private Neurona_Op1[] salidas;
	
	
	public Neurona_Op1(Neurona_Op1 n) {
		this.pesos = n.getPesos();
	}
	public Neurona_Op1(double[] p) {
		this.pesos = p;
	}
	
	public Neurona_Op1(int entradas) {
		this.pesos = new double[entradas];
		//Inicializo los pesos aleatoriamente
		for(int i = 0; i < entradas; i++) {
			this.pesos[i] = Math.random();//0-1
		}
		//this.entradas = new double[entradas];
		//this.error = 0;
		/*
		switch(op) {
			case 0:
				this.funcAct = Relu();
				break;
			case 1:
				this.funcAct = Sigmoide();
				break;
			case 2:
				this.funcAct = Nose();
				break;
			default: this.funcAct = Relu();
		}
		//*/
	}
	
	public void Pensar(double[] entradas, FuncionActivacion funcAct) {
		//Funcion Sumadora
		double suma = 0;
		for (int i = 0; i < entradas.length; i++) {
			suma += entradas[i] * pesos[i];
		}
		//Funcion de Activacion
		this.activacion = funcAct.funcion(suma);
	}
	
	public Object clone() {
		Object clone = null;
		
		try {
			
		}catch(Exception ex) {
			
		}
		
		return clone;
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */

	public double[] getPesos() {
		return pesos;
	}

	public void setPesos(double[] pesos) {
		this.pesos = pesos;
	}

}
