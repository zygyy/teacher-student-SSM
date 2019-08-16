package com.zy.dao;

import com.zy.entity.Admin;
import com.zy.entity.Teacher;
import com.zy.entity.UserLogin;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/2 14:10
 */
public interface UserLoginDao {
    /**
     * 用户登录
     * @param userLogin
     * @return
     */
    public UserLogin login(UserLogin userLogin);

    /**
     * 密码的修改
     * @param userLogin
     * @return
     */
    public int updatePassword(UserLogin userLogin);

    /**
     * 根据userid获取管理员的邮箱
     * @param userid
     * @return
     */
    public Admin getEmailAdmin(int userid);

    /**
     * 根据userid获取教师的邮箱
     * @param userid
     * @return
     */
    public Teacher getEmailTeacher(int userid);
}
