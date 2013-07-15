package com.epro.adapter.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.epro.adapter.ProjectAdapter;
import com.epro.beans.Employee;
import com.epro.beans.Group;
import com.epro.beans.HandoverDocument;
import com.epro.beans.HandoverProjectInfo;
import com.epro.beans.IssueRegister;
import com.epro.beans.IssueRegisterList;
import com.epro.beans.LookUp;
import com.epro.beans.Project;
import com.epro.beans.ProjectInfo;
import com.epro.beans.ReportDetailsDto;
import com.epro.beans.Resource;
import com.epro.mapper.ProjectBeanMapper;
import com.epro.schema.ApproveProjectRequest;
import com.epro.schema.ApproveProjectResponse;
import com.epro.schema.DeleteCheckListItemRequest;
import com.epro.schema.DeleteCheckListItemResponse;
import com.epro.schema.DeleteGroupRequest;
import com.epro.schema.DeleteGroupResponse;
import com.epro.schema.DeleteHandoverDocsRequest;
import com.epro.schema.DeleteHandoverDocsResponse;
import com.epro.schema.DeleteIssueRegisterRequest;
import com.epro.schema.DeleteIssueRegisterResponse;
import com.epro.schema.DeleteProjectDetailsRequest;
import com.epro.schema.DeleteProjectDetailsResponse;
import com.epro.schema.DeleteResourceDetailsRequest;
import com.epro.schema.DeleteResourceDetailsResponse;
import com.epro.schema.EmployeeInfo;
import com.epro.schema.EmployeeWiseReportRequest;
import com.epro.schema.EmployeeWiseReportResponse;
import com.epro.schema.GetAllGroupRequest;
import com.epro.schema.GetAllGroupResponse;
import com.epro.schema.GetAllProjectRequest;
import com.epro.schema.GetAllProjectResponse;
import com.epro.schema.GetEmployeeInfoRequest;
import com.epro.schema.GetEmployeeInfoResponse;
import com.epro.schema.GetEmployeeNameRequest;
import com.epro.schema.GetEmployeeNameResponse;
import com.epro.schema.GetHandoverDocDetailsRequest;
import com.epro.schema.GetHandoverDocDetailsResponse;
import com.epro.schema.GetIssueRegisterRequest;
import com.epro.schema.GetIssueRegisterResponse;
import com.epro.schema.GetLookUpValueRequest;
import com.epro.schema.GetLookUpValueResponse;
import com.epro.schema.GetProjectDetailsRequest;
import com.epro.schema.GetProjectDetailsResponse;
import com.epro.schema.GetProjectHandoverRequest;
import com.epro.schema.GetProjectHandoverResponse;
import com.epro.schema.GetResourceDetailsRequest;
import com.epro.schema.GetResourceDetailsResponse;
import com.epro.schema.IssueRegisterInfo;
import com.epro.schema.ProjectWiseReportRequest;
import com.epro.schema.ProjectWiseReportResponse;
import com.epro.schema.ReleaseResourceRequest;
import com.epro.schema.ReleaseResourceResponse;
import com.epro.schema.ReportDetails;
import com.epro.schema.ResourceInfo;
import com.epro.schema.SaveGroupRequest;
import com.epro.schema.SaveGroupResponse;
import com.epro.schema.SaveIssueRegisterDetailsRequest;
import com.epro.schema.SaveIssueRegisterDetailsResponse;
import com.epro.schema.SaveProjectHandoverRequest;
import com.epro.schema.SaveProjectHandoverResponse;
import com.epro.schema.SaveProjectInfoRequest;
import com.epro.schema.SaveProjectInfoResponse;
import com.epro.schema.SaveResourceDetailsRequest;
import com.epro.schema.SaveResourceDetailsResponse;
import com.google.gson.Gson;

