var id=sessionStorage.getItem("errorid");

$(function () {
    $.ajax({
        url:"http://localhost:8080/ErrorWriting/Manager/getOne.do",
        type:"POST",
        dataType: "json",
        data:"id="+id,
        success:function (result) {
            var data=result.data;
            if (result.code==0){
                $("#errorTheme").val(data.errortheme);
                $("#demoName").val(data.demoname);
                $("#ponderance").val(data.ponderance);
                $("#priority").val(data.priority);
                $("#reporterName").val(data.reporterName);
                if (data.dealerName!=""){
                    $("#dealerIdName").val(data.dealerName);
                }else {
                    $("#dealerIdName").val("开发人员未分配");
                }
                if (data.amaldarName==""){
                    $("#amaldarIdName").val("经理人员未分配");

                } else {
                    $("#amaldarIdName").val(data.amaldarName);
                }
                $("#status").val(data.status);
                $("#note").val(data.note);
                $("#version").val(data.version);
                $("#errorOfWriting").val(data.errorofwriting);
                $("#createDate").val(Format(new Date(data.createdate),"yyyy-MM-dd HH:mm:ss"));
                $("#dealDate").val(Format(new Date(data.dealdate),"yyyy-MM-dd HH:mm:ss"));
                if (data.enddate==null) {
                    $("#endDate").val("该错误报告未结束！");
                }else {
                    $("#endDate").val(Format(new Date(data.enddate),"yyyy-MM-dd HH:mm:ss"));
                }
            } else {
                alert(result.exception);
            }
        }
    });
});



$(document).on("click","#updateErrorWriting",function () {
    $.ajax({
        url: "http://localhost:8080/Developer/protal/updateError.do",
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            status: $("#status").val()
        }),
        success: function (result) {
            if (result.code==0) {
                alert(result.data);
            }
            if(result.exception!=null){
                alert(result.exception);
            }
        }
    });

});
