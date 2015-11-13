package pt.bucho.pingpong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Logger extends File {

	private static final long serialVersionUID = -952577030749280708L;
	private FileOutputStream fos;

	public Logger(String pathname) {
		super(pathname);
		try {
			fos = new FileOutputStream(this);
		} catch (NullPointerException e1) {
			System.out.println("Log path is null");
		} catch (IOException | SecurityException e2) {
			System.out.println("Log file can't be read and/or written");
		}
	}

	public void log(String message) {
		String newMessage = new Date() + " - " + message + System.lineSeparator();
		try {
			fos.write(newMessage.getBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("Log file has been moved or deleted");
		} catch (IOException e) {
			System.out.println("Could not write to log");
		}
	}
	
	public void log(Object o) {
		if (o != null) {
			log(o.toString());
		}
	}
	
	public void registerWord(String word) {
		word += " ";
		try {
			fos.write(word.getBytes());
			fos.flush();
		} catch (IOException e) {
			System.out.println("Could not register word");
		}
	}

}
