package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.Tag;
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
public interface TagMapper {
    Long countTag();//标签的个数

    List<Tag> listTag(PageBean pageBean);//列出所有标签,用于后台(分页)

    List<Tag> listTagAndCount(Integer num);//记录该标签下有多少文章
    Tag listTagByid(PageBean pageBean);//列出所有标签,用于后台(分页)

    void saveBlogTags(@Param("blogId") Long blogId, @Param("tagId") Long tagId);//文章标签表

    void deleteTagByBlogId(Long id);//根据blogid删除所有标签
    List<Tag> listTagByBlogId(Long id);//根据blogid删除所有标签

    Tag getTagByName(String name);//根据名字查询标签

    void saveTag(String name);//添加标签

    void updateTag(Tag tag);

    void deleteTag(Long id);

    Tag getTag(Long id);

    List<Tag> listTagCount();
}
