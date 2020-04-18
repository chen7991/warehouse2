package com.casic.warehouse2.controller;

import com.casic.warehouse2.bean.User;
import com.casic.warehouse2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String toLogin(){
        return "views/user/login";
    }
    @RequestMapping("/checkUser")
    public String checkUser(@RequestParam("loginname")String loginname, @RequestParam("pwd")String pwd, HttpSession session){
        User user=userService.getUserByLoginNameAndPassword(loginname,pwd);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/toIndex";
        }
        session.setAttribute("msg","请先登录");
        return "redirect:/";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
