package com.epro.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.epro.adapter.TimeSheetAdapter;
import com.epro.beans.Project;
import com.epro.beans.Role;
import com.epro.beans.TimeSheet;
import com.epro.beans.TimeSheetList;
import com.epro.beans.UserInfo;
import com.google.gson.Gson;
import com.epro.schema.Activity;
import com.epro.schema.EmployeeInfo;

/**
 * TimeSheet controller 
 * Used to Add,Edit and View TimeSheet .
 * @author Naveen Kumar M
 *
 */

@Controller
public class TimeSheetController {
	
	private static final Logger LOG = Logger.getLogger(TimeSheetController.class);
	
	@Autowired
	private TimeSheetAdapter timeSheetAdapter;
	
	 /**
     * Returns the activity list, task list and
     *  employeeInfo to add/edit time sheet.
     * @param model
     * @param request
     * @return addTimeSheet view
     */
	@RequestMapping(value = "timesheet/add", method = RequestMethod.GET)
	public String addTimeSheet(ModelMap model,HttpServletRequest request) {
		try{
			LOG.info("TimeSheetController addTimeSheet Method In");
			Gson gson = new Gson();
			HttpSession session = request.getSession();
			UserInfo user =(UserInfo) session.getAttribute("userInfo");

			List<Activity> activityList = timeSheetAdapter.getActivityInfo();
			List<EmployeeInfo> empInfoList = timeSheetAdapter.getEmployeeInfo(user.getUserId());

			model.addAttribute("activityList", gson.toJson(activityList));
			model.addAttribute("empInfoList", gson.toJson(empInfoList));
			model.addAttribute("userInfo", gson.toJson(user));
			model.addAttribute("title", "Add/Edit Time Sheet");
		}
		catch (Exception e) {
			LOG.error("Error in add timesheet :"+e.getStackTrace());
		}
		return "addTimeSheet";
	}
	
	 /**
     * Returns the activity list, project list and
     *  employeeInfo to add onBehalf time sheet.
     * @param model
     * @param request
     * @return onBehalfTimeSheet view
     */
	@RequestMapping(value = "timesheet/onBehalf", method = RequestMethod.GET)
	public String onBehalfTimeSheet(ModelMap model,HttpServletRequest request) {
		try{
			LOG.info("TimeSheetController onBehalfTimeSheet Method In");
			Gson gson = new Gson();
			
			HttpSession session = request.getSession();
			UserInfo user =(UserInfo) session.getAttribute("userInfo");

			List<Project> projectList = timeSheetAdapter.getProjectInfo();
			List<Activity> activityList = timeSheetAdapter.getActivityInfo();
			model.addAttribute("projectList", gson.toJson(projectList));
			model.addAttribute("activityList", gson.toJson(activityList));
			model.addAttribute("userInfo", gson.toJson(user));
			model.addAttribute("title", " Add Time Sheet OnBehalf ");
		}
		catch (Exception e) {
			LOG.error("Error in add timesheet :"+e.getStackTrace());
		}
		return "onBehalfTimeSheet";
	}
	
