package de.hska.intergate.saml.manage.api;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.RoleDao;
import de.hska.intergate.saml.manage.dao.RoleDaoImpl;

public class MarshallerImp {
	public static String marshallUser(User user) throws JAXBException {
		 RoleDao rdao = new RoleDaoImpl();
		 user.setRoles(rdao.getRolesByUser(user));
		
		// Create a JaxBContext
		JAXBContext jc = JAXBContext.newInstance(User.class);

		// Create the Marshaller Object using the JaxB Context
		Marshaller marshaller = jc.createMarshaller();

		// Set the Marshaller media type to JSON or XML
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
				"application/json");

		// Set it to true if you need to include the JSON root element in the
		// JSON output
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

		// Set it to true if you need the JSON output to formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Marshal the user into a string writer and output this
		StringWriter cache = new StringWriter();
		marshaller.marshal(user, cache);
		System.out.println(cache.toString());
		return cache.toString();
	}
	
	public static String marshallRole(Role role) throws JAXBException {
		// Create a JaxBContext
		JAXBContext jc = JAXBContext.newInstance(User.class);

		// Create the Marshaller Object using the JaxB Context
		Marshaller marshaller = jc.createMarshaller();

		// Set the Marshaller media type to JSON or XML
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
				"application/json");

		// Set it to true if you need to include the JSON root element in the
		// JSON output
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

		// Set it to true if you need the JSON output to formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Marshal the employee object to JSON and print the output to console
		StringWriter cache = new StringWriter();
		marshaller.marshal(role, cache);
		System.out.println(cache.toString());
		return cache.toString();
	}
}
