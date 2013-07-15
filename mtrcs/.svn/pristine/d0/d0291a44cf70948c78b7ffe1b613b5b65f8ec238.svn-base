package com.epro.mapper;

import java.util.ArrayList;
import java.util.List;

import com.epro.beans.CheckListInfo;
import com.epro.beans.Group;
import com.epro.beans.HandoverDocument;
import com.epro.beans.HandoverProjectInfo;
import com.epro.beans.Project;
import com.epro.beans.ReportDetailsDto;

public class ProjectBeanMapper {
	public static com.epro.schema.HandoverProjectInfo mapHandoverInfoTomapHandoverInfoDto(HandoverProjectInfo handoverProjectInfo) {
		com.epro.schema.HandoverProjectInfo projectInfo= new com.epro.schema.HandoverProjectInfo();
		if (handoverProjectInfo == null) {
			return null;
		}
		projectInfo.setHandOverDocumentInfos(mapHandoverDocInfoTomapHandoverDocInfoDto(handoverProjectInfo.getHandoverDocuments()));
		projectInfo.setHandOverInfos(mapHandoverInfoTomapHandoverInfoDto(handoverProjectInfo.getCheckListInfos()));
		return projectInfo;
	}
	private static List<com.epro.schema.HandoverDocumentInfo> mapHandoverDocInfoTomapHandoverDocInfoDto(List<HandoverDocument> documents){
		List<com.epro.schema.HandoverDocumentInfo> handoverDocInfo = new ArrayList<com.epro.schema.HandoverDocumentInfo>();
		for(HandoverDocument document:documents)
		{
			com.epro.schema.HandoverDocumentInfo documentInfo = new com.epro.schema.HandoverDocumentInfo();
			documentInfo.setDescription(document.getDescription());
			documentInfo.setFile(document.getFile());
			documentInfo.setProjectDocId(document.getProjectDocId());
			documentInfo.setProjectId(document.getProjectId());
			documentInfo.setDocumentName(document.getDocumentName());
			documentInfo.setDocumentType(document.getDocumentType());
			handoverDocInfo.add(documentInfo);
		}
		return handoverDocInfo;
	}
	
