<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/11
  Time: 16:10
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
        /*学生修改自己的登录密码*/
        function openModifyPasswordDialog() {
            $("#modifyPassword").dialog("open").dialog("setTitle", "修改密码")
        }

        /*密码修改关闭按钮*/
        function closePasswordModify() {
            $("#modifyPassword").dialog("close");
            resetValue();
        }

        /*学生密码修改保存按钮*/
        function savePassword() {
            var password = $("#passwordmodify").val();
            var passwordqueren = $("#passwordensure").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/updatePassword/${currentStudentUserid}/"+passwordqueren,
                dataType: "json",
                data: {
                    "password": password,
                },
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#modifyPassword").dialog("close");
                    } else {
                        $.messager.alert("系统提示", "请再次确认密码，保存失败！");
                        return;
                    }
                },
            })
        }

        /*清空填写的数据*/
        function resetValue() {
            $("#passwordmodify").val("");
            $("#passwordensure").val("");
        }


    </script>
</head>
<body>
<table id="dg" title="学生管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/student/studentGetPersonalMessage/${currentStudentUserid}"
       fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <th field="coursename" width="400" align="center">课程</th>
        <th field="teachername" width="400" align="center">任课教师</th>
        <th field="score" width="400" align="center">分数</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="JavaScript:openModifyPasswordDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改登录密码</a>
    </div>
</div>

<%--修改密码框--%>
<div id="modifyPassword" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
     closed="true" buttons="#modifyPassword-buttons">
    <table cellspacing="8px">
        <tr>
            <td>新密码:</td>
            <td><input type="password" id="passwordmodify" name="password" class="easyui-validatebox"
                       required="true"/>&nbsp;<font color="red">*</font></td>
        </tr>
        <tr>
            <td>确认密码:</td>
            <td><input type="password" id="passwordensure" name="passwordqueren" class="easyui-validatebox"
                       required="true"/>&nbsp;<font
                    color="red">*</font></td>
        </tr>
    </table>
</div>


<div id="modifyPassword-buttons">
    <a href="javascript:savePassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModify()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


</body>
</html>
