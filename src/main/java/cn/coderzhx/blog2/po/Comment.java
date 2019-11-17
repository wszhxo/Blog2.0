package cn.coderzhx.blog2.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-12
 */
@Component
public class Comment implements Serializable {
    private Long id;
    private Integer adminComment=0;//管理员评论
    private String avatar;//头像
    private String content;//评论内容
    private String createTime;//评论时间
    private String email;//邮箱
    private String nickname;//昵称
    private Long blogId;//评论的文章id

    private Long parentId;//父级评论id;
    private String pnickname;//父级昵称

    private List<Comment> replyComments = new ArrayList<>();//存储有关该id 的所有子评论

    public String getPnickname() {
        return pnickname;
    }

    public void setPnickname(String pnickname) {
        this.pnickname = pnickname;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Integer adminComment) {
        this.adminComment = adminComment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", adminComment='" + adminComment + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", blogId=" + blogId +
                ", parentId=" + parentId +
//                ", replyComments=" + replyComments +
                '}';
    }
}
