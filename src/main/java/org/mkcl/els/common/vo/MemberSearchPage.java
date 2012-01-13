package org.mkcl.els.common.vo;

import java.util.ArrayList;
import java.util.List;

public class MemberSearchPage {
    private int totalRecords;
    private List<MemberInfo> pageItems = new ArrayList<MemberInfo>();
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<MemberInfo> getPageItems() {
		return pageItems;
	}
	public void setPageItems(List<MemberInfo> pageItems) {
		this.pageItems = pageItems;
	}
    
}
