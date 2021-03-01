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

	//创建一个编辑器
        var addNewsArray = [],addNews;
        form.on("submit(addNews)", function () {
            //是否添加过信息
            if (window.sessionStorage.getItem("addNews")) {
                addNewsArray = JSON.parse(window.sessionStorage.getItem("addNews"));
            }

            var sex="";
            if ($("#sex").is(':checked')){
            	sex=$("#sex").val().trim();
			}else {
                sex=$("#sex2").val().trim();
			}

            addNews = '{"name":"' + $("#name").val() + '",';  //文章名称
            addNews += '"username":"' + $("#username").val() + '",'; //开放浏览
            addNews += '"password":"' + $("#password").val() + '",'; //发布时间
            addNews += '"birth":"' + $("#birthday").val() + '",'; //文章作者
            addNews += '"email":"' + $("#email").val() + '",'; //文章作者
            addNews += '"sex":"' + sex + '",';  //是否展示
            addNews += '"phone":"' + $("#phone").val() + '"}'; //审核状态
            addNewsArray.unshift(JSON.parse(addNews));
            window.sessionStorage.setItem("addNews", JSON.stringify(addNewsArray));
            //弹出loading

            $.ajax({
                url: "http://localhost:8082/Tester/Manager/add.do",
                type: "POST",
                dataType: "json",
                data: JSON.stringify(addNewsArray[0]),
                contentType: "application/json",
                success: function (result) {
                    if (result.code == 0) {
                        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                        setTimeout(function () {
                            top.layer.close(index);
                            top.layer.msg("用户添加成功！");
                            layer.closeAll("iframe");
                        }, 2000);
                        $('#form1')[0].reset();
                    }
                    if (result.exception != null) {
                        alert(result.exception);
                    }
                }
            });
            return false;
        });
	
});
