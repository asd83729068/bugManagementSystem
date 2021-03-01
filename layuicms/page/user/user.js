layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
    lay('#version').html('-v' + laydate.v);

    //执行一个laydate实例
    laydate.render({
        elem: '#birthday' //指定元素
    });
    var id=sessionStorage.getItem("login");
    if (id!=null) {
        $(function () {
            $.ajax({
                url: "http://localhost:8082/Manager/getOne.do",
                type: "GET",
                dataType: "json",
                data: "id=" + id,
                success: function (result) {
                    var data = result.data;
                    if (result.code == 0) {
                        if (data.sex == "男") {
                            $("#click1 i:first").trigger("click");
                        } else {
                            $("#click1 i:last").trigger("click");
                        }
                        $("#name").val(data.name);
                        $("#username").val(data.username);
                        $("#phone").val(data.phone);
                        $("#email").val(data.email);
                        $("#birthday").val(Format(new Date(data.birthday), "yyyy-MM-dd"));
                    }
                    if (result.exception != null) {
                        alert(result.exception);
                    }
                }
            });
        })
    }else {
        window.location.href="../404.html";
    }

    //添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
    })

    //判断是否修改过用户信息，如果修改过则填充修改后的信息
    if(window.sessionStorage.getItem('userInfo')){
        var userInfo = JSON.parse(window.sessionStorage.getItem('userInfo'));
        var citys;
        $(".realName").val(userInfo.realName); //用户名
        $(".userSex input[value="+userInfo.sex+"]").attr("checked","checked"); //性别
        $(".userPhone").val(userInfo.userPhone); //手机号
        $(".userBirthday").val(userInfo.userBirthday); //出生年月
        $(".userEmail").val(userInfo.userEmail); //用户邮箱
        $(".myself").val(userInfo.myself); //自我评价
        form.render();
    }

    //判断是否修改过头像，如果修改过则显示修改后的头像，否则显示默认头像
    if(window.sessionStorage.getItem('userFace')){
    	$("#userFace").attr("src",window.sessionStorage.getItem('userFace'));
    }else{
    	$("#userFace").attr("src","../../images/face.jpg");
    }

    //提交个人资料
    form.on("submit(changeUser)",function(data){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //将填写的用户信息存到session以便下次调取
        var key,userInfoHtml = '';
        var sex="";
        if ($("#click1 i:first").hasClass("layui-anim-scaleSpring")){
            sex=$("#sex").val();
        }else {
            sex=$("#sex2").val();
        }
        userInfoHtml = {
            'name' : $("#name").val(),
            'sex' : sex,
            'phone' : $("#phone").val(),
            'birth' : $("#birthday").val(),
            'id' : id,
            'email' : $("#email").val()
        };

        window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
            $.ajax({
                url:"http://localhost:8082/Manager/update.do",
                type: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data:JSON.stringify(userInfoHtml),
                success:function (result) {
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg("提交成功！");
                    },2000);
                    if (result.exception!=null){
                        layer.msg(result.exception);
                    }
                }
            });

    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    //修改密码
    form.on("submit(changePwd)",function(data){
        if ($("#newPw").val()!=$("#newPw2").val()){
            layer.msg("两次密码不一致！");
        } else {
            var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            $.ajax({
                url: "http://localhost:8082/Manager/updatePassword.do",
                type: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify({
                    id: id,
                    oldPassword: $("#oldPw").val(),
                    newPassword: $("#newPw").val()
                }),
                success: function (result) {
                    if (result.code==0) {
                        setTimeout(function () {
                            layer.close(index);
                            layer.msg("密码修改成功！");
                            $(".pwd").val('');
                        }, 2000);
                    }
                    if (result.exception != null) {
                        layer.msg(result.exception);
                    }
                }
            });
        }
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})

