var list=[];
$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/Developer/Manager/getAll.do",
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
                if (data.dealerName!="" && data.dealerName.indexOf("已离职")<0){
                    a=setTimeout(function () {
                        $("#dealerIdName").val(data.dealerName);
                    },100);
                }
                if (data.dealerName!="" && data.dealerName.indexOf("已离职")>0) {
                    $("#s1").html(data.dealerName);
                }
                if (data.amaldarName==""){
                    $("#amaldarIdName").val(userName);
                    $("#amaldarIdName").attr("num",userId); // 设置

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

$("#dealerIdName").change(function () {
    if ($("#dealerIdName").val()!="no"){
        $("#dealerIdName").removeClass("redcolor");
    }
})

$(document).on("click","#updateErrorWriting",function () {
    if ($("#dealerIdName").val()=="no"){
        alert("dealerIdName不能为空！");
        $("#dealerIdName").addClass("redcolor");
        return false;
    }
    var count=0;
    var newId=id;
    var dealerid=$("#dealerIdName").val();
    var amaldarid="";
    if ($("#amaldarIdName").val()==userName){
        amaldarid=$("#amaldarIdName").attr("num");
    }
    for (var i=0;i<list.length;i++){
        if (list[i]==dealerid){
            count=i;
            break;
        }
    }
    dealerid=list[count-1];
    var status=$("#status").val();
    $.ajax({
        url: "http://localhost:8080/Amaldar/protal/updateError.do",
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            id: newId,
            amaldarid : amaldarid,
            dealerid: dealerid,
            status: status
        }),
        success: function (result) {
            alert(result.data);
        }
    });

});
