package com.top.yuanopen.password.bean;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public class thing {
    private  int id;//id
    private  String thing_content;//事件内容
    private  int thing_rank;//事件等级
    private  String create_time;//时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThing_content() {
        return thing_content;
    }

    public void setThing_content(String thing_content) {
        this.thing_content = thing_content;
    }

    public int getThing_rank() {
        return thing_rank;
    }

    public void setThing_rank(int thing_rank) {
        this.thing_rank = thing_rank;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
