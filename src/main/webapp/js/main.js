function login()
{
 
    var email = $("#email").val();
    var password = $("#password").val();
    var jsonData = JSON.stringify({"email": email, "password": password});
    $.post ("login",
    {
        email : email,
        password : password,
    }
, loginCallBack);
}

function loginCallBack(responseTxt, statusTxt, xhr)
{
  if (statusTxt == "success" && responseTxt =="1"){
    successLogin();
  
    }else{
      failedLogin();
    }
}

    // $.ajax({ url: 'login',
    // type: 'POST',
    // contentType: 'application/json', 
    // data: jsonData, 
    // dataType: 'json', 
    //  }).done(function(ret) {
    //    if(ret=="1")
    //    {
    //     successLogin();
    //    }
    //    else{

    //    }
    // });
      
// }
function successLogin()
{
   Swal.fire({
        // position: 'top-end',
        icon: 'success',
        text:"Logged in successfully",
        title: 'Success',
        showConfirmButton: false,
        timer: 1500,
        toast:true,
        didDestroy:function(){
          $("#login-form").submit();
        }
      });
}

function failedLogin()
{
    Swal.fire({
      title: 'Failed',
      text:'Failed to Login',
      icon: 'error',
      // showCancelButton: true,
      confirmButtonText: 'Ok',
      confirmButtonColor: '#25aae2',
    });
  
}
