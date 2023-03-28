//
// function getPriorityProducts()
// {
//     $.post("home?sel=1", priorityCallback);
// }
// function getMostSellingProducts()
// {
//     $.post("home?sel=2", mostSellingCallback);
// }
//
// function priorityCallback(responseTxt, statusTxt, xhr) {
//     if (statusTxt == "success" && xhr.status == 200) {
//         var products = responseTxt
//         // console.log(products[0]);
//         var x = "";
//         for (let i = 0; i < products.length; i++) {
//             if (products[i].discount > 0) {
//                 var disc = products[i].price + products[i].price * (products[i].discount / 100);
//                 x += "            <a href='item?productId=${prod.id}\'><div class=\"col-md-4 top_brand_left\">\n" +
//                     "                                <div class=\"hover14 column\">\n" +
//                     "                                    <div class=\"agile_top_brand_left_grid\">\n" +
//                     "                                        <div class=\"agile_top_brand_left_grid_pos\">\n" +
//                     "                                            <img src=\"images/offer.png\" alt=\" \" class=\"img-responsive\"/>\n" +
//                     "                                        </div>\n" +
//                     "                                        <div class=\"agile_top_brand_left_grid1\">\n" +
//                     "                                            <figure>\n" +
//                     "                                                <div class=\"snipcart-item block\">\n" +
//                     "                                                    <div class=\"snipcart-thumb\">\n" +
//                     "                                                        <a href=\'item?productId="+products[i].id+"'><img title=\" \" alt=\" \"\n" +
//                     "                                                                                    src=\"image?imgName=" + products[i].imageUrl + "\"/></a>\n" +
//                     "                                                        <h4>" + products[i].name + "</h4>  <br>\n" +
//                     "                                                        <h4>$" + products[i].price + "<span>$" + disc + "</span></h4>\n" +
//                     "                                                    </div>\n" +
//                     "                                                    <div class=\"snipcart-details top_brand_home_details\">\n" +
//                     "                                                         <form  method=\"post\" action=\"cart\" onsubmit=\"addToCartSmall(event)\">\n" +
//                     "                                                            <fieldset>\n" +
//                     "                                                                <input type=\"hidden\" name=\"cmd\" value=\"_cart\"/>\n" +
//                     "                                                                 <input type='hidden' name='pdId' value='"+products[i].id+"'>\n"+
//                     "                                                                 <input type='hidden' name=\"item_name\" value='"+products[i].name+"'>\n"+
//                     "                                                                <input type=\"hidden\" name=\"add\" value=\"1\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"business\" value=\" \"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"item_name\"\n" +
//                     "                                                                       value=\"Fortune Sunflower Oil\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"amount\" value=\"35.99\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"discount_amount\"\n" +
//                     "                                                                       value=\"1.00\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"currency_code\" value=\"USD\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"return\" value=\" \"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"cancel_return\" value=\" \"/>\n" +
//                     "                                                                <input type=\"submit\" name=\"submit\" value=\"Add to cart\"\n" +
//                     "                                                                       class=\"button\"/>\n" +
//                     "                                                            </fieldset>\n" +
//                     "                                                        </form>\n" +
//                     "                                                    </div>\n" +
//                     "                                                </div>\n" +
//                     "                                            </figure>\n" +
//                     "                                        </div>\n" +
//                     "                                    </div>\n" +
//                     "                                </div>\n" +
//                     "                            </div></a>";
//
//             } else {
//                 x += "<div class=\"col-md-4 top_brand_left\">\n" +
//                     "                                <div class=\"hover14 column\">\n" +
//                     "                                    <div class=\"agile_top_brand_left_grid\">\n" +
//                     "                                        <div class=\"agile_top_brand_left_grid1\">\n" +
//                     "                                            <figure>\n" +
//                     "                                                <div class=\"snipcart-item block\">\n" +
//                     "                                                    <div class=\"snipcart-thumb\">\n" +
//                     "                                                        <a href=\'item?productId="+products[i].id+"'><img title=\" \" alt=\" \"\n" +
//                     "                                                                                    src=\"image?imgName=" + products[i].imageUrl + "\"/></a>\n" +
//                     "                                                        <h4>" + products[i].name + "</h4>  <br>\n" +
//
//                     "                                                        <h4>$" + products[i].price + "</h4>\n" +
//                     "                                                    </div>\n" +
//                     "                                                    <div class=\"snipcart-details top_brand_home_details\">\n" +
//                     "                                                        <form  method=\"post\" action=\"cart\" onsubmit=\"addToCartSmall(event)\">\n" +
//                     "                                                            <fieldset>\n" +
//                     "                                                                <input type=\"hidden\" name=\"cmd\" value=\"_cart\"/>\n" +
//                     "                                                                 <input type='hidden' name='pdId' value='"+products[i].id+"'>\n"+
//                     "                                                                 <input type='hidden' name=\"item_name\" value='"+products[i].name+"'>\n"+
//                     "                                                                <input type=\"hidden\" name=\"add\" value=\"1\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"business\" value=\" \"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"item_name\"\n" +
//                     "                                                                       value=\"Fortune Sunflower Oil\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"amount\" value=\"35.99\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"discount_amount\"\n" +
//                     "                                                                       value=\"1.00\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"currency_code\" value=\"USD\"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"return\" value=\" \"/>\n" +
//                     "                                                                <input type=\"hidden\" name=\"cancel_return\" value=\" \"/>\n" +
//                     "                                                                <input type=\"submit\" name=\"submit\" value=\"Add to cart\"\n" +
//                     "                                                                       class=\"button\"/>\n" +
//                     "                                                            </fieldset>\n" +
//                     "                                                        </form>\n" +
//                     "                                                    </div>\n" +
//                     "                                                </div>\n" +
//                     "                                            </figure>\n" +
//                     "                                        </div>\n" +
//                     "                                    </div>\n" +
//                     "                                </div>\n" +
//                     "                            </div>";
//             }
//             console.log("hi");
//         }
//         $('#adList').html(x + " <div class='clearfix'></div>");
//
//     }
// }
//
//     function mostSellingCallback(responseTxt, statusTxt, xhr) {
//         // window.location.href-""
//         if (statusTxt == "success" && xhr.status == 200) {
//             var products = responseTxt
//             console.log(products);
//             var x = "";
//             for (let i = 0; i < products.length; i++) {
//                 if (products[i].discount > 0) {
//                     var disc = products[i].price + products[i].price * (products[i].discount / 100);
//                     x += "<div class=\"col-md-4 top_brand_left\">\n" +
//                         "                                <div class=\"hover14 column\">\n" +
//                         "                                    <div class=\"agile_top_brand_left_grid\">\n" +
//                         "                                        <div class=\"agile_top_brand_left_grid_pos\">\n" +
//                         "                                            <img src=\"images/offer.png\" alt=\" \" class=\"img-responsive\"/>\n" +
//                         "                                        </div>\n" +
//                         "                                        <div class=\"agile_top_brand_left_grid1\">\n" +
//                         "                                            <figure>\n" +
//                         "                                                <div class=\"snipcart-item block\">\n" +
//                         "                                                    <div class=\"snipcart-thumb\">\n" +
//                         "                                                        <a href=\'item?productId="+products[i].id+"'><img title=\" \" alt=\" \"\n" +
//                         "                                                                                    src=\"image?imgName=" + products[i].imageUrl + "\"/></a>\n" +
//                         "                                                        <h4>" + products[i].name + "</h4>  <br>\n" +
//
//                         "                                                        <h4>$" + products[i].price + "<span>$" + disc + "</span></h4>\n" +
//                         "                                                    </div>\n" +
//                         "                                                    <div class=\"snipcart-details top_brand_home_details\">\n" +
//                         "                                                         <form  method=\"post\" action=\"cart\" onsubmit=\"addToCartSmall(event)\">\n" +
//                         "                                                            <fieldset>\n" +
//                         "                                                                <input type=\"hidden\" name=\"cmd\" value=\"_cart\"/>\n" +
//                         "                                                                 <input type='hidden' name='pdId' value='"+products[i].id+"'>\n"+
//                         "                                                                 <input type='hidden' name='item_name' value='"+products[i].name+"'>\n"+
//                         "                                                                <input type=\"hidden\" name=\"add\" value=\"1\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"business\" value=\" \"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"item_name\"\n" +
//                         "                                                                       value=\"Fortune Sunflower Oil\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"amount\" value=\"35.99\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"discount_amount\"\n" +
//                         "                                                                       value=\"1.00\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"currency_code\" value=\"USD\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"return\" value=\" \"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"cancel_return\" value=\" \"/>\n" +
//                         "                                                                <input type=\"submit\" name=\"submit\" value=\"Add to cart\"\n" +
//                         "                                                                       class=\"button\"/>\n" +
//                         "                                                            </fieldset>\n" +
//                         "                                                        </form>\n" +
//                         "                                                    </div>\n" +
//                         "                                                </div>\n" +
//                         "                                            </figure>\n" +
//                         "                                        </div>\n" +
//                         "                                    </div>\n" +
//                         "                                </div>\n" +
//                         "                            </div>";
//
//                 } else {
//                     x += "<div class=\"col-md-4 top_brand_left\">\n" +
//                         "                                <div class=\"hover14 column\">\n" +
//                         "                                    <div class=\"agile_top_brand_left_grid\">\n" +
//                         "                                        <div class=\"agile_top_brand_left_grid1\">\n" +
//                         "                                            <figure>\n" +
//                         "                                                <div class=\"snipcart-item block\">\n" +
//                         "                                                    <div class=\"snipcart-thumb\">\n" +
//                         "                                                       <a href=\'item?productId="+products[i].id+"'><img title=\" \" alt=\" \"\n" +
//                         "                                                                                    src=\"image?imgName=" + products[i].imageUrl + "\"/></a>\n" +
//                         "                                                        <h4>" + products[i].name + "</h4>  <br>\n" +
//                         "                                                        <h4>$" + products[i].price + "</h4>\n" +
//                         "                                                    </div>\n" +
//                         "                                                    <div class=\"snipcart-details top_brand_home_details\">\n" +
//                         "                                                          <form  method=\"post\" action=\"cart\" onsubmit=\"addToCartSmall(event)\">\n" +
//                         "                                                            <fieldset>\n" +
//                         "                                                                 <input type='hidden' name='pdId' value='"+products[i].id+"'>\n"+
//                         "                                                                 <input type='hidden' name=\"item_name\" value='"+products[i].name+"'>\n"+
//
//                         "                                                                <input type=\"hidden\" name=\"cmd\" value=\"_cart\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"add\" value=\"1\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"business\" value=\" \"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"item_name\"\n" +
//                         "                                                                       value=\"Fortune Sunflower Oil\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"amount\" value=\"35.99\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"discount_amount\"\n" +
//                         "                                                                       value=\"1.00\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"currency_code\" value=\"USD\"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"return\" value=\" \"/>\n" +
//                         "                                                                <input type=\"hidden\" name=\"cancel_return\" value=\" \"/>\n" +
//                         "                                                                <input type=\"submit\" name=\"submit\" value=\"Add to cart\"\n" +
//                         "                                                                       class=\"button\"/>\n" +
//                         "                                                            </fieldset>\n" +
//                         "                                                        </form>\n" +
//                         "                                                    </div>\n" +
//                         "                                                </div>\n" +
//                         "                                            </figure>\n" +
//                         "                                        </div>\n" +
//                         "                                    </div>\n" +
//                         "                                </div>\n" +
//                         "                            </div>";
//                 }
//             }
//             $('#mostList').html(x + " <div class='clearfix'></div>");
//
//         }
//
//     }
//
//

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
        // $('#mostList').html(x + " <div class='clearfix'></div>");

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
        // $('#mostList').html(x + " <div class='clearfix'></div>");

    }

}


