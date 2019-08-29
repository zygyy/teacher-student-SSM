package com.zy.service.impl;

import com.zy.dao.AdminDao;
import com.zy.entity.*;
import com.zy.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/5 16:26
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;


    /**
     * 查看管理员详细信息
     * @param admin
     * @return
     */
    @Override
    public List<Admin> getAdminMessage(Admin admin) {

        return adminDao.getAdminMessage(admin);
    }

    /**
     * 修改管理员详细信息
     * @param admin
     * @return
     */
    @Override
    public int updateAdminMessage(Admin admin) {

        return adminDao.updateAdminMessage(admin);
    }


    /**
     * 获取老师信息
     * @param map
     * @return
     */
    public List<Teacher> findTeacherMessage(Map<String, Integer> map){
        return adminDao.findTeacherMessage(map);
    }


    /**
     * 获取老师信息的总的条数
     * @param map
     * @return
     */
    public Long getTeacherMessageTotal(Map<String, Integer> map){
        return adminDao.getTeacherMessageTotal(map);
    }


    /**
     * 添加教师登录信息
     * @param userLogin
     * @return
     */
    public int addTeacherUserLoginMessage(UserLogin userLogin){
        return adminDao.addTeacherUserLoginMessage(userLogin);
    }

    /**
     * 辅助功能：把教师得userid和name插入userlogin1表
     * @param userLogin1
     * @return
     */
    public int addTeacherIntoUserLogin1(UserLogin1 userLogin1){
        return adminDao.addTeacherIntoUserLogin1(userLogin1);
    }


    /**
     * 管理员添加教师详细信息
     * @param teacher
     * @return
     */
    public int addTeacherSpecificMessage(Teacher teacher){
        return adminDao.addTeacherSpecificMessage(teacher);
    }

    /**
     * 判断该课程是否以及分配教师
     * @param courseid
     * @return
     */
    public Teacher judgeTeacher(int courseid){
        return adminDao.judgeTeacher(courseid);
    }

    /**
     * 获取最后一个用户的userid
     * @return
     */
    public int getMaxUserid(){
        return adminDao.getMaxUserid();
    }

    /**
     * 管理员查看学生信息
     * @param map
     * @return
     */
    public List<Student> findStudentMessage(Map<String, Object> map){
        return adminDao.findStudentMessage(map);
    }

    /**
     * 管理员查看学生信息总条数
     * @param map
     * @return
     */
    public Long getStudentMessageTotal(Map<String, Object> map){
        return adminDao.getStudentMessageTotal(map);
    }


    /**
     * 管理员添加学生登录信息
     * @param userLogin
     * @return
     */
    public int addStudentUserLoginMessage(UserLogin userLogin){
        return adminDao.addStudentUserLoginMessage(userLogin);
    }

    /**
     * 管理员添加具体学生信息
     * @param student
     * @return
     */
    public int addStudentSpecificMessage(Student student){
        return adminDao.addStudentSpecificMessage(student);
    }


    /**
     * 选课统计
     *
     * @return
     */
    public List<Student> chooseCourseTotal(){
        return adminDao.chooseCourseTotal();
    }
}
