

function getPriorityProducts()
{
    $.post("home?sel=1", priorityCallback);
}
function getMostSellingProducts()
{
    $.post("home?sel=2", mostSellingCallback);
}

function priorityCallback(responseTxt, statusTxt, xhr) {
    if (statusTxt == "success" && xhr.status == 200) {

        var products = responseTxt;
        for (let i = 0; i < products.length; i++) {
            $('#nn'+products[i].id).html(products[i].name);
            $('#ii'+products[i].id).attr("src",'image?imgName='+products[i].imageUrl);
            $('#pp'+products[i].id).html(products[i].price);
            if (products[i].discount > 0) {
                var p= products[i].price + products[i].price* (products[i].discount/100);
                $('#dd'+products[i].id).html('$'+p);
            }

        }

    }

}

function mostSellingCallback(responseTxt, statusTxt, xhr) {
    // window.location.href-""
    if (statusTxt == "success" && xhr.status == 200) {

        var products = responseTxt;
        console.log(products);
        for (let i = 0; i < products.length; i++) {
            $('#n'+products[i].id).html(products[i].name);
            $('#i'+products[i].id).attr("src",'image?imgName='+products[i].imageUrl);
            $('#p'+products[i].id).html(products[i].price);
            if (products[i].discount > 0) {
                var p= products[i].price + products[i].price* (products[i].discount/100);
                $('#d'+products[i].id).html('$'+p);
            }

        }

    }

}


