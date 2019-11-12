package cn.coderzhx.blog2.handler;

import cn.coderzhx.blog2.service.BlogService;
import cn.coderzhx.blog2.service.IpService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author zhx
 * @create 2019-11-02-17
 */
public class ViewsQuartz extends QuartzJobBean {

    @Autowired
    BlogService blogdService;
    @Autowired
    IpService ipService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        ipService.updateVisitCount();//网站统计ip和总访问数
        blogdService.addViewsToDateBase();//添加文章访问次数
    }

}
