<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/11
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        /*选课*/
        function chooseCourse() {
            var selectedRow = $("#dg").datagrid("getSelections");
            if (selectedRow.length != 1) {
                $.messager.alert("系统提示", "请选择一个课程！");
                return;
            }
            var courseid = selectedRow[0].courseid;
            var userid =${currentStudentUserid};
            $.messager.confirm("系统提示", "您确定要选择此课程？", function (r) {
                if (r) {
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/student/selectCourse",
                        dataType: "json",
                        data: {
                            "userid": userid,
                            "courseid": courseid,
                        },
                        success: function (result) {
                            if (result.success) {
                                $.messager.alert("系统提示", "选课成功！");
                            } else if (result.chooseRepeat) {
                                $.messager.alert("系统提示", "选课重复，请重新选择！");
                                return;
                            } else {
                                $.messager.alert("系统提示", "抱歉该课程还未分配教师，请重新选择！");
                                return;
                            }
                        },
                    })
                }
            });
        }
    </script>
</head>
<body>
<table id="dg" title="选课管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/student/chooseCourseList"
       fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <%--<th field="cb" checkbox="true" align="center"></th>--%>
        <th hidden="hidden" field="courseid" align="center">课程号</th>
        <th field="coursename" width="50" align="center">课程</th>
        <th field="comment" width="50" align="center">课程介绍</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="JavaScript:chooseCourse()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">选课</a>
    </div>
</div>
</body>
</html>
