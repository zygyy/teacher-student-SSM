package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/8 16:57
 * <p>
 * 学生实体类
 */
public class Student {
    private int studentid;
    private int userid;
    private UserLogin userLogin;
    private String tel;
    private int courseid;
    private Course course;
    private int teacherid;
    private Teacher teacher;
    private int score;

    private int total;

    /*重新塞值*/
    private String studentname;//学生姓名
    private String coursename;//课程名
    private String teachername;//任课教师

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", userid=" + userid +
                ", userLogin=" + userLogin +
                ", tel='" + tel + '\'' +
                ", courseid=" + courseid +
                ", course=" + course +
                ", teacherid=" + teacherid +
                ", teacher=" + teacher +
                ", score=" + score +
                ", total=" + total +
                '}';
    }


    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
}
