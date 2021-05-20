package database.models;

import java.util.ArrayList;

import database.core.Database;
import database.core.DatabaseConnector;
import enums.GradeReviewEnum;
import utils.ArrayListUtils;

public class GradeReview {
	private Research research;
	private Researcher researcher;
	private int grade;
	private int status;
	public GradeReview (Research research, Researcher researcher, int grade, int status) {
		this.research = research;
		this.researcher = researcher;
		this.grade = grade;
		this.status = status;
	}
	public Research getResearch() {
		return research;
	}
	public void setResearch(Research research) {
		this.research = research;
	}
	public Researcher getResearcher() {
		return researcher;
	}
	public void setResearcher(Researcher researcher) {
		this.researcher = researcher;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
