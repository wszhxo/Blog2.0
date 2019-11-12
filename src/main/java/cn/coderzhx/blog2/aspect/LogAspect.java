package cn.coderzhx.blog2.aspect;

import cn.coderzhx.blog2.po.RequestLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器,拦截所有操作并记录日志
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //ip地址为键,Integer为访问次数,服务器启动时,此时会被赋值为数据库的所有ip记录
    public  static HashMap<String, Long> map;
    public  static HashMap<String, Long> newmap=new HashMap<>();


    @Pointcut("execution(* cn.coderzhx.blog2.web.*.*(..))")
    //表示web目录下不包括admin目录下的所有方法
    public void log() {}


    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        Long i= map.get(ip);//获取出现次数
        if (i==null) {
            map.put(ip, 1L);
            newmap.put(ip, 1L);
        }else {
            map.put(ip, i+1);
            newmap.put(ip, i+1);
        }

        logger.info("Request : {}", requestLog);
    }

    @After("log()")
    public void doAfter() {
//        logger.info("--------doAfter--------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }



    //获取最终的ip和总访问数
    public static Long[] getVisitCount(){
        Long[] longs=new Long[2];
        Long totalCount=0l;
        Long ipCount= new Long (map.size());
        for (Map.Entry<String, Long> m : map.entrySet()) totalCount += m.getValue();
        longs[0]=ipCount;
        longs[1]=totalCount;
        return  longs;
    }


}
