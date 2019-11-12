package cn.coderzhx.blog2.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zhx
 * @create 2019-10-13-15
 */
@Component
public class Link implements Serializable {
    private Long id;
    private Integer isuse=1;
    private String name;
    private String create_time;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", isuse=" + isuse +
                ", name='" + name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
