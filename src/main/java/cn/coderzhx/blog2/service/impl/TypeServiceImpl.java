package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.mapper.BlogMapper;
import cn.coderzhx.blog2.mapper.TypeMapper;
import cn.coderzhx.blog2.po.Type;
import cn.coderzhx.blog2.service.TypeService;
import cn.coderzhx.blog2.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhx
 * @create 2019-10-17-13
 */

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;
    @Resource
    BlogMapper blogMapper;

    @Override
    public void saveType(Type type) {
        typeMapper.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public Type getType(PageBean pageBean) {

        return blogMapper.listBlogByTypeId(pageBean.getTypeId());

    }

    @Override
    public Long countType() {
        return typeMapper.countType();
    }

//    @Override
//    public PageBean listTypeByid(PageBean pageBean) {
//        Type type = typeMapper.listTypeByid(pageBean.getTypeId());//该类下的文章封装进了list中
//        PageBean pageBean1 = new PageBean(pageBean.getCurrentPage(), type.getBlogs().size(), pageBean.getPageSize());
//        pageBean1.setList(type.getBlogs());
//        return pageBean1;
//    }
    @Override
    public PageBean listTypeByid(PageBean pageBean) {
        Type type = typeMapper.listTypeByid(pageBean);//该类下的文章封装进了list中
        //这里有个空指针的bug,前提是blog表中的cover是数字,否则正常
        PageBean pageBean1 = new PageBean(pageBean.getCurrentPage(), type.getBlogs().size(), pageBean.getPageSize());
        pageBean1.setList(type.getBlogs());
        pageBean1.setTypeId(pageBean.getTypeId());
        return pageBean1;
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public PageBean<Type> listType(PageBean pageBean) {
        Long size = countType();
        //Long转化为int,方法中定义为int也没事毕竟数据还很小
        PageBean pageBean1 = new PageBean(pageBean.getCurrentPage(), Math.toIntExact(size), pageBean.getPageSize());
        pageBean1.setList(typeMapper.listType(pageBean1));
        return pageBean1;
    }

    @Override
    public List<Type> listTypeAndCount(Integer num) {
        return typeMapper.listTypeAndCount(num);
    }

    @Override
    public void updateType(Type type) {
        typeMapper.updateType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

}
