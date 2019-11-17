package cn.coderzhx.blog2.mapper;

import cn.coderzhx.blog2.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-12
 *
 */
@Mapper
@Configuration
public interface CommentMapper {
    List<Comment> listCommentParentIsNull(Long blogId);//列出所有一级评论
    List<Comment> listCommentChild(Long blogId);//内连接得到所有非一级评论
    List<Comment> listCommentById(Long id);//开始层层递归,找子评论
    void saveComment(Comment comment);//添加评论
}
