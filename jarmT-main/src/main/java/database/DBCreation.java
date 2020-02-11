package database;

public class DBCreation {
	
	public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS user ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name TEXT NOT NULL UNIQUE, "
			+ "password TEXT NOT NULL, "
			+ "created TEXT NOT NULL, "
			+ "email TEXT NOT NULL UNIQUE"
			+ ");";
	
	public static final String CREATE_PROJECT_TABLE = "CREATE TABLE IF NOT EXISTS project ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "owner_id INTEGER, "
			+ "created TEXT NOT NULL, "
			+ "name TEXT, "
			+ "FOREIGN KEY (owner_id) REFERENCES user (id) "
			+ ");";
	
	public static final String CREATE_PROJECT_USER_TABLE = "CREATE TABLE IF NOT EXISTS project_user ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "user_id INTEGER,"
			+ "project_id INTEGER, "
			+ "FOREIGN KEY (user_id) REFERENCES user (id), "
			+ "FOREIGN KEY (project_id) REFERENCES project (id) "
			+ ");";
	
	public static final String CREATE_PROJECT_TODO_TABLE = "CREATE TABLE IF NOT EXISTS project_todo ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "created TEXT NOT NULL, "
			+ "name TEXT NOT NULL, "
			+ "description TEXT, "
			+ "project_id INTEGER, "
			+ "state INTEGER, "
			+ "FOREIGN KEY (project_id) REFERENCES project (id) "
			+ ");";
	
	public static final String CREATE_PROJECT_TODO_USER_TABLE = "CREATE TABLE IF NOT EXISTS project_todo_user ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "project_todo_id INTEGER, "
			+ "user_id INTEGER, "
			+ "FOREIGN KEY (project_todo_id) REFERENCES project_todo (id), "
			+ "FOREIGN KEY (user_id) REFERENCES user (id) "
			+ ");";
	
	public static final String CREATE_PROJECT_MESSAGE_TABLE = "CREATE TABLE IF NOT EXISTS project_message ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "created TEXT NOT NULL, "
			+ "user_id INTEGER, "
			+ "message TEXT, "
			+ "project_id INTEGER, "
			+ "FOREIGN KEY (user_id) REFERENCES user (id), "
			+ "FOREIGN KEY (project_id) REFERENCES project (id) "
			+ ");";
}
