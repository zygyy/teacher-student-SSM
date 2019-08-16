package com.zy.service.impl;

import com.zy.dao.UserLoginDao;
import com.zy.entity.Admin;
import com.zy.entity.Teacher;
import com.zy.entity.UserLogin;
import com.zy.service.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/5 15:09
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserLoginDao userLoginDao;

    /**
     * 登录
     * @param userLogin
     * @return
     */
    @Override
    public UserLogin login(UserLogin userLogin) {
        return userLoginDao.login(userLogin);
    }


    /**
     *  密码的修改
     * @param userLogin
     * @return
     */
    public int updatePassword(UserLogin userLogin){
        return userLoginDao.updatePassword(userLogin);
    }

    /**
     * 根据userid获取管理员的邮箱
     * @param userid
     * @return
     */
    public Admin getEmailAdmin(int userid){
        return userLoginDao.getEmailAdmin(userid);
    }

    /**
     * 根据userid获取教师的邮箱
     * @param userid
     * @return
     */
    public Teacher getEmailTeacher(int userid){
        return userLoginDao.getEmailTeacher(userid);
    }
}
