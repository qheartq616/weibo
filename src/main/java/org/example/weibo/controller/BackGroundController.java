package org.example.weibo.controller;


import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.Admin;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;
import org.example.weibo.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RequestMapping("/background")
@Controller
public class BackGroundController {

    @Resource
    AdminService adminService;

    @RequestMapping("/index/{aid}")
    public String toIndex(@PathVariable("aid") Integer aid,Model model) {
        Admin admin = adminService.selectById(aid);
        model.addAttribute("adminCur",admin);
        return "background/index";
    }

    @RequestMapping("/login")
    public String tologin(){
        return "background/login";
    }
    @RequestMapping("/doLogin")
    public String dologin(String username, String password, HttpSession session, Model model){
        Admin admin = adminService.login(username, password);
        if(admin==null){
            model.addAttribute("msg","用户名密码错误");
            return "login";
        }else{
            session.setAttribute("admin",admin);
            return "redirect:/background/index/"+admin.getAid();
        }

    }

    @RequestMapping("/allUser/{pageStart}")
    public String UserList(@PathVariable(value = "pageStart",required = false)Integer pageStart, Integer limit,
                           Model model){
        if(pageStart==null||pageStart.equals("")){
            pageStart=1;
        }

            limit=5;

        PageInfo<User> allUser = adminService.getAllUser(pageStart, limit);
        model.addAttribute("pageInfo",allUser);
        return "background/expenses";
    }

    @RequestMapping("/doUpdate/{aid}/{pageNum}")
    public  String userUpdate(@PathVariable String aid,@PathVariable Integer pageNum,Integer id, String name, Integer status, String gender, String city, Long phone, String email, Date birthday, Date registerTime, String description, Model model){
        User user =new User();
        user.setBirthday(birthday);
        user.setUid(id);
        user.setRegisterTime(registerTime);
        user.setCity(city);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setStatus(status);
        user.setName(name);
        user.setDescription(description);
        user.setUpdateNameTime(new Date());
        adminService.updateUser(user);
        return "redirect:/background/allUser/"+pageNum;
    }
    @RequestMapping("/addUser/{aid}/{pageNum}")
    public  String addUser(@PathVariable String aid,@PathVariable Integer pageNum, String name,String repassword, String gender, String city, Long phone, String email, Date birthday, String description, Model model){
        User user =new User();
        user.setBirthday(birthday);
        user.setPassword(repassword);
        user.setRegisterTime(new Date());
        user.setCity(city);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setName(name);
        user.setDescription(description);
        user.setUpdateNameTime(new Date());
        adminService.addUser(user);

        return "redirect:/background/allUser/"+pageNum;
    }
    @DeleteMapping("/deluser/{uid}/{pageStart}")
    public  String  delUser(@PathVariable Integer uid,@PathVariable Integer pageStart) {
        adminService.deleteUser(uid);
        return "redirect:/background/allUser/" + pageStart;
    }

    //获取所有 微博信息
    @RequestMapping("/allPost/{pageStart}")
    public  String allPost(@PathVariable(required = false) Integer pageStart,@RequestParam(required = false,defaultValue = "10") Integer limit,Model model){
        PageInfo<Post> allPost = adminService.getAllPost(pageStart, limit);
        model.addAttribute("pageInfo",allPost);
        return "background/post_list";
    }
}
