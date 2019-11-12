package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhx
 * @create 2019-10-13-12
 */
@Mapper
@Configuration
public interface UserMapper {

     User checkUser(String username) ;

    String aboutme();
}
