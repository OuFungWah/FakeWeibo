package com.example.fakeweibo;

/**
 * Created by 区枫华 on 2017/9/11.
 */

public class ItemBean {

    private String time;
    private String content;

    public ItemBean(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
