package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.mapper.UserMapper;
import cn.coderzhx.blog2.po.User;
import cn.coderzhx.blog2.service.UserService;
import cn.coderzhx.blog2.util.MarkdownUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhx
 * @create 2019-10-25-10
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User checkUser(String username) {
        return userMapper.checkUser(username);
    }

    @Override
    public String aboutme() {
        return MarkdownUtils.markdownToHtmlExtensions(userMapper.aboutme()) ;
    }
}
