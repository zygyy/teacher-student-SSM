package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/6 8:45
 *
 * 教师实体类
 */
public class Teacher {
    private int teacherid;
    private int userid;
    private String email;
    private String tel;
    private int courseid;
    private UserLogin userLogin;
    private String name;//补充属性，用来接UserLogin中的name属性也就是（教师姓名）
    private Course course;
    private String coursename;//补充属性，用来接收教师所教的课程名

    private UserLogin1 userLogin1;//补充实体类，用来解决association嵌套重复问题

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

   public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public UserLogin1 getUserLogin1() {
        return userLogin1;
    }

    public void setUserLogin1(UserLogin1 userLogin1) {
        this.userLogin1 = userLogin1;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherid=" + teacherid +
                ", userid=" + userid +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", courseid=" + courseid +
                ", userLogin=" + userLogin +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", coursename='" + coursename + '\'' +
                ", userLogin1=" + userLogin1 +
                '}';
    }
}
