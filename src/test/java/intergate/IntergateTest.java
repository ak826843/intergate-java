package intergate;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;
import de.hska.intergate.saml.web.Intergate;

public class IntergateTest {

	static final String mail = "test@intergate.de";
	static final String alias = "JUnit Intergate";
	static UserDao userDao;

	static User test_user;

	@BeforeClass
	public static void method() {
		userDao = new UserDaoImpl();
	}

	@Before
	public void createTestUser() {
		User user = userDao.getUserByMail(mail);
		if (user!=null){
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
	public void testIntergate() {
		Intergate intergate = new Intergate();
		User user = intergate.getUserByMail(mail);
		assertEquals(user.getEmail(), test_user.getEmail());
		assertEquals(user.getUid(), test_user.getUid());
	}
}
