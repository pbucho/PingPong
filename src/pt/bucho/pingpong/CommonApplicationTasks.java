package pt.bucho.pingpong;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Server;

public abstract class CommonApplicationTasks {

	protected static EndPoint getApplication(ApplicationType type) {
		Kryo kryo = null;
		EndPoint endPoint = null;

		switch (type) {
		case SERVER:
			Server server = new Server();
			kryo = server.getKryo();
			endPoint = server;
			break;
		case CLIENT:
			Client client = new Client();
			kryo = client.getKryo();
			endPoint = client;
			break;
		}
		
		kryo.register(Message.class);
		
		return endPoint;
	}
	
	protected static void addShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Shutting down");
			}
		});
	}

}
