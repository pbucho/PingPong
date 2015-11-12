package pt.bucho.pingpong;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Application {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Argument missing");
			System.exit(1);
		}

		Kryo kryo;

		if (args.length > 1 && Boolean.parseBoolean(args[1])) {
			Client client = new Client();

			kryo = client.getKryo();
			kryo.register(Message.class);

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {

					client.start();
					try {
						client.connect(5000, "localhost", 5555);
					} catch (IOException e) {
						e.printStackTrace();
					}

					Message msg = new Message("Olá");

					client.sendTCP(msg);
				}
			});

			t.run();

			System.out.println("Já enviei");
		} else {

			Server server = new Server();

			kryo = server.getKryo();
			kryo.register(Message.class);

			server.start();
			try {
				server.bind(Integer.parseInt(args[0]));
			} catch (IOException e) {
				e.printStackTrace();
			}

			server.addListener(new Listener() {
				public void received(Connection connection, Object object) {
					if (object instanceof Message) {
						Message request = (Message) object;
						System.out.println(request);
					} else {
						System.out.println("Recebi uma coisa mas não percebi :/");
					}
					return;
				}
			});
		}
	}
}
