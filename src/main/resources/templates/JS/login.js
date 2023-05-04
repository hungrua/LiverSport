const userJsonUrl = "http://localhost:3000/user"
$(function(){
    /*Sign in and sign up effect*/ 
    $("#sign-up").hide();
    $("#register").on("click", function(){
        $("#sign-in").hide();
        $("#sign-up").slideDown();
    })
    $("#return-login").on("click", function(){
        $("#sign-up").hide();
        $("#sign-in").slideDown();
    })
    /*Http method*/
    $("#create-acc").on("click", function(){
        var userName = $("#floatingUserName").val();
        var email = $("#floatingEmail").val();
        var password = $("#floatingPassword").val();
        var confirmPassword = $("#floatingPasswordConfirm").val();
        if(password === confirmPassword){
            var user ={
                "userName": userName,
                "password": password,
                "email":email
            }
            $.post(userJsonUrl,user,'json')
        }
        else alert("Check your confirm password again!")
    })
})
