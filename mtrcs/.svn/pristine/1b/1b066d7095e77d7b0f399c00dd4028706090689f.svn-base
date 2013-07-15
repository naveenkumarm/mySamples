<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<div class='yui3-skin-sam'>
<div id="approvalStatus" style="display: none; color: red;">Success
!</div>
<div id="viewprojectDetails" style="display: none;">
<ul>
	<li><a href="#projectInfo">Project Information</a></li>
	<li><a href="#handoverInfo">Handover Information</a></li>
</ul>

<div>
<div id="projectInfo">
<table>
	<tr data-bind="visible: !project.projectCode() && project.projectId()" >
		<td><label>Project ID </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: 'EPRO'+presentYear+''+project.projectId()"></span>
		</td>
	</tr>
	<tr data-bind="visible: project.projectCode()" >
		<td><label>Project ID </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.projectCode"></span>
		</td>
	</tr>
	<tr>
		<td><label>Project Name </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.projectName"></span></td>
	</tr>
	<tr>
		<td><label>Customer </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.customerName"></span></td>
	</tr>
	<tr>
		<td><label>Project Type </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.projectType"></span></td>
	</tr>
	<tr>
		<td><label>Development Platform</td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.platform"></span></td>
	</tr>
	<tr>
		<td><label>Primary COE </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.primaryCOEName"></span></td>
	</tr>
	<tr>
		<td><label>Estimated Effort </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.effort"></span>&nbsp;&nbsp;Person
		Days</td>
		<!--<td data-bind="text:project.unit"></td>-->
	</tr>
	<tr>
		<td><label>Planned Start Date </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.startDate"></span></td>
	</tr>
	<tr>
		<td><label>Planned End Date </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.endDate"></span></td>
	</tr>
	<!--<tr><td>Approved by:</td><td  data-bind="text: project.approver"></td></tr>-->
	<tr>
		<td><label>Project Status </label></td>
		<td>&nbsp;:&nbsp; <span
			data-bind="if:(project.projectStatus() == 'A')">Active</span> <span
			data-bind="if:(project.projectStatus() == 'C')">Closed</span></td>
	</tr>
	<tr data-bind="if:(project.projectStatus() == 'C')">
		<td><label>Project Closure Date </label></td>
		<td>&nbsp;:&nbsp;<span data-bind="text: project.closureDate"></span></td>
	</tr>
</table>
</div>
<div id="handoverInfo" class="normal-grid">
<table width="60%">
	<thead>
		<tr>
			<th width='30%'>CheckList Item</th>
			<th width='15%'>Status</th>
			<th width='55%'>Remarks</th>

		</tr>
	</thead>
	<tbody data-bind='foreach: checkListArray'>
		<tr>
			<td data-bind="text: checkListItem"></td>
			<td data-bind="text: checkListStatus"></td>
			<td data-bind="text: remarks"></td>
		</tr>
	</tbody>
</table>

<div class="clearfix" data-bind='visible : documenArray().length != 0'>
<b style="font-size: 14px; width: 98%;">Handover Documents</b>
<div data-bind='foreach: documenArray' style="">
<div style="margin: 5px 0;"><a class="link-button" href='#'
	data-bind="text: documentName, click: $parent.download"></a></div>
</div>
</div>

</div>
</div>

</div>
<div id="approvalDiv"
	style="width: 330px; padding: 15px; border: 5px #f1f1f1 solid; display: none; margin: 0 2%;">
