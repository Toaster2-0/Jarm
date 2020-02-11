package de.jarm.gui.oberflaeche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jarm.gui.navi.Controller;
import de.jarm.gui.utils.NotificationBuilder;

public class LogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		
		NotificationBuilder.addSuccessNotification(message, "Sie haben sich erfolgreich ausgeloggt!");
		request.getSession().invalidate();
		request.getSession().setAttribute("eingeloggt", null);
		
		new LoginController().execute(request, response, message);
		return "/bv/login";
	}

}
