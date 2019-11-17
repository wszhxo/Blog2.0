package cn.coderzhx.blog2.aspect;

import cn.coderzhx.blog2.handler.ViewsQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhx
 * @create 2019-11-02-17
 */
@Configuration
public class QuartzConfig {

    private static final String VIEWS_IDENTITY = "viewsQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(ViewsQuartz.class).withIdentity(VIEWS_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(1)  //设置时间周期单位秒
                .withIntervalInHours(24)  //一天执行一次
//                .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 2018"))
                .repeatForever();
        return  TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(VIEWS_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}