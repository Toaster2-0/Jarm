package de.jarm.gui.oberflaeche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.main.data.DataController;
import de.jarm.main.data.Project;
import de.jarm.main.data.User;

public class AddMessageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		String id = request.getParameter("id");
		
		if(id!=null) {
			try {
				int projectId = new Integer(id);
				Project p = DataController.getInstance().getProjectService().getProjectById(projectId);
				request.setAttribute("currentProject", p);
				if(!request.getParameter("message").equals("")){
					DataController.getInstance().getProjectService().
					writeMessage(p, ""+request.getParameter("message"), (User) request.getSession().getAttribute("user"));					}
			} catch(Exception e) {
				message.append(e.getMessage());
			}
		} 
		
		new ProjectController().execute(request, response, message);
		return "/projects/project";
	}

}
