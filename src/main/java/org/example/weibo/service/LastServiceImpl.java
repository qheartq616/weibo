package org.example.weibo.service;

import org.example.weibo.mapper.LastMapper;
import org.example.weibo.pojo.Last;
import org.example.weibo.pojo.LastExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LastServiceImpl implements LastService {
    @Resource
    LastMapper lastMapper;

    @Override
    public Integer addLastLeave(Integer uid) {
        Last last=null;
        last.setUid(uid);
        Date date =new Date();
        last.setLattime(date);
        last.setLctime(date);
        last.setLftime(date);
        last.setLltime(date);
        int i = lastMapper.insertSelective(last);
        return i;
    }

    @Override
    public Last getLastLeave(Integer uid) {
        LastExample lastExample=new LastExample();
        lastExample.createCriteria().andUidEqualTo(uid);
        List<Last> lasts = lastMapper.selectByExample(lastExample);
        if (lasts.size()!=0){
            return lasts.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer updateLast(Last last) {
        int i = lastMapper.updateByPrimaryKeySelective(last);
        return i;
    }
}
