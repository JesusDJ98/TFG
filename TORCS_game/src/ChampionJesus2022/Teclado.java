package ChampionJesus2022;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import champ2011client.Action;
import champ2011client.SensorModel;

public class Teclado extends KeyAdapter{
	
	int gear;
	Action action;
	
	
	public Teclado(Action a, SensorModel s) {
		this.action = a;
		this.gear = s.getGear();
	}
	
	
	public void keyPressed(KeyEvent e) {
		System.out.println(" -------------------------------\n\n");
		System.out.println("\t\tPULSO "+e.getKeyCode());
		System.out.println(" -------------------------------\n\n");
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: //Evento para salir, tecla esc
			//this.action.
			break;//*/
		case KeyEvent.VK_ENTER:			//BAJAR DE MARCHA
			if(this.gear > -1) this.action.gear = this.gear-1;
			break;
		case KeyEvent.VK_LEFT:
			float v = (float)(this.action.steering + 0.5);
			if(this.action.steering > 1) this.action.steering = 1;
			else this.action.steering = v;
			break;
		case KeyEvent.VK_RIGHT:
			float v2 = (float)(this.action.steering - 0.5);
			if(this.action.steering < -1) this.action.steering = -1;
			else this.action.steering = v2;
			break;
		case KeyEvent.VK_DOWN:
			this.action.brake = -1;
			this.action.accelerate = 1;
			break;
		case KeyEvent.VK_UP:
			this.action.accelerate = 1;
			break;
		case KeyEvent.VK_SPACE:
			if(this.gear < 6) this.action.gear = this.gear+1;
			break;
		
		}
	}
}
