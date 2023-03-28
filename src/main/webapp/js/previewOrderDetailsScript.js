
function getPrevOrderDetailsList() {

    console.log("previous list");
    //var pageNo = document.getElementById("pageNo").value;
    //console.log(pageNo-10);
    $.post ("PreviewOrderDetails?orderDetailGoal=previous",

                    previewOrderDetailCallBack).fail(function() {
                                      failed('loaded order Details List Failed !!');
                                  });

}

function getNextOrderDetailsList() {
    console.log("next list")
    //var page = document.getElementById("pageNo").value;
    //console.log(page+9)
        //console.log(${pageNo});
        $.post ("PreviewOrderDetails?orderDetailGoal=next",

            previewOrderDetailCallBack).fail(function() {
                              failed('loaded order Details List Failed !!');
                          });
}
function previewOrderDetailCallBack(responseTxt, statusTxt, xhr) {
   if (statusTxt == "success"){
    //window.location.href="#";
//    console.log(responseTxt);
    console.log("responseTxt");
    if(responseTxt==="1") {
        document.getElementById("nextOrderDetailBTN").disabled = true;
        document.getElementById("prevOrderDetailBTN").disabled = true;

    } else {
    var json = JSON.parse(responseTxt);
    console.log(json);
    var orderDetailList=json.orderDetailList;
    drawOrderDetailsTable(orderDetailList);
    document.getElementById("pageNoOrderDetail").innerHTML=json.pageNo+" of "+json.pageCount;
    if(json.pageNo===json.pageCount) {
        document.getElementById("nextOrderDetailBTN").disabled = true;
    } else {
        document.getElementById("nextOrderDetailBTN").disabled = false;
    }

    if(json.pageNo===1) {
            document.getElementById("prevOrderDetailBTN").disabled = true;
        } else {
            document.getElementById("prevOrderDetailBTN").disabled = false;
        }
     //success("Updated Successfully");

     }
     }else{
       failed('loading order details Failed !!');
     }

}
//
function drawOrderDetailsTable(OrderDetailList) {
    console.log("draw table")
//    var tableBody = document.getElementById("tableBody").innerHTML;
//    tableBody="";
    document.getElementById("detailOrderTableBody").innerHTML="";
    for(let current of OrderDetailList) {
        console.log(current)
        document.getElementById("detailOrderTableBody").innerHTML += `
            <tr class="active-row">
                <td>${current.product}</td>
                <td>${current.category}</td>
                <td>${current.size}</td>
                <td>${current.quantity}</td>
                <td>${current.price}</td>
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