package com.fanwe.catchdoll.model;


/**
 */

public class WWMyThingsModel
{
    private String id;
    private String doll_name;//名称
    private String img;//图片
    private int status;//状态: 0 未领取, 1 领取中, 2 已领取 -2 已兑换
    private String status_color;//状态背景颜色
    private String status_name;//状态名称
    private String grab_time;//兑换时间
    private String pay_time;//付款时间

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDoll_name()
    {
        return doll_name;
    }

    public void setDoll_name(String doll_name)
    {
        this.doll_name = doll_name;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getStatus_name()
    {
        return status_name;
    }

    public void setStatus_name(String status_name)
    {
        this.status_name = status_name;
    }

    public String getGrab_time()
    {
        return grab_time;
    }

    public void setGrab_time(String grab_time)
    {
        this.grab_time = grab_time;
    }

    public String getPay_time()
    {
        return pay_time;
    }

    public void setPay_time(String pay_time)
    {
        this.pay_time = pay_time;
    }

    public String getStatus_color()
    {
        return status_color;
    }

    public void setStatus_color(String status_color)
    {
        this.status_color = status_color;
    }

}
