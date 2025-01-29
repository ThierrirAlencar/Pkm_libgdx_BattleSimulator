package io.github.PokemonGame.Classes.Database;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DB_FILE = "Database/core.db";
	private static final String URL = "jdbc:sqlite:" + getDatabasePath();

	private static String getDatabasePath() {
		return Paths.get(System.getProperty("user.dir"), DB_FILE).toString();
	}

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(URL);
	}
}
