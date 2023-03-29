function login(event)
{
    event.preventDefault();
    var email = $("#email").val();
    var password = $("#password").val();
    // console.log(validateEmail(email),validatePass(password));
//    if(!validateEmail(email)  ||  !validatePass(password))
//    {
//        failed('Invalid Email or Password !!');
//        return ;
//    }
    $.post ("login",
    {
        email : email,
        password : password,
    }
, loginCallBack) .fail(function() {
        failed('Invalid Email or Password !!');
    });
}



function loginCallBack(responseTxt, statusTxt, xhr)
{
    console.log(xhr.status);
    if (statusTxt == "success" &&  xhr.status == 200){
        if(responseTxt =="1") {
            // customer
            // go to home
            successLogin("Logged in successfully");
        } else if(responseTxt =="2") {
            successLoginAdmin("Logged in successfully");
        } else if(responseTxt == "0") {
            failed('Invalid Email or Password !!');
        }
    }else{
      failed('Invalid Email or Password !!');
    }
}



//function validateEmail(email)
//{
//    let pat=/^(.+)@(.+)\.(.+)$/;
//    if(!pat.test(email))
//    {
//        return false;
//    }
//    return true;
//}

//function validatePass(password)
//{
//    if(password.trim().length>0)
//    {
//        return true;
//    }
//    return false;
//}

function successLoginAdmin(msg) {
    Swal.fire({
            // position: 'top-end',
            icon: 'success',
            text:msg,
            title: 'Success',
            showConfirmButton: false,
            timer: 1500,
            toast:true,
           iconColor:'#663300',
           didDestroy:function(){
              // $("#login-form").submit();
               if(sessionStorage.getItem("toCart") && sessionStorage.getItem("toCart")=="true")
               {
                   sessionStorage.setItem("toCart","false");
                   window.location.href="cart";
               }else {
                   window.location.href = "dashboard";
               }
//                 window.location.href="dashboard";

            }
          });
}
function successLogin(msg)
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
       didDestroy:function(){
          // $("#login-form").submit();
           if(sessionStorage.getItem("toCart") && sessionStorage.getItem("toCart")=="true")
           {
               sessionStorage.setItem("toCart","false");
               window.location.href="cart";
           }else {
               window.location.href = "home";
           }
            // window.location.href="Profile";

        }
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
