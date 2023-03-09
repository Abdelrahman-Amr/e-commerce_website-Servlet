var verifyEmailReq;

function verifyEmail() {


    //console.log("enter verifyemail")

    if (window.XMLHttpRequest)
        verifyEmailReq = new XMLHttpRequest();
    else if (window.ActiveXObject)
        verifyEmailReq = new ActiveXObject(Microsoft.XMLHTTP);

    verifyEmailReq.onreadystatechange = handleVerifyEmailReq;
    var email = document.getElementById("email").value;
    //console.log(email)
    verifyEmailReq.open("GET", "VerifyEmail?email=" + email, true);
    verifyEmailReq.send(null);
}

function handleVerifyEmailReq() {
    //console.log("enter handle verifyemail")
    if (verifyEmailReq.readyState == 4 && verifyEmailReq.status == 200) {
        xmlvalue = verifyEmailReq.responseText;
        if (xmlvalue === "valid") {
            //console.log("valid email")
            document.getElementById("email_status").style.visibility = "hidden";
        } else {
            //console.log("invalid email")
            document.getElementById("email_status").style.visibility = "visible";
        }

    }
}