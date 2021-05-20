package ui;

import java.util.ArrayList;
import java.util.Scanner;

import database.core.Database;
import database.core.DatabaseConnector;
import database.models.GradeReview;
import database.models.Research;
import exceptions.EntryNotFoundException;
import exceptions.ReadIntOutOfRangeException;

public class GradeReviewCommand implements Command{
	public GradeReviewCommand() {
		
	}

	@Override
	public void execute() {
		CommonUtils commonUtils = new CommonUtils();
		Database db = DatabaseConnector.getDB();
		System.out.println("[Selecione um artigo da lista abaixo]");
		this.showAllResearches();
		System.out.print("Digite sua escolha: ");
		int selection = commonUtils.readAnyInt();
		try {
			Research selectedResearch = db.findResearchById(selection);
			ArrayList<GradeReview> foundGradeReviews = db.getGradeReviewByResearchID(selectedResearch.getId());
			showAllGradeReviews(foundGradeReviews);
			System.out.print("Escolha um revisor: ");
			selection = commonUtils.readAnyInt();
			System.out.print("Digite a nota: ");
			int grade = commonUtils.readIntBetweenRange(-3, 3);
			db.setGradeReview(selectedResearch.getId(), selection, grade);
			
		} catch (EntryNotFoundException enfe) {
			
			System.out.println("["+enfe.getMessage()+"]");
			
		} catch (ReadIntOutOfRangeException re) {
			
			System.out.println("[" + re.getMessage() + "]");
			
		}
		catch (Exception e) {
			
			System.out.println("[Erro genérico: " + e.getMessage() + "]");
			
		}
	}
	
	public void showAllResearches() {
		Database db = new Database();
		for (Research r : db.getAllResearches()) {
			System.out.println(r.getId() + " - " + r.getTitle());
		}
	}
	
	public void showAllGradeReviews(ArrayList<GradeReview> gradeReviews) {
		System.out.println("[REVISORES]");
		for (GradeReview gr : gradeReviews) {
			System.out.println(gr.getResearcher().getId() + " - " + gr.getResearcher().getName());
		}
	}
	
	
}
