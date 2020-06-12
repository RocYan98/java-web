package com.roc.javaweb.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Attractions implements Serializable {
    @TableId
    private Integer aid;
    private String uid;
    private String title;
    private String text;
    private String pic;

    @Override
    public String toString() {
        return "Attractions{" +
                "aid=" + aid +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
