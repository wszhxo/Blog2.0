package cn.coderzhx.blog2.service;

import cn.coderzhx.blog2.po.Comment;

import java.util.List;

/**
 * @author zhx
 * @create 2019-11-15-20
 */
public interface CommentService {
    List<Comment> listCommentParentIsNull(Long blogId);

    void saveComment(Comment comment);
}
