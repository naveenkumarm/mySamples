<table id="grid"></table>
		<div id="pager"></div>

		<div class="contextMenu" id="myMenu1" style="display: none">
			<ul style="width: 200px">
				<li id="add"><span class="ui-icon ui-icon-plus"
					style="float: left"></span> <span
					style="font-size: 11px; font-family: Verdana">Add Row</span></li>
				<li id="edit"><span class="ui-icon ui-icon-pencil"
					style="float: left"></span> <span
					style="font-size: 11px; font-family: Verdana">Edit Row</span></li>
				<li id="del"><span class="ui-icon ui-icon-trash"
					style="float: left"></span> <span
					style="font-size: 11px; font-family: Verdana">Delete Row</span></li>
			</ul>
		</div>
		<script type="text/javascript">
		jq(function() {
					
			jq("#grid").jqGrid(
					{
						url : '../patient/list',
						datatype : "json",
						colNames : [ 'PatientID', 'FirstName', 'LastName', 'Age',
								'Location' ],
						colModel : [ {
							name : 'patientId',
							index : 'patientId',
							width : 55,
							editable : false,
							editoptions : {
								readonly : true,
								size : 10
							},
							hidden : true
						}, {
							name : 'firstName',
							index : 'firstName',
							width : 180,
							editable : false,
							editrules : {
								required : true
							},
							editoptions : {
								size : 10
							}
						}, {
							name : 'lastName',
							index : 'lastName',
							width : 180,
							editable : false,
							editrules : {
								required : true
							},
							editoptions : {
								size : 10
							}
						}, {
							name : 'age',
							index : 'age',
							width : 140,
							editable : true,
							editrules : {
								required : true,
								number : true
							},
							editoptions : {
								size : 10
							}
						}, {
							name : 'location',
							index : 'location',
							width : 180,
							editable : false,
							editrules : {
								required : true
							},
							editoptions : {
								size : 10
							}
						} ],
						rowNum : 10,
						rowList : [ 1, 5, 10, 20, 30 ],
						pager : '#pager',
						/* 			   	sortname: 'firstName',
						 */viewrecords : true,
						height : '100%',
						//autowidth : true,
						shrinkToFit: true,
						emptyrecords : "Empty records",
						loadonce : true,
						cellEdit : true,
						cellsubmit : 'remote',
						cellurl : '../patient/editCell',
						sortable : true,
						grouping : true,
						groupingView : {
							groupField : [ 'lastName' ],
							groupDataSorted : true
						},
						loadComplete : function() {
							jq("tr.jqgrow", this).contextMenu(
									'myMenu1',
									{
										bindings : {
											'edit' : function(trigger) {
												// trigger is the DOM element ("tr.jqgrow") which are triggered
												editRow(trigger.id, true);
												// jq("#grid").jqGrid('editGridRow',trigger.id,' editSettings');
											},
											'add' : function(/*trigger*/) {

												addRow();
											},
											'del' : function(trigger) {
												deleteRow(trigger.id, true);
											}
										},
										onContextMenu : function(event/*, menu*/) {
											var rowId = jq(event.target).closest(
													"tr.jqgrow").attr("id");
											//grid.setSelection(rowId);
											// disable menu for rows with even rowids
											jq('#del').attr("disabled",
													Number(rowId) % 2 === 0);
											if (Number(rowId) % 2 === 0) {
												//jq('#del').attr("disabled","disabled").addClass('ui-state-disabled');
											} else {
												//jq('#del').removeAttr("disabled").removeClass('ui-state-disabled');
											}
											return true;
										}
									});
						},
						ondblClickRow : function(rowid) {
							editRow(rowid, null);
						},
						jsonReader : {
							root : "Results",
							page : "page",
							total : "total",
							records : "rows",
							repeatitems : false,
							cell : "cell",
							id : "id"
						},
						sortname : 'firstName',
						sortorder : "asc",
						viewsortcols : true,
						caption : "Patient List"
					});
			jq("#grid").jqGrid('editRow', 'id', {
				keys : true,
				aftersavefunc : function(rowid, response) {
					alert('after save');
				},
				errorfunc : function(rowid, response) {
					alert('...we have a problem');
				}
			});
			jq("#grid").jqGrid('saveRow', 'lastsel', {
				aftersavefunc : function(rowid, response) {
					alert('after save');
				},
				errorfunc : function(rowid, response) {
					alert('error occured');
				}
			});
			jq("#grid").jqGrid('navGrid', '#pager', {
				edit : false,
				add : false,
				del : false,
				search : true
			}, {}, {}, {}, {
				sopt : [ 'eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew' ],
				closeOnEscape : true,
				multipleSearch : true,
				closeAfterSearch : true
			});

			jQuery("#grid").jqGrid('sortableRows');

			jq("#grid").navButtonAdd('#pager', {
				caption : "Add",
				buttonicon : "ui-icon-plus",
				onClickButton : addRow,
				position : "last",
				title : "",
				cursor : "pointer"
			});

			jq("#grid").navButtonAdd('#pager', {
				caption : "Edit",
				buttonicon : "ui-icon-pencil",
				onClickButton : editRow,
				position : "last",
				title : "",
				cursor : "pointer"
			});

			jq("#grid").navButtonAdd('#pager', {
				caption : "Delete",
				buttonicon : "ui-icon-trash",
				onClickButton : deleteRow,
				position : "last",
				title : "",
				cursor : "pointer"
			});

		});
	</script>
