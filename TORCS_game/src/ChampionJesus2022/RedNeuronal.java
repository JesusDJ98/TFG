package ChampionJesus2022;

import java.util.ArrayList;

/*
 * Basado en codigo
 * https://binary-coffee.dev/post/programando-un-perceptron-multicapa-en-java
 * http://grupo.us.es/gtocoma/pid/pid10/RedesNeuronales.htm
 * https://www.grycap.upv.es/gmolto/docs/eda/EDA_Tema_14_Parte_I_gmolto.pdf
 * https://www.youtube.com/watch?v=0rsNVrA_Kxo
 */

public class RedNeuronal implements Cloneable {
	
	private ArrayList<Capa> Capas;
	private double alfa;
	//private ArrayList<double[][]> deltas;	//Creo que lo puedo introducir en sinapsis
	private ArrayList<ArrayList<Double>> sigmas;	//Podria estar en neurona o capa, pero creo que aqui mejor
	private FuncionActivacion funcAct;
	
	public RedNeuronal(ArrayList<Capa> red, FuncionActivacion act, double factor_aprendizaje) {
		this.Capas = new ArrayList<>();
		for(Capa c: red) {
			Capas.add(new Capa(c));
		}
		this.alfa = factor_aprendizaje;
		this.funcAct = act;
	}
	
	public ArrayList<Double> predict(ArrayList<Double> entrada) {
		ArrayList<Double> salida = new ArrayList<>();
		//Recorremos las capas
		for(Capa i: this.Capas) {
			salida = i.salidasCapa(entrada, this.funcAct);
			entrada = salida;	//La salida de una capa es la entrada de la siguiente
		}
		return salida;
	}
	
	/*
	 * ERROR CUADRATICO 
	 */
	public double error(ArrayList<Double> salida, ArrayList<Double> salidaEsperada) {
		double error = 0;
		for(int i =0; i < salida.size(); i++) {
			error += (Math.pow(salida.get(i)-salidaEsperada.get(i),2))/2;
		}
		
		return error;
	}
	
	public double errorTotal(ArrayList<ArrayList<Double>> entrada, ArrayList<ArrayList<Double>> salidaEsperada) {
		double error = 0;
		for(int i = 0; i < entrada.size(); i++) {
			error += error(predict(entrada.get(i)), salidaEsperada.get(i));
		}
		return error;
	}
	
	public void InitDeltas() {
		for(Capa c: this.Capas) {	//Recorremos las capas de la red
			for(Neurona n: c.getNeuronas()) {	//Recorremos las neuronas de la capa
				for(Sinapsis s: n.getConexiones()) {	//Recorremos las conexiones de cada neurona
					s.setDelta(0);
				}
			}
		}
	}
	
	public void InitSigmas() {
		this.sigmas = new ArrayList<>();
		for(Capa c: this.Capas) {
			ArrayList<Double> sigma = new ArrayList<>();
			for(Neurona n: c.getNeuronas()) {
				sigma.add(0.0);
			}
			//c.setSigmas(sigma);
			this.sigmas.add(sigma);
		}
	}
	
	public void calcSigmas(ArrayList<Double> salidaEsperada) {
		InitSigmas();
		//Recorremos en sentido inverso
		for(int i = this.Capas.size()-1; i >= 0; i--) {
			for(int j = 0; j < this.Capas.get(i).getNeuronas().size(); i++) {
				if(i == this.Capas.size()-1) {	//Capa de salida
					double y = this.Capas.get(i).getSalidas().get(j);
					double v = (y - salidaEsperada.get(j)) * this.funcAct.fderivada(y);
					this.sigmas.get(i).set(j, v);
				}else {
					double sum = 0;	//Recorremos la capa anterior
					for(int k = 0; k < this.Capas.get(i+1).getNeuronas().size(); k++) {
						double peso_mis_Sinapsis = this.Capas.get(i+1).getNeuronas().get(k).getConexiones().get(j).getPeso();
						double sigma_posterior = this.sigmas.get(i+1).get(k);
						sum += peso_mis_Sinapsis*sigma_posterior;
					}
					double salida = this.Capas.get(i).getSalidas().get(j);
					double v = this.funcAct.fderivada(salida) * sum;
					this.sigmas.get(i).set(j, v);
				}
			}
		}
	}
	
	
	public void calcDeltas() {
		for(int i = 0; i < this.Capas.size(); i++) {
			for(int j = 0; j < this.Capas.get(i).getNeuronas().size(); j++) {
				for(int k = 0; k < this.Capas.get(i).getNeuronas().get(j).getConexiones().size(); k++) {
					double v = this.sigmas.get(i).get(j) * this.Capas.get(i+1).getSalidas().get(k);
					this.Capas.get(i).getNeuronas().get(j).getConexiones().get(k).setDelta(v);
				}
			}
		}
	}
	
	
	public void actualizarPesos() {
		for(Capa c: this.Capas) {	//Recorremos las capas de la red
			for(Neurona n: c.getNeuronas()) {	//Recorremos las neuronas de la capa
				for(Sinapsis s: n.getConexiones()) {	//Recorremos las conexiones de cada neurona
					double p = s.getPeso();
					p -= this.alfa * s.getDelta();
					s.setPeso(p);
				}
			}
		}
	}
	
	public void actualizarUmbrales() {
		for(int i = 0; i < this.Capas.size(); i++) {
			for(int j = 0; j < this.Capas.get(i).getNeuronas().size(); j++) {
				double u = this.Capas.get(i).getNeuronas().get(j).getUmbral();
				u -= this.alfa * this.sigmas.get(i).get(j);
				this.Capas.get(i).getNeuronas().get(j).setUmbral(u);
			}
		}
	}
	
	public void BackPropagation(ArrayList<ArrayList<Double>> entradas, ArrayList<ArrayList<Double>> salidaEsperada) {
		InitDeltas();
		for(int i = 0; i < entradas.size(); i++) {
			predict(entradas.get(i));
			calcSigmas(salidaEsperada.get(i));
			calcDeltas();
			actualizarUmbrales();
		}
		actualizarPesos();
	}
	
	
	
	
	public void Modificar_PesoNeurona(int iCapa, int iNeurona, int iconexion, double p) {
		//System.out.println("Quiero acceder a Capa "+iCapa+" Neurona "+iNeurona+" Conexion "+iconexion);
		//System.out.println("ModRed--> Tengo "+this.Capas.size()+" capas");
		this.Capas.get(iCapa).Modificar_PesoNeurona(iNeurona, iconexion, p);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Object clone() {
		//System.out.println("Entro en clone de RedNeuronal");
		RedNeuronal clone = null;
		try {
			clone = (RedNeuronal) super.clone();
		}catch(Exception ex) {
			System.out.println("Error clonando RedNeuronal: "+ex);
		}
		ArrayList<Capa> Caux = new ArrayList<>();
		for(Capa c: this.Capas) {
			Caux.add((Capa)c.clone());
		}
		clone.Capas = Caux;
		clone.funcAct = this.funcAct;	//Esto da un poco igual
		clone.sigmas = this.sigmas;		//Esto se inicializa al arrancar asi que tb da igual
		
		return clone;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Capa c: this.Capas) {
			s += c+"\n";
		}
		
		return s;
	}
	
	
	

}