public class ProjectAdapterImpl implements ProjectAdapter {
	private static final Logger log = Logger.getLogger(ProjectAdapterImpl.class);
	private WebServiceTemplate webServiceTemplate;	
	public ProjectAdapterImpl(WebServiceTemplate webServiceTemplate) {
		super();
		this.webServiceTemplate = webServiceTemplate;
	}
	@Override
	public String saveProjectInfo(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		SaveProjectInfoRequest request = new SaveProjectInfoRequest();
		SaveProjectInfoResponse response = new SaveProjectInfoResponse();
		if(projectInfo != null){
			com.epro.schema.Project project = new com.epro.schema.Project();
			project.setProjectId(projectInfo.getProjectId());
			project.setProjectName(projectInfo.getProjectName());
			project.setProjectDesc(projectInfo.getProjectdescription());
			project.setCustomerId(projectInfo.getCustomerId());
			project.setDevPlatform(projectInfo.getPlatform());
			project.setPrimaryCOE(projectInfo.getPrimaryCOE());
			project.setEstimatedEffort(projectInfo.getEffort());
			project.setEstimatedEffortUnit(projectInfo.getUnit());
			project.setPlannedStartDate(projectInfo.getStartDate());			
			project.setPlannedEndDate(projectInfo.getEndDate());
			project.setApprovedBy(projectInfo.getApprover());
			project.setProjectCategory(projectInfo.getProjectType());
			project.setProjectClosureDate(projectInfo.getClosureDate());
			project.setProjectStatus(projectInfo.getProjectStatus());
			request.setProjectInfo(project);
		}
		try{
			response = (SaveProjectInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return response.getProjectId();
	}
	@Override
	public List<ProjectInfo> getProjectDetailsList(String projectId, String employeeId,String approvalStatus,String columnType) {
		// TODO Auto-generated method stub
		GetProjectDetailsRequest request = new GetProjectDetailsRequest();
		GetProjectDetailsResponse response = new GetProjectDetailsResponse();
		List<ProjectInfo> projectInfoList = new ArrayList<ProjectInfo>();
		if(projectId != null){
			request.setProjectId(projectId);
			request.setApprovalStatus(approvalStatus);
			request.setColumnType(columnType);
		}
		try{
			response = (GetProjectDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		if(response != null && response.getProjects() != null){
			List<com.epro.schema.Project> projectList = response.getProjects();
			for(com.epro.schema.Project project : projectList){
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo.setProjectId(project.getProjectId());
				projectInfo.setProjectName(project.getProjectName());
				projectInfo.setProjectdescription(project.getProjectDesc());
				projectInfo.setCustomerId(project.getCustomerId());
				projectInfo.setCustomerName(project.getCustomerName());
				projectInfo.setPlatform(project.getDevPlatform());
				projectInfo.setPrimaryCOE(project.getPrimaryCOE());
				projectInfo.setPrimaryCOEName(project.getPrimaryCOEName());
				projectInfo.setEffort(project.getEstimatedEffort());
				projectInfo.setUnit(project.getEstimatedEffortUnit());
				projectInfo.setStartDate(project.getPlannedStartDate());
				projectInfo.setEndDate(project.getPlannedEndDate());
				projectInfo.setApprover(project.getApprovedBy());
				projectInfo.setProjectType(project.getProjectCategory());
				projectInfo.setClosureDate(project.getProjectClosureDate());
				projectInfo.setApprovalStatus(project.getApprovalStatus());
				projectInfo.setDeleteInd(project.getDeleteInd());
				projectInfo.setProjectStatus(project.getProjectStatus());
				projectInfo.setProjectCode(project.getProjectCode());
				projectInfoList.add(projectInfo);
			}
		}
		return projectInfoList;
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
			// TODO: handle exception
		}
		List<LookUp> lookUpList = new ArrayList<LookUp>();
		if( response.getLookups() != null){
			for(com.epro.schema.LookUp lookupValue : response.getLookups()){
				LookUp lookup = new LookUp();
				lookup.setId(lookupValue.getId());
				lookup.setValue(lookupValue.getValue());
				if(lookupValue.getOptional() != null){
					lookup.setOptional(lookupValue.getOptional());
				}
				lookUpList.add(lookup);
			}
		}
		return lookUpList;
	}
	@Override
	public boolean deleteProjectInfo(String projectId) {
		// TODO Auto-generated method stub
		DeleteProjectDetailsRequest request = new DeleteProjectDetailsRequest();
		DeleteProjectDetailsResponse response = new DeleteProjectDetailsResponse();
		request.setProjectId(projectId);
		try{
			response = (DeleteProjectDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return response.getSuccess();
	}
	@Override
	public HandoverProjectInfo getProjetHandoverInfo(String projectCheckListId,String projectId, String employeeId) {
		// TODO Auto-generated method stub
		GetProjectHandoverRequest request = new GetProjectHandoverRequest();
		GetProjectHandoverResponse response = new GetProjectHandoverResponse();
		HandoverProjectInfo handoverProjectInfo = new HandoverProjectInfo();
		request.setProjectId(projectId);
		try{
			response = (GetProjectHandoverResponse) webServiceTemplate.marshalSendAndReceive(request);
			//System.out.println("response:::"+new Gson().toJson(response));
			handoverProjectInfo.setCheckListInfos(ProjectBeanMapper.mapCheckListInfoDtoTomapCheckListInfo(response.getHandoverProjectInfo().getHandOverInfos()));
			handoverProjectInfo.setHandoverDocuments(ProjectBeanMapper.mapHandoverDocDtoTomapHandoverDoc(response.getHandoverProjectInfo().getHandOverDocumentInfos()));
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return null;
		}
		return handoverProjectInfo;
	}
	@Override
	public boolean saveProjectHandoverInfo(HandoverProjectInfo handoverProjectInfo) {
		// TODO Auto-generated method stub
		SaveProjectHandoverRequest request = new SaveProjectHandoverRequest();
		SaveProjectHandoverResponse response = new SaveProjectHandoverResponse();
		request.setHandOverProjectInfo(ProjectBeanMapper.mapHandoverInfoTomapHandoverInfoDto(handoverProjectInfo));

		try{
			response = (SaveProjectHandoverResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}

	@Override
	public boolean projectApprovalStatusUpdate(String approvalStatus,String projectId, String approvalRemarks) {
		ApproveProjectRequest request = new ApproveProjectRequest();
		ApproveProjectResponse response = new ApproveProjectResponse();
		request.setApprovalRemarks(approvalRemarks);
		request.setApprovalStatus(approvalStatus);
		request.setProjectId(projectId);
		try{
			response = (ApproveProjectResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}
	
	@Override
	public List<Resource> getResourceDetails(String projectId,String employeeId,String sourceFrom){
		List<Resource> resources = new ArrayList<Resource>();
		GetResourceDetailsRequest request = new GetResourceDetailsRequest();
		GetResourceDetailsResponse response = new GetResourceDetailsResponse();
		request.setEmployeeId(employeeId);
		request.setProjectId(projectId);
		request.setSourceFrom(sourceFrom);
		try{
			response = (GetResourceDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		if(response != null){
				for(ResourceInfo resourceInfo : response.getResources()){
					Resource resource = new Resource();
					Employee employee = new Employee();
					employee.setEmployeeId(resourceInfo.getEmployeeId());
					employee.setFirstName(resourceInfo.getFirstName());
					employee.setLastName(resourceInfo.getLastName());
					resource.setEmployee(employee);
					resource.setRoleId(resourceInfo.getRoleId());
					resource.setProjectResourceId(resourceInfo.getProjectResourceId());
					resource.setActiveStatus(resourceInfo.getActiveStatus());
					resource.setAllocationPercentage(resourceInfo.getAllocationPercentage());
					resource.setBillable(convertToYesorNo(resourceInfo.getBillable()));
					resource.setCurrentLocation(resourceInfo.getLocation());
					resource.setEndDate(resourceInfo.getEndDate());
					resource.setRoleName(resourceInfo.getRoleName());
					resource.setStartDate(resourceInfo.getStartDate());
					resource.setRemoveInd(resourceInfo.getRemoveInd());
					resource.setSumOfAllocationPercent(resourceInfo.getSumOfAllocationPercent());
					resources.add(resource);
				}
		}
		return resources;
	}
	@Override
	public boolean saveResourceDetails(List<Resource> resources,String projectId){
		SaveResourceDetailsRequest request = new SaveResourceDetailsRequest();
		SaveResourceDetailsResponse response = new SaveResourceDetailsResponse();
		List<ResourceInfo> resourceList = new ArrayList<ResourceInfo>();
		for(Resource resource : resources){
			ResourceInfo resourceInfo = new ResourceInfo();
			resourceInfo.setActiveStatus(resource.getActiveStatus());
			resourceInfo.setAllocationPercentage(resource.getAllocationPercentage());
			resourceInfo.setBillable(convertToYorN(resource.getBillable()));
			resourceInfo.setEmployeeId(resource.getEmployee().getEmployeeId());
			//resourceInfo.setEndDate(formatDate(resource.getEndDate()));
			resourceInfo.setEndDate(resource.getEndDate());
			resourceInfo.setFirstName(resource.getEmployee().getFirstName());
			resourceInfo.setLastName(resource.getEmployee().getLastName());
			resourceInfo.setLocation(resource.getCurrentLocation());
			resourceInfo.setProjectId(projectId);
			resourceInfo.setProjectResourceId(resource.getProjectResourceId());
			resourceInfo.setRoleId(resource.getRoleId());
			resourceInfo.setRoleName(resource.getRoleName());
			//resourceInfo.setStartDate(formatDate(resource.getStartDate()));
			resourceInfo.setStartDate(resource.getStartDate());
			resourceList.add(resourceInfo);
		}
		request.setResourceInfos(resourceList);
		try{
			response = (SaveResourceDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}
	@Override
	public boolean deleteResourceInfo(String projectResourceId){
		DeleteResourceDetailsRequest request = new DeleteResourceDetailsRequest();
		request.setProjectResourceId(projectResourceId);
		DeleteResourceDetailsResponse response= new DeleteResourceDetailsResponse();
		try{
			response = (DeleteResourceDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}
	@Override
	public boolean deleteCheckListItems(String checkListId) {
		// TODO Auto-generated method stub
		DeleteCheckListItemRequest request = new DeleteCheckListItemRequest();
		DeleteCheckListItemResponse response = new DeleteCheckListItemResponse();
		request.setProjectCheckListId(checkListId);
		try{
			response = (DeleteCheckListItemResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}

	@Override
	public List<Employee> getEmployeeName(String employeeId,String columnType,String projectId){
		GetEmployeeNameRequest request = new GetEmployeeNameRequest();
		List<Employee> employeeList = new ArrayList<Employee>();
		request.setEmployeeId(employeeId);
		request.setColumnType(columnType);
		request.setProjectId(projectId);
		GetEmployeeNameResponse response = new GetEmployeeNameResponse();
		try{
			response = (GetEmployeeNameResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		if(response.getEmployeeInfos() != null){
			for(EmployeeInfo data : response.getEmployeeInfos()){
				Employee employee = new Employee();
				employee.setEmployeeId(data.getEmployeeId());
				employee.setFirstName(data.getFirstName());
				employee.setLastName(data.getLastName());
				employee.setSumOfAllocationPercent(data.getSumOfAllocationPercent());
				employee.setProjectLastReleasedDate(data.getProjectLastReleasedDate());
				employeeList.add(employee);
			}
		}
		return employeeList;
	}
	@Override
	public List<IssueRegister> getIssueDetails(String projectId,String employeeId){
		GetIssueRegisterRequest request = new GetIssueRegisterRequest();
		GetIssueRegisterResponse response = new GetIssueRegisterResponse();
		request.setProjectId(projectId);
		request.setEmployeeId(employeeId);
		try{
			response = (GetIssueRegisterResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		List<IssueRegister> list = new ArrayList<IssueRegister>();
		if(response.getIssueRegisterLists() != null){
			for(IssueRegisterInfo issueReg : response.getIssueRegisterLists()){
				IssueRegister register = new IssueRegister();
				register.setAction(issueReg.getAction());
				register.setAdditionalInfo(issueReg.getAdditionalInfo());
				register.setAssignedTo(issueReg.getAssingnedTo());
				register.setDateForResolution(issueReg.getDateForResolution());
				register.setDateRaised(issueReg.getDateRaised());
				register.setDescriptionOfIssue(issueReg.getDescriptionOfIssue());
				register.setImpactRating(issueReg.getImpactRating());
				register.setIssueId(issueReg.getIssueRegisterId());
				register.setRaisedBy(issueReg.getRaisedBy());
				register.setResolutionDate(issueReg.getResolutionDate());
				register.setStatus(issueReg.getStatus());
				register.setPriority(issueReg.getPriority());
				list.add(register);
			}
		}
		return list;
	}
	@Override
	public boolean saveIssueDetails(IssueRegisterList registerInfo,String userId){
		SaveIssueRegisterDetailsRequest request = new SaveIssueRegisterDetailsRequest();
		SaveIssueRegisterDetailsResponse response = new SaveIssueRegisterDetailsResponse();
		List<IssueRegisterInfo> list = new ArrayList<IssueRegisterInfo>();
		if(registerInfo.getIssueRegisterList() != null){
			for(IssueRegister issueReg : registerInfo.getIssueRegisterList()){
				IssueRegisterInfo register = new IssueRegisterInfo();
				register.setAction(issueReg.getAction());
				register.setAdditionalInfo(issueReg.getAdditionalInfo());
				register.setAssingnedTo(issueReg.getAssignedTo());
				register.setDateForResolution(issueReg.getDateForResolution());
				register.setDateRaised(issueReg.getDateRaised());
				register.setDescriptionOfIssue(issueReg.getDescriptionOfIssue());
				register.setImpactRating(issueReg.getImpactRating());
				register.setIssueRegisterId(issueReg.getIssueId());
				register.setRaisedBy(issueReg.getRaisedBy());
				register.setResolutionDate(issueReg.getResolutionDate());
				register.setStatus(issueReg.getStatus());
				register.setPriority(issueReg.getPriority());
				register.setProjectId(registerInfo.getProjectId());
				register.setUserId(userId);
				list.add(register);
			}
		}
		request.setIssueRegisterLists(list);
		try{
			response = (SaveIssueRegisterDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return response.getSuccess();
	}
	public boolean deleteIssueDetail(String issueId){
		DeleteIssueRegisterRequest request = new DeleteIssueRegisterRequest();
		DeleteIssueRegisterResponse response = new DeleteIssueRegisterResponse();
		request.setIssueRegisterId(issueId);
		try{
			response = (DeleteIssueRegisterResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}
	private String formatDate(String inputDate){
        SimpleDateFormat fromformat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat toFormat = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = null; 
        try {
              formattedDate = toFormat.format(fromformat.parse(inputDate));
        } catch (Exception e) {
            //e.printStackTrace();
              return null;
        }
        return formattedDate;
   }
	private String convertToYorN(String string){
		if(string.equals("Yes")){
			return "Y";
		}else{
			return "N";
		}
	}
	private String convertToYesorNo(String string){
		if(string.equals("Y")){
			return "Yes";
		}else{
			return "No";
		}
	}
	@Override
	public HandoverDocument getHandoverDocDetails(String projectdocId) {
		// TODO Auto-generated method stub
		GetHandoverDocDetailsRequest request = new GetHandoverDocDetailsRequest();
		GetHandoverDocDetailsResponse response = new GetHandoverDocDetailsResponse();
		HandoverDocument handoverDocument = new HandoverDocument();
		request.setProjectDocumentId(projectdocId);
		try{
			response = (GetHandoverDocDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
			//System.out.println("response:::"+new Gson().toJson(response));
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return null;
		}
		if(response!=null){
		handoverDocument.setDescription(response.getHandOverDocumentInfo().getDescription());
		handoverDocument.setFile(response.getHandOverDocumentInfo().getFile());
		handoverDocument.setProjectDocId(response.getHandOverDocumentInfo().getProjectDocId());
		handoverDocument.setDocumentName(response.getHandOverDocumentInfo().getDocumentName());
		handoverDocument.setDocumentType(response.getHandOverDocumentInfo().getDocumentType());
		
		}
		
		return handoverDocument;
	}
	@Override
	public boolean releaseResourceDetails(String projectResourceId,String fromDate,String toDate) {
		// TODO Auto-generated method stub
		ReleaseResourceRequest request = new ReleaseResourceRequest();
		ReleaseResourceResponse response = new ReleaseResourceResponse();
		request.setProjectResourceId(projectResourceId);
		request.setFromDate(fromDate);
		request.setToDate(toDate);
		try{
			response = (ReleaseResourceResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}
	@Override
	public List<ReportDetailsDto> getEmployeewiseReports(String employeeId,String fromDate, String toDate, String reportType) {
		// TODO Auto-generated method stub
		EmployeeWiseReportRequest request = new EmployeeWiseReportRequest();
		EmployeeWiseReportResponse response = new EmployeeWiseReportResponse();
		request.setEmployeeId(employeeId);
		request.setFromDate(fromDate);
		request.setToDate(toDate);
		request.setReportType(reportType);
		request.setActionType("employee");
		List<ReportDetailsDto> reportDetailsList =  new ArrayList<ReportDetailsDto>();
		try{
			response = (EmployeeWiseReportResponse) webServiceTemplate.marshalSendAndReceive(request);
			if(response != null){
				for(ReportDetails reportDetails :response.getReportDetails()){
					reportDetailsList.add(ProjectBeanMapper.mapReportDetailsSchematoBean(reportDetails));
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return reportDetailsList;
	}
	@Override
	public List<ReportDetailsDto> getProjectwiseReports(String id,
			String fromDate, String toDate, String reportType, String sourceFrom) {
		ProjectWiseReportRequest request = new ProjectWiseReportRequest();
		ProjectWiseReportResponse response = new ProjectWiseReportResponse();
		request.setId(id);
		request.setFromDate(fromDate);
		request.setToDate(toDate);
		request.setReportType(reportType);
		request.setActionType("project");
		request.setSourceFrom(sourceFrom);
		List<ReportDetailsDto> reportDetailsList =  new ArrayList<ReportDetailsDto>();
		try{
			response = (ProjectWiseReportResponse) webServiceTemplate.marshalSendAndReceive(request);
			if(response != null){
				for(ReportDetails reportDetails :response.getReportDetails()){
					reportDetailsList.add(ProjectBeanMapper.mapReportDetailsSchematoBean(reportDetails));
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return reportDetailsList;
	}
	
	@Override
	public List<EmployeeInfo> getEmployeeList(String employeeId) {
		// TODO Auto-generated method stub
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
			// TODO: handle exception
			return null;
		}
		return dataList;
	}
	/**
	 * This method is used to delete project uploaded handover docs
	 * @param projectDocId
	 * @return status
	 */
	@Override
	public boolean deleteProjectHandoverDocument(String projectDocId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		DeleteHandoverDocsRequest request = new DeleteHandoverDocsRequest();
		DeleteHandoverDocsResponse response = new DeleteHandoverDocsResponse();
		request.setProjectDocId(projectDocId);
		try{
			response = (DeleteHandoverDocsResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.getSuccess();
	}
	@Override
	public List<Group> getAllGroup(String employeeId, String groupId,
			boolean getProjectInfo) {
		GetAllGroupRequest request = new GetAllGroupRequest();
		GetAllGroupResponse response = new GetAllGroupResponse();
		List<Group> groups = new ArrayList<Group>();
		request.setEmployeeId(employeeId);
		request.setGroupId(groupId);
		request.setGetProjectInfo(getProjectInfo);
		try{
			response = (GetAllGroupResponse) webServiceTemplate.marshalSendAndReceive(request);
			if(response != null){
				for(com.epro.schema.Group group :response.getGroups()){
					groups.add(ProjectBeanMapper.mapGroupSchematoBean(group));
				}
			}
			
		}catch (Exception e) {
			return null;
		}
		return groups;
	}
	@Override
	public boolean deleteGroup(String groupId) {
		DeleteGroupRequest request = new DeleteGroupRequest();
		DeleteGroupResponse response = new DeleteGroupResponse();
		request.setGroupId(groupId);
		try{
			response = (DeleteGroupResponse) webServiceTemplate.marshalSendAndReceive(request);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return response.isSuccess();
	}
	
	@Override
	public boolean saveGroup(Group group) {
		try{
			SaveGroupRequest request = new SaveGroupRequest();
			SaveGroupResponse response = new SaveGroupResponse();
			request.setGroup(ProjectBeanMapper.mapValidProjectsToGroupBean(group));
			response = (SaveGroupResponse) webServiceTemplate.marshalSendAndReceive(request);
			return response.isSuccess();
		}catch (Exception e) {
			log.error(e.getStackTrace());
			return false;
		}
	}
	
	@Override
	public List<Project> getAllProjects(String employeeId) {
		List<Project> projects = new ArrayList<Project>();
		try{
			GetAllProjectRequest request = new GetAllProjectRequest();
			GetAllProjectResponse response = new GetAllProjectResponse();
			request.setEmployeeId(employeeId);
			response = (GetAllProjectResponse) webServiceTemplate.marshalSendAndReceive(request);
			projects.addAll(ProjectBeanMapper.mapProjectListSchematoBean(response.getProjectLists()));
		}catch (Exception e) {
			log.error(e.getStackTrace());
		}
		return projects;
	}
}
