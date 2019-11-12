package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.mapper.LinkMapper;
import cn.coderzhx.blog2.po.Link;
import cn.coderzhx.blog2.service.LinkService;
import cn.coderzhx.blog2.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhx
 * @create 2019-10-18-15
 */
@Service
public class LinkServiceImpl  implements LinkService {

    @Resource
    LinkMapper linkMapper;
    @Override
    public void saveLink(Link link) {
        linkMapper.saveLink(link);
    }

    @Override
    public void deleteLink(Long id) {
        linkMapper.deleteLink(id);
    }

    @Override
    public List<Link> listLink(PageBean pageBean) {
        return linkMapper.listLink( pageBean);
    }

    @Override
    public List<Link> listLink() {
        return linkMapper.listLink( new PageBean());
    }
}
