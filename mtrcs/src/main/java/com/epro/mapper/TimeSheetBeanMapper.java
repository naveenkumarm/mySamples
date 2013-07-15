package com.epro.mapper;

import java.util.ArrayList;
import java.util.List;

import com.epro.beans.TimeSheet;

public class TimeSheetBeanMapper {
	
	public static List<com.epro.schema.TimeSheet> mapTimeSheetInfoToDto(List<com.epro.beans.TimeSheet> timeSheetInfoList){
		List<com.epro.schema.TimeSheet> timeSheetList = new ArrayList<com.epro.schema.TimeSheet>();
		for(com.epro.beans.TimeSheet timeSheetInfo : timeSheetInfoList){
			com.epro.schema.TimeSheet timeSheet = new com.epro.schema.TimeSheet();
			timeSheet.setTimeSheetId(String.valueOf(timeSheetInfo.getId()));
			timeSheet.setEmployeeId(timeSheetInfo.getEmployeeId());
			timeSheet.setProjectId(timeSheetInfo.getProjectId());
			timeSheet.setProjectName(timeSheetInfo.getProjectName());
			timeSheet.setDate(timeSheetInfo.getDate());
			timeSheet.setTaskId(timeSheetInfo.getTaskId());
			timeSheet.setActivityId(timeSheetInfo.getActivityId());
			timeSheet.setHours(timeSheetInfo.getHours());
			timeSheet.setMinutes(timeSheetInfo.getMinutes());
			timeSheet.setBillable(timeSheetInfo.getBillable());
			timeSheet.setLocation(timeSheetInfo.getLocation());
			timeSheet.setAuthoriser(timeSheetInfo.getAuthoriser());
			timeSheet.setApprovalStatus(timeSheetInfo.getApprovalStatus());
			timeSheet.setDepartmentId(timeSheetInfo.getDepartmentId());
			timeSheet.setOnBehalf(timeSheetInfo.getOnBehalf());
			timeSheet.setWorkItems(timeSheetInfo.getWorkItems());
			timeSheetList.add(timeSheet);
		}
		return timeSheetList;
	}
	
	public static List<TimeSheet> mapDtoToTimeSheetInfo(List<com.epro.schema.TimeSheet> timeSheetInfoList){
		List<TimeSheet> timeSheetList = new ArrayList<TimeSheet>();
		for(com.epro.schema.TimeSheet timeSheetInfo : timeSheetInfoList){
			TimeSheet timeSheet = new TimeSheet();
			timeSheet.setId(Integer.parseInt(timeSheetInfo.getTimeSheetId()));
			timeSheet.setEmployeeId(timeSheetInfo.getEmployeeId());
			timeSheet.setProjectId(timeSheetInfo.getProjectId());
			timeSheet.setProjectName(timeSheetInfo.getProjectName());
			timeSheet.setDate(timeSheetInfo.getDate());
			timeSheet.setTaskId(timeSheetInfo.getTaskId());
			timeSheet.setActivityId(timeSheetInfo.getActivityId());
			timeSheet.setHours(timeSheetInfo.getHours());
			timeSheet.setMinutes(timeSheetInfo.getMinutes());
			timeSheet.setBillable(timeSheetInfo.getBillable());
			timeSheet.setLocation(timeSheetInfo.getLocation());
			timeSheet.setAuthoriser(timeSheetInfo.getAuthoriser());
			timeSheet.setApprovalStatus(timeSheetInfo.getApprovalStatus());
			timeSheet.setDepartmentId(timeSheetInfo.getDepartmentId());
			timeSheet.setDepartmentName(timeSheetInfo.getDepartmentName());
			timeSheet.setOnBehalf(timeSheetInfo.getOnBehalf());
			timeSheet.setCutOffInd(timeSheetInfo.getCutOffInd());
			timeSheet.setWorkItems(timeSheetInfo.getWorkItems());
			timeSheetList.add(timeSheet);
		}
		return timeSheetList;
	}
}
