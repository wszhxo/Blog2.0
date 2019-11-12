package cn.coderzhx.blog2.service;

import cn.coderzhx.blog2.po.Link;
import cn.coderzhx.blog2.vo.PageBean;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-18-15
 */
public interface LinkService {
    void saveLink(Link link);
    void deleteLink(Long id);
    List<Link> listLink(PageBean pageBean);

    List<Link> listLink();
}
