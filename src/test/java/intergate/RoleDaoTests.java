package intergate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.dao.RoleDao;
import de.hska.intergate.saml.manage.dao.RoleDaoImpl;

public class RoleDaoTests {
	static RoleDao roleDao;
	static Role role;

	static final String reference = "TEST_SCHEMA";
	static final String alias = "Created by Junit";
	static final String new_alias = "Edited by Junit";

	@BeforeClass
	public static void method() {
		roleDao = new RoleDaoImpl();
	}

	@Test
	public void TestGetAllRoles() {
		List<Role> rlist = roleDao.getAllRoles();
		if (rlist.size() > 0) {
			role = rlist.get(0);
			assertNotNull(role.getReference());
			assertTrue(role.getReference() != "");
		}
	}

	@Test
	public void TestCreateRole() {
		role = new Role(0, reference, alias);
		role = roleDao.createRole(role);

		assertTrue(role.getRid() > 0);
	}

	@Test
	public void TestGetRoleByReference() {
		int id = role.getRid();
		role = roleDao.getRoleByReference(role.getReference());
		assertEquals(role.getRid(), id);
	}

	@Test
	public void TestUpdateRole() {
		role.setAlias(new_alias);
		roleDao.updateRole(role);

		role = roleDao.getRoleByReference(reference);
		assertEquals(role.getAlias(), new_alias);
	}

	@Test
	public void TestDeleteRole() {
		int code = roleDao.deleteRole(role);
		assertTrue(code >= 0);
	}
}