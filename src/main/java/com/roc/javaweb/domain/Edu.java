package com.roc.javaweb.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Edu implements Serializable {
    @TableId
    private Integer eid;
    private String uid;
    private String title;
    private String name;
    private String pos;
    private String sta;

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    private String web;

    public Integer getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Edu{" +
                "eid=" + eid +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", pos='" + pos + '\'' +
                ", sta='" + sta + '\'' +
                ", web='" + web + '\'' +
                '}';
    }
}
