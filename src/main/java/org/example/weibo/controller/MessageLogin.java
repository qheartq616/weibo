package org.example.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.weibo.pojo.User;
import org.example.weibo.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MessageLogin {
@Resource
    AdminService adminService;
@RequestMapping("/message")
public  String messLogin(@RequestParam Long memPhone, @RequestParam String code,  Model model, HttpSession session){
    User userByPhone = adminService.getUserByPhone(memPhone);
    System.out.println("用户名"+userByPhone.getName());
    JSONObject userCode = (JSONObject)session.getAttribute("code");
    //验证码
    System.out.println( "验证码"+ userCode.get("code"));
    //手机号
    System.out.println( "手机号"+ userCode.get("memPhone"));
    System.out.println("传来的验证码"+code);
    if (userByPhone!=null&&code.equals(userCode.get("code"))){
        System.out.println("我进来了");
        System.out.println("传来的验证码"+code);
        session.setAttribute("user",userByPhone);
        return "redirect:/user/"+userByPhone.getUid()+"/1";
    }else{
        System.out.println("该账户没有注册");
        model.addAttribute("error","该账户没有注册");
        return "login1";
    }
}

}
