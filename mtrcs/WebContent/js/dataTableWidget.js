var DataTable = function(config) {
	 YUI().use("datasource-io","datatable-message",
	                    "datasource-jsonschema", "datatable-datasource", function(Y) {
	            var delegateInit = false;
	            var previousAjaxTxId = null;
	            var table  = null;
	              var dataSource = new Y.DataSource.IO({
	                    source: config.dataSrcUrl,
	                    ioConfig: {method: config.dataSrcMethod }
	              });
	              
	              /*
	               * DataSource listens for request/response events
	               * to abort request that is still active while next request is triggered
	               * mainly useful in tab view using one datatable.
	               */
	              dataSource.on('request', function(event) {
	                  var trans = Y.DataSource.Local.transactions[previousAjaxTxId];
	                  if(trans && trans.abort) {
	                      trans.abort();
	                  }
	                  previousAjaxTxId = event.tId;
	              });
	              
	              dataSource.on('response', function(event) {
	                  if(event.data.statusText === 'abort') {
	                      event.preventDefault();
	                  }
	              });
	                
	              if(config.scrollable) {
	               table = new Y.DataTable({
	                    columns:config.tableColumns,
	                    scrollable: "Y",
	                    height: "190px"
	                });
	              } else {
	                  table = new Y.DataTable({
	                      columns:config.tableColumns
	                  });
	              }

	                dataSource.plug(Y.Plugin.DataSourceJSONSchema, {
	                    schema: config.dataTableSchema
	                });

	                table.plug(Y.Plugin.DataTableDataSource, {
	                    datasource: dataSource,
	                    initialRequest: config.dataSrcQueryStr
	                    //cache: Y.CacheOffline,
	                    //expires: 600000, // 10 min.
	                    //max: 5
	                });
	            
	                /**
	                 * Event handlers
	                 */
	                dataSource.after("response", function(e) {
	                   
	                    //return if user moved away from the page
	                    if(!Y.one(config.id)) {
	                        return;
	                    }
	                    
	                    table.render(config.id);
	                    
	                    // Process meta data for patient
	                    if (config.handleDataReturnForPatient) {
	                        config.handleDataReturnForPatient(e.response, table);
	                    }
	                    
	                    // Process meta data
	                    if (config.handleDataReturnPayload) {
	                        if(e.response.meta.pageNumber != null && e.response.meta.pageNumber != undefined )
	                            paginator.setPage(parseInt(e.response.meta.pageNumber),true);
	                        config.handleDataReturnPayload(e.response, table);
	                    }
	                    //Initialize delegate
	                    if(!delegateInit) {
	                       // setTimeout(attachDelegates, 10);
	                    }
	                 });
	            
	            table.render(config.id);
	            table.showMessage('Loading...');
	            
	            Y.delegate('click', function(e) {
	                var target = e.currentTarget;
	                var model = this.getRecord(target);
	                var cellIndex = Y.Node.getDOMNode(target).cellIndex,
	                columnSet = this.get('columns');
	                recordSet = this.get('recordset');
	               var  column = this.getColumn(target);
	                var col = columnSet[cellIndex];
	                if(col.label == "Options"){
	                  	//alert(col.label); 
	                }     
	               
	            }, config.id, 'td', table); 
	            
	            return table;
	            
	        });
	        };