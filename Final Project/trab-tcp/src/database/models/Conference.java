package database.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import database.core.Database;
import database.core.DatabaseConnector;
import enums.GradeReviewEnum;
import exceptions.AllocationException;
import exceptions.EntryNotFoundException;
import exceptions.ReviewException;
import utils.ArrayListUtils;

public class Conference {
	private String shortName; 
	private boolean allocated;
	private ArrayList<Researcher> conferenceMembers;
	public Conference(String shortName, ArrayList<Researcher> conferenceMembers, boolean allocated) {
		this.shortName = shortName;
		this.conferenceMembers = conferenceMembers;
		this.allocated = allocated;
	}
	public boolean isAllocated() {
		return allocated;
	}
	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public ArrayList<Researcher> getConferenceMembers() {
		return conferenceMembers;
	}
	public void setConferenceMembers(ArrayList<Researcher> conferenceMembers) {
		this.conferenceMembers = conferenceMembers;
	}
	public boolean isFullyReviewed() {
		Database db = DatabaseConnector.getDB();
		if (!this.isAllocated()) {
			return false;
		}
		ArrayList<GradeReview> specificConferenceGradeReviews = db.getGradeReviewsByConferenceShortName(this.getShortName());
		for (GradeReview gr : specificConferenceGradeReviews) {
			if (gr.getStatus() == GradeReviewEnum.PENDING_REVIEW) {
				// nota ainda não atribuída em alguma das alocações
				return false;
			}
		}
		return true;
	}

	public Map<Research, Double> getAverageResearchesGrades() throws EntryNotFoundException, AllocationException, ReviewException {
		Database db = DatabaseConnector.getDB();
		if(!this.isAllocated()) {
			throw new AllocationException("Conference not allocated");
		}
		if(!this.isFullyReviewed()) {
			throw new ReviewException("Conference not fully reviewed");
		}
		ArrayList<GradeReview> specificConferenceGradeReviews = db.getGradeReviewsByConferenceShortName(this.getShortName());
		// Mapa<IdPesquisa, Array de notas>
		Map<Integer, ArrayList<Integer>> researchGrades = new HashMap<Integer, ArrayList<Integer>>();
		for (GradeReview gr : specificConferenceGradeReviews) {
			if (researchGrades.containsKey(gr.getResearch().getId())) {
				// se o artigo já esta no mapa, apenas adiciona a nota
				researchGrades.get(gr.getResearch().getId()).add(gr.getGrade());
			} else {
				// se artigo nao está no mapa, adiciona e coloca a primeira nota
				researchGrades.put(gr.getResearch().getId(), new ArrayList<Integer>(Arrays.asList(gr.getGrade())));
			}
		}
		// ao final, calcula as médias de cada artigo e devolve um mapa de Artigo, Media
		Map<Research, Double> researchAverages = new HashMap<Research, Double>();
		for (Integer key : researchGrades.keySet()) {
			Research desiredResearch = db.findResearchById(key);
			researchAverages.put(desiredResearch, 0.0);
			for (Integer grade : researchGrades.get(key)) {
				double oldValue = researchAverages.get(desiredResearch);
				double newValue = oldValue + grade;
				researchAverages.replace(desiredResearch, newValue);
			}
			double oldValue = researchAverages.get(desiredResearch);
			double newValue = oldValue / researchGrades.get(key).size();
			researchAverages.replace(desiredResearch, newValue);
		}
		return researchAverages;
	}

	public Map<Research, Double> getAcceptedResearches() throws EntryNotFoundException, AllocationException, ReviewException {
		if(!this.isAllocated()) {
			throw new AllocationException("Conference not allocated");
		}
		if(!this.isFullyReviewed()) {
			throw new ReviewException("Conference not fully reviewed");
		}
		Map<Research, Double> acceptedResearches = new HashMap<Research, Double>();
		Map<Research, Double> allResearchesAverages = this.getAverageResearchesGrades();
		for (Research rs : allResearchesAverages.keySet()) {
			if (allResearchesAverages.get(rs) >= 0) {
				acceptedResearches.put(rs, allResearchesAverages.get(rs));
			}
		}
		
		
		return acceptedResearches;
	}

	public Map<Research, Double> getRejectedResearches() throws EntryNotFoundException, AllocationException, ReviewException {
		if(!this.isAllocated()) {
			throw new AllocationException("Conference not allocated");
		}
		if(!this.isFullyReviewed()) {
			throw new ReviewException("Conference not fully reviewed");
		}
		Map<Research, Double> rejectedResearches = new HashMap<Research, Double>();
		Map<Research, Double> allResearchesAverages = this.getAverageResearchesGrades();
		for (Research rs : allResearchesAverages.keySet()) {
			if (allResearchesAverages.get(rs) < 0) {
				rejectedResearches.put(rs, allResearchesAverages.get(rs));
			}
		}
		return rejectedResearches;
	}
	
	public ArrayList<Research> getResearchesToAllocate() {
		Database db = DatabaseConnector.getDB();
		
		ArrayList<Research> conferenceResearches = db.getAllResearchesByConferenceShortName(this.getShortName());
		ArrayList<Research> researchesToAllocate = new ArrayList<Research>();
		for (Research rs : conferenceResearches) {
			researchesToAllocate.add(rs);
		}
		return researchesToAllocate;
	}

	public ArrayList<Researcher> getResearchersExceptAuthorAndFromSameOrganization(Researcher rs) {
		ArrayList<Researcher> desiredResearchers = new ArrayList<Researcher>();
		for (Researcher r : this.getConferenceMembers()) {
			if (r.getId() != rs.getId() && !r.getFromOrganization().equals(rs.getFromOrganization())) {
				desiredResearchers.add(r);
			}
		}
		return desiredResearchers;
	}
	
}
