/**
 * 
 */
package champ2011client;

import java.util.ArrayList;

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
		Piloto piloto2 = new Piloto(1, args, a);
		//Piloto piloto3 = new Piloto(2, args);
		//Piloto piloto4 = new Piloto(3, args);
		
		
		piloto1.start();
		//piloto2.start();
		
	}
		

}
