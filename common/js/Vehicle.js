$(document).ready(function(){
    var OPT = {
        Cols:[
            { Header: "", Name: "id", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "status", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "registrationNumber", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "make", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "model", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "year", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "driverDistance", Type: "Text", Width:80, RelWidth:1},
       ]
   };

   IBSheet.create({
       id:"sheet",
       el:"sheet_DIV",
       options:OPT
   });
});

function retrieve(){
    fetch("/vehicles", {
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
                    url: "/vehicles",
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
                    url: `/vehicles/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/vehicles/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}