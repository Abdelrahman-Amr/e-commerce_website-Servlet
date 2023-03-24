

function addToCartSmall(event) {
    event.preventDefault();
    $.post("cart?pdId="+event.target.pdId.value+"&quantity=1&sizeName=Small&op=1", function(data, status){
        successCart("Added Product Successfully");
    });
}
function addToCart(event) {
    event.preventDefault();
    var sizes= document.getElementsByName('sizes');
    var size = null;
    for (var s of sizes){
        if (s.checked) {
            size=s.value;
            size=$('#n'+size).val();

        }
    }
    $.post("cart?pdId="+event.target.pdId.value+"&quantity=1&sizeName="+size+"&op=1", function(data, status){
        successCart("Added Product Successfully");
    });
}


function  removeFromCart(pdId,size)
{
            $('#r'+pdId+size).fadeOut('slow', function(c){
                $('#r'+pdId+size).remove();
            });
            $('#rr'+pdId+size).fadeOut('slow', function(c){
                $('#rr'+pdId+size).remove();
            });

}

function  decreaseQuan(pdId, size)
{
    var pdQuan = +$("#q"+pdId+size).html();
    var pdTotal = +$("#t"+pdId+size).html();
    var pdPrice = +$("#p"+pdId+size).html();
    var total=+$("#total").html();;


    if(pdQuan>=1) {
        $.post("cart?pdId=" + pdId + "&sizeName=" + size+"&op=-1", function (data, status) {
            $("#q"+pdId+size).html(pdQuan-1);
            $("#t"+pdId+size).html(pdTotal-pdPrice);
            $("#total").html(total-pdPrice);

            console.log(data);
            if(pdQuan-1 ==0) {
                removeFromCart(pdId,size);
                console.log(document.getElementsByClassName("rem1").length);
                if(document.getElementsByClassName("rem1").length<=1)
                {

                    window.location.href="cart";
                }
            }
        });
    }
}
function  increaseQuan(pdId, size) {
    var pdQuan = +$("#q"+pdId+size).html();
    var pdTotal = +$("#t"+pdId+size).html();
    var pdPrice = +$("#p"+pdId+size).html();
    var total=+$("#total").html();

    $.post("cart?pdId="+pdId+"&sizeName="+size+"&op=1", function(data, status){
        $("#q"+pdId+size).html(pdQuan+1);
        $("#t"+pdId+size).html(pdTotal+pdPrice);
        $("#total").text(total+pdPrice);


    });
}


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
