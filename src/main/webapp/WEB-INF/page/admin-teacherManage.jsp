<%--
  Created by IntelliJ IDEA.
  User: 周宇
  Date: 2019/8/2
  Time: 17:20
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
        /*根据编号搜索教师*/
        function search() {
            $("#dg").datagrid('load', {
                "teacherid": $("#teacherid").val()
            });
        }

        /*load方法：它的参数为一个json对象，里面写的是你要传输的参数的键值对，调用这个方法来加载数据的时候，它传给后台的分页信息是从第一页开始的。*/

        /*打开新增教师弹框*/
        function addTeacherMessage() {
            $("#addTeacher").dialog("open").dialog("setTitle", "新增教师");
        }

        /*关闭弹框*/
        function close() {
            $("#addTeacher").dialog("close");
        }

        /*保存按钮*/
        function save() {
            var name = $("#teachername").val();
            var password = $("#teacherpassword").val();
            var email = $("#email").val();
            var tel = $("#tel").val();
            var courseid = $("#courseid").val();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/admin/addTeacherMessage",
                dataType: "json",
                data: {
                    "name": name,
                    "password": password,
                    "email": email,
                    "tel": tel,
                    "courseid": courseid,
                },
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#addTeacher").dialog("close");
                        $("#dg").datagrid("reload");
                        /*它传给后台的分布信息是当前的页码，就是实现刷新当前页的功能*/
                    } else {
                        $.messager.alert("系统提示", "该课程已分配了教师,请重新选择！");
                        return;
                    }
                },
            })

        }

        /*再次添加时清空之前添加的数据*/
        function resetValue() {
            $("#teachername").val("");
            $("#teacherpassword").val("");
            $("#email").val("");
            $("#tel").val("");
            $("#courseid").val("");
        }

    </script>
</head>
<body style="margin: 1px">
<table id="dg" title="教师管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/adminGetTeacherMessage" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="teacherid" width="50" align="center">教师编号</th>
        <th field="name" width="50" align="center">教师姓名</th>
        <th field="email" width="50" align="center">邮件</th>
        <th field="tel" width="50" align="center">联系电话</th>
        <th field="coursename" width="50" align="center">所教课程</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div id="modify-buttons">
        <a href="javascript:addTeacherMessage()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加教师信息</a>
    </div>
    <div>
        &nbsp;教师编号:&nbsp;<input type="text" id="teacherid" size="20" onkeydown="if(event.keyCode==13) search()"/>
        <a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>


<div id="addTeacher" class="easyui-dialog" style="width:400px;height:300px;padding: 10px 20px"
     closed="true" buttons="#addTeacherMessage-buttons">
    <table cellspacing="8px">
        <tr>
            <td>姓名:</td>
            <td><input type="text" id="teachername" name="name" class="easyui-validatebox"
                       required="true"/>&nbsp;<font color="red">*</font></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" id="teacherpassword" name="password" class="easyui-validatebox" required="true"/>&nbsp;<font
                    color="red">*</font></td>
        </tr>

        <tr>
            <td>邮箱:</td>
            <td><input type="text" id="email" name="email" class="easyui-validatebox" required="true"/>&nbsp;<font
                    color="red">*</font></td>
        </tr>
        <tr>
            <td>联系电话:</td>
            <td><input type="text" id="tel" name="tel" class="easyui-validatebox" required="true"/>&nbsp;<font
                    color="red">*</font></td>
        </tr>
        <tr>
            <td>分配课程号:</td>
            <td>
                <select id="courseid" name="courseid" class="input"
                        style="margin-top: 15px;height: 24px">
                    <option value="0">请为老师分配课程</option>
                    <option value="2">数学</option>
                    <option value="3">英语</option>
                    <option value="4">化学</option>
                    <option value="5">语文</option>
                    <option value="6">物理</option>
                    <option value="7">地理</option>
                    <option value="8">生物</option>
                    <option value="9">java高级</option>
                    <option value="10">C语言</option>
                    <option value="11">数据结构</option>
                    <option value="12">数据库</option>
                    <option value="13">离散数学</option>
                    <option value="14">概率论</option>
                    <option value="15">线性代数</option>
                    <option value="16">高等数学</option>
                    <option value="17">操作系统</option>
                    <option value="18">马克思主义</option>
                    <option value="19">近现代论</option>
                    <option value="20">环境与科学</option>
                    <option value="21">体育</option>
                </select>
            </td>
        </tr>

    </table>
</div>

<div id="addTeacherMessage-buttons">
    <a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:close()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>
