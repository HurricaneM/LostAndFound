// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    $("#sub").click(function(){
        if($("#username").val()===""||$("#email").val()===""||$("#Tel").val()===""||$("#password").val()===""){

            alert("请完善注册信息")
        }
        if($("#password").val()!==$("#password2").val()){
            alert("两次输入的密码不一致")
        }

        $.ajax({
            url:"http://localhost:8080/user",
            type:"POST",
            data:$("#userRegisterForm").serialize(),
            success:function(result){
                alert(result.status)
            }
        })
    })
}())