package cn.coderzhx.blog2.util;

import cn.coderzhx.blog2.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zhx
 * @create 2019-10-19-12
 */

@Configuration
public class IndexInit {
   @Autowired
    IpService ipService;
    @PostConstruct //1在构造函数执行完之后执行
    public void init() {
        ipService.getVisitCount();
    }

}
