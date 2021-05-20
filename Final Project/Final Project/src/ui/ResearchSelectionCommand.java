package ui;

import java.util.ArrayList;
import java.util.Map;

import database.core.Database;
import database.models.Conference;
import database.models.GradeReview;
import database.models.Research;
import enums.GradeReviewEnum;
import exceptions.AllocationException;
import exceptions.EntryNotFoundException;
import exceptions.ReviewException;
import utils.MapUtils;

public class ResearchSelectionCommand implements Command {

	@Override
	public void execute() {
		CommonUtils commonUtils = new CommonUtils();
		Database db = new Database();
		System.out.print("Digite uma conferência (sigla): ");
		String selectedConference = commonUtils.readShortName();
		try {
			// busca a conferencia para ver se ela existe...
			Conference foundConference = db.findConferenceByShortName(selectedConference); 
			if (!foundConference.isAllocated()) {
				System.out.println("[A conferência ainda não foi alocada]");
			}
			else if (foundConference.isFullyReviewed()) {
				// caso todas tenham sido revisadas
				this.showResearchAverages(foundConference);
				
			} else {
					System.out.println("[A conferência ainda não foi totalmente revisada]");
			}
			
		} catch (EntryNotFoundException enfe) {
			System.out.println("[" + enfe.getMessage() + "]");
		} catch (Exception e) {
			System.out.println("[Erro genérico: " + e.getMessage() + "]");
		}
	}
	
	public void showResearchAverages(Conference conference) throws EntryNotFoundException, AllocationException, ReviewException {
		Map<Research, Double> accepted = conference.getAcceptedResearches();
		Map<Research, Double> rejected = conference.getRejectedResearches();
		System.out.println("[ACEITOS]");
		if(accepted.size() == 0) {
			System.out.println("Nenhum artigo encontrado.");
		}
		MapUtils.printAverageMapOrderedByKey(accepted, MapUtils.DESC);
		System.out.println("\n[REJEITADOS]");
		if(rejected.size() == 0) {
			System.out.println("Nenhum artigo encontrado.");
		}
		MapUtils.printAverageMapOrderedByKey(rejected, MapUtils.ASC);
	}


}
