package org.mkcl.els.common.vo;

import java.util.List;

import org.mkcl.els.domain.AssemblyRole;

public class AssemblyRolesVo {
private String fromDate;
private String toDate;
private List<AssemblyRole> roles;
public String getFromDate() {
	return fromDate;
}
public String getToDate() {
	return toDate;
}
public List<AssemblyRole> getRoles() {
	return roles;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public void setRoles(List<AssemblyRole> roles) {
	this.roles = roles;
}

}
