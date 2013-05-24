<% 
response.sendRedirect("login/showLogin.do");
%>

<%-- 
 
 <html>  
    <head>  
        <title>Raphael Play</title>  
        <script src="<%=request.getContextPath()%>/scripts/raphael-min.js" type="text/javascript"></script> 
        <style type="text/css">  
            #canvas_container {  
                width: 500px;  
                border: 1px solid #aaa;  
            }  
        </style>  
    </head>  
    <body>  
          
        <script> 
        window.onload = function() {  
        	
        	var paperWidth = 800;
        	var paperHeight = 800;
        	 
        	var paper = Raphael(0, 0, paperWidth, paperHeight);
        	
        	var lastPaperPenX = 0;
        	var lastPaperPenY = 0;
        	
        	var topMargin = 10;
        	var sideMargin = 10;
        	
        	 
        	
        	/**********draw the actual flow chart*/
        	
        	var drawingSideMarginFactor = 40;
        	var drawingStartTopMargin = 50;
        	var drawingSideMargin = sideMargin + drawingSideMarginFactor; 
        	
        	var nodeStartX = drawingSideMargin;
        	var nodeStartY = drawingStartTopMargin;
        	
        	var prevNodeType = 'box';
        	var prevNodeHeight = 0;
        	var prevNodeWidth = 0;
        	
        	var nodeHeight = 30;
        	var nodeWidth = 0;
        	var textAlignFactor = 50;
        	
        	
        	var defaultTxtBoxWidth = 400;
        	var defaultTxtBoxHeight = 30;
        	var oneRowHeight = 15;
        	
        	
        	//draw the outline
        	var rect = paper.rect(sideMargin, topMargin, (defaultTxtBoxWidth + drawingSideMargin + drawingSideMarginFactor), 630);
        	rect.attr({line: 'red'});
        	
        	
        	var msg = "[Prompt]\nWelcome Mr.Suresh Govindaraj";
        	var textWidth = msg.length* 7;
        	
        	if(textWidth < defaultTxtBoxWidth)
        		textWidth = defaultTxtBoxWidth;
        	 
        	nodeWidth = textWidth;
        	nodeHeight = defaultTxtBoxHeight;
        	
        	var prevNode = paper.rect(nodeStartX, nodeStartY, nodeWidth, defaultTxtBoxHeight);
        	var prevText = paper.text(nodeWidth/2 + textAlignFactor, drawingStartTopMargin + (nodeHeight/2), msg);
        	  
        	prevText.attr(
        		{   
    	            fill: 'white'
    	        }   
        	);
        	
        	prevNode.attr(
       	        {  
       	            gradient: '90-#526c7a-#64a0c1',  
       	            stroke: '#3b4449',  
       	            'stroke-width': 1,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
       	    );  
        	
        	
        	//reset the coordinates for next node 
        	//prevNodeYEnd = drawingStartTopMargin + nodeHeight;
          	 
        	if(prevNodeType = 'box'){
        		prevNodeType = 'arrow';
        		nodeStartX = (nodeWidth/2) + nodeStartX;
        		nodeStartY = nodeHeight + nodeStartY;
        	} 
        	 
        	var arrowLineHeight = 70;
        	var arrowPointerDownLeftX = -5;
        	var arrowPointerDownLeftY = -10;
        	
        	var arrowPointerDownRightX = 5;
        	var arrowPointerDownRightY = -10;
        	
        	var arrowPath1 = paper.path("M "+nodeStartX+" "+nodeStartY+" l 0 "+arrowLineHeight+"");
        	var arrowPath2 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownLeftX+" "+arrowPointerDownLeftY+"");
        	var arrowPath3 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownRightX+" "+arrowPointerDownRightY+"");
        	 
        	arrowPath1.attr(  
       	        {  
       	            fill: 'gray',  
       	            stroke: 'gray',  
       	            'stroke-width': 2,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
       	    );  
        	
        	arrowPath2.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	arrowPath3.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	 
        	
        	if(prevNodeType = 'arrow'){
        		prevNodeType = 'box';
        		nodeStartX = drawingSideMargin;
        		nodeStartY = nodeStartY + arrowLineHeight;
        		nodeHeight = defaultTxtBoxHeight;
        	}
        	
        	
        	
        	msg = "[Question]\nAre You Planning to visit any of the foreign countries.\nPress One for U K ,Press 2 for U S A and Press 3 For Singapore";
        	textWidth = msg.length* 7;
        	
        	if(textWidth < defaultTxtBoxWidth)
        		textWidth = defaultTxtBoxWidth;
        	
        	
        	if(textWidth > defaultTxtBoxWidth) {
        		alert(textWidth)
        		var t = textWidth/defaultTxtBoxWidth;
        		if(t > 0){
        			nodeHeight = nodeHeight + (t * oneRowHeight);
        		}
        		else{
        			nodeHeight = nodeHeight + oneRowHeight;
        		}
        		
        		textWidth = defaultTxtBoxWidth;
        	}
        	
        	nodeWidth = textWidth;
        	prevNode = paper.rect(nodeStartX, nodeStartY, nodeWidth, nodeHeight);
        	prevText = paper.text(nodeWidth/2 + textAlignFactor, nodeStartY + (nodeHeight/2), msg);
        	
        	
        	prevText.attr(
           		{   
       	            fill: 'white'
       	        }
           	);
           	
           	prevNode.attr(
       	        {  
       	            fill: 'red',  
       	            stroke: '#3b4449',  
       	            'stroke-width': 1,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
        	);
           	
           	
           	
           	if(prevNodeType = 'box'){
        		prevNodeType = 'arrow';
        		nodeStartX = (nodeWidth/2) + nodeStartX;
        		nodeStartY = nodeHeight + nodeStartY;
        	} 
           	
           	
        	arrowPath1 = paper.path("M "+nodeStartX+" "+nodeStartY+" l 0 "+arrowLineHeight+"");
        	arrowPath2 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownLeftX+" "+arrowPointerDownLeftY+"");
        	arrowPath3 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownRightX+" "+arrowPointerDownRightY+"");
        	 
        	arrowPath1.attr(  
       	        {  
       	            fill: 'gray',  
       	            stroke: 'gray',  
       	            'stroke-width': 2,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
       	    );  
        	
        	arrowPath2.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	arrowPath3.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	
        	if(prevNodeType = 'arrow'){
        		prevNodeType = 'box';
        		nodeStartX = drawingSideMargin;
        		nodeStartY = nodeStartY + arrowLineHeight;
        		nodeHeight = defaultTxtBoxHeight;
        	}
        	 
        	
        	msg = "[Response]\n2";
        	textWidth = msg.length* 7;
        	
        	if(textWidth < defaultTxtBoxWidth)
        		textWidth = defaultTxtBoxWidth;
        	
        	
        	if(textWidth > defaultTxtBoxWidth) {
        		alert(textWidth)
        		var t = textWidth/defaultTxtBoxWidth;
        		if(t > 0){
        			nodeHeight = nodeHeight + (t * oneRowHeight);
        		}
        		else{
        			nodeHeight = nodeHeight + oneRowHeight;
        		}
        		
        		textWidth = defaultTxtBoxWidth;
        	}
        	
        	nodeWidth = textWidth;
        	prevNode = paper.rect(nodeStartX, nodeStartY, nodeWidth, nodeHeight);
        	prevText = paper.text(nodeWidth/2 + textAlignFactor, nodeStartY + (nodeHeight/2), msg);
        	
        	
        	prevText.attr(
           		{   
       	            fill: 'white'
       	        }
           	);
           	
           	prevNode.attr(
       	        {  
       	            fill: 'green',  
       	            stroke: '#3b4449',  
       	            'stroke-width': 1,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
        	);
           	
           	
         	
           	if(prevNodeType = 'box'){
        		prevNodeType = 'arrow';
        		nodeStartX = (nodeWidth/2) + nodeStartX;
        		nodeStartY = nodeHeight + nodeStartY;
        	} 
           	
           	
        	arrowPath1 = paper.path("M "+nodeStartX+" "+nodeStartY+" l 0 "+arrowLineHeight+"");
        	arrowPath2 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownLeftX+" "+arrowPointerDownLeftY+"");
        	arrowPath3 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownRightX+" "+arrowPointerDownRightY+"");
        	 
        	arrowPath1.attr(  
       	        {  
       	            fill: 'gray',  
       	            stroke: 'gray',  
       	            'stroke-width': 2,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
       	    );  
        	
        	arrowPath2.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	arrowPath3.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	
        	if(prevNodeType = 'arrow'){
        		prevNodeType = 'box';
        		nodeStartX = drawingSideMargin;
        		nodeStartY = nodeStartY + arrowLineHeight;
        		nodeHeight = defaultTxtBoxHeight;
        	}
        	 
        	
        	msg = "[Response]\nDo you plan to travel to U S A this year ?";
        	textWidth = msg.length* 7;
        	
        	if(textWidth < defaultTxtBoxWidth)
        		textWidth = defaultTxtBoxWidth;
        	
        	
        	if(textWidth > defaultTxtBoxWidth) {
        		alert(textWidth)
        		var t = textWidth/defaultTxtBoxWidth;
        		if(t > 0){
        			nodeHeight = nodeHeight + (t * oneRowHeight);
        		}
        		else{
        			nodeHeight = nodeHeight + oneRowHeight;
        		}
        		
        		textWidth = defaultTxtBoxWidth;
        	}
        	
        	nodeWidth = textWidth;
        	prevNode = paper.rect(nodeStartX, nodeStartY, nodeWidth, nodeHeight);
        	prevText = paper.text(nodeWidth/2 + textAlignFactor, nodeStartY + (nodeHeight/2), msg);
        	
        	
        	prevText.attr(
           		{   
       	            fill: 'white'
       	        }
           	);
           	
           	prevNode.attr(
       	        {  
       	            fill: 'red',  
       	            stroke: '#3b4449',  
       	            'stroke-width': 1,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
        	);
           	
        	if(prevNodeType = 'box'){
        		prevNodeType = 'arrow';
        		nodeStartX = (nodeWidth/2) + nodeStartX;
        		nodeStartY = nodeHeight + nodeStartY;
        	} 
           	
           	
        	arrowPath1 = paper.path("M "+nodeStartX+" "+nodeStartY+" l 0 "+arrowLineHeight+"");
        	arrowPath2 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownLeftX+" "+arrowPointerDownLeftY+"");
        	arrowPath3 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownRightX+" "+arrowPointerDownRightY+"");
        	 
        	arrowPath1.attr(  
       	        {  
       	            fill: 'gray',  
       	            stroke: 'gray',  
       	            'stroke-width': 2,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
       	    );  
        	
        	arrowPath2.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	arrowPath3.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	
        	if(prevNodeType = 'arrow'){
        		prevNodeType = 'box';
        		nodeStartX = drawingSideMargin;
        		nodeStartY = nodeStartY + arrowLineHeight;
        		nodeHeight = defaultTxtBoxHeight;
        	}
        	 
        	
        	msg = "[Response]\nYes";
        	textWidth = msg.length* 7;
        	
        	if(textWidth < defaultTxtBoxWidth)
        		textWidth = defaultTxtBoxWidth;
        	
        	
        	if(textWidth > defaultTxtBoxWidth) {
        		alert(textWidth)
        		var t = textWidth/defaultTxtBoxWidth;
        		if(t > 0){
        			nodeHeight = nodeHeight + (t * oneRowHeight);
        		}
        		else{
        			nodeHeight = nodeHeight + oneRowHeight;
        		}
        		
        		textWidth = defaultTxtBoxWidth;
        	}
        	
        	nodeWidth = textWidth;
        	prevNode = paper.rect(nodeStartX, nodeStartY, nodeWidth, nodeHeight);
        	prevText = paper.text(nodeWidth/2 + textAlignFactor, nodeStartY + (nodeHeight/2), msg);
        	
        	
        	prevText.attr(
           		{   
       	            fill: 'white'
       	        }
           	);
           	
           	prevNode.attr(
       	        {  
       	            fill: 'green',  
       	            stroke: '#3b4449',  
       	            'stroke-width': 1,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
        	);
           	
           	
        	if(prevNodeType = 'box'){
        		prevNodeType = 'arrow';
        		nodeStartX = (nodeWidth/2) + nodeStartX;
        		nodeStartY = nodeHeight + nodeStartY;
        	} 
           	
           	
        	arrowPath1 = paper.path("M "+nodeStartX+" "+nodeStartY+" l 0 "+arrowLineHeight+"");
        	arrowPath2 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownLeftX+" "+arrowPointerDownLeftY+"");
        	arrowPath3 = paper.path("M "+nodeStartX+" "+(nodeStartY + arrowLineHeight) +" l "+arrowPointerDownRightX+" "+arrowPointerDownRightY+"");
        	 
        	arrowPath1.attr(  
       	        {  
       	            fill: 'gray',  
       	            stroke: 'gray',  
       	            'stroke-width': 2,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
       	    );  
        	
        	arrowPath2.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	arrowPath3.attr(  
           	        {  
           	            gradient: 'gray',  
           	            stroke: 'gray',  
           	            'stroke-width': 2,  
           	            'stroke-linejoin': 'round',  
           	            rotation: -90  
           	        }  
           	    );
        	
        	
        	if(prevNodeType = 'arrow'){
        		prevNodeType = 'box';
        		nodeStartX = drawingSideMargin;
        		nodeStartY = nodeStartY + arrowLineHeight;
        		nodeHeight = defaultTxtBoxHeight;
        	}
        	 
        	
        	msg = "[Prompt]\nThanks for your time. Good bye";
        	textWidth = msg.length* 7;
        	
        	if(textWidth < defaultTxtBoxWidth)
        		textWidth = defaultTxtBoxWidth;
        	
        	
        	if(textWidth > defaultTxtBoxWidth) {
        		alert(textWidth)
        		var t = textWidth/defaultTxtBoxWidth;
        		if(t > 0){
        			nodeHeight = nodeHeight + (t * oneRowHeight);
        		}
        		else{
        			nodeHeight = nodeHeight + oneRowHeight;
        		}
        		
        		textWidth = defaultTxtBoxWidth;
        	}
        	
        	nodeWidth = textWidth;
        	prevNode = paper.rect(nodeStartX, nodeStartY, nodeWidth, nodeHeight);
        	prevText = paper.text(nodeWidth/2 + textAlignFactor, nodeStartY + (nodeHeight/2), msg);
        	
        	
        	prevText.attr(
           		{   
       	            fill: 'white'
       	        }
           	);
           	
           	prevNode.attr(
       	        {  
       	            gradient: '90-#526c7a-#64a0c1',  
       	            stroke: '#3b4449',  
       	            'stroke-width': 1,  
       	            'stroke-linejoin': 'round',  
       	            rotation: -90  
       	        }  
        	);
           	 
        }   --%>
        
        </script>
    
    </body>  
</html>  