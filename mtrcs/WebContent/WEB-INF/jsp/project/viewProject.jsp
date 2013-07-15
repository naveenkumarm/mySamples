<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Project</title>
</head>
<body class="yui3-skin-sam" onload="initTable()">
<br>
<center>
<div id="deleteSuccess" style="display: none; background: none repeat scroll 0 0 #DFF0D9; color: #279800; font-size: 12px;padding: 5px; width:95%;"></div>
<div id="deleteFailure" style="display: none; background: none repeat scroll 0 0 #FFE1D2; color: #FF5C5C; font-size: 12px;padding: 5px; width:95%;"></div>
</center>
<div id="availableProjects"></div>
</body>
<security:authorize ifAnyGranted='ROLE_SR.MANAGEMENT'>
<script type="text/javascript">
var userRole = "ROLE_SR.MANAGEMENT";
</script>
</security:authorize>
<security:authorize ifNotGranted='ROLE_SR.MANAGEMENT'>
<script type="text/javascript">
var userRole = "";
</script>
</security:authorize>
<script type="text/javascript">
var dataTable = null;
var optionFormatter = function(o){
	var projectId = o.record.get("projectId"); 
	var deleteInd = o.record.get("deleteInd");
	var projectStatus  = o.record.get("projectStatus");
	var projectName  = o.record.get("projectName");
	var hlink = "";
	
	if(userRole != "ROLE_SR.MANAGEMENT"){
		if(projectStatus != "C"){
			hlink='<a class="link-button" href= "/ems-web/ems/project/projectDetails?projectId='+projectId+'&pageType=view">view</a>  | <a class="link-button" href= "/ems-web/ems/project/projectDetails?projectId='+projectId+'&pageType=edit">edit</a>   | <u class="link-button" onclick=\'deleteProject('+projectId+',\"'+deleteInd+'\",\"'+projectName+'\")\'/>delete</u>';
		}
		else{
			hlink='<a class="link-button" href= "/ems-web/ems/project/projectDetails?projectId='+projectId+'&pageType=view">view</a>';
		}
	}else{
		hlink='<a class="link-button" href= "/ems-web/ems/project/projectDetails?projectId='+projectId+'&pageType=view">view</a>';
	}
	 return hlink;
    };
     
	var idFormatter = function(o){
			var projectId = o.record.get("projectId"); 
			var projectCode = o.record.get("projectCode"); 
			var hlink;
			var presentYear = new Date().getFullYear();
			if(projectCode == '' || projectCode ==null){
				hlink = 'EPRO'+presentYear+''+projectId;
			}
			else{
				hlink = projectCode;
			}
			
			 return hlink;
	};  
	var statusFormatter = function(o){
		var projectStatus= o.record.get("projectStatus"); 
		if(projectStatus == "C"){
			hlink='Closed';
		}
		else{
			hlink='Active';
		}
		 return hlink;
	};  
	
 
   
     var dataTableColumns = [ { key: 'projectCode', name: 'projectCode',label :"Project Code", formatter: idFormatter, allowHTML:true, className:'project-id'}, 
                                   	 { key: 'projectName', name: 'projectName',label :"Project Name", className:'project-name'},                               
                                     { key: "projectType", name: "projectType",label: "Project Type", className:'project-type'},
                                     { key: 'customerName', name: 'customerName',label :"Customer" },
                                     //{ key: 'platform', name: 'platform',label :"Platform" },
                                     { key: 'primaryCOEName', name: 'primaryCOEName',label :"Primary<br/>COE" },
                                     { key: 'startDate', name: 'StartDate',label :"Planned<br/>Start Date" },
                                     { key: 'endDate', name: 'EndDate',label :"Planned<br/>End Date" },
                                     { key: 'projectStatus', name: 'projectStatus',label :"Project<br/>Status" , formatter: statusFormatter, className:'project-status'},
                                     //{ key: 'approvalStatus', name: 'approvalStatus',label :"Approval Status" },
                                     { key: 'deleteInd', label :"Options" , formatter: optionFormatter,allowHTML:true, className:'options' }
                                     ];
     /**
      * Schema Definition for Results and Advance Search Results Datatable
      */
     var dataTableSchema = {
             resultListLocator: "projects",
             resultFields: [
             {key:"projectName"},
                 {key:"projectType"},
                 {key:"customerName"},
                 {key:"primaryCOEName"},
                 {key:"startDate"},
                 {key:"endDate"},
                 {key:"approvalStatus"},
                 {key:"approver"},
                 {key:"platform"},
                 {key:"projectId"},
                 {key:"projectCode"},
                 {key:"deleteInd"},
                 {key:"projectStatus"}
                 ]
                 };
    function initTable(){     
			YUI().use("datasource-io","datatable-message",
			        "datasource-jsonschema", "datatable-datasource","datatable-scroll", function(Y) {
			var config = {
			                id: "#availableProjects",
			                dataSrcUrl :  "/ems-web/ems/project/viewProject",
			                dataSrcQueryStr: "",
			                dataSrcMethod: 'POST',
			                tableColumns : dataTableColumns,
			                dataTableSchema: dataTableSchema
			                //scrollable : true
			                //handleDataReturnPayload: onDataReturn
			                
			        };
			          dataTable = DataTable(config);
			
			});
     }
    function deleteProject(projectId,deleteInd,projectName) {
   	 YUI().use('io-base', 'node', "json-parse","io","datasource-io","datatable-message",
   		   	 "datasource-jsonschema", "datatable-datasource","datatable-scroll", function(Y) {
   		 var queryStr = 'projectId='+projectId;
          	var cfg = {
          							method: 'POST',
   									data: queryStr,
   									on: {
   										success: function (id, o) {
          										 var response = Y.JSON.parse(o.responseText);
   											 if (response.deletedStatus == true || response.deletedStatus == "true") {
   												Y.one('#deleteFailure').setStyle('display','none');
   												Y.one('#deleteSuccess').setStyle('display','block');
   												Y.one("#deleteSuccess").set('innerHTML', projectName+' deleted successfully.');
   												dataTable.datasource.load({request: ''});
   											}
   											 else
   											 {
   												Y.one('#deleteSuccess').setStyle('display','none');
   												Y.one('#deleteFailure').setStyle('display','block');
   												Y.one("#deleteFailure").set('innerHTML', 'Unable to delete '+projectName+' project at this time.');
   											 }
   										}
   									}
   			  };
          	if(deleteInd=='Y'){
          		 var answer = confirm('Please confirm to delete '+projectName+' Project');
          	   	 if (!answer) {
          	   		return false;
          	   	 }
          		Y.io("/ems-web/ems/project/deleteProject", cfg);
          	}
          	else{
          		Y.one('#deleteSuccess').setStyle('display','none');
				Y.one('#deleteFailure').setStyle('display','block');
				Y.one("#deleteFailure").set('innerHTML', 'Unable to delete. Timesheet exists for <b>'+projectName+'</b> project.');

          	}
   		 });
   }
</script>
</html>