	private static List<com.epro.schema.HandoverInfo> mapHandoverInfoTomapHandoverInfoDto(List<CheckListInfo> checkListInfos){
		List<com.epro.schema.HandoverInfo> handoverInfos = new ArrayList<com.epro.schema.HandoverInfo>();
		for(CheckListInfo checkListInfo : checkListInfos)
		{
			com.epro.schema.HandoverInfo handoverInfo = new com.epro.schema.HandoverInfo();
			handoverInfo.setCheckListId(checkListInfo.getCheckListId());
			handoverInfo.setCheckListItem(checkListInfo.getCheckListItem());
			handoverInfo.setCheckListRemarks(checkListInfo.getRemarks());
			handoverInfo.setCheckListStatus(checkListInfo.getCheckListStatus());
			handoverInfo.setProjectCategory(checkListInfo.getProjectCategory());
			handoverInfo.setProjectCheckListId(checkListInfo.getProjectCheckListId());
			handoverInfo.setProjectId(checkListInfo.getProjectId());
			handoverInfos.add(handoverInfo);
		}
		return handoverInfos;
	}
	public static List<CheckListInfo> mapCheckListInfoDtoTomapCheckListInfo(List<com.epro.schema.HandoverInfo> handoverInfos){
		List<CheckListInfo> checkListInfos = new ArrayList<CheckListInfo>();
		for(com.epro.schema.HandoverInfo handoverInfo : handoverInfos){
			CheckListInfo checkListInfo = new CheckListInfo();
			checkListInfo.setCheckListId(handoverInfo.getCheckListId());
			checkListInfo.setCheckListItem(handoverInfo.getCheckListItem());
			checkListInfo.setCheckListStatus(handoverInfo.getCheckListStatus());
			checkListInfo.setProjectCategory(handoverInfo.getProjectCategory());
			checkListInfo.setProjectCheckListId(handoverInfo.getProjectCheckListId());
			checkListInfo.setProjectId(handoverInfo.getProjectId());
			checkListInfo.setRemarks(handoverInfo.getCheckListRemarks());
			checkListInfos.add(checkListInfo);
		}
		return checkListInfos;
	}
	public static List<HandoverDocument> mapHandoverDocDtoTomapHandoverDoc(List<com.epro.schema.HandoverDocumentInfo> handoverDocumentInfos){
		List<HandoverDocument> handoverDocuments = new ArrayList<HandoverDocument>();
		for(com.epro.schema.HandoverDocumentInfo handoverDocumentInfo : handoverDocumentInfos){
			HandoverDocument handoverDocument = new HandoverDocument();
			handoverDocument.setDescription(handoverDocumentInfo.getDescription());
			handoverDocument.setFile(handoverDocumentInfo.getFile());
			handoverDocument.setProjectDocId(handoverDocumentInfo.getProjectDocId());
			handoverDocument.setProjectId(handoverDocumentInfo.getProjectId());
			handoverDocument.setDocumentName(handoverDocumentInfo.getDocumentName());
			handoverDocument.setDocumentType(handoverDocumentInfo.getDocumentType());
			handoverDocuments.add(handoverDocument);
		}
		return handoverDocuments;
	}
	public static ReportDetailsDto mapReportDetailsSchematoBean(com.epro.schema.ReportDetails reportDetails) {
		ReportDetailsDto reportDetailsDto = new ReportDetailsDto();
		reportDetailsDto.setProjectId(reportDetails.getProjectId());
		reportDetailsDto.setProjectName(reportDetails.getProjectName());
		reportDetailsDto.setActivityName(reportDetails.getActivityName());
		reportDetailsDto.setTaskName(reportDetails.getTaskName());
		reportDetailsDto.setWorkItems(reportDetails.getWorkItems());
		reportDetailsDto.setEmployeeId(reportDetails.getEmployeeId());
		reportDetailsDto.setFirstName(reportDetails.getFirstName());
		reportDetailsDto.setLastName(reportDetails.getLastName());
		reportDetailsDto.setHours(reportDetails.getHours());
		reportDetailsDto.setMinutes(reportDetails.getMinutes());
		reportDetailsDto.setDate(reportDetails.getDate());
		return reportDetailsDto;
	}
	public static Project mapProjectSchematoBean(com.epro.schema.Project project) {
			Project projectInfo = new Project();
			projectInfo.setProjectId(project.getProjectId());
			projectInfo.setProjectName(project.getProjectName());
			projectInfo.setCustomerId(project.getCustomerId());
			projectInfo.setPrimaryCOE(project.getPrimaryCOEName());
			projectInfo.setEffort(project.getEstimatedEffort());
			projectInfo.setPlannedStartDate(project.getPlannedStartDate());
			projectInfo.setPlannedEndDate(project.getPlannedEndDate());
			projectInfo.setApprovedBy(project.getApprovedBy());
			projectInfo.setProjectType(project.getProjectCategory());
			projectInfo.setPlannedClosureDate(project.getProjectClosureDate());
			projectInfo.setProjectStatus(project.getProjectStatus());
			projectInfo.setDepartmentId(project.getDepartmentId());
			projectInfo.setDepartmentName(project.getDepartmentName());
			projectInfo.setLocation(project.getLocation());
			return projectInfo;
	}
	public static List<Project> mapProjectListSchematoBean(List<com.epro.schema.Project> projectLists) {
		List<Project> projects = new ArrayList<Project>();
		for(com.epro.schema.Project project : projectLists){
			projects.add(mapProjectSchematoBean(project));
		}
		return projects;
	}
	public static Group mapGroupSchematoBean(com.epro.schema.Group groupSchema) {
		Group groupBean = new Group();
		groupBean.setGroupId(groupSchema.getGroupId());
		groupBean.setGroupName(groupSchema.getGroupName());
		groupBean.setProjectList(mapProjectListSchematoBean(groupSchema.getProjects()));
		return groupBean;
	}

	public static com.epro.schema.Project mapProjectBeantoSchema(
			Project project) {
		com.epro.schema.Project projectSchema = new com.epro.schema.Project();
		projectSchema.setProjectId(project.getProjectId());
		projectSchema.setProjectName(project.getProjectName());
		return projectSchema;
	}
	
	public static List<com.epro.schema.Project> mapProjectListBeantoSchema(
			List<Project> projectList) {
		List<com.epro.schema.Project> projects = new ArrayList<com.epro.schema.Project>();
		for(Project project : projectList){
			projects.add(mapProjectBeantoSchema(project));
		}
		return projects;
	}
	
	public static com.epro.schema.Group mapGroupBeantoSchema(Group groupBean) {
		com.epro.schema.Group groupSchema = new com.epro.schema.Group();
		groupSchema.setGroupId(groupBean.getGroupId());
		groupSchema.setGroupName(groupBean.getGroupName());
		groupSchema.setProjects(mapProjectListBeantoSchema(groupBean.getProjectList()));
		return groupSchema;
	}
	
	public static com.epro.schema.Group mapValidProjectsToGroupBean(Group groupBean) {
		com.epro.schema.Group groupSchema = new com.epro.schema.Group();
		groupSchema.setGroupId(groupBean.getGroupId());
		groupSchema.setGroupName(groupBean.getGroupName());
		groupSchema.setProjects(mapValidProjects(groupBean.getProjectList()));
		return groupSchema;
	}
	
	public static List<com.epro.schema.Project> mapValidProjects(
			List<Project> projectList) {
		List<com.epro.schema.Project> projects = new ArrayList<com.epro.schema.Project>();
		for(Project project : projectList){
			if(project.getIsProjectSelected().equalsIgnoreCase("true")){
				projects.add(mapProjectBeantoSchema(project));
			}
		}
		return projects;
	}
	
}
