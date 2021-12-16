package org.example.weibo.pojo;

import java.util.Date;

public class Last {
    private Integer lastId;

    private Integer uid;

    private Date lattime;

    private Date lftime;

    private Date lctime;

    private Date lltime;

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getLattime() {
        return lattime;
    }

    public void setLattime(Date lattime) {
        this.lattime = lattime;
    }

    public Date getLftime() {
        return lftime;
    }

    public void setLftime(Date lftime) {
        this.lftime = lftime;
    }

    public Date getLctime() {
        return lctime;
    }

    public void setLctime(Date lctime) {
        this.lctime = lctime;
    }

    public Date getLltime() {
        return lltime;
    }

    public void setLltime(Date lltime) {
        this.lltime = lltime;
    }
}