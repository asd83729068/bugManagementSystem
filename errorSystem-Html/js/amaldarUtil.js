layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;



    var list=[];
$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8082/Developer/Manager/getAll.do",
        type:"GET",
        dataType: "json",
        success:function (result) {
            var listDeve=result.data;
            for (var i=0;i<listDeve.length;i++){
                $("#dealerIdName").append("<option>"+listDeve[i].name+"</option>");
                list.push(listDeve[i].id);
                list.push(listDeve[i].name);
            }
        }
    });
});


var errorid=sessionStorage.getItem("errorid");
var userId=sessionStorage.getItem("user");
    layedit.sync(editIndex);
    //创建一个编辑器
    var editIndex = layedit.build('news_content', {
        height: 600,
        hideTool: [
            'link' //超链接
            ,'unlink' //清除链接
            ,'face' //表情
            ,'image' //插入图片
            ,'help' //帮助
            ,'video' //帮助
        ]
    });
    var errorofwriting = "";

$(function () {
    $.ajax({
        url:"http://localhost:8082/ErrorWriting/Manager/getOne.do",
        type:"POST",
        dataType: "json",
        data:"id="+errorid,
        success:function (result) {
            var data=result.data;
            if (result.code==0){
                $("#errorTheme").val(data.errortheme);
                $("#demoName").val(data.demoname);
                $("#ponderance").val(data.ponderance);
                $("#priority").val(data.priority);
                $("#reporterName").val(data.reporterName);
                if (data.dealerName!="" && data.dealerName.indexOf("已离职")<0){
                    a=setTimeout(function () {
                        $("#dealerIdName").val(data.dealerName);
                    },100);
                }
                if (data.dealerName!="" && data.dealerName.indexOf("已离职")>0) {
                    $("#s1").html(data.dealerName);
                }
                if (data.amaldarName==""){
                    b=setTimeout(function () {
                        $("#amaldarIdName").val(userName);
                    },100);
                    $("#amaldarIdName").attr("num",userId); // 设置

                } else {
                    $("#amaldarIdName").val(data.amaldarName);
                }
                $("#status").val(data.status);
                $("#note").val(data.note);
                $("#version").val(data.version);
                // $("#errorOfWriting").val(data.errorofwriting);
                errorofwriting = data.errorofwriting;
                $("#createDate").val(Format(new Date(data.createdate),"yyyy-MM-dd HH:mm:ss"));
                $("#dealDate").val(Format(new Date(data.dealdate),"yyyy-MM-dd HH:mm:ss"));
                if (data.enddate==null) {
                    $("#endDate").val("该错误报告未结束！");
                }else {
                    $("#endDate").val(Format(new Date(data.enddate),"yyyy-MM-dd HH:mm:ss"));
                }
                $("#LAY_layedit_1").contents().find("body[contenteditable]").prop("contenteditable",false);
                layedit.setContent(editIndex, errorofwriting);
            } else {
                alert(result.exception);
            }
        }
    });
});

$("#dealerIdName").change(function () {
    if ($("#dealerIdName").val()!="no"){
        $("#dealerIdName").removeClass("redcolor");
    }
})




// $(document).on("click","#updateErrorWriting2",function () {
//     if ($("#dealerIdName").val()=="no"){
//         alert("dealerIdName不能为空！");
//         $("#dealerIdName").addClass("redcolor");
//         return false;
//     }
//     var count=0;
//     var newId=errorid;
//     var dealerid=$("#dealerIdName").val();
//     var amaldarid="";
//     if ($("#amaldarIdName").val()==userName){
//         amaldarid=$("#amaldarIdName").attr("num");
//     }else if ($("#amaldarIdName").val().indexOf("已离职")>0){
//         amaldarid = userId;
//     }
//     for (var i=0;i<list.length;i++){
//         if (list[i]==dealerid){
//             count=i;
//             break;
//         }
//     }
//     dealerid=list[count-1];
//     var status=$("#status").val();
    // $.ajax({
    //     url: "http://localhost:8082/Amaldar/protal/updateError.do",
    //     type: "PUT",
    //     dataType: "json",
    //     contentType: "application/json",
    //     data: JSON.stringify({
    //         id: newId,
    //         amaldarid : amaldarid,
    //         dealerid: dealerid,
    //         status: status
    //     }),
    //     success: function (result) {
    //         alert(result.data);
    //     }
    // });
    form.on("submit(updateErrorWriting)", function (data) {
        if ($("#dealerIdName").val()=="no"){
            alert("dealerIdName不能为空！");
            $("#dealerIdName").addClass("redcolor");
            return false;
        }
        var count=0;
        var newId=errorid;
        var dealerid=$("#dealerIdName").val();
        var amaldarid="";
        if ($("#amaldarIdName").val()==userName){
            amaldarid=$("#amaldarIdName").attr("num");
        }else if ($("#amaldarIdName").val().indexOf("已离职")>0){
            amaldarid = userId;
        }
        for (var i=0;i<list.length;i++){
            if (list[i]==dealerid){
                count=i;
                break;
            }
        }
        dealerid=list[count-1];
        var status=$("#status").val();
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "http://localhost:8082/Amaldar/protal/updateError.do",
            type: "PUT",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                id: newId,
                amaldarid : amaldarid,
                dealerid: dealerid,
                status: status
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

// });

});
