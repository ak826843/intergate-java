package de.hska.intergate.saml.manage.api;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;

@Path("/users")
@PermitAll
public class UserResource {
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSONUser(@PathParam("id") int uid) {
		String res = null;
		UserDao udao = new UserDaoImpl();
		User user = udao.getUserById(uid);

		try {
			res = MarshallerImp.marshallUser(user);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
}
