package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsServiceImplTest {

    @Resource
    NewsService newsService;
    @Test
    void allLike() {
        PageInfo<Object> objectPageInfo = newsService.allLike(1, 5);
        List<Object> list = objectPageInfo.getList();
        Iterator it=list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

    }
}