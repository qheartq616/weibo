package org.example.weibo.controller;

import org.example.weibo.pojo.Last;
import org.example.weibo.service.LastService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class LastController {
    @Resource
    LastService lastService;


    @ResponseBody
    @RequestMapping("/upLast/{uid}")
    public  boolean upLast(@PathVariable Integer uid, @RequestParam String type){
        Last lastLeave = lastService.getLastLeave(uid);
        Date date =new Date();
        System.out.println("type:"+type);
        if (lastLeave!=null){
            //点赞我的时间更新
            if(type.equals("like")){
                lastLeave.setLltime(date);
                Integer integer = lastService.updateLast(lastLeave);
                if(integer>0){
                    return true;
                }else{
                    return false;
                }
                //at我的 更新时间 包括转发
            }else if(type.equals("at")){
                lastLeave.setLattime(date);
                lastLeave.setLftime(date);
                Integer integer = lastService.updateLast(lastLeave);
                if(integer>0){
                    return true;
                }else{
                    return false;
                }
                //评论我的 时间更新
            }else if(type.equals("commen")){
                lastLeave.setLctime(date);
                Integer integer = lastService.updateLast(lastLeave);
                if(integer>0){
                    return true;
                }else{
                    return false;
                }
            }else {
                return  false;
            }

        }else{
            //没有就添一条
            lastService.addLastLeave(uid);
            return true;
        }
    }

}
