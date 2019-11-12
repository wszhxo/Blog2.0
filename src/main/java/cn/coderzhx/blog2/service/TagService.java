package cn.coderzhx.blog2.service;

import cn.coderzhx.blog2.po.Tag;
import cn.coderzhx.blog2.vo.PageBean;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-20
 */
public interface TagService {
    void saveTag(Tag type);

    Tag getTag(Long id);
    Long countTag();

    Tag getTagByName(String name);

    PageBean<Tag> listTag(PageBean pageBean);

    List<Tag> listTagAndCount(Integer size);

    List<Tag> listTagByIds(String ids);

    void updateTag(Tag tag);

    void deleteTag(Long id);

    PageBean listTagByid(PageBean pageBean);

    List<Tag> listTagByBlogId(Long id);

    List<Tag> listTagCount();
}
