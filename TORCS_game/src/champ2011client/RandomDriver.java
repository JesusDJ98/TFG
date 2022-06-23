package champ2011client;

import java.util.Random;

public class RandomDriver extends Controller {
	Random r = new Random();

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
		
		
		v = r.nextInt(3);  //r.nextInt(8);
		
		
		switch(v) {
			case 0: g = 0;
				break;
			case 1: g = 1;
				break;
			case 2: g = -1; //g = 2; //Para el 8
				break;
			case 3: g = 3;
				break;
			case 4: g = 4;
				break;
			case 5: g = 5;
				break;
			case 6: g = 6;
				break;
			default: g = -1;	//Seria el aleatorio 7
		}
		
		
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
		Random r = new Random();
		int dir = r.nextInt();
		float v = r.nextFloat();
		
		if(dir == 0) {
			steer = v;
		}else {
			steer = -v;
		}
		
		
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
		
		acel = r.nextFloat();
		
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
		
		freno = r.nextFloat();
		
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
		
		clutch = r.nextFloat();
		
		
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
