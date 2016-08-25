package cn.sxfei.app.modules.sapress.services.admin.po;

import java.math.BigInteger;
import java.util.Date;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "posts")
public class PostsPO extends BaseModel<PostsPO> {
    private static final long serialVersionUID = 1L;
    public static final PostsPO dao = new PostsPO();

    /**  */
    private String id;

    /**  */
    private String postAuthor;

    /**  */
    private String postDate;

    /**  */
    private String postDateGmt;

    /**  */
    private String postStatus;

    /**  */
    private String commentStatus;

    /**  */
    private String pingStatus;

    /**  */
    private String postPassword;

    /**  */
    private String postName;

    /**  */
    private String postModified;

    /**  */
    private String postModifiedGmt;

    /**  */
    private String postParent;

    /**  */
    private String guid;

    /**  */
    private String menuOrder;

    /**  */
    private String postType;

    /**  */
    private String postMimeType;

    /**  */
    private String commentCount;
    /**  */
    private String postContent;

    /**  */
    private String postTitle;

    /**  */
    private String postExcerpt;

    /**  */
    private String toPing;

    /**  */
    private String pinged;

    /**  */
    private String postContentFiltered;

    public String getId() {
        return getStr("id");
    }

    public void setId(String id) {
        this.id = id;
        set("id", id);
    }

    public String getPostAuthor() {
        return getStr("postAuthor");
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
        set("postAuthor", postAuthor);
    }

    public Date getPostDate() {
        return getDate("postDate");
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
        set("postDate", postDate);
    }

    public Date getPostDateGmt() {
        return getDate("postDateGmt");
    }

    public void setPostDateGmt(String postDateGmt) {
        this.postDateGmt = postDateGmt;
        set("postDateGmt", postDateGmt);
    }

    public String getPostStatus() {
        return getStr("postStatus");
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
        set("postStatus", postStatus);
    }

    public String getCommentStatus() {
        return getStr("commentStatus");
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
        set("commentStatus", commentStatus);
    }

    public String getPingStatus() {
        return getStr("pingStatus");
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
        set("pingStatus", pingStatus);
    }

    public String getPostPassword() {
        return getStr("postPassword");
    }

    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword;
        set("postPassword", postPassword);
    }

    public String getPostName() {
        return getStr("postName");
    }

    public void setPostName(String postName) {
        this.postName = postName;
        set("postName", postName);
    }

    public Date getPostModified() {
        return getDate("postModified");
    }

    public void setPostModified(String postModified) {
        this.postModified = postModified;
        set("postModified", postModified);
    }

    public Date getPostModifiedGmt() {
        return getDate("postModifiedGmt");
    }

    public void setPostModifiedGmt(String postModifiedGmt) {
        this.postModifiedGmt = postModifiedGmt;
        set("postModifiedGmt", postModifiedGmt);
    }

    public Long getPostParent() {
        return getLong("postParent");
    }

    public void setPostParent(String postParent) {
        this.postParent = postParent;
        set("postParent", postParent);
    }

    public String getGuid() {
        return getStr("guid");
    }

    public void setGuid(String guid) {
        this.guid = guid;
        set("guid", guid);
    }

    public Integer getMenuOrder() {
        return getInt("menuOrder");
    }

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
        set("menuOrder", menuOrder);
    }

    public String getPostType() {
        return getStr("postType");
    }

    public void setPostType(String postType) {
        this.postType = postType;
        set("postType", postType);
    }

    public String getPostMimeType() {
        return getStr("postMimeType");
    }

    public void setPostMimeType(String postMimeType) {
        this.postMimeType = postMimeType;
        set("postMimeType", postMimeType);
    }

    public Long getCommentCount() {
        return getLong("commentCount");
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
        set("commentCount", commentCount);
    }

    public String getPostContent() {
        return getStr("postContent");
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
        set("postContent", postContent);
    }

    public String getPostTitle() {
        return getStr("postTitle");
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
        set("postTitle", postTitle);
    }

    public String getPostExcerpt() {
        return getStr("postExcerpt");
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
        set("postExcerpt", postExcerpt);
    }

    public String getToPing() {
        return getStr("toPing");
    }

    public void setToPing(String toPing) {
        this.toPing = toPing;
        set("toPing", toPing);
    }

    public String getPinged() {
        return getStr("pinged");
    }

    public void setPinged(String pinged) {
        this.pinged = pinged;
        set("pinged", pinged);
    }

    public String getPostContentFiltered() {
        return getStr("postContentFiltered");
    }

    public void setPostContentFiltered(String postContentFiltered) {
        this.postContentFiltered = postContentFiltered;
        set("postContentFiltered", postContentFiltered);
    }

}