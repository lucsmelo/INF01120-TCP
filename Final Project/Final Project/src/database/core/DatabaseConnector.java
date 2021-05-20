package database.core;

public class DatabaseConnector {
	private static Database db;
	public static Database connect() {
		db = new Database();
		return db;
	}
	public static Database getDB() {
		return db;
	}
}
