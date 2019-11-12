package cn.coderzhx.blog2.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zhx
 * @create 2019-10-13-13
 */
@Component
public class Config implements Serializable {
   private Long id;
     private String name;//网站logo名字
    private String icon;//头像
    private String description;//关于我文章
    private String   wechatgzh;//微信公众号
    private String wechat_icon;//微信号
    private String     weixinpay;//微信支付图片
    private String   Alipay;//支付宝支付图片
    private String  create_time;//建站时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWechatgzh() {
        return wechatgzh;
    }

    public void setWechatgzh(String wechatgzh) {
        this.wechatgzh = wechatgzh;
    }

    public String getWechat_icon() {
        return wechat_icon;
    }

    public void setWechat_icon(String wechat_icon) {
        this.wechat_icon = wechat_icon;
    }

    public String getWeixinpay() {
        return weixinpay;
    }

    public void setWeixinpay(String weixinpay) {
        this.weixinpay = weixinpay;
    }

    public String getAlipay() {
        return Alipay;
    }

    public void setAlipay(String alipay) {
        Alipay = alipay;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", wechatgzh='" + wechatgzh + '\'' +
                ", wechat_icon='" + wechat_icon + '\'' +
                ", weixinpay='" + weixinpay + '\'' +
                ", Alipay='" + Alipay + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
