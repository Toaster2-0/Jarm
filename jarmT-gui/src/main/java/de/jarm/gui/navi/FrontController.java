package de.jarm.gui.navi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.oberflaeche.AddMessageController;
import de.jarm.gui.oberflaeche.AddTodoController;
import de.jarm.gui.oberflaeche.AddUserToProjectController;
import de.jarm.gui.oberflaeche.CreateProjectController;
import de.jarm.gui.oberflaeche.DeleteTodoController;
import de.jarm.gui.oberflaeche.EditTodoController;
import de.jarm.gui.oberflaeche.LoginController;
import de.jarm.gui.oberflaeche.LogoutController;
import de.jarm.gui.oberflaeche.RegisterController;
import de.jarm.gui.oberflaeche.RemoveUserFromProjectController;
import de.jarm.gui.oberflaeche.ProjectController;
import de.jarm.gui.oberflaeche.UserAreaController;




@WebServlet(value="*.do", loadOnStartup=1)
public class FrontController extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static final String LAYOUT_SEITE = "/WEB-INF/pages/jsp/template.jsp";
	private Map<String, Controller> controller;
	
	@Override
	public void init() throws ServletException {
		controller = new HashMap<String, Controller>();
		controller.put("/secured/projektList", new UserAreaController());
		controller.put("/bv/login", new LoginController());
		controller.put("/bv/register", new RegisterController());
		controller.put("/bv/logout", new LogoutController());
		controller.put("/projects/create", new CreateProjectController());
		controller.put("/projects/project", new ProjectController());
		controller.put("/projects/addTodo", new AddTodoController());
		controller.put("/projects/addMessage", new AddMessageController());
		controller.put("/projects/addUserToProject", new AddUserToProjectController());
		controller.put("/projects/editTodo", new EditTodoController());
		controller.put("/projects/deleteTodo", new DeleteTodoController());
		controller.put("/projects/removeUserFromProject", new RemoveUserFromProjectController());
		//controller.put("/projects/addMessage", new AddUserToTodoController());
//		controller.put("/bv/login", new LoginController());
//		controller.put("/bv/logout", new LogoutController());
//		controller.put("/secured/userArea", new UserAreaController());

		System.out.println("Frontcontroller initialisiert");
		System.out.println(LAYOUT_SEITE);
	}
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		StringBuffer meldung = new StringBuffer();
		// /mvc/test/eins.do ohne /mvc und .do ==> /test/eins
		String navi = request.getRequestURI().substring( // /mvc/test/eins.do
				request.getContextPath().length(),		// /mvc
				request.getRequestURI().length() -3);	// .do
		
		if(request.getSession().getAttribute("user") == null && !navi.equals("/bv/register")) {
			navi = "/bv/login";
		}
		System.out.println("REQUESTED: " + navi + "----------------------******************------------------------");
		
		try
		{
			Controller c = controller.get(navi);
			if (c != null)
			{
				String neueNavi = c.execute(request, response, meldung);
				if (neueNavi != null) {
					navi = neueNavi;
					System.out.println("NEU: " + navi + "----------------------******************------------------------");
				}
			}
		}
		catch (Exception e)
		{
			//meldung.append(e.toString());
			e.printStackTrace();
		}

		request.setAttribute("notifications", meldung.toString());
		String requestedUrl = "/WEB-INF/pages/jsp" + navi + ".jsp";
		
		if (isValidUrl(navi)) {
			request.setAttribute("url", requestedUrl);
		} else {
			request.setAttribute("url", "/WEB-INF/pages/jsp/error/error.jsp");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(LAYOUT_SEITE);
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	
	private boolean isValidUrl(String url) {
		if (controller.get(url) != null) {
			return true;
		}
		return false;
	}
	
}