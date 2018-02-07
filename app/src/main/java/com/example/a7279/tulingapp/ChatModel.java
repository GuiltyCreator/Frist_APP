package com.example.a7279.tulingapp;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by a7279 on 2017/11/19.
 */

public class ChatModel extends DataSupport{

    /**
     * code : 200000
     * text : 亲，已帮你找到新闻信息
     * url : http://m.toutiao.com/#channel=
     */
    private int code;
    private String text;
    private String url;
    private boolean isreceived=true;
    private String time=time_set();
    private long id=super.getBaseObjId();
    private List<ListBean> list=new ArrayList<>();

    private String time_set()
    {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int min=calendar.get(Calendar.MINUTE);
        int scend=calendar.get(Calendar.SECOND);
        String time=year+"/"+month+"/"+day+"--"+hour+":"+min+":"+scend;
        return time;
    }
    public String getId() {
        StringBuilder stringBuilder=new StringBuilder();
        return stringBuilder.append(this.id).toString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIsreceived(boolean issend) {
        this.isreceived = issend;
    }

    public boolean getIsreceived() {
        return isreceived;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


    public static class ListBean extends DataSupport{
        /**
         * article : 陕西延川一座煤矿发生故意伤害案 致1死1重伤
         * source : 新浪新闻
         * icon : http://k.sinaimg.cn/n/translate/w594h785/20171201/wkPT-fypikwt2065751.jpg/w120h90l50t164e.jpg
         * detailurl : https://news.sina.cn/2017-12-01/detail-ifyphtze3291561.d.html?vt=4&pos=8&cid=56261
         */

        private String article;
        private String source;
        private String name;
        private String icon;
        private String info;
        private String detailurl;
        private String time=time_set();

        private String time_set()
        {
            Calendar calendar=Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH)+1;
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            int hour=calendar.get(Calendar.HOUR_OF_DAY);
            int min=calendar.get(Calendar.MINUTE);
            int scend=calendar.get(Calendar.SECOND);
            String time=year+"/"+month+"/"+day+"--"+hour+":"+min+":"+scend;
            return time;
        }
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
}