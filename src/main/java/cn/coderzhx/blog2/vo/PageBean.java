package cn.coderzhx.blog2.vo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhx
 * @create 2019-07-20-16
 */
@Component
public class PageBean<T> {

    // 当前页数
    private Integer currentPage=1;
    // 总记录数
    private Integer totalCount;
    // 每页显示条数
    private Integer pageSize=7;
    // 总页数
    private Integer totalPage;
    // 起始索引
    private Integer index;
    //数据
   private List<T> list;
    //全局搜索条件
    private String word;

    //博客管理后台搜索条件
    private String title;
    private Integer published;//是否草稿



    private Integer lunbo;//是否轮播

    private Integer typeId;
    private Integer tagId;


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }


    //根据标签查出文章
    private String tags;

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }



    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
    public Integer getLunbo() {
        return lunbo;
    }

    public void setLunbo(Integer lunbo) {
        this.lunbo = lunbo;
    }

    public PageBean() {
    }


    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.totalCount = totalCount;

        this.pageSize = pageSize;

        this.currentPage = currentPage;

        if (this.currentPage == null) {
            // 如页面没有指定显示那一页.显示第一页.
            this.currentPage = 1;
        }

        if (this.pageSize == null) {
            // 如果每页显示条数没有指定,默认每页显示8条
            this.pageSize = 8;
        }

        // 计算总页数
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;

        // 判断当前页数是否超出范围
        // 不能小于1
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
        // 不能大于总页数
        if (this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }

        //设置起始下标
       // 考虑条件不存在数据为0的情况
        if (this.currentPage==0){
            this.index=0;
        }else{
            this.index = (this.currentPage - 1) * this.pageSize;
        }
    }

    // 计算起始索引
//	public int getStart() {
//		return (this.currentPage - 1) * this.pageSize;
//	}
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", index=" + index +
                ", word='" + word + '\'' +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", typeId=" + typeId +
                ", tagId=" + tagId +
                ", tags='" + tags + '\'' +
                '}';
    }

}
