package de.jarm.gui.oberflaeche;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.gui.utils.NotificationBuilder;
import de.jarm.main.data.DataController;
import de.jarm.main.data.Project;
import de.jarm.main.data.User;

public class UserAreaController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		User u = (User) request.getSession().getAttribute("user");
		
		if (u == null) {
			new LoginController().execute(request, response, message);
			return "/bv/login";
		}
		
		try {
			List<Project> myProjects = DataController.getInstance().getProjectService().getProjectsByUser(u);
			System.out.println("Projekte von user " + u.getName() + ": " + myProjects);
			request.setAttribute("myProjects", myProjects);
		} catch(Exception e){
			NotificationBuilder.addErrorNotification(message, e.getMessage());
		}
		
		return "/secured/projektList";
	}

}
