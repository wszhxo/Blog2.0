package cn.coderzhx.blog2.service;

/**
 * @author zhx
 * @create 2019-10-18-15
 */
public interface IpService {
    void getVisitCount();//获取VisitCount表
    void updateVisitCount();//更新VisitCount表,项目关闭时调用一次
}
