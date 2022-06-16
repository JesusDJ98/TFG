/**
 * 
 */
package champ2011client;

import java.util.ArrayList;

import ChampionJesus2022.Neurona;
import ChampionJesus2022.Sinapsis;

/**
 * @author Daniele Loiacono
 * Client.java
 */
public class Client_Hilos {

	//public static int a = 0;
	
	public static void main(String[] args) {
		/*
		System.out.println("-----------------------------------");
		System.out.println("\t\tEMPEZAMOS CARRERA");
		System.out.println("-----------------------------------");
		
		
		int a = 0;	//Para que se conecten en orden
		System.out.println("Conectamos pilotos");
		Piloto piloto1 = new Piloto(0, args, a);
		Piloto piloto2 = new Piloto(1, args, a);
		//Piloto piloto3 = new Piloto(2, args);
		//Piloto piloto4 = new Piloto(3, args);
		
		
		piloto1.start();
		//piloto2.start();
		//*/
		
		//Generamos neuronas
		Neurona n1 = new Neurona("Entrada 1");
		Neurona n2 = new Neurona("Entrada 2");
		Neurona s = new Neurona("Salida");
		
		//Vemos las neuronas
		System.out.println("Vemos las neuronas");
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(s);
		System.out.println(" ");
		
		//Generamos las conexiones
		Sinapsis n1s = new Sinapsis(s);
		Sinapsis n2s = new Sinapsis(s);
		System.out.println("Vemos las sinapsis");
		System.out.println(n1s);
		System.out.println(n2s);
		System.out.println(" ");
		
		ArrayList<Sinapsis> sn1 = new ArrayList<>();
		sn1.add(n1s);
		ArrayList<Sinapsis> sn2 = new ArrayList<>();
		sn2.add(n2s);
		n1.setConexiones(sn1);
		n2.setConexiones(sn2);
		
		System.out.println("Vemos las neuronas de nuevo");
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(s);
		System.out.println(" ");
		
		n1.getConexiones().get(0).setPeso(2);
		n2s.setPeso(9);
		System.out.println("Vemos las sinapsis tras un cambio");
		System.out.println(n1s);
		System.out.println(n2s);
		System.out.println("Sinapsis de N1: " + n1.getConexiones().get(0));
		System.out.println("Sinapsis de N2: " + n2.getConexiones().get(0));
		System.out.println(" ");
		
		
		
		System.out.println(" ------------- HASTA AQUI PERFECTO ----------------");
		System.out.println(" ");
		Neurona neurona1 = new Neurona(n1);
		Neurona neurona2 = new Neurona(n2);
		Neurona salida = new Neurona(s);
		System.out.println("Vemos las NUEVAS neuronas");
		System.out.println("n1: "+n1);
		System.out.println("n2: "+n2);
		System.out.println("s: "+s);
		System.out.println("neurona1: "+neurona1);
		System.out.println("neurona2: "+neurona2);
		System.out.println("salida: "+salida);
		System.out.println(" ");
		
		neurona2.setName("NEuroNA2");
		neurona1.setUmbral(5);
		System.out.println("Vemos las NUEVAS neuronas de nuevo");
		System.out.println("n1: "+n1);
		System.out.println("n2: "+n2);
		System.out.println("s: "+s);
		System.out.println("neurona1: "+neurona1);
		System.out.println("neurona2: "+neurona2);
		System.out.println("salida: "+salida);
		System.out.println(" ");
		//Perfecto no necesito cloneable, pues asi no se modifican
		
		
		
		
	}
		

}
