package com.epro.adapter;

import java.util.List;

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
import com.epro.schema.EmployeeInfo;

public interface ProjectAdapter {
	public String saveProjectInfo(ProjectInfo projectInfo);
	public List<ProjectInfo> getProjectDetailsList(String projectId,String employeeId, String approvalStatus,String columnType);
	public List<LookUp> getLookUpValue(String type , String id);
	public boolean deleteProjectInfo(String projectId);
	public HandoverProjectInfo getProjetHandoverInfo(String projectCheckListId,String projectId,String employeeId);
	public boolean saveProjectHandoverInfo(HandoverProjectInfo handoverList);
	public List<Resource> getResourceDetails(String projectId,String employeeId, String sourceFrom);
	public boolean saveResourceDetails(List<Resource> resources, String projectId);
	public boolean deleteResourceInfo(String projectResourceId);
	public List<Employee> getEmployeeName(String employeeId,String columnType,String projectId);
	public boolean projectApprovalStatusUpdate(String approvalStatus,String projectId, String approvalRemarks);
	public boolean deleteCheckListItems(String checkListId);
	public HandoverDocument getHandoverDocDetails(String projectdocId);
	public boolean releaseResourceDetails(String projectResourceId,String fromDate,String toDate);
	public List<ReportDetailsDto> getEmployeewiseReports(String employeeId,String fromDate,String toDate,String reportType);
	public List<ReportDetailsDto> getProjectwiseReports(String id,String fromDate,String toDate,String reportType, String sourceFrom);
	public List<EmployeeInfo> getEmployeeList(String employeeId);
	public boolean deleteProjectHandoverDocument(String projectDocId);
	public List<IssueRegister> getIssueDetails(String projectId,String employeeId);
	public boolean saveIssueDetails(IssueRegisterList registerInfo,String userId);
	public boolean deleteIssueDetail(String issueId);
	public List<Group> getAllGroup(String employeeId, String groupId, boolean getProjectInfo);
	public boolean deleteGroup(String groupId);
	public boolean saveGroup(Group group);
	public List<Project> getAllProjects(String employeeId);
}
