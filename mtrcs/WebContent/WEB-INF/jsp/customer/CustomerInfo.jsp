<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>
<head>
<title>Customer Information</title>
</head>
<script type="text/javascript" src="../js/countries2.js"></script>
<body class="yui3-skin-sam" onload="getListDetails();">
<br>
<div id="customerModule">
<form id="customerForm">
<div id="formDiv" align="center" style="display: none;padding-right: 25%">
	<div style="width:330px; padding:25px; border:5px #f1f1f1 solid;"> 
		<table cellspacing="0" cellpadding="0" style="width:100%;">
			<tr>
				<td style="width:40%;"><label>Customer Name <span class="mandatory">*</span></label></td>
				<td><input id="customerName"  name="customerName" data-bind="value: customerDetails.customerName" type="text" style="width: 90%;"></td>
			</tr>
			<tr>
				<td style="width:40%;"><label>Line of Business <span class="mandatory">*</span></label></td>
				<td><input name="lineOfBusiness" data-bind="value: customerDetails.lineOfBusiness" type="text" style="width: 90%;"></td>
			</tr>
			<tr>
				<td style="width:40%;"><label>Primary Contact <span class="mandatory">*</span></label></td>
				<td><input name="primaryContactPerson" data-bind="value: customerDetails.primaryContactPerson" type="text" style="width: 90%;"></td>
			</tr>
			<tr>
				<td style="width:40%;"><label>Customer Website </label></td>
				<td><input name="website" data-bind="value: customerDetails.website" type="text" style="width: 90%;">
			</tr>
			<tr>
				<td style="width:40%;"><label>Address <span class="mandatory">*</span></label></td>
				<td><textarea name="address" data-bind="value: customerDetails.address" style="width: 90%; height: 60px;"></textarea></td>
			</tr>
			<tr>
				<td style="width:40%;"><label>Country <span class="mandatory">*</span></label></td>
				<td><select  name="country" data-bind="options: customerDetails.countryListArray, optionsText: 'id', optionsValue: 'id',
				optionsCaption: 'Select', value: customerDetails.country,attr: {'title': customerDetails.country}" style="width: 90%;"></select>
				</td>
			</tr>
			<tr>
				<td style="width:40%;"><label>State <span class="mandatory">*</span></label> </td>
				<td>
				<select  name="state" data-bind="options: customerDetails.stateListArray, optionsText: 'id',optionsValue: 'id',
				optionsCaption: 'Select', value: customerDetails.state, attr: {'title': customerDetails.state}" style="width: 90%;"></select>
				</td>
			</tr>
			<tr>
				<td style="width:40%;"><label>Phone <span class="mandatory">*</span></label></td>
				<td><input name="phoneNumber" maxlength="10" data-bind="value: customerDetails.phoneNumber" type="text" style="width: 90%;"></td>
			</tr>
			<tr>
				<td style="width:40%;"><label>Fax <span class="mandatory">*</span></label></td>
				<td><input name="faxNumber" maxlength="10" data-bind="value: customerDetails.faxNumber" type="text" style="width: 90%;"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<div data-bind='click: submit' class="normal-button" style="width:65px; float:left;">Save</div>
					<div data-bind='click: cancelAddCustomer' class="normal-button" style="width:65px; float:left; margin-left:5px;">Cancel</div>
					<input type="hidden" id="customerId" name="customerId" data-bind="value: customerDetails.customerId"/>
				</td>
			</tr>
		</table>
	</div>
