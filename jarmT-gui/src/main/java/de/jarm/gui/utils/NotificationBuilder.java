package de.jarm.gui.utils;

public class NotificationBuilder {
	
	private static final String NOTIF_START_GLOBAL = "$.notify('";

	private static final String NOTIF_END_GLOBAL_SUCCESS = "',{ position:'top left', className:'success' });";
	private static final String NOTIF_END_GLOBAL_ERROR = "',{ position:'top left', className:'error' });";
	
	public static void addSuccessNotification(StringBuffer meldung, String notificationText) {
		meldung.append(createSuccessNotification(notificationText));
	}
	
	public static void addErrorNotification(StringBuffer meldung, String notificationText) {
		meldung.append(createErrorNotification(notificationText));
	}
	
	private static String createErrorNotification(String message) {
		
		return NOTIF_START_GLOBAL + message + NOTIF_END_GLOBAL_ERROR;
	}
	
	private static String createSuccessNotification(String message) {
		
		return NOTIF_START_GLOBAL + message + NOTIF_END_GLOBAL_SUCCESS;
	}
	
}
