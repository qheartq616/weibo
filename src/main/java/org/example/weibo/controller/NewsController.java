package org.example.weibo.controller;

import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.pojo.User;
import org.example.weibo.service.GroupService;
import org.example.weibo.service.NewsService;
import org.example.weibo.service.PostService;
import org.example.weibo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Resource
    PostService postService;
    @Resource
    GroupService groupService;
    @Resource
    PostMapper postMapper;
    @Resource
    UserService userService;
    @Resource
    NewsService newsService;

@RequestMapping("/atme/{uid}/{pageNum}")
    public String atme(@PathVariable Integer uid,@PathVariable Integer pageNum, Model model){

        return "news";
    }
    @RequestMapping("/likeMe/{uid}/{pageNum}")
    public String likeme(@PathVariable Integer uid,@PathVariable Integer pageNum, Model model){
        PageInfo<User> pageInfo= newsService.getAllUserLikeMeAfterLast(uid);
        model.addAttribute("pageInfo",pageInfo);
        return "news";
    }
}
