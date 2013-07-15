<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="navigation">
	<ul id="nav">
		<li><a href="../patient/view">Home</a></li>
		<li><a class="hsubs" href="#">Tab Style</a>
			<ul class="subs">
				<li><a id="left" href="#">Left Align</a></li>
				<li><a id="top" href="#">Top Align</a></li>
				<li><a id="bottom" href="#">Bottom Align</a></li>
			</ul></li>
		<li><a id="switcher" class="hsubs" href="#"></a>
		<li><a class="hsubs" href="#">Tab</a>
			<ul class="subs">
				<li><a id="dataGridOption" href="#">Data Table</a></li>
				<li><a id="progressBarOption" href="#">Progress Bar</a></li>
				<li><a id="chartsOption" href="#">Charts</a></li>
				<li><a id="treeViewOption" href="#">TreeView</a></li>
				<li><a id="GridViewOption" href="#">Dynamic Grid</a></li>
			</ul></li>
			<li><a class="hsubs" id="progressBarOption" href="#">Progress Bar</a>
			<ul class="subs">
				<li><a id="inc50" href="#">Increment Progress upto 50%.</a></li>
				<li><a id="inc100" href="#">Increment Progress upto 100%.</a></li>
				<li><a id="decZero" href="#">Decrement Progress downto 0%</a></li>
				<li><a id="opener"  href="#">Open Animated dialog box</a></li>
			</ul></li>
		<li><a class="hsubs" href="#">Chart</a>
			<ul class="subs">
				<li><a id="lineChartButton" href="#">Line Chart</a></li>
				<li><a id="barChartButton" href="#">Bar Chart</a></li>
				<li><a id="stackedBarChartButton" href="#">Stacked Bar Chart</a></li>
				<li><a id="pieChartButton1" href="#">Pie Chart for Page Hits</a></li>
				<li><a id="pieChartButton2" href="#">Pie Chart for RSS Hits</a></li>
			</ul></li>
		<li><a href="#">TreeView</a></li>
		<div id="lavalamp"></div>
	</ul>

</div>
<div class="content-inner">
<div id="tabs" class="tabs-bottom">
	<ul>
		<li><a href="#patientTab">Data Table</a></li>
		<li><a href="#progressBar">Progress Bar</a></li>
		<li><a href="#chartsTab">Charts</a></li>
		<li><a href="#treeviewTab">TreeView</a></li>		
		<li><a href="#StreamingTab">Streaming Grid</a></li>		
	</ul>
	

	<div id="progressBar">
	<%@ include file="/WEB-INF/view/progressBarView.jsp" %>		
	</div>
	
	<div id="chartsTab">
		<%@ include file="/WEB-INF/view/chartView.jsp" %>	
	</div>
	
	<div id="treeviewTab">	
	<%@ include file="/WEB-INF/view/treeView.jsp" %>		
	</div>
	
	<div id="patientTab" style="margin-top: 5px;">
			<%@ include file="/WEB-INF/view/gridView.jsp" %>				
	</div>
	
	<div id="StreamingTab" style="margin-top: 5px;">
			<%@ include file="/WEB-INF/view/dynamicGridView.jsp" %>				
	</div>
</div>
</div>

<script type="text/javascript">
var mydata = null;

if (!window.WebSocket)
	alert("WebSocket not supported by this browser");

function $() {
	return document.getElementById(arguments[0]);
}
function $F() {
	return document.getElementById(arguments[0]).value;
}

function getKeyCode(ev) {
	if (window.event)
		return window.event.keyCode;
	return ev.keyCode;
}
/* $('joinB').onclick = function(event) {
	room.join('username');
	room.chat('phrase');
	return false;
}; */
jq("#joinB").button().click(function( event ) {
	room.join('username');
	room.chat('phrase');
	return false;
});

var room = {
	join : function(name) {
		this._username = name;
		//var location = document.location.toString().replace('http://',
		//		'ws://').replace('https://', 'wss://');
		var location = "ws://localhost:7070/"
		this._ws = new WebSocket(location);
		this._ws.onopen = this._onopen;
		this._ws.onmessage = this._onmessage;
		this._ws.onclose = this._onclose;
		this._ws.onerror = this._onerror;
	},

	chat : function(text) {
			room._send(text);
	},

	_onopen : function() {			
		$('join').className = 'hidden';
		$('joined').className = '';
		$('phrase').focus();
		room._send('has joined!');
	},

	_onmessage : function(m) {
		if (true) {
			console.log(m.data);
			mydata = jq.parseJSON(m.data);
			for(var i=0,j=mydata.length;i<j;i++){
				console.log(mydata[i].patientId);
				jq("#dynamicGrid").jqGrid('setCell',mydata[i].patientId,'age',mydata[i].age,'ui-state-default');
				//jq("#dynamicGrid").jqGrid('addRowData',i+1,mydata[i]);
				}
		}
	},

	_onclose : function(m) {
		  this._ws = null; 
	},

	_onerror : function(e) {
		alert(e);
	},
	
	_send : function(message) {
			this._ws.send(message);
	}
};

	jq(function() {

		//themeswitcher		  
   		jq("#switcher").themeswitcher({
   			imgpath: "images/",
   			loadTheme: 'Redmond'
   		});
   		if ( !jq.curCSS ) {
   			 jq.curCSS = jq.css;
   		}
		    		
		//tabs
		jq("#tabs").tabs({
			collapsible: false
		});

		jq( "#dataGridOption" ).click(function() {
			jq('#tabs').tabs('select', '#patientTab');
		 });

		jq( "#progressBarOption" ).click(function() {
			jq('#tabs').tabs('select', '#progressBar');
			 });
		 
		jq( "#chartsOption" ).click(function() {
			jq('#tabs').tabs('select', '#chartsTab');
			 });
		 
		jq( "#treeViewOption" ).click(function() {
			jq('#tabs').tabs('select', '#treeviewTab');
			 });

		jq( "#GridViewOption" ).click(function() {
			jq('#tabs').tabs('select', '#StreamingTab');
			 });
		 
		jq( "#bottom" ).click(function() {
			jq("#top").hide();
			 
			jq( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
		 	.removeClass( "ui-corner-all " )
		 	jq( "#tabs" ).tabs().removeClass( "ui-tabs-vertical ui-helper-clearfix" );
			jq( "#tabs li" ).switchClass( "ui-corner-top" , "ui-corner-bottom",1000 );
			jq( "#tabs li" ).switchClass( "ui-corner-left" , "ui-corner-bottom",1000 );
		 	// move the nav to the bottom
			 jq( ".tabs-bottom .ui-tabs-nav" ).appendTo( ".tabs-bottom" );
			 
		 });

		jq( "#left" ).click(function() {

			jq( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
			jq( "#tabs li" ).switchClass( "ui-corner-top" , "ui-corner-left",1000 );
			jq( "#tabs li" ).switchClass( "ui-corner-bottom" , "ui-corner-left",1000 );
			
		 });

		jq( "#top" ).click(function() {

			jq( "#tabs" ).tabs().removeClass( "ui-tabs-vertical ui-helper-clearfix " );
			jq( "#tabs li" ).switchClass( "ui-corner-bottom" , "ui-corner-top ",1000 );
			jq( "#tabs li" ).switchClass( "ui-corner-left" , "ui-corner-top ",1000 );
			jq( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
		 	.addClass( "ui-corner-all " );
		 });
	});
</script>



