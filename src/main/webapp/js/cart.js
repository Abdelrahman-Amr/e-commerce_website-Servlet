

function addToCart(event ) {
    event.preventDefault();
    $.post("cart?pdId="+event.target.pdId.value+"&quantity=1&sizeName=Small&op=1", function(data, status){
        successCart("Added Product Successfully");
    });
}


function  removeFromCart()
{
        $('.close1').on('click', function(c){
            $('.rem1').fadeOut('slow', function(c){
                $('.rem1').remove();
            });
        });
}

function  decreaseQuan(pdId)
{
    // var pdId = $("#pdId").val();
    var pdQuan = +$("#pdQuan").val();
    var pdSize = $("#pdSize").val();

    if(pdQuan>0) {
        $.post("cart?pdId=" + pdId + "&sizeName=" + pdSize+"&op=-1", function (data, status) {
            // successCart("Added Product Successfully");
            $("#pdQuantity").html(pdQuan - 1);
            $("#pdQuan").val(pdQuan - 1);
        });
    }
}
function  increaseQuan(pdId) {
    // var pdId = $("#pdId").val();
    var pdQuan = +$("#pdQuan").val();
    var pdSize = $("#pdSize").val();

    $.post("cart?pdId="+pdId+"&sizeName="+pdSize+"&op=1", function(data, status){
        // successCart("Added Product Successfully");
        $("#pdQuantity").html(pdQuan+1);
        $("#pdQuan").val(pdQuan+1);
    });
}
//
// $.ajax({
//     url: "image",
//     type: 'POST',
//     data: formData,
//     success: function (data) {
//         console.log("hiiiiiiii");
//         alert(data);
//
//     }
// });
$()
$( '#form' ).submit(function ( e ) {

});
function  addProduct(event){
    event.preventDefault();
    var data;

    data = new FormData();
    data.append("name","abdo");
    data.append( 'file', document.getElementById("file").files[0] );
        // var file =document.getElementById("file")
    $.ajax({
        url: '../image',
        data: data,
        cache: false,
        processData: false,
        contentType: false,
        method:'POST',
        type: 'POST',
        success: function ( data ) {
            alert( data );
        }
    });

}




function uploadCallBack(responseTxt, statusTxt, xhr)
{
    console.log(xhr.status);
    if (statusTxt == "success" && responseTxt =="1" &&  xhr.status == 200){
        success("Logged in successfully");
    }else{
        failed('Invalid Email or Password !!');
    }
}
function  upload(event)
{

    event.preventDefault();
    console.log("prevent");
    var formData = new FormData(this);
    const image = document.getElementById("file").files[0];
    $.post("image", {name:"abdo", file:image},function(data, status){
        successCart("Added Product Successfully");
    });

}
function successCart(msg)
{
    Swal.fire({
        // position: 'top-end',
        icon: 'success',
        text:msg,
        title: 'Success',
        showConfirmButton: false,
        timer: 1500,
        toast:true,
        iconColor:'#663300'
    });
}

function failed(msg)
{
    Swal.fire({
        title: 'Failed',
        text:msg,
        icon: 'error',
        // showCancelButton: true,
        confirmButtonText: 'Ok',
        confirmButtonColor: '#25aae2',
        toast:true,
    });

}
