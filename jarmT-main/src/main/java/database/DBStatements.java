package database;

public class DBStatements {
	
	//QUERY
	
	public static final String SELECT_USER_LOGIN = "SELECT * FROM user WHERE email = ? AND password = ?;";
	
	public static final String SELECT_PROJECTS = "SELECT * FROM project;";
	
	public static final String SELECT_PROJECT_BY_ID = "SELECT * FROM project WHERE id = ?;";
	
	public static final String SELECT_PROJECT_USERS_BY_PROJECT_ID = "SELECT * FROM user WHERE EXISTS (SELECT 1 FROM project_user WHERE project_id = ? AND user_id = user.id);";
	
	public static final String SELECT_PROJECT_TODOS_BY_PROJECT_ID = "SELECT * FROM project_todo WHERE project_id = ?;";
	
	public static final String SELECT_PROJECT_TODO_USERS_BY_PROJECT_TODO_ID = "SELECT * FROM user WHERE EXISTS (SELECT 1 FROM project_todo_user WHERE project_todo_id = ? AND user_id = user.id);";
	
	public static final String SELECT_PROJECT_MESSAGES_BY_PROJECT_ID = "SELECT * FROM project_message WHERE project_id = ?;";
	
	public static final String SELECT_ALL_USERS = "SELECT * FROM user;";
	
	public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
	
	public static final String SELECT_PROJECTS_BY_USER_ID = "SELECT * FROM project WHERE EXISTS (SELECT 1 FROM project_user WHERE user_id = ? AND project_id = project.id) UNION SELECT * FROM project WHERE owner_id = ?;";
	
	public static final String FIND_USER_BY_NAME_OR_EMAIL = "SELECT * FROM user WHERE name LIKE ? OR email LIKE ?;";
	
	public static final String FIND_USER_IN_PROJECT_BY_NAME_OR_EMAIL = "SELECT u.id, u.email, u.name, u.password FROM user u, project_user p WHERE (u.name LIKE ? OR u.email LIKE ? ) AND p.project_id = ? AND u.id = p.user_id UNION SELECT u2.id, u2.email, u2.name, u2.password FROM user u2, project p2 WHERE (u2.name LIKE ? OR u2.email LIKE ? ) AND p2.id = ? AND u2.id = p2.owner_id;";
	
	//INSERT
	
	public static final String ADD_USER = "INSERT INTO user (name, password, created, email) VALUES (?,?,?,?);";
	
	public static final String ADD_PROJECT = "INSERT INTO project (owner_id, created, name) VALUES (?,?,?);";
	
	public static final String ADD_TODO = "INSERT INTO project_todo (created, state, name, description, project_id) VALUES (?,?,?,?,?);";
	
	public static final String ADD_MESSAGE = "INSERT INTO project_message (created, user_id, message, project_id) VALUES (?,?,?,?);";
	
	public static final String ADD_USER_TO_PROJECT = "INSERT INTO project_user (user_id, project_id) VALUES (?,?);";
	
	public static final String ADD_USER_TO_TODO = "INSERT INTO project_todo_user (project_todo_id, user_id) VALUES (?,?);";
	
	//UPDATE
	
	public static final String SET_TODO_STATE_BY_ID = "UPDATE project_todo SET state = ? WHERE id = ?;";
	
	public static final String SET_TODO_INFOS_BY_ID = "UPDATE project_todo SET state = ?, name = ?, description = ? WHERE id = ?;";
	
	//DELETE 

	public static final String REMOVE_USER_FROM_TODO = "DELETE FROM project_todo_user WHERE project_todo_id = ? AND user_id = ?;";
	
	public static final String REMOVE_TODO_BY_ID = "DELETE FROM project_todo WHERE id = ? AND project_id = ?;";
	
	public static final String REMOVE_USER_FROM_PROJECT = "DELETE FROM project_user WHERE project_id = ? AND user_id = ?;";
	
	//NOT IMPLEMENTED
	
	
	
	public static final String REMOVE_PROJECT_BY_ID = "DELETE FROM ";
}
