package com.epro.beans;

public class TimeSheet {
	
	private int id;
	private String employeeId;
	private String projectId;
	private String projectName;
	private String date;
	private String activityId;
	private String taskId;
	private String hours;
	private String minutes;
	private String billable;
	private String location;
	private String authoriser;
	private String approvalStatus;
	private String departmentId;
	private String departmentName;
	private String onBehalf;
	private String cutOffInd;
	private String workItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getBillable() {
		return billable;
	}
	public void setBillable(String billable) {
		this.billable = billable;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAuthoriser() {
		return authoriser;
	}
	public void setAuthoriser(String authoriser) {
		this.authoriser = authoriser;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getOnBehalf() {
		return onBehalf;
	}
	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}
	public String getCutOffInd() {
		return cutOffInd;
	}
	public void setCutOffInd(String cutOffInd) {
		this.cutOffInd = cutOffInd;
	}
	public String getWorkItems() {
		return workItems;
	}
	public void setWorkItems(String workItems) {
		this.workItems = workItems;
	}
	public String toString() {
        return "TimeSheet [id=" + id + ",employeeId="+employeeId+", projectId=" + projectId + ", projectName="
                + projectName + ", date=" + date +", activityId=" + activityId +", taskId="
                + taskId +", hours=" + hours +", minutes=" + minutes +", billable=" 
                + billable +", location=" + location +", authoriser="+ authoriser +", approvalStatus=" 
                + approvalStatus +", departmentId=" + departmentId +", onBehalf=" + onBehalf+", cutOffInd=" + cutOffInd+", workItems=" + workItems +"]";
    }
}