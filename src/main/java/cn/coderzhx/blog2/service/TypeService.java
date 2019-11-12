package cn.coderzhx.blog2.service;

import cn.coderzhx.blog2.po.Type;
import cn.coderzhx.blog2.vo.PageBean;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-20
 */
public interface TypeService {

    void saveType(Type type);

    Type getType(Long id);
    Type getType(PageBean pageBean);
    Long  countType();
    PageBean listTypeByid(PageBean pageBean);
    Type getTypeByName(String name);
    PageBean<Type> listType(PageBean pageBean);
    List<Type> listTypeAndCount(Integer num);
    void deleteType(Long id);
    void updateType(Type type);
}
