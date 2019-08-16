package com.zy.dao;

import com.zy.entity.Student;
import com.zy.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/9 9:12
 */
public interface TeacherDao {
    /**
     * 获取登录的教师的相关信息
     * @param teacher
     * @return
     */
    public List<Teacher> getTeacherMessage(Teacher teacher);

    /**
     * 教师详细信息修改
     * @param teacher
     * @return
     */
    public int updateTeacherMessage(Teacher teacher);

    /**
     * 老师获取相关学生的信息
     * @param map
     * @return
     */
    public List<Student> teacherGetStudentMessage(Map<String,Object> map);

    /**
     *教师获取相关学生信息的总的条数
     * @param map
     * @return
     */
    public Long teacherGetStudentMessageTotal(Map<String,Object> map);

    /**
     * 教师为学生添加成绩
     * @param student
     * @return
     */
    public int addStudentScore(Student student);


    /**
     * 导出学生信息到Excel表格
     * @param student
     * @return
     */
    public Student excelStudentList(Student student);
}
