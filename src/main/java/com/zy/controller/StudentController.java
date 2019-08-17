package com.zy.controller;

import com.zy.entity.Course;
import com.zy.entity.PageBean;
import com.zy.entity.Student;
import com.zy.entity.Teacher;
import com.zy.service.StudentService;
import com.zy.util.ResponseUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
 * @creat 2019/8/11 15:51
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    /**
     * 学生跳转个人信息界面
     *
     * @param request
     * @return
     */

    @RequestMapping(value = "/studentManage/{userid}",method = RequestMethod.GET)
    public String tip(@PathVariable String  userid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentStudentUserid", userid);
        return "studentManage";
    }

    /**
     * 跳转选课页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/student-courseManage/{userid}",method = RequestMethod.GET)
    public String skip(@PathVariable String  userid,HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentStudentUserid", userid);
        return "student-courseManage";
    }


    /**
     * 学生获取与自己相关的信息
     *
     * @param page
     * @param rows
     * @param student
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/studentGetPersonalMessage/{userid}",method = RequestMethod.POST)
    public String studentGetPersonalMessage(@PathVariable int  userid,@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Student student, HttpServletResponse response, HttpServletRequest request) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        List<Student> studentList = studentService.studentGetPersonalMessage(map);
        Long studentPersonalMessageTotal = studentService.studentGetPersonalMessageTotal(map);

        for (Student s : studentList) {
            s.setCoursename(s.getCourse().getCoursename());
            s.setTeachername(s.getTeacher().getUserLogin().getName());
        }
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(studentList);
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total", studentPersonalMessageTotal);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 显示所以课程信息
     *
     * @param page
     * @param rows
     * @param course
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/chooseCourseList",method = RequestMethod.POST)
    public String chooseCourse(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Course course, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        List<Course> courseList = studentService.courseList(map);
        Long courseListTotal = studentService.courseListTotal(map);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(courseList);
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total", courseListTotal);
        ResponseUtil.write(response, jsonObject);
        return null;
    }


    /**
     * 选课
     *
     * @param student
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectCourse",method = RequestMethod.POST)
    public String selectCourse(Student student, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取电话号码（tel）
        List<Student> resultStudentTel = studentService.selectTel(student.getUserid());
        String tel = resultStudentTel.get(0).getTel();

        //获取教师teacher对象
        Teacher teacher = studentService.selectTeacher(student.getCourseid());
        JSONObject result = new JSONObject();

        /*判断是否为已选课程*/
        if (studentService.judge(student) == null) {
            /*如果为空，则说明未选该课程，再判断该课程是否有对应得上课教师*/
            if (teacher == null) {
                /*该课程未分配教师*/
                result.put("success", false);
                ResponseUtil.write(response, result);
                return null;
            } else {
                int teacherid = teacher.getTeacherid();
                student.setTel(tel);
                student.setTeacherid(teacherid);
                int studentAddCourse = studentService.addScore(student);
                result.put("success", true);
                ResponseUtil.write(response, result);
                return null;
            }
        }else{
            result.put("chooseRepeat", true);
            ResponseUtil.write(response, result);
            return null;
        }
    }
}
