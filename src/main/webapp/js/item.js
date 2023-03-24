function  updatePrice()
{
    var sizes= document.getElementsByName('sizes');
    var size = null;
    // var prec = $('#p');
    for (var s of sizes){
        if (s.checked) {
            size=s.value;
            var perc = +$('#p'+size).val();
            var price = +$('#price').val();
            var  x = price + price * perc;
            var  discount = +$('#discount').val();
            if(discount>0)
            {
                var y  = x + x*(discount / 100);
                $('#discSpan').html('$'+y);

            }
            $('#orgPrice').html('$'+x);
        }
    }
}
