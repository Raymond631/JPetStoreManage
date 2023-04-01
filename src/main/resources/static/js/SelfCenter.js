function newVerification() {
    let image = document.getElementById("verificationCode");
    image.src = "../User/verificationCode?" + new Date().getMilliseconds();
}

$(function () {
    // $('#password_old').on('blur', function () {
    //     let pwd = {
    //         password: $(this).val()
    //     }
    //
    // })
    console.log("response");
    let settings = {
        "url": "/jpetstore/user",
        "method": "GET",
        "timeout": 0,
        "headers": {
        },
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
        $('#nickname').val(response.data.nickname)
        $('#account').val(response.data.account)
    });
});

function changeName(){

}

function changePwd(){

}