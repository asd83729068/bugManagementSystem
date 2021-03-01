layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    $(function () {
        var id=sessionStorage.getItem("id");
        $.ajax({
            url: "http://localhost:8082/Manager/getOne.do",
            type: "GET",
            dataType: "json",
            data:"id="+id,
            success: function (result) {
                var data=result.data;
                if (result.code == 0) {
                    $("#sex").val(data.sex);
                    $("#name").val(data.name);
                    $("#username").val(data.username);
                    $("#phone").val(data.phone);
                    $("#email").val(data.email);
                    $("#birthday").val(Format(new Date(data.birthday),"yyyy-MM-dd"));
                }
                if (result.exception != null) {
                    alert(result.exception);
                }
            }
        });
    })
});
