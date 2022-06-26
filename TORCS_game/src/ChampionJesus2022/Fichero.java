package ChampionJesus2022;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Fichero {
	
	private int epocas;
	
	public Fichero() {
		this.epocas = 0;
	}
	
	public ArrayList<RedNeuronal> CargarRedes(String dir, String name){
		ArrayList<RedNeuronal> redes = new ArrayList<>();
		//RedNeuronal aux = null;
		String nameF = dir+"/"+name+".txt";
		
		File f = new File(nameF);
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			boolean nuevaCapa = false;
			String sepRed = "}";
			String sepCapa = "]";
			String nameRed = "";
			String nameCapa = "";
			int cnt = 0;				//Filas de cada red
			boolean umbral = false;
			String umb = "\t],";		//umbrales
			String inipesos = "\tPesos [";
			
			ArrayList<Capa> Capas = new ArrayList<>();
			ArrayList<Neurona> Neuronas = new ArrayList<>();
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				//System.out.println(line + "\t\t" + cnt);
				if(cnt == 0) {			//Leemos las epocas
					String[] ep = line.split(": "); //Epochs: X
					this.epocas = Integer.parseInt(ep[1]);
				}else if(cnt == 1) {	//Nombre de la red
					String[] redN = line.split(" "); //Red X {
					nameRed = redN[1];
					nuevaCapa = true;
				}else {					//Informacion de la red
					if(line.equals(sepRed)) {	//Guardamos la red leida y nos preparamos para una nueva
						redes.add(new RedNeuronal(nameRed, Capas));
						Capas = new ArrayList<>();
						cnt = 0;		//Con el incremento es 1, que es entrar en el nombre de la red
					}else if(line.equals(sepCapa)) {
						nuevaCapa = true;
						Capas.add(new Capa(nameCapa, Neuronas));
						Neuronas = new ArrayList<>();
					}else if(line.equals(umb)) {	//La siguiente linea es umbrales
						umbral = true;
					}else if(umbral) {	//Line de los umbrales
						ArrayList<Double> vUmbrales = new ArrayList<>();	//Obtenemos los umbrales
						String[] words = line.split("\\["); //Umbrales - numeros
						String[] valores = words[1].split("\\]"); //numeros sep x comas
						String[] v = valores[0].split(", ");
						for(String s: v) {
							vUmbrales.add(Double.parseDouble(s));
						}
						//Editamos las neuronas con sus umbrales
						for(int i = 0; i < Neuronas.size(); i++) {	//Cambiamos os valores de los umbrales
							Neuronas.get(i).setUmbral(vUmbrales.get(i));
						}
						umbral = false;		//Terminamos umbrales
					}else if(nuevaCapa) {	//Obtenemos nombre de la capa
						String[] sobra = line.split(" \\[");//Capa nombre - nada
						String[] nombre = sobra[0].split("Capa ");
						nameCapa = nombre[1];
						nuevaCapa = false;
					}else if(!line.equals(inipesos)) {	//Generamos las conexiones con sus pesos
						ArrayList<Sinapsis> conexiones = new ArrayList<>();
						String nameN = "";
						
						String[] words = line.split(" \\["); //nombre - numeros
						String[] valores = words[1].split("\\]"); //numeros sep x comas
						String[] v = valores[0].split(", ");
						for(String s: v) {
							conexiones.add(new Sinapsis(Double.parseDouble(s)));
						}
						String[] nombre = words[0].split("Neurona "); //0 nada, 1 nombre
						nameN = nombre[1];
						Neuronas.add(new Neurona(nameN, conexiones));
					}
					
					
				}
				
				cnt ++;
			}
			
			
		}catch(Exception ex) {
			System.out.println("Error leyendo ficheros: "+ex);
		}finally {
			try {
				if(sc != null) {
					sc.close();
				}
			}catch(Exception ex) {
				System.out.println("Error cerrando archivo: "+ex);
			}
		}
		
		return redes;
	}
	
	public void Guardar(String dir, String name, String info)
	{
		FileWriter fw = null;
		String nameF = dir+"/"+name+".txt";
		try {
			fw = new FileWriter(nameF);
			String[] s = info.split("\n");
			for(String line: s) {
				fw.write(line + "\n");
			}
			
			fw.close();
		}catch(Exception ex) {
			System.out.println("Error Guardando archivo: "+ex);
		}
	}

	public int getEpocas() {
		return epocas;
	}

	public void setEpocas(int epocas) {
		this.epocas = epocas;
	}
	

}
