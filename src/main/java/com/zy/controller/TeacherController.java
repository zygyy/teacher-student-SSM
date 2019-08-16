package com.zy.controller;

import com.zy.entity.PageBean;
import com.zy.entity.Student;
import com.zy.entity.Teacher;
import com.zy.service.TeacherService;
import com.zy.util.ResponseUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/9 10:45
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 跳转教师管理学生界面
     *
     * @param request
     * @return
     */
    @RequestMapping("/teacher-studentManage")
    public String skip(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentUserid", request.getParameter("userid"));
        return "teacher-studentManage";
    }

    /**
     * 跳转教师个人信息界面
     *
     * @param request
     * @return
     */
    @RequestMapping("teacherManage")
    public String skipTeacherManage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentUserid", request.getParameter("userid"));
        return "teacherManage";
    }


    /**
     * 教师个人信息查看
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/teacherMessageList")
    public String teacherMessageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUserid(Integer.parseInt(request.getParameter("userid")));
        List<Teacher> teacherListResult = teacherService.getTeacherMessage(teacher);
        for (Teacher t : teacherListResult) {
            t.setCoursename(t.getCourse().getCoursename());
        }
        JSONArray jsonArray = new JSONArray(teacherListResult);
        ResponseUtil.write(response, jsonArray);/*ajax往前端返回值，要进行的操作*/
        return null;
    }

    /**
     * 教师修改个人信息
     *
     * @param teacher
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateTeacherMessage")
    public String updateTeacherMessage(Teacher teacher, HttpServletResponse response) throws Exception {
        int resultNum = teacherService.updateTeacherMessage(teacher);
        JSONObject jsonObject = new JSONObject();
        if (resultNum > 0) {
            jsonObject.put("success", true);
        } else {
            jsonObject.put("success", false);
        }
        ResponseUtil.write(response, jsonObject);
        return null;
    }


    /**
     * 教师获得自己所教的学生的相关信息
     *
     * @param page
     * @param rows
     * @param student
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/teacherGetStudentMessage")
    public String adminGetStudentMessage(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Student student, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //根据传过来的登录的userid，获得相关老师的teacherid
        Teacher teacher = new Teacher();
        teacher.setUserid(Integer.parseInt(request.getParameter("userid")));
        List<Teacher> teacherListResult = teacherService.getTeacherMessage(teacher);
        Map<String, Object> map = new HashMap<String, Object>();
        for (Teacher t : teacherListResult) {
            //System.out.println(t.getTeacherid());
            map.put("teacherid", t.getTeacherid());
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());

        List<Student> studentList = teacherService.teacherGetStudentMessage(map);
        Long teacherGetStudentMessage = teacherService.teacherGetStudentMessageTotal(map);
        JSONObject studentMessageResult = new JSONObject();
        for (Student s : studentList) {
            s.setStudentname(s.getUserLogin().getName());
            s.setCoursename(s.getCourse().getCoursename());
        }
        JSONArray jsonArray = new JSONArray(studentList);
        studentMessageResult.put("rows", jsonArray);
        studentMessageResult.put("total", teacherGetStudentMessage);
        ResponseUtil.write(response, studentMessageResult);
        return null;
    }

    /**
     * 老师为学生打分
     *
     * @param student
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/addStudentScore")
    public String addStudentScore(Student student, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (student.getScore() <= 100 && student.getScore() >= 0) {
            int result = teacherService.addStudentScore(student);
            if (result > 0) {
                jsonObject.put("success", true);
            } else {
                jsonObject.put("success", false);
            }
            ResponseUtil.write(response, jsonObject);
            return null;
        } else {
            jsonObject.put("success", false);
            ResponseUtil.write(response, jsonObject);
            return null;
        }
    }


    /**
     * 导出所选学生的Excel表格
     *
     * @param excelIds
     * @param student
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/teacherExcel")
    public String teacherExcel(@RequestParam(value = "excelIds") String excelIds, Student student, HttpServletResponse response) throws Exception {
        String[] excelMessage = excelIds.split(",");

        Workbook wb = new HSSFWorkbook();//定义新工作簿
        FileOutputStream fileOut = new FileOutputStream("e:\\学生成绩.xls");
        Sheet sheet = wb.createSheet("第一个sheet页");//构造sheet页
        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue("电话");
        row1.createCell(2).setCellValue("学科");
        row1.createCell(3).setCellValue("分数");
        for (int i = 0; i < excelMessage.length; i++) {
            Row row = sheet.createRow(i + 1);//长度为多少就创建多少行
            int studentids = Integer.parseInt(excelMessage[i] + "");
            //System.out.println(Integer.parseInt(excelMessage[i]+""));
            student.setStudentid(studentids);
            Student s = teacherService.excelStudentList(student);
            //System.out.println(teacherService.excelStudentList(student));
            row.createCell(0).setCellValue(s.getUserLogin().getName());//先创建单元格，再塞值*/
            row.createCell(1).setCellValue(s.getTel());
            row.createCell(2).setCellValue(s.getCourse().getCoursename());
            row.createCell(3).setCellValue(s.getScore());
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);

        wb.write(fileOut);
        fileOut.close();
        return null;
    }


}
