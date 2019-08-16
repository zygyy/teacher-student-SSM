package com.zy.dao;

import com.zy.entity.Course;
import com.zy.entity.Student;
import com.zy.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/12 14:40
 */
public interface StudentDao {

    /**
     * 登录的学生获取自己相关信息
     * @return
     */
    public List<Student> studentGetPersonalMessage(Map<String,Object> map);


    /**
     * 记录查询到的总的条数
     * @param map
     * @return
     */
    public Long studentGetPersonalMessageTotal(Map<String,Object> map);

    /**
     * 显示课程列表
     * @param map
     * @return
     */
    public List<Course> courseList(Map<String,Object> map);

    /**
     * 课程列表总数
     * @param map
     * @return
     */
    public Long courseListTotal(Map<String,Object> map);

    /**
     * 学生选课
     * @param student
     * @return
     */
    public int addScore(Student student);

    /**
     * 录入选课时，根据userid获取学生的电话号码
     * @param userid
     * @return
     */
    public List<Student> selectTel(int userid);

    /**
     * 录入选课时，根据courseid获取相关老师的teacherid
     * @param courseid
     * @return
     */
    public Teacher selectTeacher(int courseid);

    /**
     * 判断是否为已选课程
     * @param student
     * @return
     */
    public Student judge(Student student);
}