<script type="text/javascript">

function addRow() {
//init
setColumnEditable();
	 
	// Get the currently selected row
    jq("#grid").jqGrid('editGridRow','new',
    		{ 	url: "../patient/add", 
					editData: {
			    },
			    recreateForm: true,
			    beforeShowForm: function(form) {
			    },
				closeAfterAdd: true,
				reloadAfterSubmit:true,
				afterSubmit : function(response, postdata) 
				{ 
			        var result = eval('(' + response.responseText + ')');
					var errors = "";
					
			        if (result.success == false) {
						for (var i = 0; i < result.message.length; i++) {
							errors +=  result.message[i] + "<br/>";
						}
			        }  else {
			        	jq("#grid").jqGrid('setGridParam',{datatype:'json'});
						jq("#grid").jqGrid('setGridParam', {url:"../patient/list"}).trigger("reloadGrid");     
			        	jq("#dialog").text('Entry has been added successfully');
						jq("#dialog").dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										jq(this).dialog("close");} 
									}
								});
	                }
			    	// only used for adding new records
			    	var new_id = null;
			    	
					return [result.success, errors, new_id];
				}
    		});
    setColumnNonEditable();
}

function editRow(editObj,cntxInd) {
	setColumnEditable();
	var row = null;
	if(cntxInd){
		row = editObj;
	}
	else{
		// Get the currently selected row
	 	row = jq("#grid").jqGrid('getGridParam','selrow');
	}
	if( row != null ) 
		jq("#grid").jqGrid('editGridRow',row,
			{	url: "../patient/edit", 
				editData: {
		        },
		        recreateForm: true,
		        beforeShowForm: function(form) {
		        },
				closeAfterEdit: true,
				reloadAfterSubmit:true,
				afterSubmit : function(response, postdata) 
				{ 
		            var result = eval('(' + response.responseText + ')');
					var errors = "";
					
		            if (result.success == false) {
						for (var i = 0; i < result.message.length; i++) {
							errors +=  result.message[i] + "<br/>";
						}
		            }  else {		
		            	jq("#grid").jqGrid('setGridParam',{datatype:'json'});
						jq("#grid").jqGrid('setGridParam', {url:"../patient/list"}).trigger("reloadGrid");           
		            	jq("#dialog").text('Entry has been edited successfully');
						jq("#dialog").dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										jq(this).dialog("close");} 
									}
								});

	                }
		        	
					return [result.success, errors, null];
				}
			});
	else jq( "#dialogSelectRow" ).dialog();
	setColumnNonEditable();
}

function deleteRow(delObj,cntxInd) {
	setColumnEditable();
	var row = null;
	if(cntxInd){
		row = delObj;
	}
	else{
		// Get the currently selected row
	 	row = jq("#grid").jqGrid('getGridParam','selrow');
	}

    // A pop-up dialog will appear to confirm the selected action
	if( row != null ) 
		jq("#grid").jqGrid( 'delGridRow', row,
          	{ url: '../patient/delete', 
						recreateForm: true,
			            beforeShowForm: function(form) {
			              //change title
			              jq(".delmsg").replaceWith('<span style="white-space: pre;">' +
			            		  'Delete selected record?' + '</span>');
	            		  
						  //hide arrows
			              jq('#pData').hide();  
			              jq('#nData').hide();  
			            },
          				reloadAfterSubmit:true,
          				closeAfterDelete: true,
          				afterSubmit : function(response, postdata) 
						{ 
			                var result = eval('(' + response.responseText + ')');
							var errors = "";
							
			                if (result.success == false) {
								for (var i = 0; i < result.message.length; i++) {
									errors +=  result.message[i] + "<br/>";
								}
			                }  else {
			                	jq("#grid").jqGrid('setGridParam',{datatype:'json'});
								jq("#grid").jqGrid('setGridParam', {url:"../patient/list"}).trigger("reloadGrid");     
			                	jq("#dialog").text('Entry has been deleted successfully');
								jq("#dialog").dialog( 
										{	title: 'Success',
											modal: true,
											buttons: {"Ok": function()  {
												jq(this).dialog("close");} 
											}
										});
			                }
		                	// only used for adding new records
		                	var new_id = null;
		                	
							return [result.success, errors, new_id];
						}
          	});
	 else jq( "#dialogSelectRow" ).dialog();
	setColumnNonEditable();
}

function setColumnEditable(){
	 jq("#grid").setColProp('firstName',{editable:true});
	 jq("#grid").setColProp('lastName',{editable:true});
	 jq("#grid").setColProp('location',{editable:true});
}

function setColumnNonEditable(){
	 jq("#grid").setColProp('firstName',{editable:false});
   	 jq("#grid").setColProp('lastName',{editable:false});
   	 jq("#grid").setColProp('location',{editable:false});
}



</script>  
<div id="dialog" title="Feature not supported" style="display:none">
	<p>That feature is not supported.</p>
</div>

<div id="dialogSelectRow" title="Warning" style="display:none">
	<p>Please select row</p>
</div>		