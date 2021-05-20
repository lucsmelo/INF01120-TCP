package ui;

import java.util.ArrayList;

import allocator.Allocator;
import database.core.Database;
import database.core.DatabaseConnector;
import database.models.Conference;
import database.models.GradeReview;
import database.models.Research;
import database.models.Researcher;
import enums.GradeReviewEnum;
import exceptions.AllocationException;
import exceptions.EntryNotFoundException;
import exceptions.ReadIntOutOfRangeException;
import utils.ArrayListUtils;

public class ResearchBinderCommand implements Command {

	@Override
	public void execute() {
		Database db = DatabaseConnector.getDB();
		CommonUtils commonUtils = new CommonUtils();
		System.out.print("Selecione uma conferência (SIGLA): ");
		try {
			String shortName = commonUtils.readShortName();
			Conference foundConference = db.findConferenceByShortName(shortName);
			if (foundConference.isAllocated()) {
				System.out.println("[Conferência já alocada]");
				return;
			}
			System.out.print("Informe um número de revisores de 2 e 5: ");
			int neededReviewers = commonUtils.readIntBetweenRange(2, 5);
			Allocator.allocate(foundConference, neededReviewers);
		} catch(EntryNotFoundException enfe) {
			System.out.println("[" + enfe.getMessage() + "]");
		} catch (ReadIntOutOfRangeException re) {
			System.out.println("[" + re.getMessage() + "]");
		} catch (AllocationException ae) {
			System.out.println("[" + ae.getMessage() + "]");
		}
		
	}
	

}
