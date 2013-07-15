package com.epro.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.epro.adapter.ProjectAdapter;
import com.epro.adapter.TimeSheetAdapter;
import com.epro.beans.CheckListInfo;
import com.epro.beans.Employee;
import com.epro.beans.Group;
import com.epro.beans.HandOverInfo;
import com.epro.beans.HandoverDocument;
import com.epro.beans.HandoverProjectInfo;
import com.epro.beans.IssueRegister;
import com.epro.beans.IssueRegisterList;
import com.epro.beans.LookUp;
import com.epro.beans.Project;
import com.epro.beans.ProjectInfo;
import com.epro.beans.ReportDetailsDto;
import com.epro.beans.Resource;
import com.epro.beans.ResourcesInfo;
import com.epro.beans.UserInfo;
import com.epro.schema.EmployeeInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ProjectController {
	private static final Logger log = Logger.getLogger(ProjectController.class);
	public List<Project> projectsListToView = new ArrayList<Project>();
	public List<Project> projectListToApprove = new ArrayList<Project>();
	public List<Employee> resources = new ArrayList<Employee>();
	Gson gson = new Gson();
	@Autowired
	private ProjectAdapter projectAdapter;
	@Autowired
	private TimeSheetAdapter timeSheetAdapter;

	/**
	 * This method is used to add new project information.
	 * @param model
	 * @return projectInfo
	 */
	@RequestMapping(value = "/project/addProject", method = RequestMethod.GET)
	public String addNewProject(ModelMap model) {
		log.info("ProjectinfoController.NewProject()");
		Gson gson = new Gson();
		List<CheckListInfo> checkListInfos = new ArrayList<CheckListInfo>();
		List<LookUp> checklist = projectAdapter.getLookUpValue("checklist","");
		for(LookUp lookUp :checklist){
			CheckListInfo checkListInfo = new CheckListInfo();
			checkListInfo.setCheckListId(lookUp.getId());
			checkListInfo.setCheckListItem(lookUp.getValue());
			checkListInfos.add(checkListInfo);
		}
		List<LookUp> customerList = projectAdapter.getLookUpValue("customer","");
		List<LookUp> employeeList = projectAdapter.getLookUpValue("primarycoe","");
		model.addAttribute("customerList", gson.toJson(customerList));
		model.addAttribute("employeeList", gson.toJson(employeeList));
		model.addAttribute("checkLists", gson.toJson(checkListInfos));
		model.addAttribute("pageType", "add");
		model.addAttribute("projectDetail", new JsonObject());
		model.addAttribute("documents", new JsonArray());
		model.addAttribute("checkListInfos", new JsonArray());
		model.addAttribute("projectId", "");
		model.addAttribute("category", "");
		model.addAttribute("title", "Project Information");
		return "projectInfo";
	}

	/**
	 * This method is used to save project information
	 * @param projectInfo
	 * @param model
	 * @param request
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/saveProjectInfo", method = RequestMethod.POST)
	public String saveProjectInfo(
			@ModelAttribute("projectInfo") ProjectInfo projectInfo,
			ModelMap model, HttpServletRequest request) {
		log.info("ProjectController.saveProject()");
		if (projectInfo.getProjectId() == null || projectInfo.getProjectId() == "") {
			projectInfo.setProjectId("-1");
		}
		String projectId = projectAdapter.saveProjectInfo(projectInfo);
		model.addAttribute("category", projectInfo.getProjectType());
		model.addAttribute("projectId", projectId);
		return "jsonView";
	}

	/**
	 * This method is used to save project handover information
	 * @param handOverInfo
	 * @param model
	 * @return redirect into project list view
	 */
	@RequestMapping(value = "/project/saveHandOverInfo")
	public String saveHandOverInfo(
			@ModelAttribute("handoverList") HandOverInfo handOverInfo,
			ModelMap model) {
		log.info("ProjectController.saveHandOverInfo()");
		List<HandoverDocument> documents = new ArrayList<HandoverDocument>();
		List<MultipartFile> files = handOverInfo.getFiles();
		String projectId = null;
		if (handOverInfo.getCheckLists() != null)
			projectId = handOverInfo.getCheckLists().get(0).getProjectId();
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				try {
					String fileName = multipartFile.getOriginalFilename();
					String fileType = multipartFile.getContentType();
					if(fileName == null || fileName.equals("") ){
						log.info("Empty file");
					}
					else{
						HandoverDocument document = new HandoverDocument();
						document.setDescription(multipartFile.getOriginalFilename());
						document.setFile(multipartFile.getBytes());
						document.setProjectId(projectId);
						document.setDocumentName(multipartFile.getOriginalFilename());
						if (fileType.contains(".document")) {
							document.setDocumentType("application/msword");
						} else if (fileType.contains(".sheet") || fileType.contains(".ms-excel") ) {
							document.setDocumentType("application/x-msexcel");
						} else {
							document.setDocumentType(fileType);
						}
						documents.add(document);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		HandoverProjectInfo handoverProjectInfo = new HandoverProjectInfo();
		handoverProjectInfo.setHandoverDocuments(documents);
		handoverProjectInfo.setCheckListInfos(handOverInfo.getCheckLists());
		boolean status = projectAdapter.saveProjectHandoverInfo(handoverProjectInfo);
		model.addAttribute("handoverInfos", status);
		return "redirect:/ems/project/viewProjectList";
	}

	/**
	 * This method is used to edit and view project
	 * @param model
	 * @param projectId
	 * @param pageType
	 * @return projectInfo
	 */
	@RequestMapping(value = "/project/projectDetails")
	public String projectDetails(ModelMap model,
			@RequestParam("projectId") String projectId,
			@RequestParam(value = "pageType", required = false) String pageType) {
		log.info("ProjectController.projectDetails()");
		Gson gson = new Gson();
		List<ProjectInfo> projrectInfos = new ArrayList<ProjectInfo>();
		projrectInfos = projectAdapter.getProjectDetailsList(projectId, "","-1","temp_project_id");
		HandoverProjectInfo handoverProjectInfo = projectAdapter.getProjetHandoverInfo("-1", projectId, "");
		List<HandoverDocument> projectDocuments = new ArrayList<HandoverDocument>();
		List<CheckListInfo> existingCheckList = new ArrayList<CheckListInfo>();
		projectDocuments = handoverProjectInfo.getHandoverDocuments();
		existingCheckList = handoverProjectInfo.getCheckListInfos();
		ProjectInfo projrectInfo = new ProjectInfo();
		projrectInfo = projrectInfos.get(0);
		List<CheckListInfo> checkListInfos = new ArrayList<CheckListInfo>();
		List<LookUp> checklist = projectAdapter.getLookUpValue("checklist","");
		for(LookUp lookUp :checklist){
			CheckListInfo checkListInfo = new CheckListInfo();
			checkListInfo.setCheckListId(lookUp.getId());
			checkListInfo.setCheckListItem(lookUp.getValue());
			checkListInfos.add(checkListInfo);
		}
		List<LookUp> customerList = projectAdapter.getLookUpValue("customer","");
		List<LookUp> employeeList = projectAdapter.getLookUpValue("primarycoe","");
		model.addAttribute("customerList", gson.toJson(customerList));
		model.addAttribute("employeeList", gson.toJson(employeeList));
		model.addAttribute("projectDetail", gson.toJson(projrectInfo));
		model.addAttribute("documents", gson.toJson(projectDocuments));
		model.addAttribute("checkListInfos", gson.toJson(existingCheckList));
		model.addAttribute("checkLists", gson.toJson(checkListInfos));
		model.addAttribute("category", projrectInfo.getProjectType());
		model.addAttribute("projectId", projectId);
		model.addAttribute("pageType", pageType);
		model.addAttribute("title", "Project Information");
		return "projectInfo";
	}
	
	/**
	 * This method is used to download document
	 * @param model
	 * @param request
	 * @param response
	 * @param projectdocId
	 */
	@RequestMapping(value = "/project/download")
	public String downloadFile(ModelMap model, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) String projectdocId) {
		log.info("ProjectController.download()");
		HandoverDocument document = projectAdapter
				.getHandoverDocDetails(projectdocId);
		response.setContentType(document.getDocumentType());
		response.setHeader("Content-Disposition", "attachment; filename="+ document.getDocumentName());
		if (document != null) {
			try {
				FileCopyUtils.copy(document.getFile(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * This method is used to view project list
	 * @param model
	 * @return viewProject
	 */
	@RequestMapping(value = "/project/viewProjectList")
	public String viewProjectList(ModelMap model) {
		log.info("ProjectController.viewProjectList()");
		model.addAttribute("title", "Project List");
		return "viewProject";
	}

	/**
	 * This method is used to get list of projects
	 * @param model
	 * @param param
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/viewProject")
	public String getProjects(ModelMap model,
			@RequestParam(value = "param", required = false) String param) {
		log.info("ProjectController.getProjects()");
		String approvalStatus = "-1";
		if (param != null || param != "undefined" || param.equals("")) {
			approvalStatus = param;
		}
		List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();
		projectInfos = projectAdapter.getProjectDetailsList("-1", "",approvalStatus,"temp_project_id");
		model.addAttribute("projects", projectInfos);
		return "jsonView";
	}
	
	/**
	 * This method is used to view project approval page
	 * @param model
	 * @return projectApproval
	 */
	@RequestMapping(value = "/project/projectApproval", method = RequestMethod.GET)
	public String projectApproval(ModelMap model) {
		log.info("ProjectController.projectApproval()");
		model.addAttribute("checkListInfos", new JsonArray());
		model.addAttribute("documenInfos", new JsonArray());
		model.addAttribute("handoverProjectInfo", new JsonArray());
		model.addAttribute("projrectInfo", new JsonArray());
		model.addAttribute("projects", new JsonArray());
		model.addAttribute("title", "Project Approval");
		return "projectApproval";
	}

	/**
	 * This method is used to get project handover details
	 * @param model
	 * @param projectId
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/Details", method = RequestMethod.GET)
	public String getHandoverDetails(
			ModelMap model,
			@RequestParam(value = "projectId", required = false) String projectId) {
		log.info("ProjectController.getHandoverDetails()" + projectId);
		List<ProjectInfo> projrectInfos = new ArrayList<ProjectInfo>();
		projrectInfos = projectAdapter.getProjectDetailsList(projectId, "","-1","temp_project_id");
		ProjectInfo projrectInfo = new ProjectInfo();
		projrectInfo = projrectInfos.get(0);
		HandoverProjectInfo handoverProjectInfo = projectAdapter.getProjetHandoverInfo("-1", projectId, "");
		List<HandoverDocument> projectDocuments = new ArrayList<HandoverDocument>();
		projectDocuments = handoverProjectInfo.getHandoverDocuments();
		model.addAttribute("projectInfo", projrectInfo);
		model.addAttribute("checkListInfos", handoverProjectInfo.getCheckListInfos());
		model.addAttribute("documenInfos", projectDocuments);
		return "jsonView";
	}

	/**
	 * This method is used to approve project
	 * @param model
	 * @param approvalStatus
	 * @param projectId
	 * @param approvalRemarks
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/approve")
	public String projectApproval(ModelMap model,
			@RequestParam String approvalStatus,
			@RequestParam String projectId, @RequestParam String approvalRemarks) {
		log.info("ProjectController.projectApproval()");
		boolean status = projectAdapter.projectApprovalStatusUpdate(
				approvalStatus, projectId, approvalRemarks);
		model.addAttribute("success", status);
		return "jsonView";
	}

	/**
	 * This method is used to delete project
	 * @param model
	 * @param projectId
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/deleteProject")
	public String deleteProject(ModelMap model,
			@RequestParam("projectId") String projectId) {
		log.info("ProjectController.deleteProject()");
		boolean deletedStatus = projectAdapter.deleteProjectInfo(projectId);
		model.addAttribute("deletedStatus", deletedStatus);
		return "jsonView";
	}

	/**
	 * This method is used to get resources
	 * @param model
	 * @param projectId
	 * @param sourceFrom
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/getResources", method = RequestMethod.GET)
	public String getResource(ModelMap model,
			@RequestParam("projectId") String projectId, @RequestParam("sourceFrom") String sourceFrom) {
		log.info("ProjectController.getResource()");
		List<Resource> resources = new ArrayList<Resource>();
		resources = projectAdapter.getResourceDetails(projectId, "-1", sourceFrom);
		model.addAttribute("resources", resources);
		List<ProjectInfo> projrectInfos = new ArrayList<ProjectInfo>();
		if(sourceFrom != null){
			if(sourceFrom.equals("project")){
				projrectInfos = projectAdapter.getProjectDetailsList(projectId,"","-1","temp_project_id");
			} else if(sourceFrom.equals("resource")){
				projrectInfos = projectAdapter.getProjectDetailsList(projectId,"","-1","project_id");
			}
		} 
		String startDate = projrectInfos.get(0).getStartDate();
		String endDate = projrectInfos.get(0).getEndDate();
		model.addAttribute("projectStartdate",  startDate);
		model.addAttribute("projectEnddate",  endDate);
		return "jsonView";
	}

	/**
	 * This method is used to save added resources
	 * @param model
	 * @param resourceInfo
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/saveResources")
	public String saveResource(ModelMap model,
			@ModelAttribute("resourceList") ResourcesInfo resourceInfo) {
		log.info("ProjectController.saveResource()");
		boolean success = false;
		success = projectAdapter.saveResourceDetails(resourceInfo.getResourceList(), resourceInfo.getProjectId());
		model.addAttribute("success", success);
		return "jsonView";
	}

	/**
	 * This method is used to view addResource page 
	 * @param model
	 * @return addResource
	 */
	@RequestMapping(value = "/project/addNewResource")
	public String addNewResource(ModelMap model) {
		Gson gson = new Gson();
		List<LookUp> roles = projectAdapter.getLookUpValue("roles", "");
		List<LookUp> projectList = projectAdapter.getLookUpValue("project", "");
		model.addAttribute("projects", gson.toJson(projectList));
		model.addAttribute("employeeRoles", gson.toJson(roles));
		model.addAttribute("title", "Assign Resources");
		return "addResource";
	}

	/**
	 * This method is used to delete resource
	 * @param model
	 * @param projectResourceId
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/removeResource")
	public String deleteResource(ModelMap model,
			@RequestParam("projectResourceId") String projectResourceId) {
		boolean deletedStatus = projectAdapter.deleteResourceInfo(projectResourceId);
		model.addAttribute("deletedStatus", deletedStatus);
		return "jsonView";
	}

	/**
	 * This method is used to get list of employees 
	 * @param model
	 * @param employeeId
	 * @param columnType
	 * @param projectId
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/employeeLookup")
	public String getEmployeeName(ModelMap model,
			@RequestParam("employeeId") String employeeId,@RequestParam("columnType")String columnType,@RequestParam("projectId")String projectId) {
		List<Employee> employeeList = projectAdapter.getEmployeeName(employeeId,columnType,projectId);
		model.addAttribute("employees", employeeList);
		return "jsonView";
	}
	
	/**
	 * This project is used to view projectWiseReports page
	 * @param model
	 * @return projectWiseReports
	 */
	@RequestMapping(value = "/project/projectWise")
	public String projectWiseReport(ModelMap model) {
		log.info("ProjectController->projectWiseSearch");
		List<Project> projectList = new ArrayList<Project>();
		projectList = timeSheetAdapter.getProjectInfo();
		model.addAttribute("projectList", gson.toJson(projectList));
		model.addAttribute("workItemInd", gson.toJson(""));
		model.addAttribute("title", "Project Wise Timesheet Reports");
	   return "projectWiseReports";
	}
	
	/**
	 * This method is used to search project wise reports
	 * @param model
	 * @param projectId
	 * @param fromDate
	 * @param toDate
	 * @param searchType
	 * @param request
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/projectWiseSearch" , method = RequestMethod.POST)
	public String projectWiseSearch(ModelMap model,
			@RequestParam("projectId") String projectId,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,
			@RequestParam("searchType") String searchType,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("employeeWiseList", null);
		session.setAttribute("projectWiseList", null);
		List<ReportDetailsDto> reportDetails = new ArrayList<ReportDetailsDto>();
		reportDetails = projectAdapter.getProjectwiseReports(projectId,fromDate,toDate,searchType,"project");
		session.setAttribute("projectWiseList", reportDetails);
		model.addAttribute("workItemInd", searchType);
		model.addAttribute("reportDetails", reportDetails);
	   return "jsonView";
	}
	
	/**
	 * This method is used to export project wise reports
	 * @param model
	 * @param searchType
	 * @param request
	 * @return csvView
	 */
	@RequestMapping(value = "/project/getExcelProjectWise")
	public String getExcelProjectWise(ModelMap model,@RequestParam("searchType") String searchType,HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<ReportDetailsDto> employeeReports = new ArrayList<ReportDetailsDto>();
		employeeReports = (List<ReportDetailsDto>)session.getAttribute("projectWiseList");
		model.addAttribute("filename", "ProjectWiseReport.csv");
		model.addAttribute("csvList", getCsvRecords(employeeReports,searchType));
		return "csvView";	
	}
	
	/**
	 * This method is used to view employeeWiseReports page 
	 * @param model
	 * @return employeeWiseReports
	 */
	@RequestMapping(value = "/project/employeeWise")
	public String employeewise(ModelMap model) {
		List<EmployeeInfo> employeeList = projectAdapter.getEmployeeList(null);
		model.addAttribute("employeeRoles", gson.toJson(employeeList));
		model.addAttribute("title", "Resource Wise Timesheet Reports");
	    return "employeeWiseReports";
	}
	
	/**
	 * This method is used to get employee wise reports
	 * @param model
	 * @param employeeId
	 * @param fromDate
	 * @param toDate
	 * @param searchType
	 * @param request
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/getEmployeeReports", method = RequestMethod.GET)
	public String getEmployeeReports(ModelMap model,@RequestParam("employeeId") String employeeId,
			@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("searchType") String searchType,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.setAttribute("employeeWiseList", null);
		session.setAttribute("projectWiseList", null);
		List<ReportDetailsDto> employeeReports = new ArrayList<ReportDetailsDto>();
		employeeReports=projectAdapter.getEmployeewiseReports(employeeId, fromDate, toDate,searchType);
		session.setAttribute("employeeWiseList", employeeReports);
		model.addAttribute("employeeReport",employeeReports);
		model.addAttribute("searchType",searchType);
		return "jsonView";	
	}
	
	/**
	 * This method is used to export employee reports
	 * @param model
	 * @param request
	 * @param searchType
	 * @return csvView
	 */
	@RequestMapping(value = "/project/getExcelEmployeeReports", method = RequestMethod.GET)
	public String getExcelEmployeeReports(ModelMap model,HttpServletRequest request,@RequestParam("searchType") String searchType) {
		HttpSession session = request.getSession();
		List<ReportDetailsDto> employeeReports = new ArrayList<ReportDetailsDto>();
		employeeReports = (List<ReportDetailsDto>)session.getAttribute("employeeWiseList");
		model.addAttribute("filename", "EmployeeWise.csv");
		model.addAttribute("csvList", getCsvRecords(employeeReports,searchType));
		return "csvView";	
	}
	
	/**
	 * This method is used to release resource
	 * @param model
	 * @param projectResourceId
	 * @param startDate
	 * @param endDate
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/resourceRealease")
	public String releaseResourceDetails(ModelMap model,@RequestParam("projectResourceId") String projectResourceId,
		   @RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){
		boolean releaseStatus = projectAdapter.releaseResourceDetails(projectResourceId,startDate,endDate);
		model.addAttribute("releaseStatus", releaseStatus);
		return "jsonView";	
	}

	/**
	 * This method is used to delete existing document
	 * @param model
	 * @param projectDocumentId
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/deleteProjectHandoverDocument")
	public String deleteProjectHandoverDocument(ModelMap model,@RequestParam("projectDocumentId") String projectDocumentId){
		//log.info("enter the realease status");
		boolean deleteStatus = projectAdapter.deleteProjectHandoverDocument(projectDocumentId);
		model.addAttribute("deleteStatus", deleteStatus);
		return "jsonView";	
	}
	
	/**
	 * This method is used to view issue register page
	 * @param model
	 * @param requestView
	 * @param request
	 * @return issueRegisterView
	 */
	@RequestMapping(value = "/issueRegister/view")
	public String addIssueRegister(ModelMap model,@RequestParam("request") String requestView,HttpServletRequest request){
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		UserInfo user =(UserInfo) session.getAttribute("userInfo");
		List<LookUp> status = projectAdapter.getLookUpValue("Issue-Status", "");
		List<LookUp> priority = projectAdapter.getLookUpValue("Issue-Priority", "");
		List<LookUp> impactRating = projectAdapter.getLookUpValue("Issue-Impact-Rating", "");
		List<EmployeeInfo> empInfoList = timeSheetAdapter.getEmployeeInfo(user.getUserId());
		model.addAttribute("empInfoList", gson.toJson(empInfoList));
		model.addAttribute("status", gson.toJson(status));
		model.addAttribute("impactRating", gson.toJson(impactRating));
		model.addAttribute("priority", gson.toJson(priority));
		model.addAttribute("title", "Issue Register");
		model.addAttribute("view", requestView);
		return "issueRegisterView";	
	}
	
	/**
	 * This method is used to get issue list
	 * @param model
	 * @param projectId
	 * @return jsonView
	 */
	@RequestMapping(value = "/issueRegister/getIssueList", method = RequestMethod.GET)
	public String getIssues(ModelMap model,
			@RequestParam("projectId") String projectId) {
		List<IssueRegister> issueList = new ArrayList<IssueRegister>();
		issueList = projectAdapter.getIssueDetails(projectId,"");
		model.addAttribute("issueList", issueList);
		return "jsonView";
	}
	
	/**
	 * This method is used to save issues
	 * @param model
	 * @param registerInfo
	 * @param request
	 * @return jsonView
	 */
	@RequestMapping(value = "/issueRegister/save")
	public String saveIssueRegister(ModelMap model,
			@ModelAttribute("issueRegisterList") IssueRegisterList registerInfo,
			HttpServletRequest request) {
		boolean success = false;
		HttpSession session = request.getSession();
		String userId =(String)session.getAttribute("loginId");
		success = projectAdapter.saveIssueDetails(registerInfo,userId);
		model.addAttribute("success", success);
		return "jsonView";
	}
	
	/**
	 * This method is used to delete issue
	 * @param model
	 * @param issueRegisterId
	 * @return jsonView
	 */
	@RequestMapping(value = "/issueRegister/remove")
	public String deleteIssue(ModelMap model,
			@RequestParam("issueRegisterId") String issueRegisterId) {
		boolean success = false;
		success = projectAdapter.deleteIssueDetail(issueRegisterId);
		model.addAttribute("success", success);
		return "jsonView";
	}
	
	/**
	 * Export to csv file on Pending Results Page
     * This is the method which returns List<string[]> for rendering csvView
     * @param List<ReportSummaryRow>
     * @return
     */
	private List<String[]> getCsvRecords(List<ReportDetailsDto> resultList,String type) {
		
		List<String[]> csvList = new ArrayList<String[]>();
		if(resultList == null || resultList.size() == 0) return csvList;
		String[] csvHeader = null;
		if(type.equals("projectDaily")){
			csvHeader = new String[] {"Date", "Employee Id", "Employee Name", "Activity", "Task", "Work Items", "Effort"};
		}else if(type.equals("projectSummary")){
			csvHeader = new String[] {"Employee Id", "Employee Name", "Activity", "Task", "Effort"};
		}else if(type.equals("employeeDaily")){
			csvHeader = new String[] {"Date", "Project Name", "Activity Name", "Task Name", "Work Items", "Effort"};
		}else if(type.equals("employeeSummary")){
			csvHeader = new String[] {"Project Name", "Activity Name", "Task Name", "Effort"};
		}else if (type.equals("groupDaily")) {
			csvHeader = new String[] {"Date", "Project", "Employee Id", "Employee Name", "Activity", "Task", "Work Items", "Effort"};
		}else if (type.equals("groupSummary")) {
			csvHeader = new String[] {"Project", "Employee Id", "Employee Name", "Activity", "Task", "Effort"};
		}
		
		csvList.add(csvHeader);
		
		String[] csvRecord = null;
		if(type.equals("projectDaily")){
			for(ReportDetailsDto reportSummaryRow : resultList) {
				csvRecord = new String[8];
				csvRecord[0] = reportSummaryRow.getDate();
				csvRecord[1] = reportSummaryRow.getEmployeeId();
				csvRecord[2] = reportSummaryRow.getLastName()+","+reportSummaryRow.getFirstName();
				csvRecord[3] = reportSummaryRow.getActivityName();
				csvRecord[4] = reportSummaryRow.getTaskName();
				csvRecord[5] = reportSummaryRow.getWorkItems();
				csvRecord[6] = reportSummaryRow.getHours()+ ":" +reportSummaryRow.getMinutes();
				csvList.add(csvRecord);
			}
		}else if(type.equals("projectSummary")){
			for(ReportDetailsDto reportSummaryRow : resultList) {
				csvRecord = new String[8];
				csvRecord[0] = reportSummaryRow.getEmployeeId();
				csvRecord[1] = reportSummaryRow.getLastName()+","+reportSummaryRow.getFirstName();
				csvRecord[2] = reportSummaryRow.getActivityName();
				csvRecord[3] = reportSummaryRow.getTaskName();
				csvRecord[4] = reportSummaryRow.getHours()+ ":" +reportSummaryRow.getMinutes();
				csvList.add(csvRecord);
			}
		}
		else if(type.equals("employeeDaily")){
			for(ReportDetailsDto reportSummaryRow : resultList) {
				csvRecord = new String[8];
				csvRecord[0] = reportSummaryRow.getDate();
				csvRecord[1] = reportSummaryRow.getProjectName();
				csvRecord[2] = reportSummaryRow.getActivityName();
				csvRecord[3] = reportSummaryRow.getTaskName();
				csvRecord[4] = reportSummaryRow.getWorkItems();
				csvRecord[5] = reportSummaryRow.getHours()+ ":" +reportSummaryRow.getMinutes();
				csvList.add(csvRecord);
			}
		}
		else if(type.equals("employeeSummary")){
			for(ReportDetailsDto reportSummaryRow : resultList) {
				csvRecord = new String[8];
				csvRecord[0] = reportSummaryRow.getProjectName();
				csvRecord[1] = reportSummaryRow.getActivityName();
				csvRecord[2] = reportSummaryRow.getTaskName();
				csvRecord[3] = reportSummaryRow.getHours()+ ":" +reportSummaryRow.getMinutes();
				csvList.add(csvRecord);
			}
		}else if (type.equals("groupDaily")) {
			for(ReportDetailsDto reportSummaryRow : resultList) {
				csvRecord = new String[8];
				csvRecord[0] = reportSummaryRow.getDate();
				csvRecord[1] = reportSummaryRow.getProjectName();
				csvRecord[2] = reportSummaryRow.getEmployeeId();
				csvRecord[3] = reportSummaryRow.getLastName()+","+reportSummaryRow.getFirstName();
				csvRecord[4] = reportSummaryRow.getActivityName();
				csvRecord[5] = reportSummaryRow.getTaskName();
				csvRecord[6] = reportSummaryRow.getWorkItems();
				csvRecord[7] = reportSummaryRow.getHours()+ ":" +reportSummaryRow.getMinutes();
				csvList.add(csvRecord);
			}
		}else if (type.equals("groupSummary")) {
			for(ReportDetailsDto reportSummaryRow : resultList) {
				csvRecord = new String[8];
				csvRecord[0] = reportSummaryRow.getProjectName();
				csvRecord[1] = reportSummaryRow.getEmployeeId();
				csvRecord[2] = reportSummaryRow.getLastName()+","+reportSummaryRow.getFirstName();
				csvRecord[3] = reportSummaryRow.getActivityName();
				csvRecord[4] = reportSummaryRow.getTaskName();
				csvRecord[5] = reportSummaryRow.getHours()+ ":" +reportSummaryRow.getMinutes();
				csvList.add(csvRecord);
			}
		}
		return csvList;
	}
	
	/**
     * Method used to setup initial data required to createGroup.
     * @param model
     * @param request
     * @return createGroupView
     */
	@RequestMapping(value = "/project/createGroup")
	public String createGroup(ModelMap model,HttpServletRequest request) {
		log.info("Project controller createGroup method In");
		Gson gson = new Gson();
		try{
			List<Project> allProjectList = projectAdapter.getAllProjects(null);
			List<Group> groupList = projectAdapter.getAllGroup(null, null, false);
			if(groupList == null){
				model.addAttribute("allGroupList", gson.toJson(""));
			}
			else{
				model.addAttribute("allGroupList", gson.toJson(groupList));
			}
			model.addAttribute("existingProjectList", gson.toJson(""));
			model.addAttribute("allProjectList", gson.toJson(allProjectList));
			model.addAttribute("groupId", gson.toJson(""));
			model.addAttribute("groupName",  gson.toJson(""));
			model.addAttribute("mode",  gson.toJson("create"));
			model.addAttribute("title", "Create Group");
		}
		catch(Exception e){
			log.error("Error in method createGroup : "+e.getStackTrace());
		}
		return "createGroupView";	
	}
	
	/**
     * Method used to view groups.
     * @param model
     * @param groupId
     * @param groupName
     * @return showGroupView
     */
	@RequestMapping(value = "/project/viewGroup")
	public String viewGroup(ModelMap model, @RequestParam("groupId") String groupId,
			@RequestParam("groupName") String groupName) {
		log.info("Project controller viewGroup method In");
		Gson gson = new Gson();
		try{
			List<Group> list = new ArrayList<Group>();
			list = projectAdapter.getAllGroup("-1",groupId,true);
			model.addAttribute("allGroupList", gson.toJson(""));
			model.addAttribute("existingProjectList", gson.toJson(list.get(0).getProjectList()));
			model.addAttribute("allProjectList", gson.toJson(""));
			model.addAttribute("groupId",  gson.toJson(groupId));
			model.addAttribute("groupName",  gson.toJson(groupName));
			model.addAttribute("mode",  gson.toJson("view"));
			model.addAttribute("title", "View Group");
		}
		catch(Exception e){
			log.error("Error in method viewGroup : "+e.getStackTrace());
		}
		return "showGroupView";	
	}
	
	/**
     * Method allow to edit the groups.
     * @param model
     * @param groupId
     * @param groupName
     * @return editGroupView
     */
	@RequestMapping(value = "/project/editGroup")
	public String editGroup(ModelMap model, @RequestParam("groupId") String groupId,
			@RequestParam("groupName") String groupName) {
		log.info("Project controller editGroup method In");
		Gson gson = new Gson();
		try{
			List<Group> list = new ArrayList<Group>();
			list = projectAdapter.getAllGroup("-1",groupId,true);
			List<Group> groupList = projectAdapter.getAllGroup(null, null, false);
			List<Project> allProjectList = projectAdapter.getAllProjects(null);
			if(groupList == null){
				model.addAttribute("allGroupList", gson.toJson(""));
			}
			else{
				model.addAttribute("allGroupList", gson.toJson(groupList));
			}
			model.addAttribute("existingProjectList", gson.toJson(list.get(0).getProjectList()));
			model.addAttribute("allProjectList", gson.toJson(allProjectList));
			model.addAttribute("groupId",  gson.toJson(groupId));
			model.addAttribute("groupName",  gson.toJson(groupName));
			model.addAttribute("mode", gson.toJson("edit"));
			model.addAttribute("title", "Edit Group");
		}
		catch(Exception e){
			log.error("Error in method editGroup : "+e.getStackTrace());
		}
		return "editGroupView";	
	}
	
	/**
     * Method used to save group.
     * @param model
     * @param group
     * @return redirect:/ems/project/listGroup
     */
	@RequestMapping(value = "/project/saveGroup", method = RequestMethod.POST)
	public String saveGroup(ModelMap model, @ModelAttribute("group")Group group) {
		log.info("Project controller saveGroup method In");
		Gson gson = new Gson();
		try{
			boolean saveStatus = projectAdapter.saveGroup(group);
			String successMessage = null;
			String failureMessage = null;
			if(saveStatus){
				successMessage = "Group saved successfully";
			}
			else{
				failureMessage = "Unable to save";
			}
			List<Group> list = projectAdapter.getAllGroup(null,null,false);
			model.addAttribute("groupList", gson.toJson(list));
			model.addAttribute("success", successMessage);
			model.addAttribute("failure", failureMessage);
			model.addAttribute("title", "List Group");
		}
		catch(Exception e){
			log.error("Error in method saveGroup : "+e.getStackTrace());
		}
		return "redirect:/ems/project/listGroup";
	}

	/**
     * Method used to list groups after saving a group.
     * @param model
     * @param success
     * @param failure
     * @return listGroupView
     */
	@RequestMapping(value = "/project/listGroup")
	public String listGroup(ModelMap model,@RequestParam(value = "success", required = false) String success,
							@RequestParam(value = "failure", required = false) String failure) { 
		log.info("Project controller listGroup method In");
		Gson gson = new Gson();
		try{
			List<Group> list = new ArrayList<Group>();
			list = projectAdapter.getAllGroup(null,null,false);
			model.addAttribute("groupList", gson.toJson(list));
			
			if(success == null){
				model.addAttribute("success", "");
			}
			else{
				model.addAttribute("success", success);
				success = null;
			}
			if(failure == null){
				model.addAttribute("failure", "");
			}
			else{
				model.addAttribute("failure", failure);
				failure = null;
			}
			model.addAttribute("title", "List Group");
		}
		catch(Exception e){
			log.error("Error in method listGroup : "+e.getStackTrace());
		}
		return "listGroupView";	
	}
	
	/**
     * Method used to delete group.
     * @param model
     * @param groupId
     * @return jsonView
     */
	@RequestMapping(value = "/project/deleteGroup")
	public String deleteGroup(ModelMap model, @RequestParam("groupId") String groupId) {
		log.info("Project controller deleteGroup method In");
		
		try{
			boolean success = false;
			success = projectAdapter.deleteGroup(groupId);
			model.addAttribute("success", success);
		}
		catch(Exception e){
			log.error("Error in method deleteGroup : "+e.getStackTrace());
		}
		return "jsonView";	
	}
	
	/**
	 * This project is used to view groupWiseReports page
	 * @param model
	 * @return groupWiseReports
	 */
	@RequestMapping(value = "/project/groupWise")
	public String groupWiseReports(ModelMap model) {
		log.info("ProjectController.groupWiseReports()");
		List<Group> groupList = new ArrayList<Group>();
		groupList = projectAdapter.getAllGroup(null, null, false);
		model.addAttribute("groupList", gson.toJson(groupList));
		model.addAttribute("workItemInd", gson.toJson(""));
		model.addAttribute("title", "Group Wise Timesheet Reports");
	   return "groupWiseReports";
	}
	
	/**
	 * This method is used to search group wise reports
	 * @param model
	 * @param groupId
	 * @param fromDate
	 * @param toDate
	 * @param searchType
	 * @param request
	 * @return jsonView
	 */
	@RequestMapping(value = "/project/groupWiseSearch" , method = RequestMethod.POST)
	public String groupWiseSearch(ModelMap model,
			@RequestParam("groupId") String groupId,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,
			@RequestParam("searchType") String searchType,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("employeeWiseList", null);
		session.setAttribute("projectWiseList", null);
		session.setAttribute("groupWiseList", null);
		List<ReportDetailsDto> reportDetails = new ArrayList<ReportDetailsDto>();
		reportDetails = projectAdapter.getProjectwiseReports(groupId,fromDate,toDate,searchType,"group");
		session.setAttribute("groupWiseList", reportDetails);
		model.addAttribute("workItemInd", searchType);
		model.addAttribute("reportDetails", reportDetails);
	   return "jsonView";
	}
	
	/**
	 * This method is used to export project wise reports
	 * @param model
	 * @param searchType
	 * @param request
	 * @return csvView
	 */
	@RequestMapping(value = "/project/getExcelGroupWise")
	public String getExcelGroupWise(ModelMap model,@RequestParam("searchType") String searchType,HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<ReportDetailsDto> groupReports = new ArrayList<ReportDetailsDto>();
		groupReports = (List<ReportDetailsDto>)session.getAttribute("groupWiseList");
		model.addAttribute("filename", "GroupWiseReport.csv");
		model.addAttribute("csvList", getCsvRecords(groupReports,searchType));
		return "csvView";	
	}
}
