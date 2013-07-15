package com.epro.adapter;

import com.epro.beans.DashboardProjectInfo;

public interface DashboardAdapter {
	public DashboardProjectInfo getDashboardInfo(String projectId,String monthYear);
}
