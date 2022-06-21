package ChampionJesus2022;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Fichero {
	
	public RedNeuronal Cargar(String dir, String name)
	{
		RedNeuronal aux = null;
		String nameF = dir+"/"+name+".txt";
		File f = new File(nameF);
		Scanner s = null;
		try {
			s = new Scanner(f);
			int cnt = 0;
			boolean p = true;
			boolean nuevo = false;
			String iniUmb = "\t],";
			
			ArrayList<Neurona> neuronas = new ArrayList<>();
			ArrayList<Capa> capas = new ArrayList<>();
			String nameC = "";
			while(s.hasNextLine()) {
				String nameN = "";
				ArrayList<Sinapsis> conexiones = new ArrayList<>();
				cnt++;
				String line = s.nextLine();
				//System.out.println(line+ "  \t  \t  \t  "+cnt);
				
				if(cnt > 2) {
					if(nuevo){//if(line.equals("]")) {		//Termina la capa
						cnt = 0;
						p = true;
						nuevo = false;
						capas.add(new Capa(nameC, neuronas));
						neuronas = new ArrayList<>();	//Limpiamos las neuronas
					}else {
						if(line.equals(iniUmb)) {	//Salto de pesos a umbrales
							p = false;
						}else {
							ArrayList<Double> valores = new ArrayList<>();
							String[] entradas = line.split("\\[");	//0-Name | 1-Info
							String[] limpio = entradas[1].split("\\]");	//Elimino el ] del final
							try {
								String[] val = limpio[0].split(", ");
								for(String v:val) {
									valores.add((Double.parseDouble(v)));
								}
							}catch(Exception ex) {
								System.out.println("Probando si tiene comas... "+ex);
							}
							String[] names = entradas[0].split(" ");//Nombre de la neurona
							if(names.length == 3) {
								nameN = names[1]+" "+names[2];
							}else if(names.length == 2){
								nameN = names[1];
							}//else {	//Umbrales, esto no es una neurona
							
							
							if(p){	//Rellenamos los pesos en las conexiones
								for(Double pe:valores) {
									conexiones.add(new Sinapsis(pe));
								}
								neuronas.add(new Neurona(nameN, conexiones));
							}else {	//Rellenamos los umbrales
								nuevo = true;
								for(int i = 0; i < valores.size(); i++) {
									neuronas.get(i).setUmbral(valores.get(i));
								}
							}
						}
					}
				}else if(cnt == 1) {	//Nombre de la capa
					String[] intro = line.split(" ");
					nameC = intro[1];	// 0-Capa 1-Name 2-[
				}
				
			}
			aux = new RedNeuronal(capas);
		}catch(Exception ex) {
			System.out.println("Error abriendo archivo: "+ex);
		}finally {
			try {
				if(s != null) {
					s.close();
				}
			}catch(Exception ex) {
				System.out.println("Error cerrando archivo: "+ex);
			}
		}
		return aux;
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

}
