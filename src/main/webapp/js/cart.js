// class CartItem
// {
//     #id;
//     #name;
//     #price;
//     #discount;
//     #
//     constructor(id, price) {
//         this.#id = id;
//         this.#price= price;
//     }
//
// }

var cartItems = [];
var storage= sessionStorage;
// totalPrice: Subject<number> = new BehaviorSubject<number>(0);
// totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

// storage: Storage = localStorage;

// constructor() {
//
//     // read data from storage
//     let car = this.storage.getItem('cartItems');
//     let data = null;
//     if(car!=null){
//         try {
//             const dec = AES.decrypt(car, "").toString(enc.Utf8);
//             data = JSON.parse(dec);
//         }catch(e){
//             this.clearCart();
//             window.location.reload();
//         }
//     }
//     if (data != null) {
//         this.cartItems = data;
//
//         // compute totals based on the data that is read from storage
//         this.computeCartTotals();
//     }
//
// }

function addToCart(event ) {
    console.log(event.target.pdId.value);
    event.preventDefault();
    $.post("cart?pdId="+event.target.pdId.value+"&quantity=1&sizeId=1", function(data, status){
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

function  decreaseQuan()
{

}
function  increaseQuan()
{

}
    // // check if we already have the item in our cart
    // let alreadyExistsInCart: boolean = false;
    // let existingCartItem: CartItem = undefined;
    //
    // if (this.cartItems.length > 0) {
    //     // find the item in the cart based on item id
    //
    //     existingCartItem = this.cartItems.find( tempCartItem => tempCartItem.product.id === theCartItem.product.id && tempCartItem.size.id=== theCartItem.size.id);
    //
    //     // check if we found it
    //     alreadyExistsInCart = (existingCartItem != undefined);
    // }
    //
    // if (alreadyExistsInCart) {
    //     // increment the quantity
    //     existingCartItem.quantity++;
    // }
    // else {
    //     // just add the item to the array
    //     this.cartItems.push(theCartItem);
    // }
    //
    // // compute cart total price and total quantity
    // this.computeCartTotals();
// }
//
// function computeCartTotals() {
//
//     let totalPriceValue: number = 0;
//     let totalQuantityValue: number = 0;
//
//     for (let currentCartItem of this.cartItems) {
//         totalPriceValue += currentCartItem.quantity * currentCartItem.product.sal1;
//         totalQuantityValue += currentCartItem.quantity;
//     }
//
//     // publish the new values ... all subscribers will receive the new data
//     this.totalPrice.next(totalPriceValue);
//     this.totalQuantity.next(totalQuantityValue);
//
//     // // log cart data just for debugging purposes
//     // this.logCartData(totalPriceValue, totalQuantityValue);
//
//     // persist cart data
//     this.persistCartItems();
// }
//
// function persistCartItems() {
//     const encCart = JSON.stringify(this.cartItems);
//     let enc = AES.encrypt(encCart ,"").toString();
//     this.storage.setItem('cartItems',enc);
// }
// function  decrementQuantity(theCartItem: CartItem) {
//
//     theCartItem.quantity--;
//
//     if (theCartItem.quantity === 0) {
//         this.remove(theCartItem);
//     }
//     else {
//         this.computeCartTotals();
//     }
// }
//
// function  remove(theCartItem: CartItem) {
//
//     // get index of item in the array
//     const itemIndex = this.cartItems.findIndex( tempCartItem => tempCartItem.product.id === theCartItem.product.id );
//
//     // if found, remove the item from the array at the given index
//     if (itemIndex > -1) {
//         this.cartItems.splice(itemIndex, 1);
//
//         this.computeCartTotals();
//     }
// }
// function  clearCart(){
//     cartItems=[];
//     computeCartTotals();
//     storage.removeItem('cartItems');
// }

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
