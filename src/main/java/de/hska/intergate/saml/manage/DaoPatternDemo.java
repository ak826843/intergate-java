package de.hska.intergate.saml.manage;

import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;

public class DaoPatternDemo {
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		User user = new User(0, "nk@solute.de", "Normak Kottbauer");
		
		System.out.println("Let's enter a new user in the database... ");
		user = userDao.createUser(user);
		System.out.println("Done! Let's see how it looks...");
		// print all
		for (User users : userDao.getAllUsers()) {
			System.out.println("User: " + users.getAlias() + " <"
					+ users.getEmail() + ">");
		}
		System.out.println("Sh...oot. I've made a typo.... need to correct that.");
		user.setAlias("Norman Kottbauer");
		userDao.updateUser(user);
		System.out.println("Now it should be right! Let's see...");
		for (User users : userDao.getAllUsers()) {
			System.out.println("User: " + users.getAlias() + " <"
					+ users.getEmail() + ">");
		}
		System.out.println("Oh yes. That's a lot better! But.. Um... I wasn't supposed to add Norman. Then... here he goes...");
		userDao.deleteUser(user);
		for (User users : userDao.getAllUsers()) {
			System.out.println("User: " + users.getAlias() + " <"
					+ users.getEmail() + ">");
		}
	}
}