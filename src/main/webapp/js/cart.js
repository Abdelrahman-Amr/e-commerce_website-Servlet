

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

function  order()
{
    var isLogin  = $('#isLogin').val();
    if(!isLogin || isLogin=="false"){
        failedToAdd("Please Login First!");

    }else {
        var credit = null;
        const total = +$('#total').html();
        var balance = +$('#creditVal').val();
        var payment= document.getElementsByName('payment');
        for (var p of payment){
            if (p.checked) {
                credit=p.value;
            }
        }
        // console.log($('#credit').checked,credit,total);
        if(credit==1 && balance< total)
        {
            failedCredit("You don't have enough credit!");

        }
    }

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
        iconColor:'#663300',

    });
}

function failedToAdd(msg)
{
    Swal.fire({
        title: 'Failed',
        text:msg,
        icon: 'error',
        // showCancelButton: true,
        confirmButtonText: 'Ok',
        confirmButtonColor: '#25aae2',
        toast:true
    }).then(function (){
        console.log("yes");
        sessionStorage.setItem("toCart","true");
        window.location.href="login";

    });

}
function failedCredit(msg)
{
    Swal.fire({
        title: 'Failed',
        text:msg,
        icon: 'error',
        // showCancelButton: true,
        confirmButtonText: 'Ok',
        confirmButtonColor: '#25aae2',
        toast:true
    });

}
