package com.zy.service.impl;

import com.zy.dao.TeacherDao;
import com.zy.entity.Student;
import com.zy.entity.Teacher;
import com.zy.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/9 9:13
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    /**
     * 获取教师详细信息
     * @param teacher
     * @return
     */
    @Override
    public List<Teacher> getTeacherMessage(Teacher teacher) {
        return teacherDao.getTeacherMessage(teacher);
    }

    /**
     * 教师详细信息修改
     * @param teacher
     * @return
     */
    public int updateTeacherMessage(Teacher teacher){
        return teacherDao.updateTeacherMessage(teacher);
    }

    /**
     * 老师获取相关学生的信息
     * @param map
     * @return
     */
    public List<Student> teacherGetStudentMessage(Map<String,Object> map){
        return teacherDao.teacherGetStudentMessage(map);
    }

    /**
     *教师获取相关学生信息的总的条数
     * @param map
     * @return
     */
    public Long teacherGetStudentMessageTotal(Map<String,Object> map){
        return teacherDao.teacherGetStudentMessageTotal(map);
    }

    /**
     * 教师为学生添加成绩
     * @param student
     * @return
     */
    public int addStudentScore(Student student){
        return teacherDao.addStudentScore(student);
    }

    /**
     * 导出学生信息到Excel表格
     * @param student
     * @return
     */
    public Student excelStudentList(Student student){
        return teacherDao.excelStudentList(student);
    }
}