</div>
</form>
<div id="viewCustomer" align="center" style="display: none;padding-right: 25%">
	<div style="width:400px; padding:25px; border:5px #f1f1f1 solid;"> 
		<table  cellspacing="0" cellpadding="0">
			<tr>
				<td style="width:50%"><label>Customer Name</label></td>
				<td>: <span  data-bind="text: customerDetails.customerName"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Line of Business</label></td>
				<td>: <span  data-bind="text: customerDetails.lineOfBusiness"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Primary Contact</label></td>
				<td>: <span  data-bind="text: customerDetails.primaryContactPerson"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Customer Website</label></td>
				<td>: <span data-bind="text: customerDetails.website"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Address</label></td>
				<td>: <span  data-bind="text: customerDetails.address"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>State</label></td>
				<td>: <span  data-bind="text: customerDetails.state"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Country</label></td>
				<td>: <span  data-bind="text: customerCountry"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Phone</label></td>
				<td>: <span  data-bind="text: customerDetails.phoneNumber"></span></td>
			</tr>
			<tr>
				<td style="width:50%"><label>Fax</label></td>
				<td>: <span  data-bind="text: customerDetails.faxNumber"></span></td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="2">
				<div align="center" style="padding-right:76px"><div class="normal-button" id="cancel" data-bind='click: cancelAddCustomer' style="width:65px;">Back</div></div>
				</td>
			</tr>
		</table>
	</div>
</div>

<div id="successMessage" align="center" style="display: none; background: none repeat scroll 0pt 0pt #DFF0D9;; color: #279800; font-size: 12px; padding: 5px; width: 95%;margin-left: 2%;"></div>
<div id="errorMessage" align="center" style="display: none; background: none repeat scroll 0 0 #FFE1D2; color: #FF5C5C; font-size: 12px;padding: 5px; width:95%;margin-left: 2%;"></div>
	<div id="dataTableContent" style="display: none;">
		<div id="Datatable"></div>
	</div>
	<div style="display: none;" class="demo-description"></div>
</div>
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
var customerViewModel;
var intialCustomerInfo = {customerName : '',
						  customerId: '-1',
						  lineOfBusiness:'',
						  primaryContactPerson:'',
						  website:'',
						  address:'',
						  country:undefined,
						  state:undefined,
						  phoneNumber:'',
						  faxNumber:''
						};
var optionFormatter = function(o){
	var id= o.record.get("customerId");
	var deleteId= o.record.get("deleteInd");
	var customerName= o.record.get("customerName");
	//alert(customerName);
	var hlink = "";
	if(userRole != "ROLE_SR.MANAGEMENT"){																																									
		hlink='<div class="yui3-datatable-width"><a class="link-button" onclick="viewcustomer('+id+')">view</a> | <a class="link-button" onclick="editcustomer('+id+')" >edit</a>  | <a class="link-button" onclick=\'deletecustomer('+id+',\"'+deleteId+'\",\"'+customerName+'\")\'/>delete</div>';
	}else{
		hlink='<div class="yui3-datatable-width"><a class="link-button" onclick="viewcustomer('+id+')">view</a>';
	}
	return hlink;
};
var dataTableColumns = [  {key: "customerName",label: "Customer<br/>Name", name: 'CustomerName', className:'customer-name'},
                          {key: "customerId",label: "Customer Id", name: 'CustomerId', className: 'hidden-col'},
                          {key: "lineOfBusiness", label: "Line Of<br/>Business",  name: 'LineofBusiness', className:'business-name'}, 
                          {key: "primaryContactPerson",label: "Primary<br/>Contact", name: 'PrimaryContactPerson', className:'primary-contact'},
                          {key: "website",label: "Website", name: 'Website', className:'website-name'},
            	          {key: "state",label: "State", name: 'State', className:'state-name'},
            	          {key: "country",label: "Country", name: 'Country', className:'country-name'},
                          {key: "phoneNumber",label: "Phone",name: 'Phone', className:'phone-no'},
                          {key: "faxNumber",label: "Fax",name: 'Fax', className:'fax-no'},
                          {key: "deleteInd", name: 'DeleteInd', className: 'hidden-col'},		                                
                          {label:"Options", formatter:optionFormatter,allowHTML:true, className:'options'}
                      ];
var dataTableSchema = {
	  resultListLocator: "customerList",
      resultFields: [
          {key:"customerName"},
          {key:"customerId"},  
          {key:"lineOfBusiness"},
          {key:"primaryContactPerson"},
          {key:"website"},
          {key:"state"},
          {key:"country"},
          {key:"phoneNumber"},
          {key:"faxNumber"},
          {key:"deleteInd"}              
       ]
      };

function getListDetails() {
	var isGetDetails = '${datasource}';
		if(isGetDetails != ""){
			getCustomerDetails();
		}else{
			YUI().use("node",function(Y){
				Y.one("#formDiv").setStyle("display","block");
			});
		}
}

