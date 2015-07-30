package intergate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.dao.RoleDao;
import de.hska.intergate.saml.manage.dao.RoleDaoImpl;

public class RoleDaoTests {
	static RoleDao roleDao;
	static Role test_role;

	static final String reference = "TEST_SCHEMA";
	static final String alias = "Created by Junit";
	static final String new_alias = "Edited by Junit";

	@BeforeClass
	public static void method() {
		roleDao = new RoleDaoImpl();
	}

	@Before
	public void createTestRole() {
		Role role = roleDao.getRoleByReference(reference);
		if (role != null) {
			roleDao.deleteRole(role);
		}
		test_role = new Role(0, reference, alias);
		test_role = roleDao.createRole(test_role);
	}

	@After
	public void destroyRole() {
		roleDao.deleteRole(test_role);
		test_role = null;
	}

	@Test
	public void TestGetAllRoles() {
		Role role;
		List<Role> rlist = roleDao.getAllRoles();
		if (rlist.size() > 0) {
			role = rlist.get(0);
			assertNotNull(role.getReference());
			assertTrue(role.getReference() != "");
		}
	}

	@Test
	public void TestGetRoleByReference() {
		Role role = roleDao.getRoleByReference(test_role.getReference());
		assertEquals(role.getRid(), test_role.getRid());
	}

	@Test
	public void TestUpdateRole() {
		test_role.setAlias(new_alias);
		roleDao.updateRole(test_role);

		Role role = roleDao.getRoleByReference(reference);
		assertEquals(role.getAlias(), new_alias);
	}

}