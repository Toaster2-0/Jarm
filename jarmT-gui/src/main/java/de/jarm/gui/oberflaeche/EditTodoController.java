package de.jarm.gui.oberflaeche;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.gui.utils.NotificationBuilder;
import de.jarm.main.data.DataController;
import de.jarm.main.data.Project;
import de.jarm.main.data.ProjectToDo;
import de.jarm.main.data.User;
import de.jarm.main.database.exceptions.ValidierungsException;

public class EditTodoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {

		String todoIdString = request.getParameter("todoId");
		String todoTitle = request.getParameter("todoTitle");
		String todoDescription = request.getParameter("todoDescription");
		String todoStateString = request.getParameter("todoState");
		String usersAdded = request.getParameter("AddedUserIdsEditTodo");
		String projectIdString = request.getParameter("id");
		
		try {
			
			int projectId = new Integer(projectIdString);
			int todoId = new Integer(todoIdString);
			int todoState = new Integer(todoStateString);
			
			if (todoState != 0 && todoState != 1 && todoState != 2) {
				throw new ValidierungsException("Bitte einen Status festlegen!");
			}
			if (todoTitle.equals("")) {
				throw new ValidierungsException("Bitte einen Titel festlegen!");
			}
			
			Project currentProject = DataController.getInstance().getProjectService().getProjectById(projectId);
			List<ProjectToDo> currentProjectTodos = currentProject.getToDos();
			ProjectToDo currentTodo = null;
			
			if (currentProjectTodos != null) {
				
				for (ProjectToDo todo : currentProjectTodos) {
					if (todo.getId() == todoId) {
						currentTodo = todo;
						break;
					}
				}
			}
			
			if (currentTodo != null) {
				
				List<Integer> usersToAdd = new ArrayList<>();
				List<Integer> usersToRemove = new ArrayList<>();
				
				if (usersAdded != null) {
					
					List<User> usersAddedToTodo = currentTodo.getInstructedUsers();
					List<Integer> userIdsAddedToTodo = new ArrayList<>();
					
					for (User userInTodo : usersAddedToTodo) {
						
						userIdsAddedToTodo.add(userInTodo.getId());
					} 
					
					String[] allUsers = usersAdded.split(" ");
					List<Integer> usersThatShouldBeInTodo = new ArrayList<>();
					
					for (String u : allUsers) {
						
						if (!u.equals("") && !u.equals(" ")) {
							
							int thisUserId = new Integer(u);
							usersThatShouldBeInTodo.add(thisUserId);
						}
					}
					
					for (int currentUserInTodo : userIdsAddedToTodo) {
						
						if (!usersThatShouldBeInTodo.contains(currentUserInTodo)) {
							usersToRemove.add(currentUserInTodo);
						}
					}
					
					for (int currentUserShouldBeInTodo : usersThatShouldBeInTodo) {
						
						if (!userIdsAddedToTodo.contains(currentUserShouldBeInTodo)) {
							usersToAdd.add(currentUserShouldBeInTodo);
						}
					}
					
					DataController.getInstance().getProjectService().updateTodoInformation(todoId, todoTitle, todoDescription, new Integer(todoState));
					
					for (int i : usersToAdd) {
						DataController.getInstance().getProjectService().addUserToTodo(todoId, i);
					}
					
					for (int i : usersToRemove) {
						DataController.getInstance().getProjectService().removeUserFromTodo(i, todoId);
					}
					
					NotificationBuilder.addSuccessNotification(message, "Todo erfolgreich geupdated!");
				}
			}
			
		} catch(Exception e) {
			NotificationBuilder.addErrorNotification(message, e.getMessage());
		}
		
		new ProjectController().execute(request, response, message);
		return "/projects/project";
	}

}
