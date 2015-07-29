package de.hska.intergate.saml.manage;

import java.util.List;

public class User {
	private int uid;
	private String email;
	private String alias;
	private List<Role> roles;

	public User(int uid, String email, String alias) {
		this.uid = uid;
		this.email = email;
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
