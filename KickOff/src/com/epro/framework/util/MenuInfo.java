package com.epro.framework.util;

import java.util.ArrayList;
import java.util.List;

public class MenuInfo {

	private long menuId;
	private String menuName;
	private int menuOrder;
	private List<SubMenuInfo> subMenuInfoList = new ArrayList<SubMenuInfo>();
	
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public List<SubMenuInfo> getSubMenuInfoList() {
		return subMenuInfoList;
	}
	public void setSubMenuInfoList(List<SubMenuInfo> subMenuInfoList) {
		this.subMenuInfoList = subMenuInfoList;
	}
}
