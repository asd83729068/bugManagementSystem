layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function() {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    form.on("submit(updatePasswd)", function (data) {
        var user = sessionStorage.getItem("developId");
        var id = user;
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var newPassword2 = $("#newPassword2").val();
        if ( newPassword != newPassword2) {
            layer.msg("两次密码不相同请重新输入！");
            return false;
        }

        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "http://localhost:8080/Developer/protal/updatePassword.do",
            type: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                id: id,
                oldPassword: oldPassword,
                newPassword: newPassword
            }),
            success: function (result) {
                if (result.code == 0) {
                    setTimeout(function () {
                        layer.close(index);
                        layer.msg("密码更新成功！");
                    }, 2000);
                }
                if (result.exception != null) {
                    layer.msg(result.exception);
                }
            }
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})
