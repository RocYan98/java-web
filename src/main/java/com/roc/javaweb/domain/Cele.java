package com.roc.javaweb.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Cele implements Serializable {

    @TableId
    private Integer cid;
    private String uid;
    private String title;
    private String text;
    private String pic;

    @Override
    public String toString() {
        return "Cele{" +
                "cid=" + cid +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
