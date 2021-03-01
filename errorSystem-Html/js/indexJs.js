 // layui.config({
 //        base : "js/"
 //    }).use(['form','layer','jquery','laypage'],function(){
 //        var form = layui.form(),
 //            layer = parent.layer === undefined ? layui.layer : parent.layer,
 //            laypage = layui.laypage,
 //            $ = layui.jquery;
 //
 //        //加载页面数据
 //        var usersData = '';
 //        $.get("http://119.23.29.186:8082/ErrorWriting/Manager/getAll.do",{
 //            pageSize:10, //显示页面的数量
 //            pageindex:1 //当前页
 //        }, function (data) {
 //            usersData = data.data;
 //            //执行加载数据的方法
 //            usersList(data.data);
 //        });
 //
 //     //表格数据和分页
 //     function usersList(that) {
 //         //渲染数据
 //         function renderDate(curr) {
 //             var dataHtml = '';
 //             if (!that) {
 //                 currData = usersData.concat().splice(curr * nums - nums, nums);
 //             } else {
 //                 currData = that.concat().splice(curr * nums - nums, nums);
 //             }
 //             if (currData.length != 0) {
 //                 for (var i = 0; i < currData.length; i++) {
 //                     dataHtml += '<tr>'
 //                         + '<td>' + currData[i].id+ '</td>'
 //                         + '<td>' + currData[i].errortheme + '</td>'
 //                         + '<td>' + currData[i].demoname + '</td>'
 //                         + '<td>' + currData[i].ponderance + '</td>'
 //                         + '<td>' + currData[i].priority + '</td>'
 //                         + '<td>' + currData[i].reporterName + '</td>'
 //                         + '<td>' + currData[i].dealerName + '</td>'
 //                         + '<td>' + currData[i].amaldarName + '</td>'
 //                         + '<td>' + currData[i].status + '</td>'
 //                         + '<td>' + Format(new Date(currData[i].createdate),"yyyy-MM-dd") + '</td>'
 //                         + '<td>'
 //                         + '<a class="layui-btn layui-btn-mini users_edit layui-btn-mini" edit-stuId="'+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
 //                         + '</td>'
 //                         + '</tr>';
 //                 }
 //             } else {
 //                 dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
 //             }
 //             return dataHtml;
 //         }
 //
 //         //分页
 //         var nums = 10; //每页出现的数据量
 //         laypage({
 //             cont: "page",
 //             pages: Math.ceil(usersData.length / nums), //得到总页数
 //             skip: true, //是否开启跳页
 //             groups: 5, //连续显示分页数
 //             jump: function (obj) {
 //                 $("#list").html(renderDate(obj.curr));   //渲染数据
 //                 form.render(); //渲染表单
 //             }
 //         })
 //     }

 var page=1;
 var totalPage=0;

 var page2=1;
 var totalPage2=0;

 $(function() {
     $("#page1").hide();
     $("#row2").hide();
 })


 $(function() {
     showPage(1);
 });

 var status = "";
 var condition = "";
 function show() {
     status=$("#status").val().trim();
     condition=$("#condition").val().trim();
     $("#b1").nextAll("span").remove();
     if (status != "nochooes") {
         $("#row1").hide();
         $("#row2").show();
         $("#page11").hide();
         page2=1;
         $("#text11").val(page2);
         showPage2(page2,status,condition);
     } else {
         $("#b1").after("<span style='color:#b30000; margin-left: 10px;font-size: 16px'>请选择状态！</span>");
     }
 };

