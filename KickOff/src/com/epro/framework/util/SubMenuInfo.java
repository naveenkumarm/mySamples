package com.epro.framework.util;

public class SubMenuInfo {
	private long subMenuId;
	private String subMenuName;
	private int subMenuOrder;
	private int parentMenuId;
	
	public long getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(long subMenuId) {
		this.subMenuId = subMenuId;
	}
	public String getSubMenuName() {
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
	public int getSubMenuOrder() {
		return subMenuOrder;
	}
	public void setSubMenuOrder(int subMenuOrder) {
		this.subMenuOrder = subMenuOrder;
	}
	public int getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
}
