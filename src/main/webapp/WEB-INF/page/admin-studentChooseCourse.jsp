<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/29
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>

</head>
<body style="margin: 1px">
<!-- 为ECharts准备一个具备大小（宽高）的DOM-->
<div id="ployLine" style="width: 100%;height:90%;"></div>
</body>
<script type="text/javascript">
    window.onload = function () {
        getData();
    };

    /* 基于准备好的dom，初始化echarts实例*/
    var ployLine = echarts.init(document.getElementById('ployLine'));
    var courseName = [];
    var num = [];
    // 指定图表的配置项和数据
    ployLine.setOption({
        title: {
            text: '学生选课情况折线图统计'
        },
        tooltip: {},
        legend: {
            data: ['']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            type: 'bar',
            data: []
        }]
    });


    function getData() {
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/courseNumber",
            type: 'post',
            dataType: 'json',
            header: {'Content-Type': 'application/json'},
            success: function (result) {
                for (var i in result.data) {
                    courseName.push(result.data[i].coursename);
                    num.push(result.data[i].total)
                }
                console.info(courseName)
                console.info(num)

                /*  填入数据*/
                ployLine.setOption({
                    xAxis: {
                        data: courseName
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        data: num
                    }]
                });
                return result;
            }
        })
    }
</script>
</html>