/**
 * This method is used to call the list of customer information
 */
function getCustomerDetails() {
	YUI().use("datasource-io","datatable-message","datasource-jsonschema", "datatable-datasource", function(Y) {
		Y.one("#Datatable").setHTML("");
		Y.one("#formDiv").setStyle("display","none");
		Y.one("#viewCustomer").setStyle("display","none");
		Y.one("#dataTableContent").setStyle("display","block");
		Y.one("#errorMessage").setStyle("display","none");
		Y.one("#successMessage").setStyle("display","none");
		var config = {
			id: "#Datatable",
			dataSrcUrl :  "/ems-web/ems/customer/get",
			dataSrcQueryStr: "",
			dataSrcMethod: 'POST',
			tableColumns : dataTableColumns,
			dataTableSchema: dataTableSchema
		};
		dataTable = DataTable(config);
	});
}

/**
 * This method is used to call the view customer information
 */
function viewcustomer(customerID) {
	YUI().use('io-base', 'node', "json-parse","io", function(Y) {
		Y.one("#loadingImage").setStyle("display","block");
		var queryStr = 'customerId='+customerID;
		var cfg = {
			method: 'POST',
			data: queryStr,
			on: {
				success: function (id, o) {
					var response = Y.JSON.parse(o.responseText);
					if (response.customer != "undefined") {
						customerViewModel.setCustomerValues(response.customer);
						Y.one("#formDiv").setStyle("display","none");
						Y.one("#viewCustomer").setStyle("display","block");
						Y.one("#dataTableContent").setStyle("display","none");
						Y.one("#errorMessage").setStyle("display","none");
						Y.one("#successMessage").setStyle("display","none");
						Y.one("#loadingImage").setStyle("display","none");
					}
				}
			}
		};
		Y.io("/ems-web/ems/customer/edit", cfg);
	});
}

/**
 * This method is used to call the edit customer information
 */
function editcustomer(customerID) {
	customerViewModel.setCustomerValues(intialCustomerInfo);
	viewModel.errors.showAllMessages(false); 
	YUI().use('io-base', 'node', "json-parse","io", function(Y) {
		Y.one("#loadingImage").setStyle("display","block");
		var queryStr = 'customerId='+customerID;
		var cfg = {
			method: 'POST',
			data: queryStr,
			on: {
				success: function (id, o) {
					var response = Y.JSON.parse(o.responseText);
					if (response.customer != "undefined") {
						customerViewModel.setCustomerValues(response.customer);
						Y.one("#formDiv").setStyle("display","block");
						Y.one("#viewCustomer").setStyle("display","none");
						Y.one("#dataTableContent").setStyle("display","none");
						Y.one("#errorMessage").setStyle("display","none");
						Y.one("#successMessage").setStyle("display","none");
						Y.one("#loadingImage").setStyle("display","none");
						Y.one("#customerName").set("disabled",true); 
					}
				}
			}
		};
		Y.io("/ems-web/ems/customer/edit", cfg);
	});
}

/**
 * This method is used to call the delete customer information
 */
function deletecustomer(customerID,deleteId,customerName) {
	if(deleteId=='Y'){
		var answer = confirm("Please confirm to delete "+customerName+" customer");
		if (!answer) {
			return false;
		}
		YUI().use('io-base', 'node', "json-parse","io", function(Y) {
			Y.one("#loadingImage").setStyle("display","block");
			var queryStr = 'customerId='+customerID;
			var cfg = {
				method: 'POST',
				data: queryStr,
				on: {
					success: function (id, o) {
						var response = Y.JSON.parse(o.responseText);
						if (response.success == true || response.success == "true") {
							Y.one("#loadingImage").setStyle("display","none");
							dataTable.datasource.load({request: ''});
							Y.one("#successMessage").setHTML("<b>"+customerName+"</b> deleted successfully").setStyle("display","block");
							Y.one('#errorMessage').setStyle("display","none");
						}
					}
				}
			};
			Y.io("/ems-web/ems/customer/delete", cfg);
		});
	}
	else {
			YUI().use('event', 'node', 'tabview', function (Y) {
			Y.one("#successMessage").setStyle("display","none");
			Y.one("#errorMessage").setHTML("Unable to delete. Project exists for <b>"+customerName+"</b> customer").setStyle("display","block");
		});
	}	 
}

