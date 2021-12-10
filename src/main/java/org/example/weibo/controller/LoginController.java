package org.example.weibo.controller;

import org.example.weibo.pojo.User;
import org.example.weibo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Resource
	UserService userService;

	/*@RequestMapping("test")
	public String test(){
		return "1";
	}*/

	@RequestMapping("checkLogin")
	public String checkLogin(String name, String password, Model model, HttpSession session){
		User user = userService.login(name, password);
		if (user==null){
			model.addAttribute("error","用户名或密码错误！");
			return "login";
		}else {
			session.setAttribute("user",user);
			return "redirect:/user/"+user.getUid()+"/1";
		}
	}
}
