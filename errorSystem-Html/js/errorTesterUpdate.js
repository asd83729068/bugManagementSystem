layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    var testerId=sessionStorage.getItem("testerId");
    var id = sessionStorage.getItem("errorid");
    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);
    //创建一个编辑器
    var editIndex = layedit.build('news_content', {
        height: 500,
        uploadImage: {
            url: 'http://localhost:8082/file/uploadImageEdit?token=asdassbduasbudahbuduha'
        }
    });
    var errorofwriting = "";
    $(function () {
        $.ajax({
            url: "http://localhost:8082/ErrorWriting/Manager/getOne.do",
            type: "POST",
            dataType: "json",
            data: "id=" + id,
            success: function (result) {
                var data = result.data;
                if (result.code == 0) {
                    $("#errorTheme").val(data.errortheme);
                    $("#demoName").val(data.demoname);
                    $("#ponderance").val(data.ponderance);
                    $("#d1 input").val("( 严重性 )  "+data.ponderance);
                    $("#priority").val(data.priority);
                    $("#d2 input").val("( 优先级 )  "+data.priority);
                    $("#reporterName").val(data.reporterName);
                    if (data.dealerName != "") {
                        $("#dealerIdName").val(data.dealerName);
                    } else {
                        $("#dealerIdName").val("开发人员未分配");
                    }
                    if (data.amaldarName == "") {
                        $("#amaldarIdName").val("经理人员未分配");

                    } else {
                        $("#amaldarIdName").val(data.amaldarName);
                    }
                    $("#status").val(data.status);
                    $("#d3 input").val("( 状态 )  "+showStatusName(data.status));
                    $("#note").val(data.note);
                    $("#version").val(data.version);
                    // $("#errorOfWriting").val(data.errorofwriting);
                    errorofwriting = data.errorofwriting;


                    $("#createDate").val(Format(new Date(data.createdate), "yyyy-MM-dd HH:mm:ss"));
                    $("#dealDate").val(Format(new Date(data.dealdate), "yyyy-MM-dd HH:mm:ss"));
                    if (data.enddate == null) {
                        $("#endDate").val("该错误报告未结束！");
                    } else {
                        $("#endDate").val(Format(new Date(data.enddate), "yyyy-MM-dd HH:mm:ss"));
                    }
                    layedit.setContent(editIndex, errorofwriting);
                } else {
                    alert(result.exception);
                }
            }
        });
    });

    function showStatusName(status) {
        if (status=="0"){
            return "关闭";
        } else if (status=="1"){
            return "开放";
        } else if (status=="2"){
            return "已分配";
        } else if (status=="3"){
            return "拒绝";
        }else if (status=="4"){
            return "已修复";
        }else if (status=="5"){
            return "无法修复";
        } else {
            return "未知";
        }
    }

    $(".layui-select-title").addClass("select2");

    form.on("submit(updateErrorWriting)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "http://localhost:8082/Tester/protal/updateError.do",
            type: "PUT",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                demoname: $("#demoName").val(),
                errorofwriting: layedit.getContent(editIndex),
                errortheme: $("#errorTheme").val(),
                id: id,
                note: $("#note").val(),
                ponderance: $("#ponderance").val(),
                priority: $("#priority").val(),
                status: $("#status").val(),
                reporterid: testerId,
                version: $("#version").val()
            }),
            success: function (result) {
                if (result.code == 0) {
                    setTimeout(function () {
                        layer.close(index);
                        layer.msg("错误报告更新成功！");
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
