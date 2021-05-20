package allocator;

import java.util.ArrayList;

import database.core.Database;
import database.core.DatabaseConnector;
import database.models.Conference;
import database.models.GradeReview;
import database.models.Research;
import database.models.Researcher;
import enums.GradeReviewEnum;
import exceptions.AllocationException;
import utils.ArrayListUtils;

public class Allocator {
	
	
	public static void allocate(Conference foundConference, int neededReviewers) throws AllocationException {
		System.out.println("Iniciando alocação...\n");
		Database db = DatabaseConnector.getDB();
		ArrayList<GradeReview> newReviews = new ArrayList<GradeReview>();
		for (int i = 0; i < neededReviewers; i++) {
			ArrayList<Research> researchesToAllocate = foundConference.getResearchesToAllocate();
			while (researchesToAllocate.size() != 0) {
					Research lowestIDResearch = ArrayListUtils.getLowestIDResearch(researchesToAllocate);
					System.out.println("Artigo de ID " + lowestIDResearch.getId() + " escolhido...");
					ArrayList<Researcher> possibleResearchers = foundConference.getResearchersExceptAuthorAndFromSameOrganization(lowestIDResearch.getAuthor());
					
					possibleResearchers = ArrayListUtils.getOnlyWithInterestOnTopic(possibleResearchers, lowestIDResearch.getTopic());
					possibleResearchers = ArrayListUtils.removeAlreadyReviewed(lowestIDResearch, possibleResearchers, newReviews);
					GradeReview newGradeReview = new GradeReview(lowestIDResearch, null, 0, GradeReviewEnum.PENDING_REVIEW);
					printFinalPossibleReviewers(possibleResearchers);
					possibleResearchers = ArrayListUtils.getOrderedByNumberOfReviews(possibleResearchers, newReviews);
					printFinalPossibleReviewers(possibleResearchers);
					if (possibleResearchers.size() == 0) {
						throw new AllocationException("Could not allocate desired number of reviewers.");
					}
					Researcher targetReviewer = possibleResearchers.get(0);
					
					newGradeReview.setResearcher(targetReviewer);
					System.out.println("Artigo id " + lowestIDResearch.getId() + " alocado ao revisor id " + newGradeReview.getResearcher().getId() + "\n\n");
					newReviews.add(newGradeReview);
					researchesToAllocate.remove(lowestIDResearch);
				}
		}
		for (GradeReview gr : newReviews) {
			db.queryInsert(gr);
			foundConference.setAllocated(true);
		}
		System.out.println("\nFim da alocação.\n");
	}

	public static void printFinalPossibleReviewers(ArrayList<Researcher> researchers) {
		String researcherList = "";
		for (Researcher rt : researchers) {
			researcherList += rt.getId() + ",";
		}
		System.out.println("Possíveis revisores: " + researcherList.substring(0, Math.max(0, researcherList.length() - 1)));
	}
}
