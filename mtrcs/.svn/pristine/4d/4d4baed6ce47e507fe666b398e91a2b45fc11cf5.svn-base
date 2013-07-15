package com.epro.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.client.core.WebServiceTemplate;

import com.epro.adapter.DashboardAdapter;
import com.epro.adapter.ProjectAdapter;
import com.epro.beans.ActivityDetail;
import com.epro.beans.DashboardProjectInfo;
import com.epro.schema.ActivityDetails;
import com.epro.schema.DeleteProjectDetailsRequest;
import com.epro.schema.DeleteProjectDetailsResponse;
import com.epro.schema.ProjectEfforDetailsRequest;
import com.epro.schema.ProjectEfforDetailsResponse;

public class DashboardAdapterImpl implements DashboardAdapter {
	private WebServiceTemplate webServiceTemplate;	
	public DashboardAdapterImpl(WebServiceTemplate webServiceTemplate) {
		super();
		this.webServiceTemplate = webServiceTemplate;
	}
	@Override
	public DashboardProjectInfo getDashboardInfo(String projectId,String monthYear) {
		// TODO Auto-generated method stub
		ProjectEfforDetailsRequest request = new ProjectEfforDetailsRequest();
		ProjectEfforDetailsResponse response = new ProjectEfforDetailsResponse();
		request.setProjectId(projectId);
		request.setMonthYear(monthYear);
		try{
			response = (ProjectEfforDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
		}
		DashboardProjectInfo dashboardProjectInfo = new DashboardProjectInfo();
		if(response.getEffortDetails() != null){
			//ActivityDetails activity = response.getEffortDetails().getActivity();
			List<ActivityDetail> activityDetailList = new ArrayList<ActivityDetail>();
			for(ActivityDetails activity : response.getEffortDetails().getActivityDetails()){
				ActivityDetail activityDetail = new ActivityDetail();
				activityDetail.setActivity(activity.getActivity());
				activityDetail.setHours(activity.getHours());
				activityDetailList.add(activityDetail);
				
			}
			dashboardProjectInfo.setActivityDetailList(activityDetailList);
			dashboardProjectInfo.setActualEffort(response.getEffortDetails().getActualEffort());
			dashboardProjectInfo.setActualEndDate(response.getEffortDetails().getActualEndDate());
			dashboardProjectInfo.setActualStartDate(response.getEffortDetails().getActualStartDate());
			dashboardProjectInfo.setHours(response.getEffortDetails().getHours());
			dashboardProjectInfo.setMinutes(response.getEffortDetails().getMinutes());
			dashboardProjectInfo.setMonthYear(response.getEffortDetails().getMonthYear());
			dashboardProjectInfo.setPlannedEffort(response.getEffortDetails().getPlannedEffort());
			dashboardProjectInfo.setPlannedEndDate(response.getEffortDetails().getPlannedEndDate());
			dashboardProjectInfo.setPlannedStartDate(response.getEffortDetails().getPlannedStartDate());
			dashboardProjectInfo.setProjectId(response.getEffortDetails().getProjectId());
		}
		return dashboardProjectInfo;
	}
}
