layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

        //加载页面数据
        var newsData = '';
        $.get("http://localhost:8082/Manager/getList.do", function (result) {
            var data = result.data;
            newsData = data;
            //执行加载数据的方法
            newsList();
        })

    //查询
    $(".search_btn").click(function(){
        if($(".search_input").val() != ''){
            var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                $.ajax({
                    url : "http://localhost:8082/Manager/selectLikeUsername.do",
                    type : "get",
                    dataType : "json",
                    data:"username="+$(".search_input").val().trim(),
                    success : function(result){
                        var data = result.data;
                        newsData = data;
                        newsList();
                    }
                })

                layer.close(index);
            },2000);
        }else{
            layer.msg("请输入需要查询的内容");
        }
    })

	//添加
	$(".newsAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加管理员用户",
			type : 2,
			content : "newsAdd.html",
			success : function(layero, index){
				layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})




	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
		    var names="";
            for(var j=0;j<$checked.length;j++){
                for(var i=0;i<newsData.length;i++){
                    if(newsData[i].id == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
                        var id="#"+newsData[i].id;
                        names += $(id).parent().parent().find("td:eq(2)").text()+",";
                    }
                }
            }
            names=names.substring(0,names.length-1);
			layer.confirm("确定删除选中这些用户"+names+"?",{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
                    var ids="";
	            	for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<newsData.length;i++){
							if(newsData[i].id == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
							    var id="#"+newsData[i].id;
							    ids += newsData[i].id+",";
                                $(id).parents("tr").remove();
								newsData.splice(i,1);
                                newsList(newsData);
							}
						}
	            	}

	            	$.ajax({
                        url : "http://localhost:8082/Manager/deleteSome.do?ids="+ids,
                        type : "DELETE",
                        dataType: "json",//预期服务器返回的数据类型
                        success: function (result) {
                            if (result.exception!=null){
                                alert(result.exception);
                            }
                        }
                    })

	            	$('.news_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);
					layer.msg("删除成功");
	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的用户");
		}
	})

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//是否展示
	form.on('switch(isShow)', function(data){
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
			layer.msg("展示状态修改成功！");
        },2000);
	})





	//操作
	$("body").on("click",".news_edit",function(){  //编辑
        var _this = $(this);
        sessionStorage.setItem("id", _this.attr("data-id"));
        var index = layui.layer.open({
            title : "编辑用户信息",
            type : 2,
            content : "edit.html",
            success : function(layero, index){

            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(index);
        })
        layui.layer.full(index);
	});




	$("body").on("click",".news_collect",function(){  //查看.
        var _this = $(this);
        sessionStorage.setItem("id",_this.attr("data-id"));
        var index = layui.layer.open({
            title : "显示用户信息",
            type : 2,
            content : "showInfo.html",
            success : function(layero, index){

            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(index);
        })
        layui.layer.full(index);
	});


	$("body").on("click",".news_del",function(){  //删除
		var currentId=sessionStorage.getItem("login");
		var _this = $(this);
		if (_this.attr("data-id")==currentId){
		    layer.msg("不能删除自己的用户！");
			return;
		}
		var name=$(this).parent().parent().find("td:eq(2)").text();
		layer.confirm("确定删除用户"+name+"?",{icon:3, title:'提示信息'},function(index){
			_this.parents("tr").remove();
			for(var i=0;i<newsData.length;i++){
				if(newsData[i].id == _this.attr("data-id")){
				    $.ajax({
                        url : "http://localhost:8082/Manager/delete.do?id="+newsData[i].id+"",
                        type : "delete",
                        dataType: "json",//预期服务器返回的数据类型
                        success: function (result) {
							if (result.code==0){
								layer.msg(result.data);
							}
							if (result.exception!=null) {
                                layer.msg(result.exception);
							}
                        }
                    });
					newsData.splice(i,1);
					newsList(newsData);
					break;
				}
			}
			layer.close(index);
		});
	});

	function newsList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = newsData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
						+'<td>'+currData[i].id+'</td>'
			    	+'<td>'+currData[i].name+'</td>'
			    	+'<td>'+currData[i].username+'</td>'
			    	+'<td>'+currData[i].sex+'</td>'
			    	+'<td>'+currData[i].phone+'</td>'
			    	+'<td>'+currData[i].email+'</td>'
                        +'<td>'+Format(new Date(currData[i].birthday),"yyyy-MM-dd")+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-small news_edit" data-id="'+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-normal layui-btn-small news_collect" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe600;</i> 查看</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-small news_del" id="'+currData[i].id+'" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 20; //每页出现的数据量
		if(that){
			newsData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".news_content").html(renderDate(newsData,obj.curr));
				$('.news_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
});

