function logout()
{
    logoutPopup();

}

function logoutCallBack(responseTxt, statusTxt, xhr)
{
    if (statusTxt == "success" && responseTxt =="1"){
        success("Logged out Successfully");
    }else{
        failed('Failed to Logout !!');
    }
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
function logoutPopup(){
    Swal.fire({
        title: 'Are you sure you want to logout ?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        confirmButtonColor: '#25aae2',
        toast:true
        // cancelButtonColor: '#25aae2',
        // denyButtonColor: '#25aae2',

    }).then((result) => {
        if (result.value) {
            $.get ("logout",
                {
                }
                , logoutCallBack).fail(function() {
                failed('Failed to Logout !!');
            });
        }});
}
