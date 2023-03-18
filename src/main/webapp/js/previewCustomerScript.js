
function getPrevCustomerList() {

    console.log("previous list");
    //var pageNo = document.getElementById("pageNo").value;
    //console.log(pageNo-10);
    $.post ("PreviewCustomer?goal=previous",

                    previewCustomerCallBack).fail(function() {
                                      failed('loaded Customer List Failed !!');
                                  });

}

function getNextCustomerList(page) {
    console.log("next list")
    //var page = document.getElementById("pageNo").value;
    //console.log(page+9)
        //console.log(${pageNo});
        $.post ("PreviewCustomer?goal=next",

            previewCustomerCallBack).fail(function() {
                              failed('loaded Customer List Failed !!');
                          });
}
function previewCustomerCallBack(responseTxt, statusTxt, xhr) {
   if (statusTxt == "success"){
    //window.location.href="#";
    //console.log(responseTxt);
    var json = JSON.parse(responseTxt);
    console.log(json);
    var CustomerList=json.customerDtoList;
    drawTable(CustomerList);
    document.getElementById("pageNo").innerHTML=json.pageNo+" of "+json.pageCount;
    if(json.pageNo===json.pageCount) {
        document.getElementById("nextBTN").disabled = true;
    } else {
        document.getElementById("nextBTN").disabled = false;
    }

    if(json.pageNo===1) {
            document.getElementById("prevBTN").disabled = true;
        } else {
            document.getElementById("prevBTN").disabled = false;
        }
     //success("Updated Successfully");

     }else{
       //failed('Updated Failed !!');
     }
}

function drawTable(customerList) {
    console.log("draw table")
    var tableBody = document.getElementById("tableBody").innerHTML;
    tableBody="";
    document.getElementById("tableBody").innerHTML="";
    for(let current of customerList) {
        console.log(current)
        document.getElementById("tableBody").innerHTML += `
            <tr class="active-row">
                <td>${current.email}</td>
                <td>${current.userName}</td>
                <td>${current.address}</td>
                <td>${current.phone}</td>
                <td>${current.creditLimit}</td>
            </tr>
        `
    }
//    tableBody =
//        '<c:forEach items="${customerList}" var="current">' +
//                    '<tr class="active-row">' +
//                        '<td>${current.email}</td>' +
//                        '<td>${current.userName}</td>' +
//                        '<td>${current.address}</td>' +
//                        '<td>${current.phone}</td>' +
//                        '<td>${current.creditLimit}</td>' +
//                    '</tr>' +
//                '</c:forEach>';
}