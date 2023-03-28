
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
        if(responseTxt=="1") {
            document.getElementById("nextCustomerBTN").disabled = true;
            document.getElementById("prevCustomerBTN").disabled = true;

        } else {

            var json = JSON.parse(responseTxt);
            console.log(json);
            var CustomerList=json.customerDtoList;
            drawCustomerTable(CustomerList);
            document.getElementById("customerPageNo").innerHTML=json.pageNo+" of "+json.pageCount;
            if(json.pageNo===json.pageCount) {
                document.getElementById("nextCustomerBTN").disabled = true;
            } else {
                document.getElementById("nextCustomerBTN").disabled = false;
            }

            if(json.pageNo===1) {
                    document.getElementById("prevCustomerBTN").disabled = true;
                } else {
                    document.getElementById("prevCustomerBTN").disabled = false;
                }
             //success("Updated Successfully");
        }
     }else{
       //failed('Updated Failed !!');
     }
}

function drawCustomerTable(customerList) {
    console.log("draw table")
//    var tableBody = document.getElementById("tableBody").innerHTML;
//    tableBody="";
    document.getElementById("customerTableBody").innerHTML="";
    for(let current of customerList) {
        console.log(current)
        document.getElementById("customerTableBody").innerHTML += `
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