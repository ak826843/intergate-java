package de.hska.intergate.saml.manage;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(factoryClass = RoleFactory.class, factoryMethod = "createRole")
public class Role {
	private int rid;
	private String reference;
	private String alias;
	private int defaultFlag;

	public Role(int rid, String reference, String alias, int defaultFlag) {
		this.rid = rid;
		this.reference = reference;
		this.alias = alias;
		this.defaultFlag = defaultFlag;
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

	public int getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
}
