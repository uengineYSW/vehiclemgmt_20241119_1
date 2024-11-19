$(document).ready(function(){
    var OPT = {
        Cols:[
            { Header: "등록자 정보", Name: "registrationId", Type: "Text", Width:80, RelWidth:1},
            { Header: "차량번호", Name: "vehicleNumber", Type: "Text", Width:80, RelWidth:1},
            { Header: "등록일자", Name: "registrationDate", Type: "Text", Width:80, RelWidth:1},
            { Header: "출발지", Name: "departure", Type: "Text", Width:80, RelWidth:1},
            { Header: "출발시간", Name: "departureTime", Type: "Text", Width:80, RelWidth:1},
            { Header: "운행전 누적 km", Name: "accumulatedDistanceBefore", Type: "Text", Width:80, RelWidth:1},
            { Header: "도착지", Name: "destination", Type: "Text", Width:80, RelWidth:1},
            { Header: "도착시간", Name: "arrivalTime", Type: "Text", Width:80, RelWidth:1},
            { Header: "운행 후 누적 km", Name: "accumulatedDistanceAfter", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "drivingDistance", Type: "Text", Width:80, RelWidth:1},
            { Header: "", Name: "purpose", Type: "Text", Width:80, RelWidth:1},
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
    fetch("/vehiclePerformances", {
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
                    url: "/vehiclePerformances",
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
                    url: `/vehiclePerformances/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/vehiclePerformances/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}