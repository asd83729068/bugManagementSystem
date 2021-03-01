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
    
    var id=sessionStorage.getItem("id");
    $(function () {
        $.ajax({
            url: "http://localhost:8082/Amaldar/protal/getOwn.do?id="+id,
            type: "GET",
            dataType: "json",
            success: function(result) {
                var data=result.data;
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
                    $("#birthday").val(Format(new Date(data.birth),"yyyy-MM-dd"));
                }
                if (result.exception != null) {
                    alert(result.exception);
                }
            }
        })
    });

    form.on("submit(addNews)", function () {
        var sex="";
        if ($("#click1 i:first").hasClass("layui-anim-scaleSpring")){
            sex=$("#sex").val();
        }else {
            sex=$("#sex2").val();
        }
        var name=$("#name").val();
        var phone=$("#phone").val();
        var email=$("#email").val();
        var birth=$("#birthday").val();
        $.ajax({
            url: "http://localhost:8082/Amaldar/protal/update.do",
            type: "PUT",
            dataType: "JSON",
            contentType: "application/json",
            data:JSON.stringify({
                birth: birth,
                email:email,
                id: id,
                name: name,
                phone: phone,
                sex:sex
            }),
            success: function(result) {
                if (result.code == 0) {
                    var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("用户更新成功！");
                    }, 2000);
                }
                if (result.exception != null) {
                    alert(result.exception);
                }
            }
        })
        return false;
    });

});
