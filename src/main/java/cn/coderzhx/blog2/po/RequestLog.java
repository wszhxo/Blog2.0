package cn.coderzhx.blog2.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhx
 * @create 2019-10-19-10
 */
@Component
public class RequestLog implements Serializable {
    private String url;//访问地址
    private String ip;//访问ip
    private String classMethod;//访问方法
    private Object[] args;//参数
    private Long ipcount;//该ip访问次数

    public RequestLog(){}
    public RequestLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }

    public Long getIpcount() {
        return ipcount;
    }

    public void setIpcount(Long ipcount) {
        this.ipcount = ipcount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
