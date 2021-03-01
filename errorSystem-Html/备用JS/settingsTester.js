
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

    $(function () {
        var user = JSON.parse(sessionStorage.getItem("testerId"));
        var id=user;
        $.ajax({
            url:"http://localhost:8080/Tester/protal/getOwn.do",
            type:"GET",
            dataType:"json",
            data:"id="+id,
            success:function (result) {
                var data=result.data;
                $("#name").val(data.name);
                $("#username").val(data.username);
                $("#phone").val(data.phone);
                $("#emailaddress").val(data.email);
                $("#birthday").val(Format(new Date(data.birthday),"yyyy-MM-dd"));
                var sex=data.sex;
                if (sex=="男"){
                    $("#sex").attr("checked",true);

                }else {
                    $("#sex2").attr("checked",true);

                }
                if (data.sex == "男") {
                    $("#click1 i:first").trigger("click");
                } else {
                    $("#click1 i:last").trigger("click");
                }
            }
        });
    });


    form.on("submit(update)", function (data) {
        var user = JSON.parse(sessionStorage.getItem("testerId"));
        var id=user;
        var name=$("#name").val().trim();
        var phone=$("#phone").val().trim();
        var email=$("#emailaddress").val().trim();
        var birth=$("#birthday").val().trim();
        var sex="";
        if ($("#sex").is(':checked')){
            sex=$("#sex").val().trim();
        }else {
            sex=$("#sex2").val().trim();
        }

        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "http://localhost:8080/Tester/protal/update.do",
            type: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                id: id,
                phone: phone,
                email: email,
                sex: sex,
                name: name,
                birth: birth
            }),
            success: function (result) {
                if (result.code == 0) {
                    setTimeout(function () {
                        layer.close(index);
                        layer.msg("信息更新成功！");
                    }, 2000);
                }
                if (result.exception != null) {
                    layer.msg(result.exception);
                }
            }
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
});
