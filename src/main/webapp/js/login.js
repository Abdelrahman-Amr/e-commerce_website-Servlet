function login(event)
{
    event.preventDefault();
    var email = $("#email").val();
    var password = $("#password").val();
    console.log(validateEmail(email),validatePass(password));
    if(!validateEmail(email)  ||  !validatePass(password))
    {
        failed('Invalid Email or Password !!');
        return ;
    }
    $.post ("login",
    {
        email : email,
        password : password,
    }
, loginCallBack);
}

function logout()
{

    $.get ("logout",
        {
        }
        , logoutCallBack);
}

function loginCallBack(responseTxt, statusTxt, xhr)
{
  if (statusTxt == "success" && responseTxt =="1"){
    success("Logged in successfully");
  
    }else{
      failed('Invalid Email or Password !!');
    }
}

function logoutCallBack(responseTxt, statusTxt, xhr)
{
    if (statusTxt == "success" && responseTxt =="1"){
        success("Logged out Successfully");
    }else{
        failed('Failed to Logout !!');
    }
}

function validateEmail(email)
{
    let pat=/^(.+)@(.+)\.(.+)$/;
    if(!pat.test(email))
    {
        return false;
    }
    return true;
}

function validatePass(password)
{
    if(password.trim().length>0)
    {
        return true;
    }
    return false;
}

function success(msg)
{
   Swal.fire({
        // position: 'top-end',
        icon: 'success',
        text:msg,
        title: 'Success',
        showConfirmButton: false,
        timer: 1500,
        toast:true,
        didDestroy:function(){
          // $("#login-form").submit();
            window.location.href="home";
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
