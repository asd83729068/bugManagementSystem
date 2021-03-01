$("#save").click(function(){
    var username=$("#test_username").val().trim();
    var password=$("#test_password").val().trim();
    if (username!="" && password!="") {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json",
            url: "http://localhost:8082/Tester/protal/login.do",//url
            data: JSON.stringify({
                username: username,
                password: password
            }),
            success: function (result) {
                if (result.code == 0) {
                    sessionStorage.setItem("testerId",result.data.id);
                    window.location.href="page/tester/testerIndex.html";
                } else {
                    alert("用户名或密码错误！");
                }
            }
        });
    }else {
        alert("用户名和密码不能为空！");
    }
    $("#test_password").val('');
});

$("#save2").click(function(){
    var username=$("#amadl_username").val().trim();
    var password=$("#amadl_password").val().trim();
    if (username!="" && password!="") {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json",
            url: "http://localhost:8082/Amaldar/protal/login.do",//url
            data: JSON.stringify({
                username: username,
                password: password
            }),
            success: function (result) {
                if (result.code == 0) {
                    // function User(id, name) {
                    //     this.id = id;
                    //     this.name = name;
                    // }
                    // var user = new User(result.data.id, result.data.name);
                    // sessionStorage.setItem("user", JSON.stringify(user));
                    sessionStorage.setItem("user",result.data.id);
                    window.location.href="page/amaldar/index.html";
                } else {
                    alert("用户名或密码错误！");
                }
            }
        });
    }else {
        alert("用户名和密码不能为空！");
    }
    $("#amadl_password").val('');
});

$("#save3").click(function(){
    var username=$("#devel_username").val().trim();
    var password=$("#devel_password").val().trim();
    if (username!="" && password!="") {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json",
            url: "http://localhost:8082/Developer/protal/login.do",//url
            data: JSON.stringify({
                username: username,
                password: password
            }),
            success: function (result) {
                if (result.code == 0) {
                    sessionStorage.setItem("developId",result.data.id);
                    window.location.href="page/developer/developerIndex.html";
                } else {
                    alert("用户名或密码错误！");
                }
            }
        });
    }else {
        alert("用户名和密码不能为空！");
    }
    $("#devel_password").val('');
});


function formClear() {
    $(':input','#form1')
        .not(':button, :submit, :reset, :hidden')
        .val('')
}

function formClear2() {
    $(':input','#form2')
        .not(':button, :submit, :reset, :hidden')
        .val('')
}

function formClear3() {
    $(':input','#form3')
        .not(':button, :submit, :reset, :hidden')
        .val('')
}
