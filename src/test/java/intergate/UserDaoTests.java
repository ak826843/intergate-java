package intergate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;

public class UserDaoTests {
	static UserDao userDao;
	static User user;

	static final String email = "mm@test.de";
	static final String alias = "Max Muster";
	static final String new_alias = "Tina Muster";

	@BeforeClass
	public static void method() {
		userDao = new UserDaoImpl();
	}

	@Test
	public void TestGetAllUsers() {
		List<User> ulist = userDao.getAllUsers();
		if (ulist.size() > 0) {
			user = ulist.get(0);
			assertNotNull(user.getEmail());
			assertTrue(user.getEmail() != "");
		}
	}

	@Test
	public void TestCreateUser() {
		user = new User(0, email, alias);
		user = userDao.createUser(user);

		assertTrue(user.getUid() > 0);
	}

	@Test
	public void TestGetUserByMail() {
		int id = user.getUid();
		user = userDao.getUserByMail(user.getEmail());
		assertEquals(user.getUid(), id);
	}

	@Test
	public void TestUpdateUser() {
		user.setAlias(new_alias);
		userDao.updateUser(user);

		user = userDao.getUserByMail(email);
		assertEquals(user.getAlias(), new_alias);
	}

	@Test
	public void TestDeleteUser() {
		int code = userDao.deleteUser(user);
		assertTrue(code >= 0);
	}
}
