var checkLists;
var employeeIds;
var employeeRoles; 

//Create business logic in a YUI sandbox using the 'io' and 'json' modules
YUI().use("node", "io", "dump", "json-parse",function (Y) {
    var callback = {
        timeout : 3000,
        sync:true,
        on : {
            success : function (x,o) {
             
                try {
                    messages = Y.JSON.parse(o.responseText);
                    employeeIds = messages.employeeIds;
                    employeeRoles = messages.employeeRoles;
                }
                catch (e) {
                    alert("JSON Parse failed!");
                    return;
                }
            
            },

            failure : function (x,o) {
                alert("Async call failed!");
            }

        }
    };
Y.io("lookup", callback);

});