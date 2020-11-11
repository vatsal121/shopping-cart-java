package com.isi.manager;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

	public static void addValueToSession(HttpServletRequest req, String sessionKeyName, Object sessionDataToAdd) {
		HttpSession session = req.getSession();
		session.setAttribute(sessionKeyName, sessionDataToAdd);
	}

	public static void removeValueFromSession(HttpServletRequest req, String sessionKeyName) {
		HttpSession session = req.getSession();
		session.removeAttribute(sessionKeyName);
	}

	public static void clearAllSessionsAndInvalidate(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.invalidate();
	}
	public static ArrayList<Object> getAllValuesInSession(HttpServletRequest req) {
		ArrayList<Object> listToReturn = new ArrayList<Object>();

		HttpSession session = req.getSession();

		Enumeration<String> listSessionKeys = session.getAttributeNames();
		if (listSessionKeys != null) {

			while (listSessionKeys.hasMoreElements()) {
				String key = listSessionKeys.nextElement();
				Object obj = session.getAttribute(key);
				listToReturn.add(obj);
			}
		}
		return listToReturn;
	}

	public static Object findSpecificSessionValue(HttpServletRequest req, String sessionKeyName) {
		HttpSession session = req.getSession();
		return session.getAttribute(sessionKeyName);
	}

	public static boolean checkSessionExistsOrNot(HttpServletRequest req, String sessionKeyName) {
		HttpSession session = req.getSession();
		return session.getAttribute(sessionKeyName) != null;
	}

}
