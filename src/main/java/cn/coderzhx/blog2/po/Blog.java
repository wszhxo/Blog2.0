package cn.coderzhx.blog2.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhx
 * @create 2019-10-13-10
 */
@Component
public class Blog implements Serializable, Cloneable {
    private Long id;
    private String cover;//封面
    private String title;//标题
    private String summary;//简介
    private String content;//markdwon内容
    private String createTime;//创建时间
    private String updateTime;//更新时间
    private Type typeId;//种类
    private Integer commentabled=0;//是否开启评论
    private Integer shareState=0;//转载声明
    private Long views=0L;//访问人数
    private Integer published;//是否草稿
    private String topTime;//置顶时间
    private Integer istop=0;//是否置顶
    private Integer lunbo=0;//是否轮播

    private List<Tag> tags=new ArrayList<>();//该文章下的所有标签
    private String tagIds;//标签的id字符串集合,用于业务


    @Override
    public Blog clone() {
        Blog blog = null;
        try {
            blog = (Blog) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return blog;
    }

    public Integer getIstop() {
        return istop;
    }
    //因为前台表单是check 传过来的是on
    public void setIstop(String istop) {
        this.istop = (istop.equals("on")||Integer.valueOf(istop).equals(1)? 1:0);
    }
    public String getTagIds() {
        return tagIds;
    }
    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getLunbo() {
        return lunbo;
    }

    public void setLunbo(String lunbo) {
        this.lunbo = (lunbo.equals("on")||Integer.valueOf(lunbo).equals(1)? 1:0);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTopTime() {
        return topTime;
    }

    public void setTopTime(String topTime) {
        this.topTime = topTime;
    }


    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Type getTypeId() {
        return typeId;
    }

    public void setTypeId(Type typeId) {
        this.typeId = typeId;
    }

    public Integer getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(String commentabled) {
        System.out.println(commentabled);
        this.commentabled = (commentabled.equals("on")||Integer.valueOf(commentabled).equals(1)? 1:0);
    }


    public Integer getShareState() {
        return shareState;
    }

    public void setShareState(String shareState) {
        this.shareState = (shareState.equals("on")||Integer.valueOf(shareState).equals(1)? 1:0);
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    //抽取标签集合用逗号分隔
    public String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", typeId=" + typeId +
                ", commentabled=" + commentabled +
                ", shareState=" + shareState +
                ", views=" + views +
                ", published=" + published +
                ", topTime='" + topTime + '\'' +
                ", istop=" + istop +
                ", lunbo=" + lunbo +
                ", tags=" + tags +
                ", tagIds='" + tagIds + '\'' +
                '}';
    }
}
