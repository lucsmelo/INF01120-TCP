package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import database.models.GradeReview;
import database.models.Research;
import database.models.Researcher;

public class ArrayListUtils {
	public static Research getLowestIDResearch(ArrayList<Research> researchList) {
		Research lowestIDResearch = null;
		for (Research rs : researchList) {
			if (lowestIDResearch == null) {
				lowestIDResearch = rs;
			} else {
				if (rs.getId() < lowestIDResearch.getId()) {
					lowestIDResearch = rs;
				}
			}
		}
		return lowestIDResearch;
	}
	
	public static ArrayList<Researcher> getOnlyWithInterestOnTopic(ArrayList<Researcher> researcherList, String topic) {
		ArrayList<Researcher> interestedInTopic = new ArrayList<Researcher>();
		for (Researcher r : researcherList) {
			if(r.getResearchTopics().contains(topic)) {
				interestedInTopic.add(r);
			}
		}
		return interestedInTopic;
	}
	
	public static ArrayList<Researcher> getOrderedByNumberOfReviews(ArrayList<Researcher> possibleResearchers, ArrayList<GradeReview> currentReviews) {
		Map<Researcher, Integer> reviewsPerResearcher = new HashMap<Researcher, Integer>();
		
		for (Researcher r : possibleResearchers) {
			reviewsPerResearcher.put(r, 0);
		}
		
		for (GradeReview gr : currentReviews) {
			if(reviewsPerResearcher.containsKey(gr.getResearcher())) {
				int newCount = reviewsPerResearcher.get(gr.getResearcher()) + 1;
				reviewsPerResearcher.replace(gr.getResearcher(), newCount);
			}
		}
		
		
		
		ArrayList<Researcher> orderedResearchers = new ArrayList<Researcher>();
		Researcher iterationResearcherWithLowestReviewCount;
		while (reviewsPerResearcher.keySet().size() != 0) {
			iterationResearcherWithLowestReviewCount = null;
			for (Researcher r : reviewsPerResearcher.keySet()) {
				if (iterationResearcherWithLowestReviewCount == null) {
					iterationResearcherWithLowestReviewCount = r;
				} else {
					if (reviewsPerResearcher.get(r) < reviewsPerResearcher.get(iterationResearcherWithLowestReviewCount)) {
						iterationResearcherWithLowestReviewCount = r;
					} else if (reviewsPerResearcher.get(r) == reviewsPerResearcher.get(iterationResearcherWithLowestReviewCount)) {
						if (r.getId() < iterationResearcherWithLowestReviewCount.getId()) {
							iterationResearcherWithLowestReviewCount = r;
						}
					}
				}
			}
			orderedResearchers.add(iterationResearcherWithLowestReviewCount);
			reviewsPerResearcher.remove(iterationResearcherWithLowestReviewCount);
		}
		return orderedResearchers;
	}
	
	public static ArrayList<Researcher> removeAlreadyReviewed(Research currentResearch, ArrayList<Researcher> possibleResearchers, ArrayList<GradeReview> currentReviews) {
		ArrayList<Researcher> filteredResearchers = new ArrayList<Researcher>(possibleResearchers);
		for (Researcher r : possibleResearchers) {
			for (GradeReview gr : currentReviews) {
				if (currentResearch.getId() == gr.getResearch().getId() &&  r.getId() == gr.getResearcher().getId()) {
					filteredResearchers.remove(r);
				}
			}
		}
		return filteredResearchers;
	}
}
