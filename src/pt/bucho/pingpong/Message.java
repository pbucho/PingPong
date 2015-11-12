package pt.bucho.pingpong;

public class Message {

	private String content;

	public Message(String msg){
		content = msg;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString(){
		return getContent();
	}
	
}
