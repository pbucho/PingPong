package pt.bucho.pingpong;

import java.io.IOException;
import java.util.Date;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientApplication extends CommonApplicationTasks {

	protected static String destAddr;
	protected static int destPort;
	protected static int interval;
	
	/**
	 * arg0 - destination address
	 * arg1 - destination port
	 * arg2 - communication timeout (ms)
	 *  
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Arguments missing");
			System.exit(1);
		} else {
			destAddr = args[0];
			destPort = Integer.parseInt(args[1]);
			interval = Integer.parseInt(args[2]);
		}

		addShutdownHook();
		final Client client = (Client) getApplication(ApplicationType.CLIENT);

		client.start();

		client.addListener(new Listener() {
			public void received(Connection cn, Object object) {
				if (object instanceof Message) {
					Message msg = (Message) object;
					System.out.print("received: " + msg);
					Message response = new Message(String.valueOf(new Date()));
					System.out.println(", sending: " + response);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e){
						System.out.println("Thread interrupted");
					}
					client.sendTCP(response);
				}
			}
		});

		try {
			client.connect(5000, destAddr, destPort);
			Message msg = new Message("Hello");
			client.sendTCP(msg);
		} catch (IOException e) {
			System.out.println("Could not communicate with server");
		}
		
		while(true){}
	}
}
