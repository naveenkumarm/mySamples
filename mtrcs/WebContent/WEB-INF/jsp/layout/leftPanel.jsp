<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<div class="yui3-skin-sam">
<div id="myTree" style="padding-left: 9%;padding-top: 2%;"></div>
</div>
<security:authorize access="hasRole('ROLE_DEVELOPER')">
<script type="text/javascript">

            var child =  [ {
			                label: "TimeSheet",
			                id :'timeSheet',
			                children: [ 
			                    {
			                        label : "Add",
			                        id :'addTimeSheet'
			                        
			                    },
			                    {
			                        label : "View",
			                        id :'viewTimeSheet'
			                        
			                    }
			                ]
			            },
			            {
			               label: "Settings",
			               id :'settings',
			               children: [ 
			                    {
			                        label : "Change Password",
			                        id :'changepassword'
			                        
			                    }			                   
			                ]
			            }]
			           
</script>
</security:authorize>
<security:authorize access="hasAnyRole('ROLE_ACCOUNT MANAGER','ROLE_TEAM LEADER','ROLE_BUSINESS ANALYST')">
<script type="text/javascript">

            var child =  [ 
		                {
			                label: "Issue Register",
			                id :'issueRegister',
			                children: [ 
			                    {
			                        label : "Add",
			                        id :'addIssues'
			                        
			                    },
			                    {
			                        label : "View",
			                        id :'viewIssues'
			                        
			                    }
			                ]
			            },
			             {
			               label: "Settings",
			               id :'settings',
			               children: [ 
			                    {
			                        label : "Change Password",
			                        id :'changepassword'
			                        
			                    }			                   
			                ]
			            }]
			           
</script>
</security:authorize>
<security:authorize  access="hasRole('ROLE_MANAGER')">
<script type="text/javascript">
            var child =  
            [
            	{
                    label: "Project",
                    id :'project',
                    children: [ 
                        {
                            label : "List Project",
                            id :'listProjects'
                            
                        }
                    ]
                },       
           		{
                    label: "TimeSheet",
                    id :'timeSheet',
                    children: [ 
                        {
                            label : "Add",
                            id :'addTimeSheet'
                            
                        },
                        {
                            label : "Add On Behalf",
                            id :'addOnBehalf'
                            
                        },
                        {
                            label : "View",
                            id :'viewTimeSheet'
                            
                        }
                    ]
                },
                {
	                label: "Issue Register",
	                id :'issueRegister',
	                children: [ 
	                    {
	                        label : "Add",
	                        id :'addIssues'
	                        
	                    },
	                    {
	                        label : "View",
	                        id :'viewIssues'
	                        
	                    }
	                ]
	            },
	             {
			        label: "Settings",
			        id :'settings',
			        children: [ 
			           {
			                label : "Change Password",
			                id :'changepassword'		                        
			           }			                   
			        ]
			  }]
        
</script>
</security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN/PMO')">
<script type="text/javascript">
            var child =  
            	[ 

                 {
          label: "Customer",
          id :'customer',
          children: [ 
              {
                  label : "Add",
                  id :'addCustomer'
                  
              },
              {
                  label : "List",
                  id :'listCustomer'
                  
              }
          ]
      },
      {
          label: "Project",
          id :'project',
          children: [ 
              {
                  label : "List Project",
                  id :'listProjects'
                  
              },
              {
                  label : "Add New Project",
                  id :'addProject'
                  
              },
              {
                  label : "Assign Resource",
                  id :'assignResource'
                  
              },
              {
                  label : "Create Group",
                  id :'createGroup'
                  
              },
              {
                  label : "List Group",
                  id :'listGroup'
                  
              }
          ]
      },
      {
          label: "TimeSheet",
          id :'timeSheet',
          children: [ 
              {
                  label : "Add",
                  id :'addTimeSheet'
                  
              },
              {
                  label : "View",
                  id :'viewTimeSheet'
                  
              }
          ]
      },
    //Child added for reports
      {
          label: "Reports",
          id :'reports',
          children: [ 
              {
                  label : "Project Wise",
                  id :'projectWise'
                  
              },
              {
                  label : "Resource Wise",
                  id :'employeeWise'
                  
              },
              {
                  label : "Group Wise",
                  id :'groupWise'
                  
              }
          ]
      },
      {
			label: "Settings",
			id :'settings',
			children: [ 
			{
			       label : "Change Password",
			       id :'changepassword'
			                        
			},
			{
			       label : "Create User",
			       id :'createuser'
			                        
			},
			{
               	   label : "List User",
                   id :'listUser'
                  
           	 }				                   
		 ]
	 }]
        
</script>
</security:authorize>
<security:authorize access="hasRole('ROLE_SR.MANAGEMENT')">
<script type="text/javascript">
            var child = [ 

                            {
                               label: "Customer",
                               id :'customer',
                               children: [ 
                                   {
                                       label : "List",
                                       id :'listCustomer'
                                       
                                   }
                               ]
                           },
                           {
                               label: "Project",
                               id :'project',
                               children: [ 
                                   {
                                       label : "List Project",
                                       id :'listProjects'
                                       
                                   },
                                   {
                                       label : "Project Approval",
                                       id :'projectApproval'
                                       
                                   }
                               ]
                           },
                           {
                               label: "TimeSheet",
                               id :'timeSheet',
                               children: [ 
                                   {
                                       label : "Add",
                                       id :'addTimeSheet'
                                       
                                   },
                                   {
                                       label : "View",
                                       id :'viewTimeSheet'
                                       
                                   }
                               ]
                           },
                         //Child added for reports
                           {
                               label: "Reports",
                               id :'reports',
                               children: [ 
                                   {
                                       label : "Project Wise",
                                       id :'projectWise'
                                       
                                   },
                                   {
                                       label : "Resource Wise",
                                       id :'employeeWise'
                                       
                                   },
                                   {
                                       label : "Group Wise",
                                       id :'groupWise'
                                       
                                   }
                               ]
                           },{
                               label: "Dashboard",
                               id :'dashboard'
                           
                           },
                             {
						        label: "Settings",
						        id :'settings',
						        children: [ 
						           {
						                label : "Change Password",
						                id :'changepassword'		                        
						           }			                   
			        ]
			  }]
        
