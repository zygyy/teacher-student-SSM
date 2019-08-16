<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/2
  Time: 17:19
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
        function addStudentMessage() {
            $("#addStudent").dialog("open").dialog("setTitle", "新增学生");
        }

        function save() {
            var name = $("#name").val();
            var password = $("#password").val();
            var tel = $("#tel").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/admin/addStudentMessage",
                dataType: "json",
                data: {
                    "name": name,
                    "password": password,
                    "tel": tel,
                },
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#addStudent").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                },
                error: function () {
                    $.messager.alert("系统提示", "保存失败！");
                    return;
                }
            })
        }

        function close() {
            $("#addStudent").dialog("close");
        }

        function resetValue(){
            $("#name").val("");
            $("#password").val("");
            $("#tel").val("");
        }
    </script>

</head>
<body style="margin: 1px">
<table id="dg" title="学生管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/adminGetStudentMessage" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="studentname" width="50" align="center">学生姓名</th>
        <th field="tel" width="50" align="center">电话</th>
        <th field="coursename" width="50" align="center">课程</th>
        <th field="teachername" width="50" align="center">任课教师</th>
        <th field="score" width="50" align="center">分数</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div id="student-buttons">
        <a href="javascript:addStudentMessage()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加学生信息</a>
    </div>
</div>

<div id="addStudent" class="easyui-dialog" style="width:400px;height:300px;padding: 10px 20px"
     closed="true" buttons="#addStudentMessage-buttons">
    <table cellspacing="8px">
        <tr>
            <td>姓名:</td>
            <td><input type="text" id="name" name="name" class="easyui-validatebox"
                       required="true"/>&nbsp;<font color="red">*</font></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" id="password" name="password" class="easyui-validatebox"
                       required="true"/>&nbsp;<font
                    color="red">*</font></td>
        </tr>

        <tr>
            <td>联系电话:</td>
            <td><input type="text" id="tel" name="tel" class="easyui-validatebox" required="true"/>&nbsp;<font
                    color="red">*</font></td>
        </tr>
    </table>
</div>

<div id="addStudentMessage-buttons">
    <a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:close()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
