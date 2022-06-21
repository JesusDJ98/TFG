/**
 * 
 */
package champ2011client;

import java.util.ArrayList;
import java.util.StringTokenizer;

import champ2011client.Controller.Stage;

/**
 * @author Daniele Loiacono
 * 
 */
public class Piloto extends Thread{

	private static int UDP_TIMEOUT = 10000;
	private static int port;
	private static String host;
	private static String clientId;
	private static boolean verbose;
	private static int maxEpisodes;
	private static int maxSteps;
	private static Stage stage;
	private static String trackName;
	
	private String[] args;
	private int name;
	public static int a;
	public static ArrayList lista;
	
	public Piloto(int numero, String[] arg, int ab) {
		this.args = arg;
		this.name = numero;
		a = ab;
		//lista = l;
	}

	/**
	 * @param args
	 *            is used to define all the options of the client.
	 *            <port:N> is used to specify the port for the connection (default is 3001)
	 *            <host:ADDRESS> is used to specify the address of the host where the server is running (default is localhost)  
	 *            <id:ClientID> is used to specify the ID of the client sent to the server (default is championship2009) 
	 *            <verbose:on> is used to set verbose mode on (default is off)
	 *            <maxEpisodes:N> is used to set the number of episodes (default is 1)
	 *            <maxSteps:N> is used to set the max number of steps for each episode (0 is default value, that means unlimited number of steps)
	 *            <stage:N> is used to set the current stage: 0 is WARMUP, 1 is QUALIFYING, 2 is RACE, others value means UNKNOWN (default is UNKNOWN)
	 *            <trackName:name> is used to set the name of current track
	 */
	
	public void run() {
		
		/*Esperar a que me den permiso*/
		do {
			System.out.println("Soy Piloto "+this.name+" y estoy esperando a "+ this.a);
			try {
				Thread.sleep(2000);
			}catch(Exception ex) {
				
			}
			
		}while(a != this.name);
		a++;
		parseParameters(this.args, this.name);	//Todo se genera aqui, modificar lo que quiera
		SocketHandler mySocket = new SocketHandler(host, port, verbose);
		String inMsg;
		
		//Controller driver = load(args[0]);//*/
		//Controller driver = new SimpleDriver();
		Controller driver = new JesusDriver();
		driver.setStage(stage);
		driver.setTrackName(trackName);
		
		/* Build init string */
		float[] angles = driver.initAngles();
		String initStr = clientId + "(init";
		for (int i = 0; i < angles.length; i++) {
			initStr = initStr + " " + angles[i];
		}
		initStr = initStr + ")";
		long curEpisode = 0;
		boolean shutdownOccurred = false;
		do {
			

			/*
			 * Client identification
			 */
			do {
				System.out.println("Piloto "+this.name+" --> Intentando entrar en el puerto "+(3001+this.name));
				mySocket.send(initStr);
				inMsg = mySocket.receive(UDP_TIMEOUT);
			} while (inMsg == null || inMsg.indexOf("***identified***") < 0);
			/*	*/
			/*
			 * Start to drive
			 */
			System.out.println("Piloto "+this.name+" --> Empiezo a conducir");
			long currStep = 0;
			while (true) {
				/*
				 * Receives from TORCS the game state
				 */
				inMsg = mySocket.receive(UDP_TIMEOUT);
				System.out.println("Piloto "+this.name+" --> state game: " + inMsg);
				

				if (inMsg != null) {

					/*
					 * Check if race is ended (shutdown)
					 */
					if (inMsg.indexOf("***shutdown***") >= 0) {
						shutdownOccurred = true;
						System.out.println("Piloto "+this.name+" --> Server shutdown!");
						break;
					}

					/*
					 * Check if race is restarted
					 */
					if (inMsg.indexOf("***restart***") >= 0) {
						driver.reset();
						if (verbose)
							System.out.println("Piloto "+this.name+" --> Server restarting!");
						break;
					}

					Action action = new Action();
					if (currStep < maxSteps || maxSteps == 0)
						action = driver.control(new MessageBasedSensorModel(
								inMsg));
					else
						action.restartRace = true;

					currStep++;
					mySocket.send(action.toString());
					System.out.println("\n-------------------------------");
				} else
					System.out.println("Piloto "+this.name+" --> Server did not respond within the timeout");
			}
			

		} while (++curEpisode < maxEpisodes && !shutdownOccurred);

		/*
		 * Shutdown the controller
		 */
		driver.shutdown();
		mySocket.close();
		System.out.println("Piloto "+this.name+" --> Client shutdown.");
		System.out.println("Piloto "+this.name+" --> Bye, bye!");

	}

	private static void parseParameters(String[] args, int n) {
		/*
		 * Set default values for the options
		 */
		port = 3001 + n;
		host = "localhost";
		clientId = "championship2011";
		//System.out.println("");
		//clientId = "Player";
		verbose = true;
		maxEpisodes = 1;
		maxSteps = 0;
		stage = Stage.UNKNOWN;
		trackName = "unknown";
		/*stage = Stage.RACE;
		trackName = "race";//*/
		
		for (int i = 1; i < args.length; i++) {
			StringTokenizer st = new StringTokenizer(args[i], ":");
			String entity = st.nextToken();
			String value = st.nextToken();
			if (entity.equals("port")) {
				port = Integer.parseInt(value);
			}
			if (entity.equals("host")) {
				host = value;
			}
			if (entity.equals("id")) {
				clientId = value;
			}
			if (entity.equals("verbose")) {
				if (value.equals("on"))
					verbose = true;
				else if (value.equals(false))
					verbose = false;
				else {
					System.out.println(entity + ":" + value
							+ " is not a valid option");
					System.exit(0);
				}
			}
			if (entity.equals("id")) {
				clientId = value;
			}
			if (entity.equals("stage")) {
				stage = Stage.fromInt(Integer.parseInt(value));
			}
			if (entity.equals("trackName")) {
				trackName = value;
			}
			if (entity.equals("maxEpisodes")) {
				maxEpisodes = Integer.parseInt(value);
				if (maxEpisodes <= 0) {
					System.out.println(entity + ":" + value
							+ " is not a valid option");
					System.exit(0);
				}
			}
			if (entity.equals("maxSteps")) {
				maxSteps = Integer.parseInt(value);
				if (maxSteps < 0) {
					System.out.println(entity + ":" + value
							+ " is not a valid option");
					System.exit(0);
				}
			}
		}
	}

	private static Controller load(String name) {
		Controller controller=null;
		try {
			controller = (Controller) (Object) Class.forName(name)
					.newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println(name	+ " is not a class name");
			System.exit(0);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return controller;
	}
}
