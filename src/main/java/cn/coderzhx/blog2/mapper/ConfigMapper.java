package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhx
 * @create 2019-10-18-14
 */
@Mapper
@Configuration
public interface ConfigMapper {
    Config getConfig();//获取配置
    void updateConfig(Config config);//更新配置
}
