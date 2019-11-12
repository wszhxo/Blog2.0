package cn.coderzhx.blog2.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhx
 * @create 2019-10-26-14
 */
@Configuration
public class WebPathFigurer implements WebMvcConfigurer {
    @Value("${file.uploadfolder}")
    private String uploadfolder;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///"+uploadfolder);
    }

}