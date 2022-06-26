package champ2011client;

import java.util.ArrayList;

import ChampionJesus2022.RedNeuronal;

public class MyDriver extends Controller {
	String name;
	RedNeuronal volante;	//De -1 a 1
	RedNeuronal pedal;		//Accel de 0 a 1  & break de 0 a 1
	RedNeuronal marcha;		//De -1 a 6
	
	/*
	 * Me falta clutch pedal	--> Nose si meterlo como marcha o hacerle una red para el
	 * meta 	--> pero este no lo quiero
	 * focus	-->  Determina que sensor recibe info (-90,90)
	 */
	
	/*
	 * F
	 */
	
	public MyDriver(String n, ArrayList<RedNeuronal> redes) {
		this.name = n;
		this.volante = redes.get(0);
		this.pedal = redes.get(1);
		this.marcha = redes.get(2);
	}



	@Override
	public Action control(SensorModel sensors) {
		// TODO Auto-generated method stub
		
		
		Action action = new Action ();
        action.gear = getGear(sensors);
        action.steering = getSteer(sensors);
        action.accelerate = getAccel(sensors);
        action.brake = getBreak(sensors);
        action.clutch = getClutch(sensors);
        return action;
	}
	
	/*
	 * MARCHAS
	 * -1 	--> Marcha atras
	 * 0 	--> Quieto
	 * 1-6	--> Avanzar
	 * 
	 * Tambien depende de las RPM
	 * que van desde 0 hasta 7000 o 8000
	 */
	private int getGear(SensorModel sensors) {
		int v = 0;
		//System.out.println("");
		int g = sensors.getGear();
		double rpm  = sensors.getRPM();
		
		
		
		return g;
	}
	
	/*
	 * VOLANTE
	 * [-1,1]
	 * -1	--> Ful right
	 * 1	--> Full left
	 */
	private float getSteer(SensorModel sensors){
		float steer = 0;
		
		return steer;
	}
	
	/*
	 * PEDAL de ACELERACION
	 * [0,1]
	 * 0	--> No hace nada
	 * 1	--> Full acelera
	 */
	private float getAccel(SensorModel sensors) {
		float acel = 0;
		
		return acel;
	}
	
	/*
	 * PEDAL de FRENO
	 * [0,1]
	 * 0	--> No hace nada
	 * 1	--> Frena en seco
	 */
	private float getBreak(SensorModel sensors) {
		float freno = 0;
		
		return freno;
	}
	
	/*
	 * PEDAL de EMBRAGUE
	 * [0,1]
	 * 0	--> No hace nada
	 * 1	--> Pisa embrague a full
	 */
	private float getClutch(SensorModel sensors) {
		float clutch = 0;
		
		
		return clutch;
	}
	
	/*	0 o 1
	 * 0 	--> Nada
	 * 1	--> Restart race
	 */
	private int getMeta() {
		int meta = 0;
		
		return meta;
	}
	
	/*
	 * Focus Direction in degrees
	 * [-90,90]
	 * 
	 */
	private float getFocus(SensorModel sensors) {
		float focus = 0;
		
		return focus;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		System.out.println("Restarting the race!");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		System.out.println("Bye bye!");
	}

}
