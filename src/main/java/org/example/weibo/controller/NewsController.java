package org.example.weibo.controller;

import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.pojo.*;
import org.example.weibo.service.GroupService;
import org.example.weibo.service.NewsService;
import org.example.weibo.service.PostService;
import org.example.weibo.service.UserService;
import org.example.weibo.utils.ListUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
        List<Comment> allComments = newsService.getAllComments(uid);
        int size = allComments.size();
        System.out.println("评论条数"+size);
        List<CommentLike> commentLike = newsService.getCommentLike(uid);
        List<PostLike> likedPost = newsService.getLikedPost(uid);
        model.addAttribute("Likesize",commentLike.size()+likedPost.size());
        model.addAttribute("commentSize",size);
        model.addAttribute("atSize",0);
        return "news";
    }
    @RequestMapping("/comment/{uid}/{pageNum}")
    public String comment(@PathVariable Integer uid,@PathVariable Integer pageNum, Model model){
        List<Comment> allComments = newsService.getAllComments(uid);
        int size = allComments.size();
        System.out.println("评论条数"+size);
        PageInfo<Comment> comments = newsService.getComments(pageNum, 5, uid);

        List<CommentLike> commentLike = newsService.getCommentLike(uid);
        List<PostLike> likedPost = newsService.getLikedPost(uid);

        model.addAttribute("Likesize",commentLike.size()+likedPost.size());

        model.addAttribute("commentSize",size);
        model.addAttribute("CommentpageInfo",comments);
        model.addAttribute("atSize",0);
        return "news";
    }
    @RequestMapping("/likeMe/{uid}/{pageNum}")
    public String likeme(@PathVariable Integer uid, @PathVariable @RequestParam(defaultValue = "1") Integer pageNum, Model model){
        PageInfo<Object> objectPageInfo = newsService.allLike(uid, pageNum);

        List<Comment> allComments = newsService.getAllComments(uid);

        int size = allComments.size();

        List<CommentLike> commentLike = newsService.getCommentLike(uid);
        List<PostLike> likedPost = newsService.getLikedPost(uid);
        model.addAttribute("Likesize",commentLike.size()+likedPost.size());

        System.out.println("评论条数"+size);
        model.addAttribute("commentSize",size);
        model.addAttribute("pageInfo",objectPageInfo);
        model.addAttribute("atSize",0);
        return "news";
    }
}
