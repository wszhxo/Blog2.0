package cn.coderzhx.blog2.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author zhx
 * @create 2019-10-13-11
 */
@Component
public class Type implements Serializable {
    private Long id;
    private String name;
    private Integer sum;//记录数量
    //该分类下的文章
    private ArrayList<Blog> blogs = new ArrayList<>();
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }


    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                ", blogs=" + blogs +
                '}';
    }
}
