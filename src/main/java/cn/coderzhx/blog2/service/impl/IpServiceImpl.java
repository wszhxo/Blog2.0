package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.aspect.LogAspect;
import cn.coderzhx.blog2.mapper.IpMapper;
import cn.coderzhx.blog2.service.IpService;
import cn.coderzhx.blog2.po.RequestLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhx
 * @create 2019-10-19-13
 */
@Service
public class IpServiceImpl implements IpService {
    @Resource
    IpMapper ipMapper;
    static HashMap<String, Long> map1;
    @Override
    public void getVisitCount() {
        map1=new HashMap<>();
        //获取visitCount表的所有记录
        List<RequestLog> visitCount = ipMapper.getVisitCount();
        //添加进Map
        if (visitCount.size()>0){
            for (RequestLog requestLog : visitCount) {
                map1.put(requestLog.getIp(),requestLog.getIpcount());
            }
            LogAspect.map=map1;
        }
    }

    @Override
    public void updateVisitCount() {
        //获取网站积累的ip数和总浏览数
        HashMap<String, Long> map3 = LogAspect.map;
        //删除数据库表
        ipMapper.deleteVisitCount();
        //重新添加表数据
        for (Map.Entry<String, Long> stringLongEntry : map3.entrySet()) {
            ipMapper.addVisitCount(stringLongEntry.getKey(),stringLongEntry.getValue());
        }
    }


}
