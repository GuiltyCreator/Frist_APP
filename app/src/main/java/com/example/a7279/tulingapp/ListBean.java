package com.example.a7279.tulingapp;

import org.litepal.crud.DataSupport;

import java.util.Calendar;

/**
 * Created by a7279 on 2017/12/4.
 */

public class ListBean extends DataSupport {
    private String article;
    private String source;
    private String name;
    private String icon;
    private String info;
    private String detailurl;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailur1) {
        this.detailurl = detailurl;
    }
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
