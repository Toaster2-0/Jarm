

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.main.data.DataController;
import de.jarm.main.data.User;

@WebServlet("*.findUser")
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName").trim().toLowerCase();
		String projectIdString = request.getParameter("projectId");
		int projectId = -1;
		boolean requestForTodo = false;
		
		if (projectIdString != null) {
			projectId = new Integer(projectIdString.trim());
			requestForTodo = true;
			System.out.println("Request for todo in project with id " + projectId);
		}
		
		StringBuilder responseString = new StringBuilder();
		
		try {
			List<User> usersFound;
			
			if (requestForTodo) {
				usersFound = DataController.getInstance().getUserService().findUserInProjectByNameOrEmail(userName, projectId);
			} else {
				usersFound = DataController.getInstance().getUserService().findUserByNameOrEmail(userName);
			}
			
			System.out.println("found " + usersFound.size() + " users for queryString: " + userName);
			
			for (User user : usersFound) {
				responseString.append(user.getId());
				responseString.append("<&>");
				responseString.append(user.getEmail());
				responseString.append("<&>");
				responseString.append(user.getName());
				responseString.append("<:>");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(responseString.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
