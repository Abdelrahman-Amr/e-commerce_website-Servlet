
function getPrevOrderList() {

    console.log("previous list");
    //var pageNo = document.getElementById("pageNo").value;
    //console.log(pageNo-10);
    $.post ("PreviewOrder?orderGoal=previous",

                    previewOrderCallBack).fail(function() {
                                      failed('loaded order List Failed !!');
                                  });

}

function getNextOrderList() {
    console.log("next list")
    //var page = document.getElementById("pageNo").value;
    //console.log(page+9)
        //console.log(${pageNo});
        $.post ("PreviewOrder?orderGoal=next",

            previewOrderCallBack).fail(function() {
                              failed('loaded order List Failed !!');
                          });
}
function previewOrderCallBack(responseTxt, statusTxt, xhr) {
   if (statusTxt == "success"){
    //window.location.href="#";
//    console.log(responseTxt);
    console.log("responseTxt");
    var json = JSON.parse(responseTxt);
    console.log(json);
    var orderList=json.orderList;
    drawTable(orderList);
    document.getElementById("orderPageNo").innerHTML=json.pageNo+" of "+json.pageCount;
    if(json.pageNo===json.pageCount) {
        document.getElementById("nextOrderBTN").disabled = true;
    } else {
        document.getElementById("nextOrderBTN").disabled = false;
    }

    if(json.pageNo===1) {
            document.getElementById("prevOrderBTN").disabled = true;
        } else {
            document.getElementById("prevOrderBTN").disabled = false;
        }
     //success("Updated Successfully");

     }else{
       //failed('Updated Failed !!');
     }
}
//
function drawTable(customerList) {
    console.log("draw table")
//    var tableBody = document.getElementById("tableBody").innerHTML;
//    tableBody="";
    document.getElementById("masterOrderTableBody").innerHTML="";
    for(let current of customerList) {
        console.log(current)
        document.getElementById("masterOrderTableBody").innerHTML += `
            <tr class="active-row">
                <td>${current.id}</td>
                <td>${current.customer}</td>
                <td>${current.date}</td>
                <td>${current.total}</td>
                <td><input type="button" value="Order Details" onclick="location.href='PreviewOrderDetails?orderId=${current.id}'"></td>
            </tr>
        `
    }
}
////    tableBody =
////        '<c:forEach items="${customerList}" var="current">' +
////                    '<tr class="active-row">' +
////                        '<td>${current.email}</td>' +
////                        '<td>${current.userName}</td>' +
////                        '<td>${current.address}</td>' +
////                        '<td>${current.phone}</td>' +
////                        '<td>${current.creditLimit}</td>' +
////                    '</tr>' +
////                '</c:forEach>';
//}