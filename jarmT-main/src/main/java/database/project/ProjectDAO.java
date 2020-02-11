package database.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.DBController;
import database.DBStatements;
import de.jarm.main.data.Message;
import de.jarm.main.data.Project;
import de.jarm.main.data.ProjectToDo;
import de.jarm.main.data.User;
import de.jarm.main.database.exceptions.NoDataFoundException;
import de.jarm.main.utils.DateUtils;

public class ProjectDAO {
	
	
	public List<Project> loadAllProjects() throws Exception {
		List<Project> projects = new ArrayList<Project>();
		
		try (Connection con = DBController.getInstance().getConnection();
		         Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery(DBStatements.SELECT_PROJECTS);				
			while (rs.next()) {
				int ownerId = rs.getInt("owner_id");	
				int projectId = rs.getInt("id");
				User u = getUserById(ownerId);
				Project p = new Project(projectId, rs.getString("name"), u, DateUtils.toDate(rs.getString("created")));
				
				p.setMessages(getProjectMessages(projectId));
				p.setToDos(getProjectTodos(projectId));
				p.setSubscribers(getProjectUsers(projectId));
				projects.add(p);
			}
			
			rs.close();
			return projects;
			
		}
	}
	
	public Project loadProjectById(int projectId) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECT_BY_ID)) {
			
			pstmt.setInt(1, projectId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int ownerId = rs.getInt("owner_id");			
				User u = getUserById(ownerId);
				Project p = new Project(projectId, rs.getString("name"), u, DateUtils.toDate(rs.getString("created")));
				
				p.setMessages(getProjectMessages(projectId));
				p.setToDos(getProjectTodos(projectId));
				p.setSubscribers(getProjectUsers(projectId));
				
				return p;
			} else {
				throw new NoDataFoundException();
			}						
		}
	}	
	
	public List<Project> loadProjectsByUser(int userId) throws Exception {
		
		List<Project> userProjects = new ArrayList<Project>();
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECTS_BY_USER_ID)) {
			
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int ownerId = rs.getInt("owner_id");	
				int projectId = rs.getInt("id");
				User u = getUserById(ownerId);
				Project p = new Project(projectId, rs.getString("name"), u, DateUtils.toDate(rs.getString("created")));
				
				p.setMessages(getProjectMessages(projectId));
				p.setToDos(getProjectTodos(projectId));
				p.setSubscribers(getProjectUsers(projectId));
				
				userProjects.add(p);
			} 
			
			return userProjects;
		}
	}
	
	public Project addProject(Project p) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_PROJECT,Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, p.getOwner().getId());
			pstmt.setString(2, DateUtils.toString(LocalDateTime.now()));
			pstmt.setString(3, p.getTitle());		
			pstmt.execute();
			
			ResultSet generatedKey = pstmt.getGeneratedKeys();
			if (generatedKey.next()) {
				p.setId(generatedKey.getInt(1));
			} else {
				throw new Exception("Fehler beim Erstellen des Projekts!");
			}
			return p;		
		} 	
	}
	
	public void addUserToProject(Project p, User u) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_USER_TO_PROJECT)) {
			
			pstmt.setInt(1, u.getId());
			pstmt.setInt(2, p.getId());		
			pstmt.execute();
			
		}		
	}
	
	public ProjectToDo addTodoToProject(Project p, ProjectToDo t) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_TODO, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setString(1, DateUtils.toString(LocalDateTime.now()));
			pstmt.setInt(2, t.getState());
			pstmt.setString(3, t.getName());
			pstmt.setString(4, t.getDescription());
			pstmt.setInt(5, p.getId());
			pstmt.execute();
			
			ResultSet generatedKey = pstmt.getGeneratedKeys();
			
			if (generatedKey.next()) {
				t.setId(generatedKey.getInt(1));
			} else {
				throw new Exception("Todo konnte nicht hinzugefügt werden!");
			}	
			return t;			
		} 	
	}
	
	public void addUserToTodo(ProjectToDo t, User u) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_USER_TO_TODO)) {
			
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, u.getId());
			pstmt.execute();		
		}		
	}
	
	public void addUserToTodoById(int todoId, int userId) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_USER_TO_TODO)) {
			
			pstmt.setInt(1, todoId);
			pstmt.setInt(2, userId);
			pstmt.execute();		
		}		
	}
	
	public Message addMessageToProject(Project p, Message m) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_MESSAGE, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setString(1, DateUtils.toString(LocalDateTime.now()));
			pstmt.setInt(2, m.getAuthor().getId());
			pstmt.setString(3, m.getMessage());
			pstmt.setInt(4, p.getId());
			pstmt.execute();
			
			ResultSet generatedKey = pstmt.getGeneratedKeys();
			if (generatedKey.next()) {
				m.setId(generatedKey.getInt(1));
			} else {
				throw new Exception("Nachricht konnte nicht hinzugefügt werden!");
			}
			return m;		
		}	
	}
	
	public void setTodoState(ProjectToDo t, int state) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SET_TODO_STATE_BY_ID)) {
			
			pstmt.setInt(1, state);
			pstmt.setInt(2, t.getId());
			pstmt.execute();
			
		}	
	}
	
	public void setTodoInformation(int todoId, String title, String description, int state) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SET_TODO_INFOS_BY_ID)) {
			
			pstmt.setInt(1, state);
			pstmt.setString(2, title);
			pstmt.setString(3, description);
			pstmt.setInt(4, todoId);
			pstmt.execute();
			
		}	
	}
	
	public void removeUserFromTodo(int todoId, int userId) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.REMOVE_USER_FROM_TODO)) {
			
			pstmt.setInt(1, todoId);
			pstmt.setInt(2, userId);
			pstmt.execute();
			
		}	
	}
	
	public void removeTodoById(int todoId, int projectId) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.REMOVE_TODO_BY_ID)) {
			
			pstmt.setInt(1, todoId);
			pstmt.setInt(2, projectId);
			pstmt.execute();
			
		}	
	}
	
	public void removeUserFromProjectById(int userId, int projectId) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.REMOVE_USER_FROM_PROJECT)) {
			
			pstmt.setInt(1, projectId);
			pstmt.setInt(2, userId);
			pstmt.execute();
			
		}	
	}
	
	//HELPERS
	
	private User getUserById(int userId) throws Exception{
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_USER_BY_ID)) {
			
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				User u = new User(userId, rs.getString("name"), rs.getString("password"), rs.getString("email"));
				return u;
			} else {
				throw new NoDataFoundException();
			}	
		}
	}
	
	private List<ProjectToDo> getProjectTodos(int projectId) throws Exception{
		List<ProjectToDo> todos = new ArrayList<>();
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECT_TODOS_BY_PROJECT_ID)) {
			
			pstmt.setInt(1, projectId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int todoId = rs.getInt("id");
				List<User> attachedUsers = getProjectTodoUsers(todoId);
				ProjectToDo d = new ProjectToDo(todoId, rs.getString("name"), rs.getString("description"), attachedUsers, rs.getInt("state"), DateUtils.toDate(rs.getString("created")));
				todos.add(d);
			}
			return todos;
		}
	}
	
	private List<User> getProjectTodoUsers(int todoId) throws Exception{
		List<User> projectTodoUsers = new ArrayList<User>();
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECT_TODO_USERS_BY_PROJECT_TODO_ID)) {
			
			pstmt.setInt(1, todoId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				projectTodoUsers.add(u);
			}
			return projectTodoUsers; 
		} 
	}
	
	private List<User> getProjectUsers(int projectId) throws Exception{
		List<User> projectUsers = new ArrayList<User>();
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECT_USERS_BY_PROJECT_ID)) {
			pstmt.setInt(1, projectId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"));
				projectUsers.add(u);
			}
			return projectUsers;
		}
	}
	
	private List<Message> getProjectMessages(int projectId) throws Exception {
		List<Message> projectMessages = new ArrayList<Message>();
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECT_MESSAGES_BY_PROJECT_ID)) {
			pstmt.setInt(1, projectId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User u = getUserById(rs.getInt("user_id"));
				Message m = new Message(rs.getInt("id"), rs.getString("message"), DateUtils.toDate(rs.getString("created")), u);
				projectMessages.add(m);
			}
			return projectMessages;		
		}		
	}
}
