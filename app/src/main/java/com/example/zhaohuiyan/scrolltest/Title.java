package com.example.zhaohuiyan.scrolltest;

/**
 * Created by Wode9 on 2017/7/15.
 */

public class Title {
    private int id;//序号
    private String name;
    private boolean isShow;
    private boolean isCrease;

    public Title(int id,String name, boolean isShow, boolean isCrease) {
        this.id = id;
        this.name = name;
        this.isShow = isShow;
        this.isCrease = isCrease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isCrease() {
        return isCrease;
    }

    public void setCrease(boolean crease) {
        isCrease = crease;
    }
}
