var verifyEmailReq;

function verifyEmail() {
    //console.log("enter verifyemail")

    var email = document.getElementById("email").value;

    $.get ("VerifyEmail?email="+email
            , handleVerifyEmailReq);
}

function handleVerifyEmailReq(responseTxt, statusTxt, xhr) {
    //console.log("enter handle verifyemail")
    if (statusTxt == "success" ) {
        if(responseTxt =="0") {
        // valid email
            document.getElementById("email_status").style.display="none";
            document.getElementById("email").style.borderColor="#DBDBDB"; //#DBDBDB
            document.getElementById("email").style.hover="#663300"; //#DBDBDB

            //document.getElementById("email_status").style.visibility="block";

        } else {
        //console.log("invalid email")
            document.getElementById("email_status").style.display="block";
            document.getElementById("email_status").style.visibility="visible";
            document.getElementById("email").style.borderColor="red"; //#DBDBDB
        }
    } else {
        document.getElementById("email_status").style.display="none";
        document.getElementById("email").style.borderColor="#DBDBDB"; //#DBDBDB
        document.getElementById("email").style.hover="#663300"; //#DBDBDB
    }
}
function Register(event) {
    event.preventDefault();
    var userName = document.getElementById("userName").value;
    var birthDate = document.getElementById("birthDate").value;
    var phone = document.getElementById("phone").value;
    var address = document.getElementById("address").value;
    var credit = document.getElementById("credit").value;
    var email = document.getElementById("email").value;
    var passwd = document.getElementById("password").value;
    var confirmPasswd = document.getElementById("confirmedPassword").value;
    if(validateData(passwd,confirmPasswd)) {
        //register
        var userJson = {

            userName:userName, address:address, birthday:birthDate, phone:phone,  creditLimit:credit, email:email, passwd:passwd

        };
        console.log(userJson);
        $.post ("Register",
        JSON.stringify({userName:userName, address:address, phone:phone, birthday:birthDate, email:email,  creditLimit:credit,  password:passwd})
        , registerCallBack).fail(function() {
                                  failed('Registration Failed !!');
                              });
    }
}

function registerCallBack(responseTxt, statusTxt, xhr)
{
  if (statusTxt == "success" && responseTxt =="1"){
      successLogin("Registered Successfully");

    }else{
      failed('Registration Failed !!');
    }
}

function validateData(passwd,confirmPasswd) {
    var valid=false;
    if(passwd!==confirmPasswd) {
        document.getElementById("matchingPasswd").style.display = "block";
        document.getElementById("matchingPasswd").style.visibility = "visible";
        document.getElementById("confirmedPassword").style.borderColor = "red";
        valid=false;
    } else {
        document.getElementById("matchingPasswd").style.display = "none";
        document.getElementById("confirmedPassword").style.borderColor = "#DBDBDB";
        valid=true;
    }
    return valid;
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
       iconColor:'#663300',
       didDestroy:function(){
          // $("#login-form").submit();
            //window.location.href="home";
            window.location.href="Profile";

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
