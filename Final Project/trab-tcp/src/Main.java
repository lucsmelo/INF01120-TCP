import database.core.Database;
import database.core.DatabaseConnector;
import database.models.Research;
import database.repositories.ResearchMockRepository;
import ui.TextUI;

public class Main {

	public static void main(String[] args) {
		Database db = DatabaseConnector.connect();
		db.addFakeData();

		TextUI textUI = new TextUI();
		textUI.init();
	}

}
