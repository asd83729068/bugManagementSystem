layui.config({
    base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var usersData = '';
    $.get("http://localhost:8082/ErrorWriting/Manager/getAll.do",{
        pageSize:5, //显示页面的数量
        pageindex:1 //当前页
    }, function (data) {
        usersData = data.data;
        //执行加载数据的方法
        usersList(data.data);
    })

    //表格数据和分页
    function usersList(that) {
        //渲染数据
        function renderDate(curr) {
            var dataHtml = '';
            if (!that) {
                currData = usersData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td>' + currData[i].id+ '</td>'
                        + '<td>' + currData[i].Name + '</td>'
                        + '<td>' + currData[i].Sex + '</td>'
                        + '<td>' + currData[i].Email + '</td>'
                        + '<td>' + currData[i].Address + '</td>'
                        + '<td>' + currData[i].Birth + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini users_edit layui-btn-mini"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="' + currData[i].Id + '"><i class="layui-icon"></i> 删除</a>'
                        + '</td>'
                        + '</tr>';
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 5; //每页出现的数据量
        laypage({
            cont: "page",
            pages: Math.ceil(usersData.length / nums), //得到总页数
            skip: true, //是否开启跳页
            groups: 5, //连续显示分页数
            jump: function (obj) {
                $(".users_content").html(renderDate(obj.curr));   //渲染数据
                form.render(); //渲染表单
            }
        })
    }

})
