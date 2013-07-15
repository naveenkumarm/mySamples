package com.epro.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epro.adapter.DashboardAdapter;
import com.epro.adapter.ProjectAdapter;
import com.epro.beans.DashboardProjectInfo;
import com.epro.beans.LookUp;
import com.google.gson.Gson;
@Controller
public class DashboardController {
	@Autowired
	private ProjectAdapter projectAdapter;
	@Autowired
	private DashboardAdapter dashboardAdapter;
	Gson gson = new Gson();
	@RequestMapping(value = "/dashboard/view", method = RequestMethod.GET)
	public String getDashboardView(ModelMap model,HttpServletRequest request) {
		List<LookUp> projectList = projectAdapter.getLookUpValue("dashboard-project","A");
		model.addAttribute("projects", gson.toJson(projectList));
		model.addAttribute("title", "Dashboard");
		return "dashboardView";
	}
	@RequestMapping(value = "/dashboard/getProjectList", method = RequestMethod.GET)
	public String getProjectList(ModelMap model,HttpServletRequest request
								,@RequestParam("projectType") String projectType) {
		List<LookUp> projectList = projectAdapter.getLookUpValue("dashboard-project",projectType);
		model.addAttribute("projects", projectList);
		return "jsonView";
	}
	@RequestMapping(value = "/dashboard/getMonth", method = RequestMethod.GET)
	public String getMonthForProject(ModelMap model,@RequestParam("projectId") String projectId) {
		List<LookUp> months = projectAdapter.getLookUpValue("month-year",projectId);
		model.addAttribute("months", months);
		return "jsonView";
	}
	@RequestMapping(value = "/dashboard/getDashboardInfo", method = RequestMethod.GET)
	public String getDashboardInfo(ModelMap model,@RequestParam("projectId") String projectId
												,@RequestParam("monthYear") String monthYear) {
		DashboardProjectInfo dashboardProjectInfo = dashboardAdapter.getDashboardInfo(projectId,monthYear);
		model.addAttribute("dashboardInfo", dashboardProjectInfo);
		return "jsonView";
	}
}
