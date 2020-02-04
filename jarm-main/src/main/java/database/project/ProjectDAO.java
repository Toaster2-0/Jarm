package database.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import utils.DateUtils;

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
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Project loadProjectById(int projectId) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_PROJECT_BY_ID)) {
			
			pstmt.setInt(1, projectId);
			ResultSet rs = pstmt.executeQuery();
			
			int ownerId = rs.getInt("owner_id");			
			User u = getUserById(ownerId);
			Project p = new Project(projectId, rs.getString("name"), u, DateUtils.toDate(rs.getString("created")));
			
			p.setMessages(getProjectMessages(projectId));
			p.setToDos(getProjectTodos(projectId));
			p.setSubscribers(getProjectUsers(projectId));
			
			rs.close();
			return p;
			
		} catch (Exception e) {
			throw e;
		}
	}	
	
	public void addProject(Project p) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_PROJECT)) {
			
			pstmt.setInt(1, p.getOwner().getId());
			pstmt.setString(2, DateUtils.toString(LocalDate.now()));
			pstmt.setString(3, p.getTitle());		
			pstmt.execute();
			
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	public void addUserToProject(Project p, User u) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_USER_TO_PROJECT)) {
			
			pstmt.setInt(1, u.getId());
			pstmt.setInt(2, p.getId());		
			pstmt.execute();
			
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	public void addTodoToProject(Project p, ProjectToDo t) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_TODO)) {
			
			pstmt.setString(1, DateUtils.toString(LocalDate.now()));
			pstmt.setInt(2, t.getState());
			pstmt.setString(3, t.getName());
			pstmt.setString(4, t.getDescription());
			pstmt.setInt(5, p.getId());
			pstmt.execute();
			
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	public void addUserToTodo(ProjectToDo t, User u) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_USER_TO_TODO)) {
			
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, u.getId());
			pstmt.execute();
			
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	public void addMessageToProject(Project p, Message m) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.ADD_MESSAGE)) {
			
			pstmt.setString(1, DateUtils.toString(LocalDate.now()));
			pstmt.setInt(2, m.getAuthor().getId());
			pstmt.setString(3, m.getMessage());
			pstmt.setInt(4, p.getId());
			pstmt.execute();
			
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	public void setTodoState(ProjectToDo t, int state) throws Exception {
		
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SET_TODO_STATE_BY_ID)) {
			
			pstmt.setInt(1, state);
			pstmt.setInt(2, t.getId());
			pstmt.execute();
			
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	//HELPERS
	
	private User getUserById(int userId) throws Exception{
		try (Connection con = DBController.getInstance().getConnection();
		         PreparedStatement pstmt = con.prepareStatement(DBStatements.SELECT_USER_BY_ID)) {
			
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			User u = new User(userId, rs.getString("name"), rs.getString("password"), rs.getString("email"));
			rs.close();
			return u;
		} catch (SQLException e) {
			throw e;
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
			
			rs.close();
			return todos;
		} catch (Exception e) {
			throw e;
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
			rs.close();
			return projectTodoUsers; 
		} catch (Exception e) {
			throw e;
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
			rs.close();
			return projectUsers;
		} catch(SQLException e) {
			throw e;
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
			rs.close();
			return projectMessages;
			
		} catch (SQLException e) {
			throw e;
		}
		
	}
}
