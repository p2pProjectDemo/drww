package com.drww.controller.login;

import com.drww.service.login.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * className:LoginController
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 17:28
 */
@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "toUserLogin")
    public String toUserLogin(){
        return "login/login";
    }


    @RequestMapping(value = "userLogin")
    public String userLogin(@RequestParam Map map, Model model, RedirectAttributes redirectAttributes) {
        System.out.println(map);
        boolean rememberMe = false;
        if(map.get("rememberMe")==null){
            rememberMe=false;
        }else {
            rememberMe=true;
        }
        //使用shiro编写认证操作
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("userName").toString(), map.get("passWord").toString());
        //3.执行登录方法
        try {
            subject.login(token);
            //登陆成功
        } catch (UnknownAccountException e) {
            // e.printStackTrace();
            //登录失败 用户名不存在
            redirectAttributes.addFlashAttribute("msg","用户名不存在");
            return "redirect:/toUserLogin";
        }catch (IncorrectCredentialsException e) {
            // e.printStackTrace();
            //登录失败 密码错误
            redirectAttributes.addFlashAttribute("msg","密码错误");
            return "redirect:/toUserLogin";
        }
        return "index";
    }

    //注销用户
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "login/login";
    }


}
