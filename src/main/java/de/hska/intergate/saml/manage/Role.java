package de.hska.intergate.saml.manage;

public class Role {
	private int rid;
	private String reference;
	private String alias;

	public Role(int rid, String reference, String alias) {
		this.rid = rid;
		this.reference = reference;
		this.alias = alias;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
