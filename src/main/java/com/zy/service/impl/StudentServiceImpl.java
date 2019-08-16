package com.zy.service.impl;

import com.zy.dao.StudentDao;
import com.zy.entity.Course;
import com.zy.entity.Student;
import com.zy.entity.Teacher;
import com.zy.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/12 14:41
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    /**
     * 登录的学生获取自己相关信息
     * @return
     */
    public List<Student> studentGetPersonalMessage(Map<String,Object> map){
        return studentDao.studentGetPersonalMessage(map);
    }

    /**
     * 记录查询到的总的条数
     * @param map
     * @return
     */
    public Long studentGetPersonalMessageTotal(Map<String,Object> map){
        return studentDao.studentGetPersonalMessageTotal(map);
    }

    /**
     * 学生选课
     * @param student
     * @return
     */
    public int addScore(Student student){
        return studentDao.addScore(student);
    }

    /**
     * 显示课程列表
     * @param map
     * @return
     */
    public List<Course> courseList(Map<String,Object> map){
        return studentDao.courseList(map);
    }

    /**
     * 课程列表总数
     * @param map
     * @return
     */
    public Long courseListTotal(Map<String,Object> map){
        return studentDao.courseListTotal(map);
    }

    /**
     * 录入选课时，根据userid获取学生的电话号码
     * @param userid
     * @return
     */
    public List<Student> selectTel(int userid){
        return studentDao.selectTel(userid);
    }

    /**
     * 录入选课时，根据courseid获取相关老师的teacherid
     * @param courseid
     * @return
     */
    public Teacher selectTeacher(int courseid){
        return studentDao.selectTeacher(courseid);
    }

    /**
     * 判断是否为已选课程
     * @param student
     * @return
     */
    public Student judge(Student student){
        return studentDao.judge(student);
    }
}
