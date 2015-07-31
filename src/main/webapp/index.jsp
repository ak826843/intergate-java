<%@ page import="org.springframework.security.saml.SAMLCredential"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="org.opensaml.saml2.core.Attribute"%>
<%@ page import="org.springframework.security.saml.util.SAMLUtil"%>
<%@ page import="org.opensaml.xml.util.XMLHelper"%>
<%@ page import="de.hska.intergate.saml.manage.dao.UserDao"%>
<%@ page import="de.hska.intergate.saml.manage.dao.UserDaoImpl"%>
<%@ page import="de.hska.intergate.saml.manage.User"%>
<%@ page import="de.hska.intergate.saml.manage.dao.RoleDao"%>
<%@ page import="de.hska.intergate.saml.manage.dao.RoleDaoImpl"%>
<%@ page import="de.hska.intergate.saml.manage.Role"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<jsp:include page="/WEB-INF/templates/head.jsp" />
<body>
	<div id="site-wrapper">
		<%--     <jsp:include page="/WEB-INF/templates/navigation.jsp"/> --%>
		<div class="main" id="main-two-columns">
			<div class="left" id="main-content">
				<div class="section">
					<div class="section-content">
						<div class="post">
							<div class="post-title">
								<h2 class="label label-green">Authenticated user</h2>
							</div>
							<p class="quiet large">Overview of the authenticated user's
								data.</p>
							<div class="post-body">
								<%
									Authentication authentication = SecurityContextHolder.getContext()
											.getAuthentication();
									SAMLCredential credential = (SAMLCredential) authentication
											.getCredentials();
									pageContext.setAttribute("authentication", authentication);
									pageContext.setAttribute("credential", credential);
									pageContext.setAttribute("assertion", XMLHelper
											.nodeToString(SAMLUtil.marshallMessage(credential
													.getAuthenticationAssertion())));

									List<Role> uroles = new ArrayList<Role>();
									RoleDao roleDao = new RoleDaoImpl();
									UserDao userDao = new UserDaoImpl();
									User user = userDao.getUserByMail(authentication.getName());
									if (user == null) {
										user = new User(0, authentication.getName(),
												"Created by Intergate");
										user = userDao.createUser(user);

										if (user.getUid() == 0) {
											pageContext
													.setAttribute("dbresult",
															"User couldn't be created. Please contact an administrator!");
										} else {
											pageContext.setAttribute("dbresult",
													"User was successfully created. Welcome!");
										}
									} else {
										pageContext.setAttribute("dbresult",
												"User already exists. Welcome back!");
										uroles = roleDao.getRolesByUser(user);
									}
									pageContext.setAttribute("roleList", uroles);
								%>
								<p>
									<table>
										<tr>
											<td colspan="2"><h5>General information</h5></td>
										</tr>
										<tr>
											<td width="200"><strong>Status:</strong></td>
											<td><c:out value="${dbresult}" /></td>
										</tr>
										<tr>
											<td width="200"><strong>Name:</strong></td>
											<td><c:out value="${authentication.name}" /></td>
										</tr>
										<tr>
											<td width="200"><strong>Roles:</strong></td>
										</tr>
										<tr>
											<td colspan="2">
												<table>
													<c:forEach items="${roleList}" var="item">
														<tr>
															<td><c:out value="${item.getReference()}" /></td>
														</tr>
													</c:forEach>
												</table>
											</td>
										</tr>
									</table>
								<p>
									<div>
										<form class="left" action="<c:url value="/saml/logout"/>"
											method="get">
											<input type="submit" value="Global Logout" class="button" />
										</form>
										<form class="left" action="<c:url value="/saml/logout"/>"
											method="get">
											<input type="hidden" name="local" value="true" /> <input
												type="submit" value="Local Logout" class="button" />
										</form>
									</div>
									<br /> <br />
									<table>
										<tr>
											<td><h5>Assertion XML</h5></td>
										</tr>
										<tr>
											<td><textarea style="height: 400px" disabled="disabled"><c:out
														value="${assertion}" /></textarea></td>
										</tr>
									</table>
							</div>
						</div>
						<div class="clearer">&nbsp;</div>
					</div>
				</div>
				<div class="clearer">&nbsp;</div>
			</div>
		</div>
	</div>
	(c) 2015 created by Spring - modified by Patrick Hofmann
</body>
</html>