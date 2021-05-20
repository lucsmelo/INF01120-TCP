package utils;

import java.util.Map;

import database.models.Research;

public class MapUtils {
	static public final int ASC = 0;
	static public final int DESC = 1;
	static public void printAverageMapOrderedByKey(Map<Research, Double> acceptedResearches, int direction) {
		Research iterationBestResearch;
		Double iterationBestGrade;
		while (acceptedResearches.keySet().size() != 0) {
			iterationBestResearch = null;
			iterationBestGrade = 0.0;
			for (Research rs : acceptedResearches.keySet()) {
				if (iterationBestResearch == null) {
					iterationBestResearch = rs;
					iterationBestGrade = acceptedResearches.get(iterationBestResearch);
				} else {
					switch (direction) {
					case ASC:
						if (acceptedResearches.get(rs) <= iterationBestGrade) {
							iterationBestResearch = rs;
							iterationBestGrade = acceptedResearches.get(rs);
						}
					break;
					
					case DESC:
						if (acceptedResearches.get(rs) >= iterationBestGrade) {
							iterationBestResearch = rs;
							iterationBestGrade = acceptedResearches.get(rs);
						}
					break;
					}
				}
			}
			System.out.println("Artigo: " + iterationBestResearch.getTitle() + " - Nota: " + iterationBestGrade);
			acceptedResearches.remove(iterationBestResearch);
		}		
	}
}