</script>
</security:authorize>
<script type="text/javascript">
YUI({gallery: 'gallery-2012.08.29-20-10'}).use("gallery-yui3treeview-ng", function(Y) {
    var treeview = new Y.TreeView({
            startCollapsed: false,
            children: child
    });
    treeview.render("#myTree");
});
YUI().use('node', "event","io", function(Y) {
	Y.on('click', function(e){
			if(Y.one("#customerModule") != null){
				customerViewModel.setCustomerValues(intialCustomerInfo);
				viewModel.errors.showAllMessages(false); 
				Y.one("#viewCustomer").setStyle("display","none");
				Y.one("#formDiv").setStyle("display","block");
				Y.one("#errorMessage").setStyle("display","none");
				Y.one("#successMessage").setStyle("display","none");
				Y.one("#dataTableContent").setStyle("display","none");
				Y.one("#customerName").set("disabled",false);
			}
			else{
				document.location.href = "/ems-web/ems/customer/view?request=add";
			}
		
		e.stopPropagation();
    }, '#addCustomer');
	Y.on('click', function(e){
		if(Y.one("#customerModule") != null){
			getCustomerDetails();
		}
		else{
			document.location.href = "/ems-web/ems/customer/view?request=get";
		}
		
		e.stopPropagation();
    }, '#listCustomer');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/project/viewProjectList";
	
	e.stopPropagation();
	}, '#listProjects');
	Y.on('click', function(e){
	
		document.location.href = "/ems-web/ems/project/addProject";
	
		e.stopPropagation();
	}, '#addProject');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/project/addNewResource";
	
	e.stopPropagation();
	}, '#assignResource');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/project/projectApproval";
	
	e.stopPropagation();
	}, '#projectApproval');
	
	Y.on('click', function(e){
	
		document.location.href = "/ems-web/ems/timesheet/add";
	
		e.stopPropagation();
	}, '#addTimeSheet');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/timesheet/onBehalf";
	
		e.stopPropagation();
	}, '#addOnBehalf');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/timesheet/view";
	
		e.stopPropagation();
	}, '#viewTimeSheet');
	//Child added for reports
	Y.on('click', function(e){
			
			document.location.href = "/ems-web/ems/project/projectWise";
		
			e.stopPropagation();
		}, '#projectWise');
	Y.on('click', function(e){
		document.location.href = "/ems-web/ems/project/groupWise";
		e.stopPropagation();
	}, '#groupWise');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/project/employeeWise";
	
		e.stopPropagation();
	}, '#employeeWise');
	Y.on('click', function(e){
		
		document.location.href = "/ems-web/ems/dashboard/view";
	
		e.stopPropagation();
	}, '#dashboard');
	Y.on('click', function(e){
		if(Y.one("#issueRegister_container") != null){
			Y.one("#addIssueRegister").setStyle("display","block");
			Y.one("#viewIssueRegister").setStyle("display","none");
			view = "add";
			self.projectId(undefined);
			self.issueList.removeAll();
		}else{
			document.location.href = "/ems-web/ems/issueRegister/view?request=add";
		}
		e.stopPropagation();
	}, '#addIssues');
	Y.on('click', function(e){
		if(Y.one("#issueRegister_container") != null){
			Y.one("#addIssueRegister").setStyle("display","none");
			Y.one("#viewIssueRegister").setStyle("display","block");
			view = "list";
			self.projectId(undefined);
			self.issueList.removeAll();
		}else{
			document.location.href = "/ems-web/ems/issueRegister/view?request=list";
		}
		e.stopPropagation();
	}, '#viewIssues');
	
	Y.on('click', function(e){
		document.location.href = "/ems-web/ems/login/changePassword";
		e.stopPropagation();
	}, '#changepassword');
	
		
	

	Y.on('click', function(e){
		document.location.href = "/ems-web/ems/project/createGroup";
	
		e.stopPropagation();
	}, '#createGroup');
	
	Y.on('click', function(e){
	
	document.location.href = "/ems-web/ems/project/listGroup";

	e.stopPropagation();
	}, '#listGroup');
	Y.on('click', function(e){
			if(Y.one("#userModule") != null){
				userViewModel.setUserValues(intialuserInfo);
				AppViewModel.errors.showAllMessages(false); 
				Y.one("#viewUser").setStyle("display","none");
				Y.one("#formDiv").setStyle("display","block");
				Y.one("#errorUserMessage").setStyle("display","none");
				Y.one("#successMessage").setStyle("display","none");
				Y.one("#dataTableContent").setStyle("display","none");
				Y.one("#userId").set("disabled",false);
				Y.one("#password").set("disabled",false);
			}
			else{
				document.location.href = "/ems-web/ems/login/userview?request=add";
			}
		e.stopPropagation();
    }, '#createuser');
	Y.on('click', function(e){
		if(Y.one("#userModule") != null){
			getUserDetails();
		}
		else{
			document.location.href = "/ems-web/ems/login/userview?request=get";
		}
		e.stopPropagation();
    }, '#listUser');
});
</script>
