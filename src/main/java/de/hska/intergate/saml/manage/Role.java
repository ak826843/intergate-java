package de.hska.intergate.saml.manage;

public class Role {
	private int rid;
	private String reference;
	private String alias;
	private Boolean standard;

	public Role(int rid, String reference, String alias, Boolean standard) {
		this.rid = rid;
		this.reference = reference;
		this.alias = alias;
		this.standard = standard;
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

	public Boolean getStandard() {
		return standard;
	}

	public void setStandard(Boolean standard) {
		this.standard = standard;
	}

}
