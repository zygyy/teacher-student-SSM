package com.zy.controller;

import com.zy.entity.*;
import com.zy.service.AdminService;
import com.zy.util.ResponseUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/5 15:32
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    /**
     * 跳转教师信息管理界面
     *
     * @return
     */
    @RequestMapping("/admin-teacherManage")
    public String teacherManage() {
        return "admin-teacherManage";
    }

    /**
     * 跳转学生课程统计
     *
     * @return
     */
    @RequestMapping("/admin-studentChooseCourse")
    public String studentCourseTotal() {
        return "admin-studentChooseCourse";
    }

    /**
     * 跳转学生信息管理界面
     *
     * @return
     */
    @RequestMapping("/admin-studentManage")
    public String studentManage() {
        return "admin-studentManage";
    }


    /**
     * 跳转管理员个人页面
     *
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping(value = "/adminManage/{userid}", method = RequestMethod.GET)
    public String adminManage(@PathVariable String userid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        /*获取请求传过来的userid，并传入下一个界面*/
        session.setAttribute("currentUserid", userid);
        return "adminManage";
    }


    /**
     * 管理员个人信息显示
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/adminMessageList/{userid}", method = RequestMethod.POST)
    public String login(@PathVariable int userid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Admin admin = new Admin();
        admin.setUserid(userid);
        List<Admin> adminMessage = adminService.getAdminMessage(admin);
        /*将查询到的数据封装为JSON格式返回前端,前端只需要对应属性名称即可获取值
         * 如果是嵌套的JSON，前端需要解析(也可以在后端先取值，再次塞到JSON中，前端filed对应属性)
         * */
        JSONArray row = new JSONArray(adminMessage);
        ResponseUtil.write(response, row);
        return null;
    }


    /**
     * 管理员个人信息修改
     *
     * @param admin
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateAdminMessage", method = RequestMethod.POST)
    public String save(Admin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        resultTotal = adminService.updateAdminMessage(admin);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }


    /**
     * 管理员查看教师信息，分页展示
     *
     * @param page
     * @param rows
     * @param teacher
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/adminGetTeacherMessage", method = RequestMethod.POST)
    /*@RequestParam可以对传入参数指定参数名*/
    public String getTeacherMessageList(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Teacher teacher, HttpServletResponse response) throws Exception {
        /*获取页码和每页记录，页面可以修改*/
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));//当前页和每页记录数塞到PageBean里面
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("teacherid", teacher.getTeacherid());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        /*JSONObject:对象JSON;JSONArray:数组JSON*/
        JSONObject teacherMessageResult = new JSONObject();
        List<Teacher> teacherList = adminService.findTeacherMessage(map);
        Long teacherMessageTotal = adminService.getTeacherMessageTotal(map);
        /*将姓名和课程先获取，再次塞入Teacher类用来接收的属性里，这样前端就不用再次解析嵌套的JSON返回值*/
        for (Teacher t : teacherList) {
            t.setName(t.getUserLogin().getName());
            t.setCoursename(t.getCourse().getCoursename());
        }
        JSONArray jsonArray = new JSONArray(teacherList);
        teacherMessageResult.put("rows", jsonArray);
        teacherMessageResult.put("total", teacherMessageTotal);
        ResponseUtil.write(response, teacherMessageResult);
        return null;

    }


    /**
     * 管理员添加教师的具体信息，为其分配课程
     *
     * @param userLogin
     * @param userLogin1
     * @param teacher
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addTeacherMessage ", method = RequestMethod.POST)
    public String addTeacherLoginMessage(UserLogin userLogin, UserLogin1 userLogin1, Teacher teacher, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        /*判断该课程是否已经分配了教师*/
        if (adminService.judgeTeacher(teacher.getCourseid()) == null) {
            int resultLoginTotal = adminService.addTeacherUserLoginMessage(userLogin);
            teacher.setUserid(adminService.getMaxUserid());//先添加userlogin表,在获取其userid,给teacher表的userid设置值

            /*把教师信息添加到辅助表中去*/
            userLogin1.setUserid(adminService.getMaxUserid());
            userLogin1.setName1(userLogin.getName());
            int resultLoginTotal1 = adminService.addTeacherIntoUserLogin1(userLogin1);
            int resultTeacherSpecificTotal = adminService.addTeacherSpecificMessage(teacher);
            if (resultLoginTotal > 0 && resultTeacherSpecificTotal > 0 && resultLoginTotal1 > 0) {
                result.put("success", true);
            }
            ResponseUtil.write(response, result);
            return null;
        } else {
            result.put("success", false);
            ResponseUtil.write(response, result);
            return null;
        }

    }


    /**
     * 管理员获取学生的相关信息
     *
     * @param page
     * @param rows
     * @param student
     * @param response
     * @return
     * @throws Exception NetWork中的header中传过来page和rows
     *                   page: 1
     *                   rows: 10
     */
    @RequestMapping(value = "/adminGetStudentMessage", method = RequestMethod.POST)
    public String adminGetStudentMessage(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Student student, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        List<Student> students = adminService.findStudentMessage(map);
        Long studentMessageTotal = adminService.getStudentMessageTotal(map);
        JSONObject studentMessageResult = new JSONObject();
        /*重新赋值*/
        for (Student s : students) {
            s.setStudentname(s.getUserLogin().getName());
            s.setCoursename(s.getCourse().getCoursename());
            s.setTeachername(s.getTeacher().getUserLogin1().getName1());
        }
        JSONArray jsonArray = new JSONArray(students);
        studentMessageResult.put("rows", jsonArray);
        studentMessageResult.put("total", studentMessageTotal);
        ResponseUtil.write(response, studentMessageResult);
        return null;
    }

    /**
     * 管理员添加学生登录账号与密码，并录入相应信息
     *
     * @param userLogin
     * @param student
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addStudentMessage ", method = RequestMethod.POST)
    public String addStudentMessage(UserLogin userLogin, Student student, HttpServletResponse response) throws Exception {
        student.setUserid(adminService.getMaxUserid() + 1);
        int resultUserLoginTotal = adminService.addStudentUserLoginMessage(userLogin);
        int resultStudentTotal = adminService.addStudentSpecificMessage(student);
        JSONObject result = new JSONObject();
        if (resultUserLoginTotal > 0 && resultStudentTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 统计选课人数
     *
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/courseNumber")
    public Result courseNum(HttpServletResponse response) throws Exception {
        List<Student> studentChooseCourseList = adminService.chooseCourseTotal();
        for (Student s : studentChooseCourseList) {
            s.setCoursename(s.getCourse().getCoursename());
            System.out.println(s);
        }
        return new Result(studentChooseCourseList);
    }

}