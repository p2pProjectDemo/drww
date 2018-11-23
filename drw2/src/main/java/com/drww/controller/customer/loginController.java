package com.drww.controller.customer;

import com.drww.entity.Customer;
import com.drww.service.customer.LoginService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * className:loginController
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-22 09:03
 */
@Controller
@RequestMapping(value = "customer")
public class loginController {
        @Autowired
        private LoginService loginService;


    /**
     * 注册
     * @param customer
     * @return
     */
        @ResponseBody
        @RequestMapping(value = "register")
        public Object register(Customer customer){

            //加密密码
            String hashAlgorithmName = "MD5";
            Object salt = null;
            int hashIterations= 2;
            Object simpleHash = new SimpleHash(hashAlgorithmName, customer.getPassWord(), salt, hashIterations);
            customer.setPassWord(simpleHash.toString());
            int register = loginService.register(customer);
                Map map = new HashMap();
                if(register>0) {
                    map.put("msg", "1");
                    map.put("userName",customer.getUserName());
                }else {
                    map.put("msg", "0");
                }
            return map;
        }

    /**
     * 根据用户名查询
     * @param verifyVal
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getByName")
    public Object getByName(String verifyVal){
        Customer customer = loginService.getByName(verifyVal);
        Map map = new HashMap();
        if (customer!=null){
            // 此账号已被占用
            map.put("retCode","1");
            return map;
        }else {
            //标识此用户名可以用
            map.put("retCode","2");
            return map;
        }
    }

    /**
     * 登录验证
     * @param session
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "SignIn")
    public Object SignIn(HttpSession session,@RequestParam Map map){
        Map map1 = new HashMap();
        Object userName1 = map.get("userName");
        Object passWord1 = map.get("passWord");
        String hashAlgorithmName = "MD5";
        Object salt = null;
        int hashIterations= 2;
        Object md5PassWord = new SimpleHash(hashAlgorithmName, passWord1, salt, hashIterations);
        String userName = userName1.toString();
        String passWord = md5PassWord.toString();
        Customer customer =new Customer();
        customer.setUserName(userName);
        customer.setPassWord(passWord);


        Customer customer1 = loginService.SignIn(customer);
        if(customer1!=null){
            session.setAttribute("CustomerName",map.get("userName"));
            session.setAttribute("CustomerPassWord",map.get("passWord"));
            session.setMaxInactiveInterval(600000);
            map1.put("msg","1");
        }else{
            map1.put("msg","用户名或者密码不正确");
        }
        return map1;
    }

    /**
     * 检查是否登录
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "jySession")
    public Object jySession(HttpSession session) {
        Object customerName = session.getAttribute("CustomerName");
        Map map = new HashMap();
        if (customerName != null&&customerName!="") {
            map.put("session", customerName);
        } else {
            map.put("session", "1");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "singOut")
    public Object singOut(HttpSession session){
        //退出清除session
        session.removeAttribute("CustomerName");
        //再次检查
        Object customerName = session.getAttribute("CustomerName");
        Map map = new HashMap();
        if (customerName != null&&customerName!="") {
            map.put("session", "2");
        } else {
            map.put("session", "1");
        }
    return map;
    }









    //验证图片开始
    @RequestMapping("/showLoginView")
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    /**
     * 获取验证码
     *
     * @param response
     * @param session
     */
    @RequestMapping("/getVerifyCode")
    public void generate(HttpServletResponse response, HttpSession session) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCodeValue = drawImg(output);

        session.setAttribute("verifyCodeValue", verifyCodeValue);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 绘画验证码
     *
     * @param output
     * @return
     */
    private String drawImg(ByteArrayOutputStream output) {
        String code = "";
        // 随机产生4个字符
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        // 调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 随机参数一个字符
     *
     * @return
     */
    private char randomChar() {
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }

    /**
     * 登录逻辑
     */
    @ResponseBody
    @RequestMapping("/login")
    public Object login(HttpServletRequest request, HttpSession session) {
        Map map = new HashMap();
        //用户输入的验证码
        String inputVerifyCode = request.getParameter("verifyCode");
        //session中储存的验证码
        String verifyCodeValue = (String) session.getAttribute("verifyCodeValue");

        if (verifyCodeValue.equals(inputVerifyCode)) {
            map.put("msg","1");
            return map;
        }else{
            map.put("msg","2");
            return map;
        }
    }
    //验证结束
}
