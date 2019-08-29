package com.zy.service;

import com.zy.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/5 16:25
 */
public interface AdminService {

    /**
     * 获取登录的管理员的相关信息
     * @param admin
     * @return
     */
    public List<Admin> getAdminMessage(Admin admin);

    /**
     * 管理员详细信息修改
     * @param admin
     * @return
     */
    public int updateAdminMessage(Admin admin);



    /**
     * 获取老师信息
     * @param map
     * @return
     */
    public List<Teacher> findTeacherMessage(Map<String, Integer> map);


    /**
     * 获取老师信息的总的条数
     * @param map
     * @return
     */
    public Long getTeacherMessageTotal(Map<String, Integer> map);

    /**
     * 添加教师登录信息
     * @param userLogin
     * @return
     */
    public int addTeacherUserLoginMessage(UserLogin userLogin);

    /**
     * 辅助功能：把教师得userid和name插入userlogin1表
     * @param userLogin1
     * @return
     */
    public int addTeacherIntoUserLogin1(UserLogin1 userLogin1);


    /**
     * 管理员添加教师详细信息
     * @param teacher
     * @return
     */
    public int addTeacherSpecificMessage(Teacher teacher);

    /**
     * 判断该课程是否以及分配教师
     * @param courseid
     * @return
     */
    public Teacher judgeTeacher(int courseid);

    /**
     * 获取最后一个用户的userid
     * @return
     */
    public int getMaxUserid();


    /**
     * 管理员查看学生信息
     * @param map
     * @return
     */
    public List<Student> findStudentMessage(Map<String, Object> map);

    /**
     * 管理员查看学生信息的总条数
     * @param map
     * @return
     */
    public Long getStudentMessageTotal(Map<String, Object> map);


    /**
     * 管理员添加学生登录信息
     * @param userLogin
     * @return
     */
    public int addStudentUserLoginMessage(UserLogin userLogin);

    /**
     * 管理员添加具体学生信息
     * @param student
     * @return
     */
    public int addStudentSpecificMessage(Student student);


    /**
     * 选课统计
     *
     * @return
     */
    public List<Student> chooseCourseTotal();
}