package com.roc.javaweb.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Msg implements Serializable {
    @TableId
    private int mid;
    private String name;
    private String msg;
    private String time;

    @Override
    public String toString() {
        return "Msg{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
