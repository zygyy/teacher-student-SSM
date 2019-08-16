package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/7 10:04
 *
 * 课程
 */
public class Course {
    private int courseid;
    private String coursename;
    private String comment;

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid=" + courseid +
                ", coursename='" + coursename + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
