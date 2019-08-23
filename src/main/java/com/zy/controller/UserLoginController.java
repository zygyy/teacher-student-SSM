package com.zy.controller;

import com.zy.entity.Admin;
import com.zy.entity.Teacher;
import com.zy.entity.UserLogin;
import com.zy.service.UserLoginService;
import com.zy.util.Mail;
import com.zy.util.Random;
import com.zy.util.ResponseUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/2 15:15
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

    HashMap<String,Integer> map=new HashMap<>();

    @Resource
    private UserLoginService userLoginService;


    /**
     * 登录，判断roleid跳转不同界面
     *
     * @param userLogin
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(UserLogin userLogin, HttpServletRequest request) throws Exception {
        UserLogin resultUser = userLoginService.login(userLogin);
        if (resultUser == null) {
            request.setAttribute("errorMsg", "用户名或登录类型选择错误！");
            return "login";
        } else {
            if (userLogin.getRoleid() == 1) {
                if (resultUser.getPassword().equals(request.getParameter("password"))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("currentRoleName", resultUser.getRole().getRolename());
                    session.setAttribute("currentUser", resultUser);
                    return "main";
                } else {
                    request.setAttribute("errorMsg", "密码错误，请重新填写！");
                    return "login";
                }
            } else if (userLogin.getRoleid() == 2) {
                if (resultUser.getPassword().equals(request.getParameter("password"))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("currentRoleName", resultUser.getRole().getRolename());
                    session.setAttribute("currentUser", resultUser);
                    return "mainTeacher";
                } else {
                    request.setAttribute("errorMsg", "密码错误，请重新填写！");
                    return "login";
                }
            } else {
                if (resultUser.getPassword().equals(request.getParameter("password"))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("currentRoleName", resultUser.getRole().getRolename());
                    session.setAttribute("currentUser", resultUser);
                    return "mainStudent";
                } else {
                    request.setAttribute("errorMsg", "密码错误，请重新填写！");
                    return "login";
                }
            }

        }
    }


    /**
     * 修改登录密码（学生都调用此方法,因为学生没email字段）
     * @param userid
     * @param passwordqueren
     * @param userLogin
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePassword/{userid}/{passwordqueren}",method = RequestMethod.POST)
    public String savePassword(@PathVariable int  userid,@PathVariable String  passwordqueren,UserLogin userLogin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        if (userLogin.getPassword().equals(passwordqueren)) {
            userLogin.setUserid(userid);
            int resultTotal = userLoginService.updatePassword(userLogin);
            if (resultTotal > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
            ResponseUtil.write(response, result);
            return null;
        } else {
            result.put("success", false);
            ResponseUtil.write(response, result);
            return null;
        }
    }


    /**
     * 退出登录（管理员，教师，学生都调用此方法）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();//主要用于注销:调用该方法,会清空已定义的session,而不是清空session里的值
        return "login";
    }


    /**
     * 发送邮件（管理员,教师调用此方法）
     * @param userid
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAchieveCode/{userid}",method = RequestMethod.GET)
    public String getAchieveCode(@PathVariable int  userid,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Mail mail=new Mail();
        /*生成随机码*/
        Random random=new Random();
        int  randomArise=random.randomArise();
        map.put("key",randomArise);//将随机码存放到map里面
        /*只设置了一个管理员,所以只判断了userid是否为1,是1则为管理员登录,调用管理员的方法;不为1,则调用教师方法*/
        if(userid==1){
            Admin admin=userLoginService.getEmailAdmin(userid);
            /*把获取到的邮箱和随机码传进去*/
            mail.sendMail(admin.getEmail(),randomArise);
        }else{
            Teacher teacher=userLoginService.getEmailTeacher(userid);
            mail.sendMail(teacher.getEmail(),randomArise);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }


    /**
     * 修改登录密码（管理员,教师调用此方法）
     * @param userid
     * @param passwordqueren
     * @param code
     * @param userLogin
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePasswordAdminAndTeacher/{userid}/{passwordqueren}/{code}",method = RequestMethod.POST)
    public String savePasswordAdmin(@PathVariable int  userid,@PathVariable String  passwordqueren,@PathVariable int  code,UserLogin userLogin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        /*判断密码和验证密码是否相同*/
        if (userLogin.getPassword().equals(passwordqueren)) {
            /*判断验证码是否正确*/
            if(map.get("key").equals(code)){
                userLogin.setUserid(userid);
                int resultTotal = userLoginService.updatePassword(userLogin);
                result.put("success", true);
                ResponseUtil.write(response, result);
                return null;
            }else{
                result.put("codeFalse", true);
                ResponseUtil.write(response, result);
                return null;
            }
        } else {
            result.put("success", false);
            ResponseUtil.write(response, result);
            return null;
        }
    }

}

