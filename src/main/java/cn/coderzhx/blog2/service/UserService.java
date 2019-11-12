package cn.coderzhx.blog2.service;

import cn.coderzhx.blog2.po.User;

/**
 * @author zhx
 * @create 2019-10-25-10
 */
public interface UserService {

    User checkUser(String username);

    String aboutme();
}
