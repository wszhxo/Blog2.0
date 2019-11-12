package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.Blog;
import cn.coderzhx.blog2.po.Type;
import cn.coderzhx.blog2.vo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-12
 */
@Mapper
@Configuration
public interface BlogMapper {

     Long countBlog(PageBean pageBean);//计算总文章数

    List<Blog> listBlog(PageBean pageBean1);//列出所有文章
    Type listBlogByTypeId(Integer id);//列出所有文章
//    Tag listBlogByTagId(Integer id);//列出所有文章

    List<Blog> listRecommendBlogTop(Integer size);//显示指定数量的推荐文章

    Blog getBlogById(Long id);//根据id获取文章

    List<String> getYearAndMonth();//获取文章包含的年份月份
    List<Blog> findByYear(String yearMonth);//根据年月获取文章

    void saveBlog(Blog blog);//添加文章

    void updateBlog(Blog blog);//修改文章

    void deleteBlog(Long id);//删除文章

    List<Blog> listLunboBlog();//轮播文章

    void addViewsToDateBase(@Param("blogId") Long blogId, @Param("count") Long count);
}