<f:form id="approvalForm" name="approvalForm">
	<b><u>Approve Or Decline</u></b>
	<input type="hidden" data-bind="value: project.projectId"
		name="projectId" id="projectId">
	<table cellspacing="0" cellpadding="0"
		style="width: 100%; margin-top: 10px;">
		<tr>
			<td><label>Approve</label></td>
			<td><input type="radio" name="approvalStatus" value="Approved"
				data-bind="checked: project.approvalStatus">Yes&nbsp;&nbsp;
			<input type="radio" name="approvalStatus" value="Declined"
				data-bind="checked: project.approvalStatus">No</td>
		</tr>
		<tr>
			<td><label>Remarks</label></td>
			<td><textarea data-bind="value: project.approvalRemarks"
				id="approvalRemarks" name="approvalRemarks" style="height: 20px;"></textarea>
			<span class="validationMessage" id="approvalRemarks-error"
				style="display: none;">Approval Remarks Needed</span></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div data-bind="click: approveProject" class="normal-button"
				style="width: 65px; margin-top: 5px; float: left;">Save</div>
			<a href="/ems-web/ems/project/projectApproval"
				style="width: 65px; float: left; margin: 5px 0 5px 5px; text-decoration: none;"
				class="normal-button">Cancel</a></td>
		</tr>
	</table>

</f:form></div>
</div>
<div class="normal-grid" id="projectInfoGrid"
	style="padding: 0px; 0 px; margin-left: 1%">
<div class='yui3-skin-sam'>
<div id='radioContainer'>
<button id="all" class='radio'>All</button>
<button id="awaiting" name="awaiting" class='radio'>Awaiting</button>
<button id="approved" class='radio'>Approved</button>
</div>
</div>
<div class="normal-grid"  style="width: 100%;">
<table>
	<thead>
		<tr>
			<th width='12%'>Project Id</th>
			<th width='10%'>Project</th>
			<th width='10%'>Customer</th>
			<th width='10%'>Category</th>
			<th width='10%'>Planned Start Date</th>
			<th width='10%'>Planned End Date</th>
			<th width='13%'>Platform</th>
			<th width='7%'>Project Status</th>
			<th width='7%'>Planned Effort (Person Days)</th>
			<th width='6%'>Approval<br>Status</th>
		</tr>
	</thead>
	<tbody style="background-color: white !important;"
		data-bind="visible: projectsArray().length == 0">
		<tr>
			<td colspan="9" style="text-align: center;"><span> No
			Data Found </span></td>
		</tr>
	</tbody>
	<tbody data-bind='foreach: projectsArray'>
		<tr data-bind="click: $root.goToProject">
			<td data-bind="visible:!projectCode(), text: 'EPRO'+presentYear+''+projectId()"></td>
			<td data-bind="visible: projectCode(), text: projectCode"></td>
			<td data-bind="text: projectName"></td>
			<td data-bind="text: customerName"></td>
			<td data-bind="text: projectType"></td>
			<td data-bind="text: startDate"></td>
			<td data-bind="text: endDate"></td>
			<td data-bind="text: platform"></td>
			<td><span data-bind="if:(projectStatus() == 'A')">Active</span>
			<span data-bind="if:(projectStatus() == 'C')">Closed</span></td>
			<td data-bind="text: effort"></td>
			<td
				data-bind="attr :{'class': $root.setbackgroundStyle($data, $index()),'title': $root.setTitle($data, $index())}"></td>
		</tr>
	</tbody>
</table>
</div>
</div>



