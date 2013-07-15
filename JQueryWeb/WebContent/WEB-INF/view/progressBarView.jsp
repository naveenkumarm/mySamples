
<div id="dialog" title="Basic dialog" style="display: none;">
<p>This is an animated dialog.
By clicking this Progress is completed by 60%.The dialog window can be moved, 
resized and closed with the 'x' icon.Close this Dialog to attain 100% progress in demo.</p>
</div>
<br/>
<br/>
<br/>
<div id="progressbar"><div class="progress-label">Loading...</div></div>
<br/>
<span>Please check the Menu bar to view some progress in progress bar.</span>
<script type="text/javascript">

//progress bar initial workout.

 var progressbar = jq( "#progressbar" ),
progressLabel = jq( ".progress-label" );

jq("#progressbar").progressbar({
	value : 40,
	 change: function() {
		 progressLabel.text( progressbar.progressbar( "value" ) + "%" );
		 },
		 complete: function() {
		 progressLabel.text( "Complete!" );
		 }
});


	function progress(finalValue, incInd) {
		var val = progressbar.progressbar("value") || 0;
		if (incInd) {
			if (val < finalValue) {
				progressbar.progressbar("value", val + 1);
				setTimeout(function(){progress(finalValue,incInd)}, 100);
			}
		} else {
			progressbar.progressbar("value", val - 1);
			if (val > finalValue) {
				setTimeout(function(){progress(finalValue,incInd)}, 100);
			}
		}
	}

	jq("#opener").click(function() {

		jq('#tabs').tabs('select', '#progressBar');
		
		progress(60,true);
		
		jq("#dialog").dialog({
			modal : true,
			autoOpen : true,
			height : 255,
			width : 300,
			beforeClose : function(event, ui) {
				progress(100,true);
				jq("#opener").hide();
			},
			show : {
				effect : "blind",
				duration : 1000
			},
			hide : {
				effect : "explode",
				duration : 1000
			}
		});
	});

	jq("#inc50").click(function() { jq('#tabs').tabs('select', '#progressBar'); progress(50,true); });

	jq("#inc100").click(function() {jq('#tabs').tabs('select', '#progressBar'); progress(100,true); });

	jq("#decZero").click(function() {jq('#tabs').tabs('select', '#progressBar');progress(0,false); });

</script>
