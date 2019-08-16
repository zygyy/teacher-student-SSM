package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/2 14:09
 *
 * 登录实体类（包含管理员，教师和学生）
 */
public class UserLogin {
    private int userid;
    private String name;
    private String password;
    private int roleid;
    private Role role;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roleid=" + roleid +
                ", role=" + role +
                '}';
    }
}