<script type="text/javascript">
	var AppViewModel;
	var projects = [];
	var checkListInfos = [];
	var projectDetail;
	var projectInfo;
	var documenInfos = [];
	var handoverProjectInfo = [];
	var documents = [];
	var presentYear = new Date().getFullYear();
	YUI({
		filter : 'raw'
	}).use("yui", "tabview", "io-form", "datasource-jsonschema",
			"datasource-io", "datasource-io", function(Y) {
				tabview = new Y.TabView( { srcNode : '#viewprojectDetails' });
				tabview.render();
				initKo();
			});
	function projectViewModel() {
		var AppViewModelApproval = {};
		var self = this;
		YUI().use(
				'io-base',
				'json-parse',
				'json-stringify',
				'node',
				function(Y) {
					Y.one("#loadingImage").setStyle("display", "block");
					self.projectId = ko.observable();
					self.project = {
						projectId : ko.observable(""),
						projectCode : ko.observable(""),
						projectName : ko.observable(""),
						projectdescription : ko.observable(""),
						projectType : ko.observable(""),
						customerName : ko.observable(""),
						platform : ko.observable(""),
						primaryCOEName : ko.observable(""),
						effort : ko.observable(""),
						unit : ko.observable(""),
						startDate : ko.observable(""),
						endDate : ko.observable(""),
						approver : ko.observable(""),
						closureDate : ko.observable(""),
						projectStatus : ko.observable(""),
						approvalStatus : ko.observable(""),
						approvalRemarks : ko.observable("")
					};
					self.checkListArray = ko.observableArray( []);
					self.documenArray = ko.observableArray( []);
					self.projectsArray = ko.observableArray( []);
					self.addExistingProjects = function(projects){
						self.projectsArray.removeAll();
						for(var i=0 ; i < projects.length ; i++)
						self.projectsArray.push(new addProjects(projects[i]));
						};
					Y.io("/ems-web/ems/project/viewProject", {
						method : 'GET',
						sync : true,
						on : {
							success : function(id, o) {
								messages = Y.JSON.parse(o.responseText);
								projects = messages.projects;
								Y.one("#loadingImage").setStyle("display", "none");
								// alert(projects);
								self.addExistingProjects(projects);
								//ko.mapping.fromJS(messages.projects, {}, self.projectsArray);
							}
						}
					});
				});
		YUI().use('button-group', function(Y) {
			var buttonGroupRadio = new Y.ButtonGroup( {
				srcNode : '#radioContainer',
				type : 'radio'
			});
			buttonGroupRadio.render();
			buttonGroupRadio.on('selectionChange', function(e) {
				var param = buttonGroupRadio.getSelectedValues();
				self.refreshData(param);
				Y.log('buttonGroup selection changed');
			});
			Y.one('#all').addClass('yui3-button-selected');
		});
		self.refreshData = function(param) {
			YUI().use(
					'io-base',
					'json-parse',
					'json-stringify',
					'node',
					function(Y) {
						Y.one("#loadingImage").setStyle("display", "block");
						Y.io("/ems-web/ems/project/viewProject?param=" + param,
								{
									method : 'GET',
									sync : true,
									on : {
										success : function(id, o) {
											messages = Y.JSON.parse(o.responseText);
											projects = messages.projects;
											Y.one("#loadingImage").setStyle("display", "none");
											self.addExistingProjects(projects);
											//ko.mapping.fromJS(messages.projects, {}, self.projectsArray);
										}
									}
								});
					});
		};
		self.goToProject = function(project) {
			var record = ko.toJS(project);
			YUI().use(
					'io-base',
					'node',
					"json-parse",
					'array-extras',
					'querystring-stringify',
					"io",
					function(Y) {
						Y.one("#loadingImage").setStyle("display", "block");
						var cfg = {
							method : 'GET',
							sync : true,
							on : {
								success : function(id, o) {
									messages = Y.JSON.parse(o.responseText);
									Y.one("#loadingImage").setStyle("display","none");
									checkListInfos = messages.checkListInfos;
									documenInfos = messages.documenInfos;
									projectInfo = messages.projectInfo;
									//alert(projectInfo.approvalStatus);
									//alert(projectInfo.projectName);
									ko.mapping.fromJS(messages.checkListInfos, {}, self.checkListArray);
									ko.mapping.fromJS(messages.projectInfo, {}, self.project);
									ko.mapping.fromJS(messages.documenInfos,{}, self.documenArray);
									self.project.approvalRemarks('');
									Y.one('#viewprojectDetails').setStyle('display', 'block');
									Y.one('#projectInfoGrid').setStyle('display', 'none');
									Y.one('#approvalDiv').setStyle('display','block');

								}
							}
						};
						Y.io("/ems-web/ems/project/Details?projectId="
								+ record.projectId, cfg);
					});
		};
		self.download = function(doc) {
			var record = ko.toJS(doc);
			document.location.href = "/ems-web/ems/project/download?id="
					+ record.projectDocId;
		};

		self.approveProject = function(project) {
			var record = ko.toJS(project);

			YUI().use('io-base',
							'node',
							"json-parse",
							'array-extras',
							'querystring-stringify',
							"io",
							function(Y) {
								var form = Y.one('#approvalForm');
								var ApprovalInfo = Y.QueryString.stringify(Y.Array.reduce(
														Y.one(form).all(
																		'input[name],select[name],textarea[name]')._nodes,
														{},
														function(init, el,
																index, array) {
															init[el.name] = el.value;
															return init;

														}));
								var approvalStatus = self.project.approvalStatus();
								var projectId = Y.one("#projectId").get("value");
								var approvalRemarks = Y.one("#approvalRemarks").get("value");

								function isApprove() {
									var status = true;
									if (approvalStatus == 'Declined') {
										if (approvalRemarks == '') {
											Y.one('#approvalRemarks-error').setStyle('display','block');
											status = false;
										} else {
											Y.one('#approvalRemarks-error').setStyle('display', 'none');
										}
									}
									return status;
								}
								var url = '/ems-web/ems/project/approve';
								url = url + '?approvalStatus=' + approvalStatus
										+ '&projectId=' + projectId
										+ '&approvalRemarks=' + approvalRemarks;
								var cfg = {
									method : 'GET',
									//data: ApprovalInfo,
									on : {
										success : function(id, o) {
											var message = Y.JSON
													.parse(o.responseText);
											if (message != null) {
												if (message.success == true
														|| message.success == 'true') {
													AppViewModel.refreshData("All");
													Y.one('#awaiting').removeClass('yui3-button-selected');
													Y.one('#approved').removeClass('yui3-button-selected');
													Y.one('#all').addClass('yui3-button-selected');
													Y.one("#loadingImage").setStyle("display","none");
													Y.one('#viewprojectDetails').setStyle('display','none');
													Y.one('#projectInfoGrid').setStyle('display','block');
													Y.one('#approvalDiv').setStyle('display','none');
													//Y.one('#approvalStatus').setStyle('display','block');
												}

											}

										}
									}
								};
								if (isApprove()) {
									Y.one('#approvalRemarks-error').setStyle('display', 'none');
									Y.one("#loadingImage").setStyle("display","block");
									Y.io(url, cfg);
								}
							});
		};

		self.setbackgroundStyle = function(data, index) {
			var response = ko.toJS(data);
			if (response.approvalStatus == 'Approved')
				return "approved";
			else if (response.approvalStatus == 'Declined')
				return "declined";
			else if (response.approvalStatus == 'Awaiting for Approval')
				return "awaiting";
		};
		self.setTitle = function(data, index) {
			var response = ko.toJS(data);
			if (response.approvalStatus == 'Approved')
				return "Approved";
			else if (response.approvalStatus == 'Declined')
				return "Declined";
			else if (response.approvalStatus == 'Awaiting for Approval')
				return "Awaiting";
		};
		function addProjects(project) {
			var self = this;
			self.projectId  = ko.observable(project.projectId);
			self.projectCode  = ko.observable(project.projectCode);
			self.projectName  = ko.observable(project.projectName);
			self.projectdescription  = ko.observable(project.projectdescription);
			self.projectType  = ko.observable(project.projectType);
			self.customerName  = ko.observable(project.customerName);
			self.platform  = ko.observable(project.platform);
			self.primaryCOEName  = ko.observable(project.primaryCOEName);
			self.effort  = ko.observable(project.effort);
			self.unit  = ko.observable(project.unit);
			self.startDate  = ko.observable(project.startDate);
			self.endDate  = ko.observable(project.endDate);
			self.approver  = ko.observable(project.approver);
			self.closureDate  = ko.observable(project.closureDate);
			self.projectStatus  = ko.observable(project.projectStatus);
			self.approvalStatus  = ko.observable(project.approvalStatus);
			self.approvalRemarks  = ko.observable(project.approvalRemarks);
		}
	}
	function initKo() {
		AppViewModel = new projectViewModel();
		ko.applyBindings(AppViewModel);
	}
</script>
</body>
</html>