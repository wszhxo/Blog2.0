package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.RequestLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-18-14
 */
@Mapper
@Configuration
public interface IpMapper {
    List<RequestLog> getVisitCount();//获取ip表
    void addVisitCount(String ip, Long count);//添加表
    void deleteVisitCount();//清空表
}
