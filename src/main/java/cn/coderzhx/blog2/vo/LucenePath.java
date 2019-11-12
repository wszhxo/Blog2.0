package cn.coderzhx.blog2.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhx
 * @create 2019-11-02-19
 */
@Component
@ConfigurationProperties(prefix="lucene")
public class LucenePath {
    // lucene的索引路径 写到配置文件里面
    private static String path;

    public static String getPath() {
        return path;
    }

    public void setPath(String path) {
        LucenePath.path = path;
    }
}