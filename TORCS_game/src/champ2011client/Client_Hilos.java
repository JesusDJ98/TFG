/**
 * 
 */
package champ2011client;

import java.util.ArrayList;
import java.util.Random;

import ChampionJesus2022.Capa;
import ChampionJesus2022.Fichero;
import ChampionJesus2022.FuncionActivacion;
import ChampionJesus2022.Neurona;
import ChampionJesus2022.RedNeuronal;
import ChampionJesus2022.Sigmoide;
import ChampionJesus2022.Sinapsis;

/**
 * @author Daniele Loiacono
 * Client.java
 */
public class Client_Hilos {

	//public static int a = 0;
	
	public static void main(String[] args) {
		
		
		
		System.out.println("-----------------------------------");
		System.out.println("\t\tEMPEZAMOS CARRERA");
		System.out.println("-----------------------------------");
		
		
		int a = 0;	//Para que se conecten en orden
		System.out.println("Conectamos pilotos");
		Piloto piloto1 = new Piloto(0, args, a);
		//Piloto piloto2 = new Piloto(1, args, a);
		//Piloto piloto3 = new Piloto(2, args);
		//Piloto piloto4 = new Piloto(3, args);
		
		
		piloto1.start();
		//piloto2.start();
		//*/
		
		
//		if(fin) {
//			System.out.println("GUARDO");
//			String dir = "C:\\Users\\jesus\\OneDrive\\Escritorio\\TFG_Coche\\TFG\\TORCS_game\\Archivos";
//			String name = "Salida_Ejecucion";
//			ArrayList<String> reci = piloto1.getRecibidos();
//			ArrayList<String> send = piloto1.getEnviados();
//			String info = "";
//			int tama = send.size();
//			if(reci.size() > send.size()) {
//				tama =reci.size();
//			}
//			for(int i = 0; i < tama; i++) {
//				info += "Recibido: "+reci.get(i)+"\n";
//				info += "Enviado: "+send.get(i)+"\n\n--------------------\n";
//			}
//			Fichero f = new Fichero();
//			f.Guardar(dir, name, info);
//		}
		/*
		double factor_aprendizaje = 0.1;
		FuncionActivacion FSigmoide = new Sigmoide();
		//Disseño red
		//Conexiones
		Sinapsis e1o1 = new Sinapsis();	//Neurona Entrada 1 con Oculta
		Sinapsis e1o2 = new Sinapsis();
		Sinapsis e1o3 = new Sinapsis();
		Sinapsis e2o1 = new Sinapsis();	//Neurona Entrada 2 con Oculta
		Sinapsis e2o2 = new Sinapsis();
		Sinapsis e2o3 = new Sinapsis();
		Sinapsis o1s = new Sinapsis();	//Neurona Oculta 1 con Salida
		Sinapsis o2s = new Sinapsis();	//Neurona Oculta 2 con Salida
		Sinapsis o3s = new Sinapsis();	//Neurona Oculta 3 con Salida
		Sinapsis ss = new Sinapsis();	//Salida de la Neurona de Salida... si necesaria
		
		
		ArrayList<Sinapsis> se1 = new ArrayList<>();
		se1.add(e1o1);se1.add(e1o2); se1.add(e1o3);
		ArrayList<Sinapsis> se2 = new ArrayList<>();
		se2.add(e2o1);se2.add(e2o2); se2.add(e2o3);
		ArrayList<Sinapsis> so1 = new ArrayList<>();
		so1.add(o1s);
		ArrayList<Sinapsis> so2 = new ArrayList<>();
		so2.add(o2s);
		ArrayList<Sinapsis> so3 = new ArrayList<>();
		so3.add(o3s);
		ArrayList<Sinapsis> Sinap_s = new ArrayList<>();
		Sinap_s.add(ss);
		
		//Generamos neuronas
		Neurona e1 = new Neurona("Entrada 1",se1);
		Neurona e2 = new Neurona("Entrada 2",se2);
		Neurona o1 = new Neurona("Oculta 1",so1);
		Neurona o2 = new Neurona("Oculta 2",so2);
		Neurona o3 = new Neurona("Oculta 3",so3);
		Neurona s = new Neurona("Salida",Sinap_s);

		
		
		
		
		//Genero las capas
		ArrayList<Neurona> capaE = new ArrayList<>();
		capaE.add((Neurona)e1.clone());capaE.add(e2);
		ArrayList<Neurona> capaE2 = new ArrayList<>();
		capaE2.add(e1);capaE2.add(e2);
		ArrayList<Neurona> capaO1 = new ArrayList<>();
		capaO1.add(o1);capaO1.add(o2);capaO1.add(o3);
		ArrayList<Neurona> capaS = new ArrayList<>();
		capaS.add(s);
		
		
		Capa CE = new Capa("Entrada", capaE);
		//Capa CE2 = new Capa("Entrada", capaE);
		//Capa CE3 = new Capa("Entrada", capaE2);
		Capa CO1 = new Capa("Oculta1", capaO1);
		Capa CS = new Capa("Salida",capaS);
		ArrayList<Capa> perceptron = new ArrayList<>();
		perceptron.add((Capa)CE.clone());
		perceptron.add((Capa)CO1.clone());
		perceptron.add((Capa)CS.clone());
		/* Necesito esto porue sino deberia de hacer una copia en perceptron y no mola pues e sun arraylist...
		perceptron.add(CE);
		perceptron.add(CO1);
		perceptron.add(CS);
		// */
		
		/*RedNeuronal red1 = new RedNeuronal("Prueba",perceptron, FSigmoide, factor_aprendizaje);
		//RedNeuronal red2 = new RedNeuronal(perceptron, FSigmoide, factor_aprendizaje);	//HAy que pasarle una copia
		RedNeuronal red2 = new RedNeuronal(red1);
		red2.setNombre("Prueba2");
		System.out.println(" ");
		System.out.println("------------------ Redes ----------------------");
		System.out.println(red1);
		System.out.println("\n\tRed2");
		System.out.println(red2);
		/*red1.Modificar_PesoNeurona(1, 1, 0, 8);
		red2.Modificar_PesoNeurona(0, 0, 0, 9);
		o3s.setPeso(10);//Modifico una sinapsis	--> No afecta
		System.out.println(" ");
		System.out.println("----- CAMBIO -----");
		System.out.println(red1);
		System.out.println("\n\tRed2");
		System.out.println(red2);//*/
		/*ArrayList<Neurona> todasN = red1.getNeuronas();
		todasN.get(3).setUmbral(0.1);
		ArrayList<Sinapsis> todasS = red2.getConexiones();
		todasS.get(5).setPeso(2);
		System.out.println(" ");
		System.out.println("----- CAMBIO -----");
		System.out.println(red1);
		System.out.println("\n\tRed2");
		System.out.println(red2);//*/
		
		
		
		
		/*
		System.out.println("\n\n---------------------Guardar y leer archivo ---------------");
		Fichero f = new Fichero();
		String dir = "C:\\Users\\jesus\\OneDrive\\Escritorio\\TFG_Coche\\TFG\\TORCS_game\\Archivos";
		String name = "Red1";
		f.Guardar(dir, name, red1.toString());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//*/
		/*Fichero f = new Fichero();
		String dir = "C:\\Users\\jesus\\OneDrive\\Escritorio\\TFG_Coche\\TFG\\TORCS_game\\Archivos";
		String name = "Red1";
		//RedNeuronal red3 = f.Cargar(dir, name);
		System.out.println("\n\n---------------------------------------------------\n\n");
		
		ArrayList<RedNeuronal> redes = f.CargarRedes(dir, name);
		String s = "";
		for(RedNeuronal r: redes) {
			s += r.toString() + "\n";
			//System.out.println(r);
		}
		System.out.println(s);
		/*System.out.println("\n\n\n");
		System.out.println(red3);//*/
	}
		

}
