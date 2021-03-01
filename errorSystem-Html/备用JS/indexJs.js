jQuery(function ($) {
    var performance = [12, 43, 34, 22, 12, 33, 4, 17, 22, 34, 54, 67],
        visits = [123, 323, 443, 32],
        traffic = [
            {
                Source: "Direct", Amount: 323, Change: 53, Percent: 23, Target: 600
            },
            {
                Source: "Refer", Amount: 345, Change: 34, Percent: 45, Target: 567
            },
            {
                Source: "Social", Amount: 567, Change: 67, Percent: 23, Target: 456
            },
            {
                Source: "Search", Amount: 234, Change: 23, Percent: 56, Target: 890
            },
            {
                Source: "Internal", Amount: 111, Change: 78, Percent: 12, Target: 345
            }];


    $("#shieldui-chart1").shieldChart({
        theme: "dark",

        primaryHeader: {
            text: "Visitors"
        },
        exportOptions: {
            image: false,
            print: false
        },
        dataSeries: [{
            seriesType: "area",
            collectionAlias: "Q Data",
            data: performance
        }]
    });

    $("#shieldui-chart2").shieldChart({
        theme: "dark",
        primaryHeader: {
            text: "Traffic Per week"
        },
        exportOptions: {
            image: false,
            print: false
        },
        dataSeries: [{
            seriesType: "pie",
            collectionAlias: "traffic",
            data: visits
        }]
    });

    $("#shieldui-grid1").shieldGrid({
        dataSource: {
            data: traffic
        },
        sorting: {
            multiple: true
        },
        rowHover: false,
        paging: false,
        columns: [
            { field: "Source", width: "170px", title: "Source" },
            { field: "Amount", title: "Amount" },
            { field: "Percent", title: "Percent", format: "{0} %" },
            { field: "Target", title: "Target" },
        ]
    });
});


$(function () {
    $.ajax({
        url: "http://localhost:8080/Amaldar/protal/getErrorByStatus.do",
        data: "status=" + "",
        type: "GET",
        success: function (result) {
            $("#stu_table tbody").empty();
            var errors = result.data;
            $.each(errors, function(index, item) {
                var idTd = $("<td></td>").append(item.id);
                var errorthemeTd = $("<td></td>").append(item.errortheme);
                var demonameTd = $("<td></td>").append(item.demoname);
                var ponderanceTd = $("<td></td>").append(item.ponderance);
                var priorityTd = $("<td></td>").append(item.priority);
                var reportTd = $("<td></td>").append(item.reporterName);
                if (item.dealerName==""){
                    var dealerTd = $("<td style='color: #b30000'></td>").append("开发人员未分配");
                }else {
                    var dealerTd = $("<td></td>").append(item.dealerName);
                }
                if (item.amaldarName==""){
                    var amaldarTd = $("<td style='color: #b30000'></td>").append("经理人员未查看");
                }else {
                    var amaldarTd=$("<td></td>").append(item.amaldarName);
                }
                var statusTd = $("<td></td>").append(showStatusName(item.status));
                var createTd = $("<td></td>").append(
                    new Date(item.createdate).toLocaleDateString());
                var editBtn = $("<button></button>").addClass(
                    "btn btn-primary btn-sm edit-btn").append(
                    $("<span></span>").addClass(
                        "glyphicon glyphicon-pencil")).append("编辑");
                editBtn.attr("edit-stuId",item.id);

                var editBtnTd = $("<td></td>").append(editBtn);

                $("<tr></tr>").append(idTd).append(errorthemeTd).append(demonameTd)
                    .append(ponderanceTd).append(priorityTd).append(reportTd)
                    .append(dealerTd).append(amaldarTd).append(createTd).append(statusTd)
                    .append(editBtnTd).appendTo(
                    "#stu_table tbody");

            });
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
    } else {
        return "未知";
    }
}

function show() {
    var status=$("#status").val().trim();
    $("#b1").nextAll("span").remove();
    if (status!="nochooes") {
        $.ajax({
            url: "http://localhost:8080/Amaldar/protal/getErrorByStatus.do",
            data: "status=" + status,
            type: "GET",
            success: function (result) {
                //显示学生数据
                if (result.success) {
                    build_stu_table(result);
                } else {
                    $("#stu_table tbody").empty();
                    $("#b1").after("<span style='color:#b30000; margin-left: 10px;font-size: 16px'>没有该状态的数据！</span>");
                }
            }

        });
    }else {
        $("#b1").after("<span style='color:#b30000; margin-left: 10px;font-size: 16px'>请选择状态！</span>");
    }
};


function build_stu_table(result) {
    $("#stu_table tbody").empty();
    var errors = result.data;
    $.each(errors, function(index, item) {
        var idTd = $("<td></td>").append(item.id);
        var errorthemeTd = $("<td></td>").append(item.errortheme);
        var demonameTd = $("<td></td>").append(item.demoname);
        var ponderanceTd = $("<td></td>").append(item.ponderance);
        var priorityTd = $("<td></td>").append(item.priority);
        var reportTd = $("<td></td>").append(item.reporterName);
        if (item.dealerName==""){
            var dealerTd = $("<td style='color: #b30000'></td>").append("开发人员未分配");
        }else {
            var dealerTd = $("<td></td>").append(item.dealerName);
        }
        if (item.amaldarName==""){
            var amaldarTd = $("<td style='color: #b30000'></td>").append("经理人员未查看");
        }else {
            var amaldarTd=$("<td></td>").append(item.amaldarName);
        }
        var statusTd = $("<td></td>").append(showStatusName(item.status));
        var createTd = $("<td></td>").append(
            new Date(item.createdate).toLocaleDateString());
        var editBtn = $("<button></button>").addClass(
            "btn btn-primary btn-sm edit-btn").append(
            $("<span></span>").addClass(
                "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit-stuId",item.id);


        var editBtnTd = $("<td></td>").append(editBtn);

        $("<tr></tr>").append(idTd).append(errorthemeTd).append(demonameTd)
            .append(ponderanceTd).append(priorityTd).append(reportTd)
            .append(dealerTd).append(amaldarTd).append(createTd).append(statusTd)
            .append(editBtnTd).appendTo(
            "#stu_table tbody");

    });

}

$(document).on("click",".edit-btn",function() {
    sessionStorage.setItem("errorid",$(this).attr("edit-stuid") );
    window.location.href="errorWriting.html";
});
