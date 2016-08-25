package cn.sxfei.app.modules.sapress.web.admin.dto;

import cn.sxfei.app.core.base.dto.DTO;

public class Comment extends DTO {
    private Comment Comment;
    private String id;
    private String username;
    private String text;
    private String status;
    private String comment_count;
    private String created;
    private String contentUrl;
    private String content_title;
    // //////////
    private String parent_id;
    private String content_id;
    private String author;
    private String content_module;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent_module() {
        return content_module;
    }

    public void setContent_module(String content_module) {
        this.content_module = content_module;
    }

    public Comment getComment() {
        return Comment;
    }

    public void setComment(Comment comment) {
        Comment = comment;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
