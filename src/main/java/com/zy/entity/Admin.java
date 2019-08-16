package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/5 16:14
 *
 * 管理员详细信息
 */
public class Admin {
    private int id;
    private int userid;
    private String email;
    private String tel;
    private UserLogin userLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userid=" + userid +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", userLogin=" + userLogin +
                '}';
    }
}