$("#page1").click(function () {

        page--;
        if (page <= 1) {
            $("#page1").hide();
        }
        $("#text1").val(page);
        if (page < totalPage) {
            $("#page2").show();
        }
        showPage(page);

})

 $("#page2").click(function () {
     page++;
     if (page>1){
         $("#page1").show();
     }
     $("#text1").val(page);
     if (page==totalPage){
         $("#page2").hide();
     }
     showPage(page);
 })


 $("#retu").click(function () {
     if ($("#text1").val!=null && $("#text1").val()!=page && $("#text1").val()<=totalPage && $("#text1").val()>0){
         showPage($("#text1").val());
         page=$("#text1").val();
     }else {
         return false;
     }
     if ($("#text1").val()==1){
         $("#page1").hide();
     }
     if ($("#text1").val()>1){
         $("#page1").show();
     }
     if ($("#text1").val()==totalPage){
         $("#page2").hide();
     }
     if ($("#text1").val()<totalPage){
         $("#page2").show();
     }
 })


 $("#page11").click(function () {

     page2--;
     if (page2 <= 1) {
         $("#page11").hide();
     }
     $("#text11").val(page2);
     if (page2 < totalPage2) {
         $("#page22").show();
     }
     showPage2(page2,status,condition);

 })

 $("#page22").click(function () {
     page2++;
     if (page2>1){
         $("#page11").show();
     }
     $("#text11").val(page2);
     if (page2==totalPage2){
         $("#page22").hide();
     }
     showPage2(page2,status,condition);
 })


 $("#retu2").click(function () {
     if ($("#text11").val()!=null && $("#text11").val()!=page2 && $("#text11").val()<=totalPage2 && $("#text11").val()>0){
         showPage2($("#text11").val(),status,condition);
         page2=$("#text11").val();
     }else {
         return false;
     }
     if ($("#text11").val()==1){
         $("#page11").hide();
     }
     if ($("#text11").val()>1){
         $("#page11").show();
     }
     if ($("#text11").val()==totalPage2){
         $("#page22").hide();
     }
     if ($("#text11").val()<totalPage2){
         $("#page22").show();
     }
 })

 function showPage(page) {
     $.ajax({
         url : "http://localhost:8082/ErrorWriting/Manager/page.do",
         data : "pageNum="+page+"&pageSize="+11+"&status="+"&condition=",
         type : "GET",
         success : function(result) {
             $("#stu_table tbody").empty();
             var errors = result.data.content;
             totalPage=result.data.totalPages;
             $("#totalPage").text(totalPage);
             if (totalPage == 1){
                 $("#page2").hide();
             }
             $.each(errors, function (index, item) {
                 var idTd = $("<td></td>").append(item.id);
                 var errorthemeTd = $("<td></td>").append(item.errortheme);
                 var demonameTd = $("<td></td>").append(item.demoname);
                 var ponderanceTd = $("<td></td>").append(item.ponderance);
                 var priorityTd = $("<td></td>").append(item.priority);
                 var reportTd = $("<td></td>").append(item.reporterName);
                 if (item.dealerName == "") {
                     var dealerTd = $("<td style='color: #b30000'></td>").append("开发人员未分配");
                 } else {
                     var dealerTd = $("<td></td>").append(item.dealerName);
                 }
                 if (item.amaldarName == "") {
                     var amaldarTd = $("<td style='color: #b30000'></td>").append("经理人员未查看");
                 } else {
                     var amaldarTd = $("<td></td>").append(item.amaldarName);
                 }
                 var statusTd = $("<td></td>").append(showStatusName(item.status));
                 var createTd = $("<td></td>").append(
                     new Date(item.createdate).toLocaleDateString());
                 var editBtn = $("<button></button>").addClass(
                     "btn btn-primary btn-sm edit-btn").append(
                     $("<span></span>").addClass(
                         "glyphicon glyphicon-pencil")).append("编辑");
                 editBtn.attr("edit-stuId", item.id);

                 var editBtnTd = $("<td></td>").append(editBtn);

                 $("<tr></tr>").append(idTd).append(errorthemeTd).append(demonameTd)
                     .append(ponderanceTd).append(priorityTd).append(reportTd)
                     .append(dealerTd).append(amaldarTd).append(createTd).append(statusTd)
                     .append(editBtnTd).appendTo(
                     "#stu_table tbody");

             });
         }

     });
 }


 function showPage2(page2,status,condition) {
     $.ajax({
         url : "http://localhost:8082/ErrorWriting/Manager/page.do",
         data : "pageNum="+page2+"&pageSize="+11+"&status="+status+"&condition="+condition,
         type : "GET",
         success : function(result) {
             $("#stu_table tbody").empty();
             var errors = result.data.content;
             totalPage2=result.data.totalPages;
             if (totalPage2!=0){
                 $("#b1").nextAll("span").remove();
             }else {
                 $("#b1").after("<span style='color:#b30000; margin-left: 10px;font-size: 16px'>没有任何数据！</span>");
             }
             if (totalPage2==1 || totalPage2==0 || totalPage2<$("#text11").val()){
                $("#page22").hide();
            }
            if (totalPage2!=1 && totalPage2!=0 && totalPage2>$("#text11").val()) {
                $("#page22").show();
            }
             $("#totalPage2").text(totalPage2);
             $.each(errors, function (index, item) {
                 var idTd = $("<td></td>").append(item.id);
                 var errorthemeTd = $("<td></td>").append(item.errortheme);
                 var demonameTd = $("<td></td>").append(item.demoname);
                 var ponderanceTd = $("<td></td>").append(item.ponderance);
                 var priorityTd = $("<td></td>").append(item.priority);
                 var reportTd = $("<td></td>").append(item.reporterName);
                 if (item.dealerName == "") {
                     var dealerTd = $("<td style='color: #b30000'></td>").append("开发人员未分配");
                 } else {
                     var dealerTd = $("<td></td>").append(item.dealerName);
                 }
                 if (item.amaldarName == "") {
                     var amaldarTd = $("<td style='color: #b30000'></td>").append("经理人员未查看");
                 } else {
                     var amaldarTd = $("<td></td>").append(item.amaldarName);
                 }
                 var statusTd = $("<td></td>").append(showStatusName(item.status));
                 var createTd = $("<td></td>").append(
                     new Date(item.createdate).toLocaleDateString());
                 var editBtn = $("<button></button>").addClass(
                     "btn btn-primary btn-sm edit-btn").append(
                     $("<span></span>").addClass(
                         "glyphicon glyphicon-pencil")).append("编辑");
                 editBtn.attr("edit-stuId", item.id);

                 var editBtnTd = $("<td></td>").append(editBtn);

                 $("<tr></tr>").append(idTd).append(errorthemeTd).append(demonameTd)
                     .append(ponderanceTd).append(priorityTd).append(reportTd)
                     .append(dealerTd).append(amaldarTd).append(createTd).append(statusTd)
                     .append(editBtnTd).appendTo(
                     "#stu_table tbody");

             });
         }

     });
 }



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



    $(document).on("click", ".edit-btn", function () {
        sessionStorage.setItem("errorid", $(this).attr("edit-stuid"));
        window.location.href = "errorWriting.html";
    });

//});
