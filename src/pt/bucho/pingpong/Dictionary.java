package pt.bucho.pingpong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Dictionary extends File {

	private static final long serialVersionUID = -2244813962062209877L;

	private Map<Integer, String> dic;

	public Dictionary(String pathname) {
		super(pathname);

		int index = 0;
		dic = new HashMap<Integer, String>();

		try {
			Charset inputCharset = Charset.forName("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(this), inputCharset));

			String line = br.readLine();
			while (line != null) {
				dic.put(index, line);
				index++;
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not read dictionary file");
		} catch (IOException e) {
			System.out.println("Could not read line from dictionary file");
		}

	}

	public String getTranslation(int foreign) {
		return dic.get(foreign % dic.size());
	}

}
