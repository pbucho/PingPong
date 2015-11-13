package pt.bucho.pingpong;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerApplication extends CommonApplicationTasks {

	static int port;
	static Dictionary dic;
	static Logger log;
	static int interval;

	/**
	 * arg0 - port to listen on
	 * arg1 - timeout between communications (ms)
	 * arg1 - dictionary location
	 * arg2 - log location
	 * 
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Arguments missing");
			System.exit(1);
		} else {
			port = Integer.parseInt(args[0]);
			interval = Integer.parseInt(args[1]);
			dic = new Dictionary(args[2]);
			log = new Logger(args[3]);
		}

		addShutdownHook();
		final Server server = (Server) getApplication(ApplicationType.SERVER);
		
		server.start();
		try {
			server.bind(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof Message) {
					Message msg = (Message) object;

					String translated = dic.getTranslation(msg.hashCode());

					if (translated == null) {
						System.out.println("was null");
					}

					log.registerWord(translated);
					Message response = new Message(String.valueOf(translated
							.hashCode()));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						System.out.println("Thread interrupted");
					}
					server.sendToTCP(connection.getID(), response);
				}
				return;
			}
		});
	}
}
