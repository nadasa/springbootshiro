package com.hishamx.controller;

import com.hishamx.model.User;
import com.hishamx.model.UserInfo;
import com.hishamx.service.UserService;
import com.hishamx.service.user.UserInfoService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getUserInfo")
    // @RequiresPermissions("userInfo:view")//权限管理;
    @ResponseBody
    public User getUserInfo() {
        User user = userService.getUserInfo();
        if(user!=null){
            System.out.println("user.getName():"+user.getName());
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }

    @RequestMapping("/getUsers/{username}")
    @ResponseBody
    public UserInfo getUsers(String username) {
        UserInfo userInfo =userInfoService.findByUsername(username);
        if(userInfo!=null){
            System.out.println("user.getName():"+userInfo.getName());
            logger.info("user.getAge():"+userInfo.getUsername());
        }
        return userInfo;
    }


    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String login(String username, String password, HttpServletRequest request, Model model) {
        try {
            username="admin";
            password="123456";

            // 想要得到 SecurityUtils.getSubject()　的对象．．访问地址必须跟ｓｈｉｒｏ的拦截地址内．不然后会报空指针
            Subject user = SecurityUtils.getSubject();
            // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
            // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
            // 当以上认证成功后会向下执行,认证失败会抛出异常
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                user.login(token);
                model.addAttribute("greeting", username);
            } catch (LockedAccountException lae) {
                token.clear();
                request.setAttribute("error", "用户已经被锁定不能登录，请与管理员联系！");
                return "/login";
            } catch (ExcessiveAttemptsException e) {
                token.clear();
                request.setAttribute("error", "账号：" + username + " 登录失败次数过多,锁定10分钟!");
                return "/login";
            } catch (AuthenticationException e) {
                token.clear();
                request.setAttribute("error", "用户或密码不正确！");
                return "/login";
            }
//            UserLoginFormMap userLogin = new UserLoginFormMap();
//            Session session = SecurityUtils.getSubject().getSession();
//            userLogin.put("userId", session.getAttribute("userSessionId"));
//            userLogin.put("accountName", username);
//            userLogin.put("loginIP", session.getHost());
//            userLoginMapper.addEntity(userLogin);
//            request.removeAttribute("error");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登录异常，请联系管理员！");
            return "/login";
        }
        return "index";

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        // 使用权限管理工具进行用户的退出，注销登录
        SecurityUtils.getSubject().logout(); // session
        // 会销毁，在SessionListener监听session销毁，清理权限缓存
        return "login";
    }

}
