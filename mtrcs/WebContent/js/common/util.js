DataTable = function(config){
	 var table  = null;
	 YUI().use("datasource-io","datatable-message","datasource-jsonschema", "datatable-datasource", function(Y) {
	            var delegateInit = false;
	            var previousAjaxTxId = null;
	           
	              var dataSource = new Y.DataSource.IO({
	                    source: config.dataSrcUrl,
	                    ioConfig: {method: config.dataSrcMethod }
	              });
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
	                dataSource.after("response", function(e) {
	                    if(!Y.one(config.id)) {
	                        return;
	                    }	                    
	                    table.render(config.id);
	                    if (config.handleDataReturnForPatient) {
	                        config.handleDataReturnForPatient(e.response, table);
	                    }
	                    if (config.handleDataReturnPayload) {
	                        if(e.response.meta.pageNumber != null && e.response.meta.pageNumber != undefined )
	                            paginator.setPage(parseInt(e.response.meta.pageNumber),true);
	                        config.handleDataReturnPayload(e.response, table);
	                    }	                    
	                    if(!delegateInit) {
// 	                        setTimeout(attachDelegates, 10);
	                    }
	                 });	            
	            table.render(config.id);
	            table.showMessage('Loading...');
	        });
	 return table;
}
AutoComplete = function(config){
	YUI().use('node','node-core','io-base','json-parse','json-stringify','event','autocomplete', 'autocomplete-highlighters', function(Y){
	var minLength = 1;
	if(Y.Lang.isValue(config.minLength)){
		minLength = config.minLength;
	}

    var input = Y.one('#'+config.inputId).plug(Y.Plugin.AutoComplete, {
    	resultHighlighter: 'phraseMatch',
    	resultListLocator: config.resultsLocator,
    	resultTextLocator: config.textLocatorCallback,
    	maxResults: config.maxResults,
    	minQueryLength: minLength,
    	queryDelay: 300			    	
    });
    //Check for a request Template
	if(Y.Lang.isValue(config.requestTemplate)){
		input.ac.set('requestTemplate', config.requestTemplate);
		input.ac.set('source', config.queryUrl);
	}
	else{
		input.ac.set('source', config.queryUrl + '{query}');
	}
    
    if(Y.Lang.isValue(config.beforeSearchCallback)){
    	input.ac.before('query', config.beforeSearchCallback);
    }
    if(Y.Lang.isValue(config.onSelectCallback)){
    	input.ac.on('select', config.onSelectCallback);
    }
    if(Y.Lang.isValue(config.afterSelectCallback)){
    	input.ac.after('select', config.afterSelectCallback);
    }
 return input;
	});

}