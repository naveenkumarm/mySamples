package com.epro.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.epro.adapter.TimeSheetAdapter;
import com.epro.beans.LookUp;
import com.epro.beans.Project;
import com.epro.beans.TimeSheet;
import com.epro.mapper.TimeSheetBeanMapper;
import com.epro.schema.Activity;
import com.epro.schema.DeleteTimesheetDetailsRequest;
import com.epro.schema.DeleteTimesheetDetailsResponse;
import com.epro.schema.EmployeeInfo;
import com.epro.schema.GetActivityInfoRequest;
import com.epro.schema.GetActivityInfoResponse;
import com.epro.schema.GetAssignedProjectsRequest;
import com.epro.schema.GetAssignedProjectsResponse;
import com.epro.schema.GetEmployeeInfoRequest;
import com.epro.schema.GetEmployeeInfoResponse;
import com.epro.schema.GetLookUpValueRequest;
import com.epro.schema.GetLookUpValueResponse;
import com.epro.schema.GetProjectInfoRequest;
import com.epro.schema.GetProjectInfoResponse;
import com.epro.schema.GetTimeSheetRequest;
import com.epro.schema.GetTimeSheetResponse;
import com.epro.schema.SaveTimeSheetRequest;
import com.epro.schema.SaveTimeSheetResponse;

public class TimeSheetAdapterImpl implements TimeSheetAdapter{
	
	private static final Logger LOG = Logger.getLogger(TimeSheetAdapterImpl.class);
	
	private WebServiceTemplate webServiceTemplate;	
	public TimeSheetAdapterImpl(WebServiceTemplate webServiceTemplate) {
		super();
		this.webServiceTemplate = webServiceTemplate;
	}
	
	@Override
	public List<LookUp> getLookUpValue(String type , String id){
		GetLookUpValueRequest request = new GetLookUpValueRequest();
		GetLookUpValueResponse response = new GetLookUpValueResponse();
		request.setId(id);
		request.setType(type);
		try{
			response = (GetLookUpValueResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			LOG.error("Error in getLookUpValue service :"+e.getStackTrace());
		}
		List<LookUp> lookUpList = new ArrayList<LookUp>();
		for(com.epro.schema.LookUp lookupValue : response.getLookups()){
			LookUp lookup = new LookUp();
			lookup.setId(lookupValue.getId());
			lookup.setValue(lookupValue.getValue());
			lookUpList.add(lookup);
		}
		return lookUpList;
	}
	
	@Override
	public List<Project> getProjectInfo(){
		GetProjectInfoRequest request = new GetProjectInfoRequest();
		GetProjectInfoResponse response = new GetProjectInfoResponse();
		try{
			response = (GetProjectInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			LOG.error("Error in getProjectInfo service :"+e.getStackTrace());
		}
		List<Project> projectList = new ArrayList<Project>();
		for(com.epro.schema.Project prj : response.getProjects()){
			Project project = new Project();
			project.setProjectId(prj.getProjectId());
			project.setProjectName(prj.getProjectName());
			project.setEmployeeList(prj.getEmployees());
			project.setProjectClosedInd(prj.getProjectClosedInd());
			projectList.add(project);
		}
		return projectList;
	}

	@Override
	public List<Activity> getActivityInfo() {
		
		GetActivityInfoRequest request = new GetActivityInfoRequest();
		GetActivityInfoResponse response = new GetActivityInfoResponse();
		try{
			response = (GetActivityInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			LOG.error("Error in getActivityInfo service :"+e.getStackTrace());
		}
		return response.getActivities();
	}

	@Override
	public List<TimeSheet> getTimeSheetInfo(String employeeId,String projectId,String month,String year,String fromDate,String toDate) {
		
		GetTimeSheetRequest request = new GetTimeSheetRequest();
		GetTimeSheetResponse response = new GetTimeSheetResponse();
		request.setProjectId(projectId);
		request.setFromDate(fromDate);
		request.setToDate(toDate);
		request.setYear(year);
		request.setMonth(month);
		if(employeeId != null)
			request.setEmployeeId(employeeId); //else from userholder object
		try{
			response = (GetTimeSheetResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			LOG.error("Error in getTimeSheetInfo service :"+e.getStackTrace());
		}
		List<TimeSheet> timeSheetList = null;
		if(response.getTimesheets() != null){
			timeSheetList = TimeSheetBeanMapper.mapDtoToTimeSheetInfo(response.getTimesheets());
		}
		return timeSheetList;
	}

	@Override
	public boolean saveTimeSheetInfo(List<TimeSheet> timeSheetList) {
		
		SaveTimeSheetRequest request = new SaveTimeSheetRequest();
		SaveTimeSheetResponse response = new SaveTimeSheetResponse();
		request.setTimeSheetInfos(TimeSheetBeanMapper.mapTimeSheetInfoToDto(timeSheetList));
		try{
			response = (SaveTimeSheetResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			LOG.error("Error in saveTimeSheetInfo service :"+e.getStackTrace());
		}
		return response.getSuccess();
	}

	@Override
	public List<EmployeeInfo> getEmployeeInfo(String employeeId) {

		GetEmployeeInfoRequest request = new GetEmployeeInfoRequest();
		GetEmployeeInfoResponse response = new GetEmployeeInfoResponse();
		if(employeeId != null)
			request.setEmployeeId(employeeId);
		List<com.epro.schema.EmployeeInfo> dataList = null;
		try{
			response = (GetEmployeeInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
			if(response != null){
				dataList = response.getEmployeeInfos();
			}
		}catch (Exception e) {
			LOG.error("Error in getEmployeeInfo service :"+e.getStackTrace());
			return null;
		}
		return dataList;
	}

	@Override
	public boolean deleteTimesheetInfo(String timesheetId) {

		DeleteTimesheetDetailsRequest request = new DeleteTimesheetDetailsRequest();
		DeleteTimesheetDetailsResponse response = new DeleteTimesheetDetailsResponse();
		request.setTimesheetId(timesheetId);
		try{
			response = (DeleteTimesheetDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			LOG.error("Error in deleteTimesheetInfo service :"+e.getStackTrace());
			return false;
		}
		return response.getSuccess();
	}
	/**
	 * This method is used to get Assigned project list for the resource
	 */
	@Override
	public List<Project> getAssignedProjectsList(String employeeId) {

		List<com.epro.schema.Project>  projectDetailsList = null;
		List<Project> projectList = null;
		GetAssignedProjectsRequest request = new GetAssignedProjectsRequest();
		GetAssignedProjectsResponse response = new GetAssignedProjectsResponse();
		request.setEmployeeId(employeeId);
		try{
			response = (GetAssignedProjectsResponse) webServiceTemplate.marshalSendAndReceive(request);
			if(response != null){
				projectDetailsList = response.getProjectDetails();
				if(projectDetailsList != null){
					projectList = new ArrayList<Project>();
					for(com.epro.schema.Project data : projectDetailsList){
						Project project = new Project();
						project.setProjectId(data.getProjectId());
						project.setProjectName(data.getProjectName());
						projectList.add(project);
					}
				}
			}
		}catch (Exception e) {
			LOG.error("Error in getAssignedProjectsList service :"+e.getStackTrace());
			return null;
		}
		return projectList;
	}
}
