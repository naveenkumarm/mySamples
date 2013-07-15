package com.epro.adapter;

import java.util.List;

import com.epro.beans.LookUp;
import com.epro.beans.Project;
import com.epro.beans.TimeSheet;
import com.epro.schema.Activity;
import com.epro.schema.EmployeeInfo;

public interface TimeSheetAdapter {
	public List<LookUp> getLookUpValue(String type , String id);
	public List<Project> getProjectInfo();
	public List<Activity> getActivityInfo();
	public List<TimeSheet> getTimeSheetInfo(String employeeId,String projectId,String month,String year,String fromDate,String toDate);
	public boolean saveTimeSheetInfo(List<TimeSheet> timeSheetList);
	public List<EmployeeInfo> getEmployeeInfo(String employeeId);
	public boolean deleteTimesheetInfo(String timesheetId);
	public List<Project> getAssignedProjectsList(String employeeId);
}
