package org.example.weibo.controller;

import org.example.weibo.pojo.User;
import org.example.weibo.service.AdminService;
import org.example.weibo.service.LastService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;

@Controller
public class RegisterController {
    @Resource
    AdminService adminService;
    @Resource
    LastService lastService;
    @ResponseBody
    @RequestMapping("/register")
    public int register(User user, Model model){
        Date date=new Date();
        user.setUpdateNameTime(date);
        user.setRegisterTime(date);
        int i = adminService.addUser(user);
//        //设置最后离开时间
//        lastService.addLastLeave(user.getUid());
        return i;
    }
    @ResponseBody
    @RequestMapping("/phone")
    public boolean phoneTest(Long phone, Model model){
        User userByPhone = adminService.getUserByPhone(phone);
        if(userByPhone!=null){
            return false;
        }else {
            return true ;
        }
    }
}
