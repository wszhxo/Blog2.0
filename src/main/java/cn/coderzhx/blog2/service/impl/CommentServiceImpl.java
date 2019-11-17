package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.mapper.CommentMapper;
import cn.coderzhx.blog2.po.Comment;
import cn.coderzhx.blog2.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhx
 * @create 2019-11-15-20
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;
       //存储为二级评论
    List<Comment> tempReplys ;

    @Override
    public List<Comment> listCommentParentIsNull(Long blogId){
        //1.列出一级评论
        List<Comment> commentList = commentMapper.listCommentParentIsNull(blogId);

        //2.除一级以外的级联评论类似于一棵树,有多层
        for (Comment comment : commentList) {
            List<Comment> comments = commentMapper.listCommentById(comment.getId());
            //3.把上述的一级除外的评论,转化为二级评论放到一级评论commentList下的每个Comment的集合List<Comment>中
            //回复顶级评论的可能有多个所以要循环
            for (Comment c : comments) {
                 tempReplys = new ArrayList<>();
                chageToChildList(c);
                //添加到一级评论的List下形成只有两级的评论
                comment.setReplyComments(tempReplys);
            }
        }

        return commentList;

    }

    @Override
    public void saveComment(Comment comment) {
        comment.setAvatar("http://q4.qlogo.cn/headimg_dl?dst_uin="+comment.getEmail().split("@")[0]+"&spec=1");
        commentMapper.saveComment(comment);
    }

    //用于转化为二级集合,递归
    void chageToChildList(Comment comment){
        tempReplys.add(comment);
        if(comment.getReplyComments().size()>0){
            for (Comment c : comment.getReplyComments()) {
                chageToChildList(c);
            }
        }
    }






}

