package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.Type;
import cn.coderzhx.blog2.vo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-12
 */
@Mapper
@Configuration
public interface TypeMapper {

     Long countType();//分类的个数

    List<Type> listType(PageBean pageBean);//列出所有分类,用于后台(分页)
    Type listTypeByid(PageBean pageBean);//列出所有分类,用于后台(分页)

    List<Type> listTypeAndCount(Integer num);//记录该类下有多少文章

    void updateType(Type type);//更新

    void deleteType(Long id);

    Type getTypeByName(String name);

    void saveType(Type type);

    Type getType(Long id);
}
