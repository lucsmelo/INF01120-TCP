package database.core;

import java.util.ArrayList;
import java.util.Arrays;

import database.models.Conference;
import database.models.GradeReview;
import database.models.Research;
import database.models.Researcher;
import database.repositories.ConferenceMockRepository;
import database.repositories.GradeReviewMockRepository;
import database.repositories.ResearchMockRepository;
import database.repositories.ResearcherMockRepository;
import enums.GradeReviewEnum;
import exceptions.EntryNotFoundException;

public class Database {
	public Database() {
		
	}
	
	public void queryInsert(Research newResearch) {
		ResearchMockRepository.researchesRepository.add(newResearch);
	}
	
	public void queryInsert(Researcher newResearcher) {
		ResearcherMockRepository.researchersRepository.add(newResearcher);
	}
	
	public void queryInsert(Conference newConference) {
		ConferenceMockRepository.conferencesRepository.add(newConference);
	}
	
	public void queryInsert(GradeReview newGradeReview) {
		GradeReviewMockRepository.gradeReviewRepository.add(newGradeReview);
	}
	
	public Research findResearchById(int id) throws EntryNotFoundException {
		for (Research r : ResearchMockRepository.researchesRepository) {
			if (r.getId() == id) {
				return r;
			}
		}
		throw new EntryNotFoundException("Research not found for givenID");
	}
	
	public Researcher findResearcherById(int id) throws EntryNotFoundException {
		for (Researcher r : ResearcherMockRepository.researchersRepository) {
			if (r.getId() == id) {
				return r;
			}
		}
		throw new EntryNotFoundException("Researcher not found for givenID");
	}
	
	public Conference findConferenceByShortName(String shortName) throws EntryNotFoundException {
		for (Conference c : ConferenceMockRepository.conferencesRepository) {
			if (c.getShortName().equals(shortName)) {
				return c;
			}
		}
		throw new EntryNotFoundException("Conference not found for short name");
	}
	
	public ArrayList<Research> getAllResearchesByConferenceShortName(String shortName) {
		ArrayList<Research> selectedResearches = new ArrayList<Research>();
		for (Research r : this.getAllResearches()) {
			if (r.getConference().getShortName() == shortName) {
				selectedResearches.add(r);
			}
		}
		return selectedResearches;
	}
	
	public ArrayList<GradeReview> getGradeReviewsByConferenceShortName (String shortName) {
		ArrayList<GradeReview> selectedGradeReviews = new ArrayList<GradeReview>();
		for (GradeReview outerGradeReview : GradeReviewMockRepository.gradeReviewRepository) {
			for (Research rs : this.getAllResearchesByConferenceShortName(shortName)) {
				if (rs.getId() == outerGradeReview.getResearch().getId()) {
					selectedGradeReviews.add(outerGradeReview);
				}
			}
		}
		return selectedGradeReviews;
	}
	
	public ArrayList<Research> getAllResearches() {
		return ResearchMockRepository.researchesRepository;
	}
	
	public ArrayList<GradeReview> getGradeReviewByResearchID(int id) {
		ArrayList<GradeReview> selectedGradeReviews = new ArrayList<GradeReview>();
		for (GradeReview gr : GradeReviewMockRepository.gradeReviewRepository) {
			if (gr.getResearch().getId() == id) {
				selectedGradeReviews.add(gr);
			}
		}
		return selectedGradeReviews;
	}

	public void setGradeReview(int researchId, int reviewerId, int grade) throws EntryNotFoundException {
		ArrayList<GradeReview> desiredGradeReviews = this.getGradeReviewByResearchID(researchId);
		for (GradeReview gr : desiredGradeReviews) {
			if (gr.getResearcher().getId() == reviewerId) {
				gr.setGrade(grade);
				gr.setStatus(GradeReviewEnum.FINISHED_REVIEW);
				return;
			}
		}
		throw new EntryNotFoundException("O revisor desejado não pertence ao artigo");
	}
	
