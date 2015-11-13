package pt.bucho.pingpong;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 5898308067068987706L;
	private final String content;

	public Message() {
		content = "null";
	}

	public Message(String msg) {
		content = msg;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return getContent();
	}

}
