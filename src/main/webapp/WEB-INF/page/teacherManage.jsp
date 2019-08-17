<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/9
  Time: 10:31
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

        /*教师修改自己的登录密码*/
        function openModifyPasswordDialog() {
            $("#modifyPassword").dialog("open").dialog("setTitle", "修改密码")
        }

        /*教师修改自己的个人信息*/
        function openTeacherModify() {
            var select = $("#dg").datagrid("getSelections");
            if (select.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据");
                return;
            }
            var row = select[0];
            $("#modify").dialog("open").dialog("setTitle", "编辑教师信息");
            $("#mt").form("load", row);
        }

        /*保存按钮*/
        function save() {
            var userid =${currentUserid};
            var email = $("#email").val();
            var tel = $("#tel").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/teacher/updateTeacherMessage",
                dataType: "json",
                data: {
                    "userid": userid,
                    "email": email,
                    "tel": tel,
                },
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#modify").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "请再次确认密码，保存失败！");
                        return;
                    }
                },
            })
        }


        /*关闭按钮*/
        function close() {
            $("#modify").dialog("close");
        }

        /*密码修改保存按钮*/
        function savePassword() {
            var password = $("#passwordmodify").val();
            var passwordqueren = $("#passwordensure").val();
            var code = $("#code").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/updatePasswordAdminAndTeacher/${currentUserid}/"+passwordqueren+"/"+code,
                dataType: "json",
                data: {
                    "password": password,
                },
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#modifyPassword").dialog("close");
                    } else if (result.codeFalse) {
                        $.messager.alert("系统提示", "验证码错误，保存失败！");
                        return;
                    } else {
                        $.messager.alert("系统提示", "密码错误，保存失败！");
                        return;
                    }
                },
            })

        }

        /*清空填写的数据*/
        function resetValue() {
            $("#passwordmodify").val("");
            $("#passwordensure").val("");
            $("#email").val("");
            $("#tel").val("");
            $("#code").val("");
        }

        /*密码修改关闭按钮*/
        function closePasswordModify() {
            $("#modifyPassword").dialog("close");
            resetValue();
        }

        /*获取验证码按钮，直接传请求*/
        function achieveCode() {
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/user/getAchieveCode/${currentUserid}",
                dataType: "json",
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "验证码发送成功，请注意查收！");
                    }
                },
            })
        }

    </script>
</head>
<body style="margin: 1px">
<table id="dg" title="教师个人信息管理" class="easyui-datagrid"
       url="${pageContext.request.contextPath}/teacher/teacherMessageList/${currentUserid}" fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <th field="teacherid" width="302" align="center">教师编号</th>
        <th field="email" width="302" align="center">电子邮箱</th>
        <th field="tel" width="302" align="center">联系电话</th>
        <th field="coursename" width="302" align="center">所教课程</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openTeacherModify()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改个人资料</a>
        <a href="JavaScript:openModifyPasswordDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改登录密码</a>
    </div>
</div>

<%--修改密码框--%>
<div id="modifyPassword" class="easyui-dialog" style="width:500px;height:200px;padding: 10px 20px"
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
        <tr>
            <td>验证码:</td>
            <td><input type="text" id="code" name="code" class="easyui-validatebox" required="true"/>&nbsp;<font
                    color="red">*</font></td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td><a href="javascript:achieveCode()" class="easyui-linkbutton" iconCls="icon-ok">获取验证码</a></td>
        </tr>

    </table>
</div>

<div id="modifyPassword-buttons">
    <a href="javascript:savePassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModify()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


<%--修改个人资料框--%>
<div id="modify" class="easyui-dialog" style="width:600px;height:200px;padding: 10px 20px"
     closed="true" buttons="#modify-buttons">
    <form id="mt" method="post">
        <table cellspacing="8px">
            <tr>
                <td>邮箱:</td>
                <td><input type="text" id="email" name="email" class="easyui-validatebox" validType="email"
                           required="true"/>&nbsp;<font color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>电话:</td>
                <td><input type="text" id="tel" name="tel" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
        </table>
    </form>
</div>

<div id="modify-buttons">
    <a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:close()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
