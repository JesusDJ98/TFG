package ChampionJesus2022;

import java.util.ArrayList;

public class Controlador {

	int epoch;
	
	public Controlador() {
		this.epoch = 0;
	}
	
	public ArrayList<ArrayList<RedNeuronal>> CargarRedes() {
		String dir = "C:\\Users\\jesus\\OneDrive\\Escritorio\\TFG_Coche\\TFG\\TORCS_game\\Archivos";
		ArrayList<ArrayList<RedNeuronal>> redes = new ArrayList<>();
		Fichero f = new Fichero();
		String extension = ".txt";
		String[] names = {"Primero", "Segundo", "Hijo1", "Hijo2", "H1Mutado", "H2Mutado", "TerceroEntrenado", "CuartoEntrenado"};
		
		for(int i = 0; i < names.length; i++) {
			String name = names[i] + extension;
			redes.add(f.CargarRedes(dir, name));
		}
		//Todos deberias de tener las mismas epocas
		this.epoch = f.getEpocas();
		
		return redes;
	}
	
	/*
	 * EL orden es
	 * 1. Pos 1
	 * 2. Pos 2
	 * 3. Hijo1
	 * 4. Hijo2
	 * 5. Hijo1Mutado
	 * 6. Hijo2Mutado
	 * 7. Pos 3 entrenado con 1
	 * 8. Pos 4 entrenado con 1
	 * 
	 * 
	 * Quiero guardar las nuevas neuronas sobreescribiendo las anteriores
	 * pero para no perderlas, tambien las guardo en otra carpeta
	 */
	public void Guardar(ArrayList<ArrayList<RedNeuronal>> pilotos, int epoch) {
		String dirAntiguos = "C:\\Users\\jesus\\OneDrive\\Escritorio\\TFG_Coche\\TFG\\TORCS_game\\Redes_Antiguos";
		String dir = "C:\\Users\\jesus\\OneDrive\\Escritorio\\TFG_Coche\\TFG\\TORCS_game\\Archivos";
		String ini = "" + epoch + "_";
		String extension = ".txt";
		String[] names = {"Primero", "Segundo", "Hijo1", "Hijo2", "H1Mutado", "H2Mutado", "TerceroEntrenado", "CuartoEntrenado"};
		
		Fichero f = new Fichero();
		
		for(int i = 0; i < pilotos.size(); i++) {
			String nameA = ini + names[i] + extension;
			String name = names[i] + extension;
			String info = "Epocas: "+epoch+"\n";
			for(RedNeuronal r: pilotos.get(i)) {
				info += r.toString() + "\n";
			}
			f.Guardar(dirAntiguos, nameA, info);	//Para no perderlos
			f.Guardar(dir, name, info);				//Para reutilizarlo luego
		}
		
	}
}
