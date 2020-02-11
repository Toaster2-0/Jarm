package de.jarm.gui.utils;

public class Util {
	public static boolean isValidEmailAddress(String email) {
        String ePattern = ".*@.*\\..*";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}

}
