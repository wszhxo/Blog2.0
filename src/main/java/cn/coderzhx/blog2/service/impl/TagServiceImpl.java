package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.mapper.TagMapper;
import cn.coderzhx.blog2.po.Tag;
import cn.coderzhx.blog2.service.TagService;
import cn.coderzhx.blog2.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhx
 * @create 2019-10-17-13
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    TagMapper tagMapper;
    @Override
    public void saveTag(Tag tag) {
        tagMapper.saveTag(tag.getName());
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Long countTag() {
        return tagMapper.countTag();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public PageBean<Tag> listTag(PageBean pageBean) {
        Long size = countTag();
        //Long转化为int,方法中定义为int也没事毕竟数据还很小
        PageBean pageBean1 = new PageBean(pageBean.getCurrentPage(), Math.toIntExact(size), pageBean.getPageSize());
        pageBean1.setList(tagMapper.listTag(pageBean1));
        return pageBean1;
    }

    @Override
    public List<Tag> listTagAndCount(Integer size) {
        return tagMapper.listTagAndCount(size);
    }

    @Override
    public List<Tag> listTagByIds(String ids) {
        return null;
    }

    @Override
    public void updateTag( Tag tag) {
        tagMapper.updateTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public PageBean listTagByid(PageBean pageBean) {
        Tag  tag= tagMapper.listTagByid(pageBean);//该类下的文章封装进了list中
        PageBean pageBean1 = new PageBean(pageBean.getCurrentPage(), tag.getBlogs().size(), pageBean.getPageSize());
        pageBean1.setList(tag.getBlogs());
        pageBean1.setTagId(pageBean.getTagId());
        return pageBean1;
    }

    @Override
    public List<Tag> listTagByBlogId(Long id) {
        return tagMapper.listTagByBlogId(id);
    }

    @Override
    public List<Tag> listTagCount() {
        return tagMapper.listTagCount();
    }
}
