package de.jarm.gui.oberflaeche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.gui.utils.JavaScriptFunctions;
import de.jarm.gui.utils.NotificationBuilder;
import de.jarm.main.database.exceptions.ValidierungsException;
import de.jarm.main.data.DataController;
import de.jarm.main.data.Message;
import de.jarm.main.data.Project;
import de.jarm.main.data.User;

public class ProjectController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		
		String projectIdString;
		
		if (request.getMethod().equals("GET")) {
			projectIdString = request.getParameter("projectId");
		} else {
			projectIdString = request.getParameter("id");
		}
		
		if (projectIdString == null) {
			return new UserAreaController().execute(request, response, message);
		}
		
		try {
			int projectId = new Integer(projectIdString);
			Project p = DataController.getInstance().getProjectService().getProjectById(projectId);
			request.setAttribute("currentProject", p);
			
			User u = (User)request.getSession().getAttribute("user");
			
			List<Integer> addedSubscriberIds = new ArrayList<Integer>();
			List<User> addedSubscribers = p.getSubscribers();
			boolean userInProject = false;
			
			for (User user : addedSubscribers) {
				addedSubscriberIds.add(user.getId());
			}
			
			if (p.getOwner().getId() != u.getId()) {
				for (int thisId : addedSubscriberIds) {
					if (u.getId() == thisId) {
						userInProject = true;
						break;
					}
				}
			} else {
				userInProject = true;
			}

			if(!userInProject) {
				new UserAreaController().execute(request, response, message);
				return "/secured/projektList";
			}
		
			request.setAttribute("script", JavaScriptFunctions.FIND_USER_FOR_TODO + " " + JavaScriptFunctions.FIND_USER_FOR_PROEJCT + " " + JavaScriptFunctions.EDIT_TODO);
			
			try {

				List<Message> messagesList = p.getMessages();
				Collections.reverse(messagesList);
				
				request.setAttribute("nachrichten", messagesList);
				
			} catch (Exception e) {
				NotificationBuilder.addErrorNotification(message, e.getMessage());
			}
		}catch (ValidierungsException e){
			NotificationBuilder.addErrorNotification(message, e.getMessage());
			return new UserAreaController().execute(request, response, message);
		}
		
		return null;
	}

}
