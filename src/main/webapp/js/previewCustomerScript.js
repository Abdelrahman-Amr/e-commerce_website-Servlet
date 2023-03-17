
function getPrevCustomerList() {

    console.log("previous list");
    var pageNo = document.getElementById("pageNo").value;
    console.log(pageNo-10);
    $.post ("PreviewCustomer",
        JSON.stringify({startInd:pageNo-10}) )

}

function getNextCustomerList(page) {
    console.log("next list")
    //var page = document.getElementById("pageNo").value;
    //console.log(page+9)
        //console.log(${pageNo});
        $.post ("PreviewCustomer",

            previewCustomerCallBack).fail(function() {
                              failed('loaded Customer List Failed !!');
                          });
}
function previewCustomerCallBack(responseTxt, statusTxt, xhr) {
   if (statusTxt == "success" && responseTxt =="1"){
    //window.location.href="#";
    drawTable();
     //success("Updated Successfully");

     }else{
       //failed('Updated Failed !!');
     }
}

function drawTable() {
    var tableBody = document.getElementById("tableBody").innerHTML;
    tableBody =
        '<c:forEach items="${customerList}" var="current">' +
                    '<tr class="active-row">' +
                        '<td>${current.email}</td>' +
                        '<td>${current.userName}</td>' +
                        '<td>${current.address}</td>' +
                        '<td>${current.phone}</td>' +
                        '<td>${current.creditLimit}</td>' +
                    '</tr>' +
                '</c:forEach>';

}