package ChampionJesus2022;

public class Sigmoide implements FuncionActivacion{

	@Override
	public double funcion(double x) {
		double y = 0;
		/*
		 * Si las salidas van de 0 a 1 
		 */
		/*
		y = 1/(1 + Math.pow(Math.E, -x));
		//*/
		
		/*
		 * Si las salidas van de -1 a 1 
		 */
		///*
		y = Math.tanh(x);	//Tangente hiperbolica --> e^x - e^-x /e^x + e^-x
		//*/
		
		return y;
	}

	@Override
	public double fderivada(double x) {
		double y = funcion(x);
		return y*(1-y);
	}

}
