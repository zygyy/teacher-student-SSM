<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/9
  Time: 10:33
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
        /*添加/修改学生成绩*/
        function addScore() {
            var select = $("#dg").datagrid("getSelections");
            if (select.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = select[0];
            $("#addStudentScore").dialog("open").dialog("setTitle", "添加/修改学生成绩");
            $("#add").form("load", row);
        }

        function close() {
            $("#addStudentScore").dialog("close");
        }

        /*教师给学生修改分数*/
        function save() {
            var score = $("#score").val();
            var studentid = $("#studentid").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/teacher/addStudentScore",
                dataType: "json",
                data: {
                    "score": score,
                    "studentid": studentid,
                },
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#addStudentScore").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "分数超过范围，请从新打分！");
                        return;
                    }
                },
            })
        }

        /*清空*/
        function resetValue() {
            $("#score").val("");
        }

        /*导出所选的学生的excel表格*/
        function excel() {
            var selectedRow = $("#dg").datagrid("getSelections");
            if (selectedRow.length == 0) {
                $.messager.alert("系统提示", "请选择需要导出的学生的相关信息！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRow.length; i++) {
                strIds.push(selectedRow[i].studentid);
            }
            var excelIds = strIds.join();
            $.messager.confirm("系统提示", "您确定要导出这<font color=red>" + selectedRow.length + "</font>条数据吗?", function (r) {
                if (r) {
                    $.post("${pageContext.request.contextPath}/teacher/teacherExcel", {excelIds: excelIds}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "数据已成功导出！");
                        }
                    }, "json");
                }
            });
        }


    </script>
</head>
<body>
<table id="dg" title="学生管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/teacher/teacherGetStudentMessage/${currentUserid}" fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th hidden="hidden" field="studentid" width="50" align="center">学生id</th>
        <th field="studentname" width="50" align="center">学生姓名</th>
        <th field="tel" width="50" align="center">电话</th>
        <th field="coursename" width="50" align="center">课程</th>
        <th field="score" width="50" align="center">分数</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div id="modify-buttons">
        <a href="javascript:addScore()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加/修改学生成绩</a>
        <a href="javascript:excel()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">导出学生成绩</a>
    </div>
</div>

<div id="addStudentScore" class="easyui-dialog" style="width:300px;height:200px;padding: 10px 20px"
     closed="true" buttons="#add-buttons">
    <form id="add">
        <table cellspacing="8px">
            <tr>
                <td><input type="text" style="display: none;" id="studentid" name="studentid" class="easyui-validatebox"
                           required="true"/>&nbsp;<font color="red"></font></td>
            </tr>
            <tr>
                <td>分数:</td>
                <td><input type="text" id="score" name="score" class="easyui-validatebox"
                           required="true"/>&nbsp;<font color="red">*</font></td>
            </tr>
        </table>
    </form>
</div>

<div id="add-buttons">
    <a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:close()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


</body>
</html>
