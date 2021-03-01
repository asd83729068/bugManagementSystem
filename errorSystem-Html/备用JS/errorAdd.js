layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function() {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    var id = sessionStorage.getItem("testerId");

    $(function () {
        $.ajax({
            url: "http://localhost:8080/Tester/protal/getOwn.do",
            type: "GET",
            dataType: "json",
            data: "id=" + id,
            success: function (result) {
                var data = result.data;
                $("#reporterName").val(data.name);
            }
        })
    });


    $(".layui-select-title").addClass("select2");

    form.on("submit(addErrorWr)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "http://localhost:8080/Tester/protal/add.do",
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                demoname: $("#demoName").val().trim(),
                errorofwriting: $("#errorOfWriting").val().trim(),
                errortheme: $("#errorTheme").val().trim(),
                note: $("#note").val().trim(),
                ponderance: $("#ponderance").val().trim(),
                priority: $("#priority").val().trim(),
                reporterid: id,
                status: $("#status").val().trim(),
                version: $("#version").val().trim()
            }),
            success: function (result) {
                if (result.code == 0) {
                    setTimeout(function () {
                        layer.close(index);
                        layer.msg("错误报告添加成功！");
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
