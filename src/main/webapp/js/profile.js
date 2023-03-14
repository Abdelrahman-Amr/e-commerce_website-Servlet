function updateProfile(event) {
    event.preventDefault();
    var userName = document.getElementById("userName").value;
    var birthDate = document.getElementById("birthDate").value;
    var phone = document.getElementById("phone").value;
    var address = document.getElementById("address").value;
    var credit = document.getElementById("credit").value;
        //register
    var userJson = {
        userName:userName, address:address, birthday:birthDate, phone:phone,  creditLimit:credit, email:"", passwd:""

    };
    console.log(userJson);
    $.post ("Profile",
    JSON.stringify({userName:userName, address:address, phone:phone, birthday:birthDate, email:"",  creditLimit:credit,  password:""})
    , updateCallBack).fail(function() {
                              failed('Updated Failed !!');
                          });
    }

    function updateCallBack(responseTxt, statusTxt, xhr)
    {
      if (statusTxt == "success" && responseTxt =="1"){
        success("Updated Successfully");

        }else{
          failed('Updated Failed !!');
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