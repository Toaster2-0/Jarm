package de.jarm.gui.oberflaeche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.gui.utils.NotificationBuilder;
import de.jarm.main.data.DataController;
import de.jarm.main.data.User;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		
		if (request.getMethod().equals("POST")) {
			String email = request.getParameter("Email");
			String passwort = request.getParameter("Passwort");
			
			try {
				User u = DataController.getInstance().getUserService().login(email, passwort);
				if (u != null) {
					NotificationBuilder.addSuccessNotification(message, "Sie haben sich erfolgreich eingeloggt!");
					request.getSession().setAttribute("user", u);
					new UserAreaController().execute(request, response, message);
					return "/secured/projektList";
				}
				else {
					NotificationBuilder.addSuccessNotification(message, "Benutzername oder Passwort falsch!");
					request.setAttribute("emailValue", email);
				}
			} catch(Exception e) {
				NotificationBuilder.addErrorNotification(message, e.getMessage());
			}
		}
		return null;
	}
}