	public void addFakeData() {
		Researcher r1 = new Researcher(1, "João", "UFRGS", new ArrayList<String>(Arrays.asList("Software Product Lines", "Software Reuse", "Modularity")));
		Researcher r2 = new Researcher(2, "Ana", "USP", new ArrayList<String>(Arrays.asList("Software Architecture", "Modularity", "Software Reuse")));
		Researcher r3 = new Researcher(3, "Manoel", "UFRGS", new ArrayList<String>(Arrays.asList("Software Product Lines", "Software Testing")));
		Researcher r4 = new Researcher(4, "Joana", "UFRJ", new ArrayList<String>(Arrays.asList("Software Product Lines", "Software Reuse", "Software Architecture", "Aspect-oriented Programming")));
		Researcher r5 = new Researcher(5, "Miguel", "UFRGS", new ArrayList<String>(Arrays.asList("Software Architecture", "Modularity", "Software Testing")));
		Researcher r6 = new Researcher(6, "Beatriz", "UFRJ", new ArrayList<String>(Arrays.asList("Software Reuse", "Software Testing", "Aspect-oriented Programming")));
		Researcher r7 = new Researcher(7, "Suzana", "UFRGS", new ArrayList<String>(Arrays.asList("Aspect-oriented Programming", "Modularity", "Software Reuse")));
		Researcher r8 = new Researcher(8, "Natasha", "UFRJ", new ArrayList<String>(Arrays.asList("Modularity", "Software Reuse", "Software Quality", "Software Product Lines")));
		Researcher r9 = new Researcher(9, "Pedro", "USP", new ArrayList<String>(Arrays.asList("Aspect-oriented Programming", "Software Architecture")));
		Researcher r10 = new Researcher(10, "Carlos", "USP", new ArrayList<String>(Arrays.asList("Software Reuse", "Modularity")));
		this.queryInsert(r1);
		this.queryInsert(r2);
		this.queryInsert(r3);
		this.queryInsert(r4);
		this.queryInsert(r5);
		this.queryInsert(r6);
		this.queryInsert(r7);
		this.queryInsert(r8);
		this.queryInsert(r9);
		this.queryInsert(r10);
		
		Conference ICSE = new Conference("ICSE", new ArrayList<Researcher>(Arrays.asList(r1, r2, r3, r4, r5, r6, r7)), false);
		Conference FSE = new Conference("FSE", new ArrayList<Researcher>(Arrays.asList(r1, r2, r3, r4, r5, r6, r7)), true);
		Conference SBES = new Conference("SBES", new ArrayList<Researcher>(Arrays.asList(r4, r5, r6, r7, r8, r9, r10)), true);
		this.queryInsert(ICSE);
		this.queryInsert(FSE);
		this.queryInsert(SBES);
		
		Research rs1 = new Research(1, "Coupling and Cohesion", r1, "Modularity", SBES);
		Research rs2 = new Research(2, "Design Patterns", r6, "Software Reuse", FSE);
		Research rs3 = new Research(3, "AspectJ", r7, "Aspect-oriented Programming", FSE);
		Research rs4 = new Research(4, "Feature Model", r8, "Software Product Lines", FSE);
		Research rs5 = new Research(5, "Architecture Recovery", r9, "Software Architecture", FSE);
		Research rs6 = new Research(6, "Funcional Testing", r10, "Software Testing", FSE);
		Research rs7 = new Research(7, "COTs", r6, "Software Reuse", ICSE);
		Research rs8 = new Research(8, "Pointcut", r7, "Aspect-oriented Programming", ICSE);
		Research rs9 = new Research(9, "Product Derivation", r8, "Software Product Lines", ICSE);
		Research rs10 = new Research(10, "Architecture Comformance", r9, "Software Architecture", ICSE);
		Research rs11 = new Research(11, "Structural Testing", r10, "Software Testing", ICSE);
		this.queryInsert(rs1);
		this.queryInsert(rs2);
		this.queryInsert(rs3);
		this.queryInsert(rs4);
		this.queryInsert(rs5);
		this.queryInsert(rs6);
		this.queryInsert(rs7);
		this.queryInsert(rs8);
		this.queryInsert(rs9);
		this.queryInsert(rs10);
		this.queryInsert(rs11);
		
		GradeReview gr1 = new GradeReview(rs1, r8, 2, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr2 = new GradeReview(rs1, r10, 0, GradeReviewEnum.PENDING_REVIEW);
		GradeReview gr3 = new GradeReview(rs2, r7, 2, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr4 = new GradeReview(rs2, r2, 3, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr5 = new GradeReview(rs3, r4, -1, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr6 = new GradeReview(rs3, r6, 1, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr7 = new GradeReview(rs4, r1, 1, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr8 = new GradeReview(rs4, r3, 0, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr9 = new GradeReview(rs5, r4, -3, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr10 = new GradeReview(rs5, r5, -3, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr11 = new GradeReview(rs6, r3, -1, GradeReviewEnum.FINISHED_REVIEW);
		GradeReview gr12 = new GradeReview(rs6, r6, 0, GradeReviewEnum.FINISHED_REVIEW);
		this.queryInsert(gr1);
		this.queryInsert(gr2);
		this.queryInsert(gr3);
		this.queryInsert(gr4);
		this.queryInsert(gr5);
		this.queryInsert(gr6);
		this.queryInsert(gr7);
		this.queryInsert(gr8);
		this.queryInsert(gr9);
		this.queryInsert(gr10);
		this.queryInsert(gr11);
		this.queryInsert(gr12);
		
		
	}
	
}
