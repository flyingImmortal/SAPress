package cn.sxfei.app.modules.sapress.interfaces.base.dto;

import java.util.List;

import cn.sxfei.app.core.base.dto.DTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;

public class PostDTO extends DTO {

    private String id;
    private String month;
    private String day;
    private String monthDay;
    private String title;
    private List<Terms> category;
    private String author;
    private String comment;
    private String viewTotle;
    private String contentArticle;
    private String originalUrl;
    private List<Comments> commentList;
    
    public List<Comments> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comments> commentList) {
        this.commentList = commentList;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Terms> getCategory() {
        return category;
    }

    public void setCategory(List<Terms> category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getViewTotle() {
        return viewTotle;
    }

    public void setViewTotle(String viewTotle) {
        this.viewTotle = viewTotle;
    }

    public String getContentArticle() {
        return contentArticle;
    }

    public void setContentArticle(String contentArticle) {
        this.contentArticle = contentArticle;
    }

}
