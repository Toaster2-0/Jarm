package database;

import java.time.LocalDateTime;
import java.util.List;

import database.project.ProjectDAO;
import database.user.UserDAO;
import de.jarm.main.data.DataController;
import de.jarm.main.data.Message;
import de.jarm.main.data.Project;
import de.jarm.main.data.ProjectToDo;
import de.jarm.main.data.User;

public class DatabaseTests {
	
	private static UserDAO userDao = new UserDAO();
	private static ProjectDAO projectDao = new ProjectDAO();
	
	public static void main(String[] args) {
		
		User u1 = new User("Jonas", "asd", "jonas@web.de");
		User u2 = new User("Magdalena", "asd", "magdalena@web.de");
		User u3 = new User("Andreas", "asd", "andreas@web.de");
		User u4 = new User("Ronja", "asd", "ronja@web.de");
		
		
		System.out.println("TEST 1 - USER ADDEN --------------------");
		try {
			userDao.addUser(u1);
			System.out.println("ERFOLGREICH");
		} catch (Exception e) {
			System.out.println("FEHLER " + e.getMessage());
		}
		System.out.println("TEST 1 - USER ADDEN --------------------");
		try {
			userDao.addUser(u2);
			System.out.println("ERFOLGREICH");
		} catch (Exception e) {
			System.out.println("FEHLER " + e.getMessage());
		}
		System.out.println("TEST 1 - USER ADDEN --------------------");
		try {
			userDao.addUser(u3);
			System.out.println("ERFOLGREICH");
		} catch (Exception e) {
			System.out.println("FEHLER " + e.getMessage());
		}
		System.out.println("TEST 1 - USER ADDEN --------------------");
		try {
			userDao.addUser(u4);
			System.out.println("ERFOLGREICH");
		} catch (Exception e) {
			System.out.println("FEHLER " + e.getMessage());
		}

		
//		System.out.println("\n\nTEST 2 - LOGIN --------------------");
//		try {
//			User u = userDao.logIn("jonas@web.de", "123");
//			if (u == null) {
//				throw new Exception("Kein User zurückgekommen");
//			}
//			if (!u.getName().equals("Jonas")) {
//				throw new Exception("Falscher name im User Objekt");
//			}
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 3 - LOGIN MIT FALSCHEM PW --------------------");
//		try {
//			User u = userDao.logIn("test", "1234");
//			System.out.println("FEHLER ");
//		} catch (Exception e) {
//			System.out.println("ERFOLGREICH " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 4 - LOGIN MIT NICHT VORHANDENEM USER --------------------");
//		try {
//			User u = userDao.logIn("test123", "1234");
//			System.out.println("FEHLER ");
//		} catch (Exception e) {
//			System.out.println("ERFOLGREICH " + e.getMessage());
//		}
//		
//		
//		System.out.println("\n\nTEST 5 - SELBER USER ERNEUT ANLEGEN --------------------");
//		try {
//			userDao.addUser(u1);
//			System.out.println("FEHLER");
//		} catch (Exception e) {
//			System.out.println("ERFOLGREICH " + e.getMessage());
//		}
//		
//		
//		System.out.println("\n\nTEST 6 - ALLE USER LADEN --------------------");
//		try {
//			List<User> users = userDao.loadUsers();
//			if (users == null) throw new Exception("Keine user vorhanden");
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 7 - ANDERE BENUTZER ANLEGEN --------------------");
//		try {
//			userDao.addUser(u2);
//			userDao.addUser(u3);
//			userDao.addUser(u4);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 8 - SELECT USER BY ID --------------------");
//		try {
//			User u = userDao.loadUserById(userDao.loadUsers().get(0).getId());
//			System.out.println("ERFOLGREICH, USER: " + u.getName() + ", " + u.getEmail());
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		
//		// ID ZU USERN HINZUF�GEN
//		try {
//			u1 = userDao.loadUserById(userDao.loadUsers().get(0).getId());
//			u2 = userDao.loadUserById(userDao.loadUsers().get(1).getId());
//			u3 = userDao.loadUserById(userDao.loadUsers().get(2).getId());
//			u4 = userDao.loadUserById(userDao.loadUsers().get(3).getId());
//		} catch(Exception e) {
//			System.out.println("KRITISCHER FEHLER TESTS WERDEN ABGEBROCHEN");
//			return;
//		}
//
//		Project p1 = new Project("Projekt1", u1);
//		Project p2 = new Project("Projekt2", u2);
//		Project p3 = new Project("Projekt3", u3);
//		Project p4 = new Project("Projekt4", u4);
//		
//		
//		System.out.println("\n\nTEST 9 - PROJEKT ANLEGEN --------------------");
//		try {
//			projectDao.addProject(p1);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 10 - ALLE PROJEKTE LADEN --------------------");
//		try {
//			p1 = projectDao.loadAllProjects().get(0);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 11 - USER ZU PROJEKT HINZUFÜGEN --------------------");
//		try {
//			projectDao.addUserToProject(p1, u2);
//			projectDao.addUserToProject(p1, u3);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		
//		ProjectToDo t1 = new ProjectToDo("Todo1", "description1");
//		
//		System.out.println("\n\nTEST 12 - TODO ZU PROJEKT HINZUFÜGEN --------------------");
//		try {
//			projectDao.addTodoToProject(p1, t1);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		try {
//			p1 = projectDao.loadAllProjects().get(0);
//			t1 = p1.getToDos().get(0);
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 13 - USER ZU TODO HINZUFÜGEN --------------------");
//		try {
//			projectDao.addUserToTodo(t1, u2);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 14 - ZWEITEN USER ZU TODO HINZUFÜGEN --------------------");
//		try {
//			projectDao.addUserToTodo(t1, u3);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		Message m1 = new Message("Message1", u1);
//		Message m2 = new Message("Message2", u2);
//		
//		System.out.println("\n\nTEST 15 - MESSAGE ZU PROJEKT HINZUFÜGEN --------------------");
//		try {
//			projectDao.addMessageToProject(p1, m1);
//			projectDao.addMessageToProject(p1, m2);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		try {
//			p1 = projectDao.loadAllProjects().get(0);
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 16 - TODO STATE ÄNDERN --------------------");
//		try {
//			projectDao.setTodoState(p1.getToDos().get(0), 1);
//			System.out.println("ERFOLGREICH");
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		System.out.println("\n\nTEST 17 - FIND BY NAME OR EMAIL --------------------");
//		try {
//			List<User> users = userDao.searchUserByNameOrEmail("@web");
//			
//			if (users.size() != 4) throw new Exception("nicht genau 4 user gefunden!");
//			
//			System.out.println("ERFOLGREICH " + users);
//		} catch (Exception e) {
//			System.out.println("FEHLER " + e.getMessage());
//		}
//		
//		// BREAKPOINT TEST
//		try {
//			List<Project> projects = projectDao.loadAllProjects();
//			System.out.println("test");
//		} catch(Exception  e) {
//			System.out.println(e.getMessage());
//		}

	}

}
