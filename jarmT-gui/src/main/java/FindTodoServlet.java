

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.main.data.DataController;
import de.jarm.main.data.Project;
import de.jarm.main.data.ProjectToDo;
import de.jarm.main.data.User;

@WebServlet("*.findTodo")
public class FindTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String todoIdString = request.getParameter("todoId");
		String projectIdString = request.getParameter("projectId");
		StringBuilder responseString = new StringBuilder();
		
		if (todoIdString != null && projectIdString != null) {

			try {

				int projectId = new Integer(projectIdString);
				int todoId = new Integer(todoIdString);
				
				Project p = DataController.getInstance().getProjectService().getProjectById(projectId);
				
				List<ProjectToDo> todos = p.getToDos();
				
				if (todos != null) {
					
					for (ProjectToDo projectToDo : todos) {
						
						if (projectToDo.getId() == todoId) {
							
							responseString.append(projectToDo.getId());
							responseString.append("<&>");
							responseString.append(projectToDo.getName());
							responseString.append("<&>");
							responseString.append(projectToDo.getDescription());
							responseString.append("<&>");
							responseString.append(projectToDo.getState());
							responseString.append("<u>");
							
							List<User> instructedUsers = projectToDo.getInstructedUsers();
							
							if (instructedUsers != null) {
								
								for (User u : instructedUsers) {
									
									responseString.append(u.getId());
									responseString.append("<&>");
									responseString.append(u.getEmail());
									responseString.append("<&>");
									responseString.append(u.getName());
									responseString.append("<n>");									
								}									
							}							
							break;
						}		
					}
				}			
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(responseString.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
