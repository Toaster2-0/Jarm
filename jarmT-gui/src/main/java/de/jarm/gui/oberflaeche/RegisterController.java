package de.jarm.gui.oberflaeche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.main.database.exceptions.ValidierungsException;
import de.jarm.main.data.DataController;
import de.jarm.main.data.User;
import de.jarm.gui.utils.NotificationBuilder;
import de.jarm.gui.utils.Util;


public class RegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		if (request.getMethod().equals("POST")) {
			String email = request.getParameter("Email");
			String name = request.getParameter("Name");
			String passwort = request.getParameter("Passwort");
			String passwortNochmal = request.getParameter("Passwortwiederholung");
					
			try {			
				if (!passwort.equals(passwortNochmal)) {
					throw new ValidierungsException("Die Passwörter müssen übereinstimmen");
				}			
				if(!Util.isValidEmailAddress(email)) {
					throw new ValidierungsException("Bitte eine gültige Email eingeben");
				}
				User u = DataController.getInstance().getUserService().create(name, passwort, email);
				NotificationBuilder.addSuccessNotification(message, "Sie haben sich erfolgreich registriert!");
				request.getSession().setAttribute("user", u);
				new UserAreaController().execute(request, response, message);
				return "/secured/projektList";
			} catch(ValidierungsException e) {
				NotificationBuilder.addErrorNotification(message, e.getMessage());
				request.setAttribute("emailValue", email);
				request.setAttribute("nameValue", name);
				request.setAttribute("vornameValue", name);
			}					
		}
		return null;
	}		
}