	/**
     * Returns the activity list, project list and
     *  employee List to view time sheet.
     * @param model
     * @param request
     * @return viewTimeSheet view
     */
	@RequestMapping(value = "timesheet/view", method = RequestMethod.GET)
	public String viewTimeSheet(ModelMap model,HttpServletRequest request) {
		try{
			LOG.info("TimeSheetController viewTimeSheet Method In");

			Gson gson = new Gson();
			HttpSession session = request.getSession();
			boolean manager = false;
			UserInfo user =(UserInfo) session.getAttribute("userInfo");

			List<Project> projectList = timeSheetAdapter.getProjectInfo();
			List<Activity> activityList = timeSheetAdapter.getActivityInfo();
			List<EmployeeInfo> empInfoList = timeSheetAdapter.getEmployeeInfo(null);
			List<EmployeeInfo> userInfoList = timeSheetAdapter.getEmployeeInfo(user.getUserId());
			List<Project> managerProjects = new ArrayList<Project>();
			for(Role role : user.getUserRoles()){
				if(role.getRoleName().equalsIgnoreCase("ROLE_MANAGER")){
					manager = true;
				}
			}
			if(manager){
				List<Project> managerProjectList = timeSheetAdapter.getAssignedProjectsList(user.getUserId());
				for(Project managerProject : projectList ) {
					for(Project project : managerProjectList ) {
						if(managerProject.getProjectId().equalsIgnoreCase(project.getProjectId())){
							managerProjects.add(managerProject);
						}
					}
				}
				model.addAttribute("projectList", gson.toJson(managerProjects));
			}
			else{
				model.addAttribute("projectList", gson.toJson(projectList));
			}
			model.addAttribute("activityList", gson.toJson(activityList));
			model.addAttribute("empInfoList", gson.toJson(empInfoList));
			model.addAttribute("userInfo", gson.toJson(user));
			model.addAttribute("userInfoList", gson.toJson(userInfoList));
			model.addAttribute("title", "View Time Sheet");
		}
		catch (Exception e) {
			LOG.error("Error in viewTimeSheet :"+e.getStackTrace());
		}
		return "viewTimeSheet";
	}
	
	/**
     * Method used to get the time sheet list.
     * @param model
     * @param request
     * @param empId
     * @param projectId
     * @param month
     * @param year
     * @param fromDate
     * @param toDate
     * @return jsonView
     */
	@RequestMapping(value = "timesheet/get", method = RequestMethod.GET)
	public String getTimeSheet(ModelMap model,
			@RequestParam(value = "empId", required = true) String empId,
			@RequestParam(value = "projectId", required = false) String projectId,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate) {
		try{
			LOG.info("TimeSheetController getTimeSheet Method In");
			LOG.info("empId,projectId,month,year,fromDate,toDate ="
					+empId+","+ projectId+","+ month+","+year+","+ fromDate+","+ toDate);

			List<EmployeeInfo> empInfoList = new ArrayList<EmployeeInfo>();
			List<TimeSheet> timeSheetList = new ArrayList<TimeSheet>();
			empInfoList = timeSheetAdapter.getEmployeeInfo(empId);
			timeSheetList = timeSheetAdapter.getTimeSheetInfo(empId, projectId, month, year, fromDate, toDate);
			model.addAttribute("empInfoList", empInfoList);
			model.addAttribute("empProjectList", empInfoList.get(0).getProjects());
			model.addAttribute("timSheetList", timeSheetList);
		}
		catch (Exception e) {
			LOG.error("Error in getTimeSheet  :"+e.getStackTrace());
		}
		return "jsonView";
	}
	
	/**
     * Method used to save time sheet list.
     * @param model
     * @param timeSheetList
     * @param projectId
     * @param employeeId
     * @return success view
     */
	@RequestMapping(value = "timesheet/save", method = RequestMethod.POST)
	public String saveTimeSheet(ModelMap model,@ModelAttribute("timeSheetList")TimeSheetList timeSheetList,
			@RequestParam(value = "projectId", required = false) String projectId,
			@RequestParam(value = "empId", required = false) String employeeId) {
		try{
			LOG.info("TimeSheetController save Method In");
			boolean success = timeSheetAdapter.saveTimeSheetInfo(timeSheetList.getTimeSheetList());
		}
		catch (Exception e) {
			LOG.error("Error in save timesheet :"+e.getStackTrace());
		}
		return "landingView";
	}
	
	/**
     * Method used to remove time sheet with respect to id.
     * @param model
     * @param id
     * @return jsonView
     */
	@RequestMapping(value = "timesheet/remove", method = RequestMethod.GET)
	public String removeTimeSheet(ModelMap model,@RequestParam(value = "id")int id) {
		try{
			LOG.info("TimeSheetController remove Method In ");
			LOG.info("TimeSheetController remove id: "+id);
			if(id > 0){
				timeSheetAdapter.deleteTimesheetInfo(""+id);
			}
			model.addAttribute("success",true);
		}
		catch (Exception e) {
			LOG.error("Error in remove timesheet :"+e.getStackTrace());
		}
		return "jsonView";
	}
	
}
