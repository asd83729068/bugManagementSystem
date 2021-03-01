layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	//登录按钮事件
	form.on("submit(login)",function(data) {
        $.ajax({
            url: "http://localhost:8082/Manager/login.do",
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                username: $("#username").val(),
                password: $("#password").val()
            }),
            success: function (result) {
                var data=result.data;
                if (result.code == 0) {
                    sessionStorage.setItem("login",data.id);
                    window.location.href="index.html";
                } else {
                    layer.msg("用户名或密码错误！");
                }

            }
        })
        return false;
    })
})
