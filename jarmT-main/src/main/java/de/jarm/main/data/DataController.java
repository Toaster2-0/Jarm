package de.jarm.main.data;

import de.jarm.main.interfaces.ProjectServiceInterface;
import de.jarm.main.database.exceptions.ValidierungsException;

import de.jarm.main.interfaces.UserServiceInterface;

public class DataController {
	
	
	private ProjectServiceInterface projectService;
	private UserServiceInterface userService;
	private static DataController instance;

	private DataController () {
		projectService = new ProjectService();
		userService = new UserService();
	}
	
	public ProjectServiceInterface getProjectService() {
		return projectService;
	}
	
	public UserServiceInterface getUserService() {
		return userService;
	}
	
	public static DataController getInstance () {
		if (DataController.instance == null) {
			DataController.instance = new DataController ();
		}
		return DataController.instance;
	}
	
	public static void main(String[] args) {
		String password = "25";
		try {
			for(int i = 0; i<= 100; i++) {
				
				DataController.getInstance().getUserService().create("moin"+i, password, "xaver.lol"+i+"@gmx.de");				
			}
		} catch (ValidierungsException e) {
			System.out.println("Fehler");
			e.printStackTrace();
		}
		try {
			for(int i = 0; i<= 100; i++) {
				DataController.getInstance().getProjectService().create("project title"+i, DataController.getInstance().getUserService().login("xaver.lol"+i+"@gmx.de", "25"));
			}
			System.out.println(DataController.getInstance().getUserService().login("xaver.lol0@gmx.de", "25").getId());
			
			User u = DataController.getInstance().getUserService().login("xaver.lol0@gmx.de", "25");
			ProjectServiceInterface pro = DataController.getInstance().getProjectService();
			pro.writeMessage(pro.getProjectsByUser(u).get(0), "hi", u);
			
		} catch (ValidierungsException e) {
			System.out.println("Fehler");
			e.printStackTrace();
		}
		System.out.println("fertig");
	}
}