function Country(id,value){
	this.id = id;
	this.value = value;
};
function State(id,value){
	this.id = id;
	this.value = value;
};

var  viewModel = function() {
var self = this;
	self.customerDetails = {
		customerId : ko.observable("-1"),
		customerName :  ko.observable("").extend({ required: {message: 'Customer Name is required'}}),
		lineOfBusiness : ko.observable("").extend({ required: {message: 'Line Of Business is required'}}),
		primaryContactPerson : ko.observable("").extend({ required: {message: 'Primary Contact Person is required'}}),
		website:ko.observable("").extend({validateWebsite: true}),
		address : ko.observable("").extend({ required: {message: 'Address is required'}}),
		countryListArray : ko.observableArray([]),
		country : ko.observable().extend({required: {message: 'Country is required'}}),
		stateListArray : ko.observableArray([]),
		state : ko.observable().extend({required: {message: 'State is required'}}),
		phoneNumber :ko.observable("").extend({ required: {message: 'Phone Number is required'},number: true, minLength: 10}),
		faxNumber : ko.observable("").extend({ required: {message: 'Fax Number is required'},number: true, minLength: 10})
	};
	for (var j=0 ; j < country_arr.length ; j++) {
		self.customerDetails.countryListArray.push(new Country(country_arr[j],country_arr[j]));
	}

	self.customerDetails.country.subscribe(function(country) {
		for (var j=0 ; j < country_arr.length ; j++) {
			var state_arr = s_a[j+1].split("|");
			if(country_arr[j] == country){
				var stateList = state_arr;
				self.customerDetails.stateListArray.removeAll();
				for (var m=0; m < stateList.length ; m++) {
					self.customerDetails.stateListArray.push(new State(stateList[m],stateList[m]));
				}
			}
		}
	});		
	self.customerCountry = ko.observable();
	self.customerState = ko.observable();

    viewModel.errors = ko.validation.group(self.customerDetails,{deep: false});
    viewModel.errors.showAllMessages(false);  

	self.submit =function () {
		if (viewModel.errors().length == 0) {
			var customerInfo = "";
			YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
				Y.one("#loadingImage").setStyle("display","block");
				var form = Y.one('#customerForm');
				customerInfo = Y.QueryString.stringify(Y.Array.reduce(Y.one(form).all('input[name],select[name],textarea[name]')._nodes, {}, function (init, el, index, array) {                   
				init[el.name] = el.value;
				return init;					
				}));
				var cfg = {
					method: 'POST',
					data: customerInfo,
					on: {
						success: function (id, o) {
							if (o.responseText != null) {
								var responseText = Y.JSON.parse(o.responseText);
								getCustomerDetails();
								Y.one("#loadingImage").setStyle("display","none");
							}
						}
					}
				};
				Y.io("/ems-web/ems/customer/save", cfg);
			});
		} else {
			viewModel.errors.showAllMessages();            
		}
	};
	
	self.cancelAddCustomer = function() {
		getCustomerDetails();
	};
	
	self.setCustomerValues = function(response) {
		ko.mapping.fromJS(response, {}, self.customerDetails);
		ko.mapping.fromJS(response.country, {}, self.customerCountry);
		ko.mapping.fromJS(response.state, {},self.customerDetails.state);
	};
};
	ko.validation.rules['validateWebsite'] = {
		validator: function (val, errorMsg) {
			if (val == '' || val == null || val == undefined){
				return true;
			}
			if(val.match(/^(ht|f)tps?:\/\/[a-z0-9-\.]+\.[a-z]{2,4}\/?([^\s<>\#%"\,\{\}\\|\\\^\[\]`]+)?$/)){
				return true;
			}
		},
		message: 'Invalid website address.'
	};
	ko.validation.registerExtenders();

	customerViewModel = new viewModel();
	ko.applyBindings(customerViewModel);
</script>
</html>