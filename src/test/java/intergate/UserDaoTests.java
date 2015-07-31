package intergate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.RoleDao;
import de.hska.intergate.saml.manage.dao.RoleDaoImpl;
import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;

public class UserDaoTests {

	static final String mail = "mm@test.de";
	static final String alias = "Max Muster";
	static final String new_alias = "Tina Muster";

	static UserDao userDao;
	static User test_user;

	@BeforeClass
	public static void method() {
		userDao = new UserDaoImpl();
	}

	@Before
	public void createTestUser() {
		User user = userDao.getUserByMail(mail);
		if (user != null) {
			userDao.deleteUser(user);
		}
		test_user = new User(0, mail, alias);
		test_user = userDao.createUser(test_user);
	}

	@After
	public void destroyUser() {
		userDao.deleteUser(test_user);
		test_user = null;
	}

	@Test
	public void TestGetAllUsers() {
		User user;
		List<User> ulist = userDao.getAllUsers();
		if (ulist.size() > 0) {
			user = ulist.get(0);
			assertNotNull(user.getEmail());
			assertTrue(user.getEmail() != "");
		}
	}

	@Test
	public void TestGetUserByMail() {
		User user = userDao.getUserByMail(test_user.getEmail());
		assertEquals(user.getUid(), test_user.getUid());
	}

	@Test
	public void TestUpdateUser() {
		test_user.setAlias(new_alias);
		userDao.updateUser(test_user);

		User user = userDao.getUserByMail(mail);
		assertEquals(user.getAlias(), new_alias);
	}

	@Test
	public void TestUserRoles() {
		RoleDao roleDao = new RoleDaoImpl();
		List<Role> roles = new ArrayList<Role>();
		roles = roleDao.getAllRoles();
		Role role = roles.get(0);

		userDao.addRoleToUser(test_user, role);

		test_user.setRoles(roleDao.getRolesByUser(test_user));

		assertEquals(test_user.getRoles().get(0).getRid(), role.getRid());
	}

}
