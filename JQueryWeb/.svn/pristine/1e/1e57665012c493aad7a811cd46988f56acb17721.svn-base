<div>
<table id="dynamicGrid"></table>
<div id="pager1"></div>
<br/>
<button id="joinB"> &nbsp;Start Streaming &nbsp;</button>
<br/>
</div>
		<script type="text/javascript">
		jq(function() {
		jq("#dynamicGrid").jqGrid({
			 url:'../patient/list',
			  datatype: "json", 
			  colNames:['PatientID','FirstName','LastName', 'Age', 'Location'], 
			  colModel:[
				   		{name:'patientId',index:'patientId', width:55},
				   		{name:'firstName',index:'firstName', width:200},
				   		{name:'lastName',index:'lastName', width:200},
				   		{name:'age',index:'age', width:100},
				   		{name:'location',index:'location', width:200}
				   	],
			 rowNum:10, 
			 rowList:[1,5,10,20,30],
			  pager: '#pager1',
			    viewrecords: true,
			    emptyrecords: "Empty records",
			    loadonce: true,
			    height : '100%',
			    shrinkToFit: true,
			    sortable : true,
			    jsonReader : {
			        root: "Results",
			        page: "page",
			        total: "total",
			        records: "rows",
			        repeatitems: false,
			        cell: "cell",
			        id: "patientId"
			    },
				 sortname: 'firstName',
			     sortorder: "asc", 
			     caption:"Dynamic Patient List" 
				     }); 
		}); 
		jq("#grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false});
</script>  