<html>
    <head>
        <title>Use jsTree</title>
        <script>
            jq(document).ready(function(){
            	jq.jstree._themes = "/JQueryWeb/styles/jstree/themes/";
                jq("#treeViewDiv").jstree({
                    "json_data" : {
                        "data":[
                            {
                                "data" : "Search engines",
                                "children" :[
                                             {"data":"Yahoo", "metadata":{"href":"http://www.yahoo.com"}},
                                             {"data":"Bing", "metadata":{"href":"http://www.bing.com"}},
                                             {"data":"Google", "children":[{"data":"Youtube", "metadata":{"href":"http://youtube.com"}},{"data":"Gmail", "metadata":{"href":"http://www.gmail.com"}},{"data":"Orkut","metadata":{"href":"http://www.orkut.com"}}], "metadata" : {"href":"http://youtube.com"}}
                                            ],
                                "state" : "open"
                            },
                            {
                                "data" : "Networking sites",
                                "children" :[
                                    {"data":"Facebook", "metadata":{"href":"http://www.fb.com"}},
                                    {"data":"Twitter", "metadata":{"href":"http://twitter.com"}}
                                ]
                            }
                        ]
                    },
		    "themes" :
		    {
		    	"theme" : "apple",
    			"dots" : true,
    			"icons" : false
		    },
		    "unique" : {
				"error_callback" : function (n, p, f) {
					// alert("Duplicate node `" + n + "` with function `" + f + "`!");
				}
			},
		   /*  "core" : {
		        "animation" : 50
		    }, */
		    /*"types" :
		    {
			"default":
			{
				"icon":
				{
					"image":"http://static.jstree.com/v.1.0rc/_docs/_drive.png"
				}
			}
		    },*/
			
			"types" : {
    "valid_children" : [ "folder" ],
    "types" : {
        "folder" : {
            "valid_children" : [ "file" ],
            "icon" : { "image" : "/home/akshar/rajesh1.jpg"},
            "max_depth" : 1
        },

        "file" : {
            "valid_children" : [ "none" ],
            "icon" : { "image" : "/path/to/images/file.png" },
        }
    }
},		
                    "plugins" : [ "themes", "json_data", "ui", "types","crrm", "checkbox", "sort","hotkeys","dnd", "unique","contextmenu"]
                }).bind("select_node.jstree", function(e, data)
                    {
                        if(jQuery.data(data.rslt.obj[0], "href"))
                        {
                            window.location=jQuery.data(data.rslt.obj[0], "href");
                        }
                        else
                        {
                          //  alert("No href defined for this element");
                        }
                    }) ;              
                                                     
                jq("#toggle_dots , #toggle_icons").button().click(function ( event ) { 
            		jq("#treeViewDiv").jstree(this.value);
            	});                         
                 			
    			jq("#set_theme1").button().click(function( event ) {
    				jq("#treeViewDiv").jstree("set_theme","apple");
    			});
    			jq("#set_theme2").button().click(function( event ) {
    				jq("#treeViewDiv").jstree("set_theme","default");
    			});
    			jq("#create_1").button().click(function( event ) {
    				jq("#treeViewDiv").jstree("create"); 
    			});
    			jq("#remove_1").button().click(function( event ) {
    				jq("#treeViewDiv").jstree("remove"); 
    			});
    			jq("#rename_1").button().click(function( event ) {
    				jq("#treeViewDiv").jstree("rename"); 
    			});
                    
                	
                    /* jq("#treeViewDiv").jstree({ 
                		"themes" : {
                			"theme" : "default",
                			"dots" : false,
                			"icons" : false
                		},
                		"plugins" : [ "themes", "html_data" ]
                	}); 
 */                    
                   
            });
            
         
        </script>
      
        
    </head>
    <body>     
        <div id="treeViewDiv">     
        </div>
          <br/>             
        <div>
<button id="toggle_dots" >&nbsp;toggle_dots&nbsp;</button>
<button id="toggle_icons" >&nbsp;toggle_icons&nbsp;</button>
<button id="set_theme1" >&nbsp;set_theme1&nbsp;</button>
<button id="set_theme2" >&nbsp;set_theme2&nbsp;</button>
<button id="create_1" >&nbsp;create_1&nbsp;</button>
<button id="remove_1" >&nbsp;remove_1&nbsp;</button>
<button id="rename_1" >&nbsp;rename_1&nbsp;</button>


        </div>

    </body>
<script type="text/javscript">


</script>
</html>
