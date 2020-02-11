package de.jarm.gui.oberflaeche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.gui.utils.NotificationBuilder;
import de.jarm.main.data.DataController;
import de.jarm.main.data.User;

public class CreateProjectController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		if (request.getMethod().equals("POST")) {
			
			String projectName = request.getParameter("ProjectTitle");
			
			try {
				DataController.getInstance().getProjectService().create(projectName, (User) request.getSession().getAttribute("user") );
				NotificationBuilder.addSuccessNotification(message, "Projekt " + projectName + " erfolgreich erstellt!");
			} catch(Exception e) {
				NotificationBuilder.addErrorNotification(message, e.getMessage());
			}			
		}
		new UserAreaController().execute(request, response, message);
		return "/secured/projektList";
	}

}
