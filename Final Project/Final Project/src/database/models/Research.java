package database.models;

public class Research {
	private int id;
	private String title;
	private Researcher author;
	private String topic;
	private Conference conference;
	public Research(int id, String title, Researcher author, String topic, Conference conference) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.topic = topic;
		this.conference = conference;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Researcher getAuthor() {
		return author;
	}
	public void setAuthor(Researcher author) {
		this.author = author;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Conference getConference() {
		return conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
}
