package ChampionJesus2022;

import java.util.Random;

import champ2011client.Action;
import champ2011client.Controller;
import champ2011client.SensorModel;

public class ManualDriver extends Controller {
	Random r = new Random();
	
	Action action = new Action ();

	@Override
	public Action control(SensorModel sensors) {
		// TODO Auto-generated method stub
		
		Teclado t = new Teclado(action, sensors);
		//this.action = 
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*action.gear = getGear(sensors);
        action.steering = getSteer(sensors);
        action.accelerate = getAccel(sensors);
        action.brake = getBreak(sensors);
        action.clutch = getClutch(sensors);*/
        return action;
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
