function addProductAdmin(event) {
    console.log("enter");
    event.preventDefault();
    var productName = document.getElementById("productName").value;
    var productCategory = document.getElementById("category").value;
    var productPrice = document.getElementById("productPrice").value;
    var productDiscount = document.getElementById("productDiscount").value;
    var productQuantity = document.getElementById("productQuantity").value;
    var productPriority = document.getElementById("productPriority").value;
    var productDescription = $('#productDescription').val();

//    console.log(productCategory);

    var data = new FormData();

    var productInfo = JSON.stringify({name:productName, catg_id: productCategory, price:productPrice, discount:productDiscount,
    quantity:productQuantity, priority:productPriority,  description:productDescription, imageUrl: null});

    //console.log(productInfo);

    data.append("productInfo", productInfo);
//    data.append("categoryId", productCategory);
    data.append('file', document.getElementById("productImage").files[0]);

    $.ajax({
            url: 'AddingProduct',
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            method:'POST',
            type: 'json',
            success: function ( data, textStatus ) {
                console.log(data);
                //console.log("success");
                if(data=="1") {
                    successLoginAdmin("Adding new product successfully");
//                    addingProductCallBack();
                }else{
                    failed('Failed to add product !!');

                }

                // alert( data );
            },

            fail: failCallBack
    });

}

function failCallBack(xhr, textStatus, errorThrown){
    console.log("fail");
               failed('Failed to add product !!');
//               alert('request failed');
            }

//function addingProductCallBack() {
//    console.log("done");
//}
