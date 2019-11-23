package cn.coderzhx.blog2.handler;

import cn.coderzhx.blog2.po.Blog;
import cn.coderzhx.blog2.service.BlogService;
import cn.coderzhx.blog2.service.IpService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

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
        //更新缓存的*文章访问次数
        for (Map.Entry<Long, Long> m : blogdService.getNewBlogVisit().entrySet()) {
            //还要更新缓存中的文章的访问次数
            Blog blogById = blogdService.getBlogById(m.getKey());
            blogById.setViews(m.getValue()+blogById.getViews());
            blogdService.updateBlogViews(blogById);
        }

        ipService.updateVisitCount();//网站统计ip和总访问数
        blogdService.addViewsToDateBase();//添加文章访问次数
    }

}
