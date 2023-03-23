

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
