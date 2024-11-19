$(document).ready(function(){
    var OPT = {
        Cols:[
            { Header: "", Name: "id", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "requesterName", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "organization", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "employeeNumber", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "officeNumber", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "mobileNumber", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "requestDate", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "approverInfo", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "approverPosition", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "usagePurpose", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "numberOfPassengers", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "routeSetting", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "remarks", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "passengerContact", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "attachedDocuments", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "cancellationReason", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "usageCategory", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "carType", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "mainDepartment", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "operationSection", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "operationType", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "includeDriver", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "progressStage", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "period", Type: "Text", Width:80, RelWidth:1},
       ]
   };

   IBSheet.create({
       id:"sheet",
       el:"sheet_DIV",
       options:OPT
   });
});

function retrieve(){
    fetch("/carAssignments", {
        method: 'GET',
        headers: {
            "Cache-Control": "no-cache",
            "Pragma": "no-cache",
            "Content-Type": "application/json"
        }
    }).then(res => {
        return res.json();
    }).then(json => {
        sheet.loadSearchData(json)
    }).catch(error => {
        console.error("에러", error);
    });
}

function addData(){
   sheet.addRow();
}

function deleteData(){
    sheet.deleteRow(sheet.getFocusedRow());
}

function save(){
    var rows = sheet.getSaveJson()?.data;

    for(var i=0; i<rows.length;i++){
        switch(rows[i].STATUS){
            case "Added":
                var saveRow = rows[i];
                $.ajax({
                    url: "/carAssignments",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(saveRow)
                });
                break;
            case "Changed":
                var rowObj = sheet.getRowById(rows[i].id);
                var changedData = JSON.parse(sheet.getChangedData(rowObj))["Changes"][0];
                var id = rows[i].seq;
                $.ajax({
                    url: `/carAssignments/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/carAssignments/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}