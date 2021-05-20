package database.models;

import java.util.ArrayList;

public class Researcher {
	private int id;
	private String name, fromOrganization;
	private ArrayList<String> researchTopics;
	public Researcher(int id, String name, String fromOrganization, ArrayList<String> researchTopics) {
		this.id = id;
		this.name = name;
		this.fromOrganization = fromOrganization;
		this.researchTopics = researchTopics;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFromOrganization() {
		return fromOrganization;
	}
	public void setFromOrganization(String fromOrganization) {
		this.fromOrganization = fromOrganization;
	}
	public ArrayList<String> getResearchTopics() {
		return researchTopics;
	}
	public void setResearchTopics(ArrayList<String> researchTopics) {
		this.researchTopics = researchTopics;
	}
}
