package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.Link;
import cn.coderzhx.blog2.vo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-18-15
 */
@Mapper
@Configuration
public interface LinkMapper {
    void saveLink(Link link);//添加友链
    void deleteLink(Long id);//删除友链
    List<Link> listLink(PageBean pageBean);
}
