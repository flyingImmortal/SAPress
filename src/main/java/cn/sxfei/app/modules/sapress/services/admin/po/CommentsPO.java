package cn.sxfei.app.modules.sapress.services.admin.po;

import java.util.Date;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "comments", key = "comment_id")
public class CommentsPO extends BaseModel<CommentsPO> {
    public static final CommentsPO dao = new CommentsPO();
    /**  */
    private Long commentId;

    /**  */
    private String commentPostId;

    /**  */
    private String commentAuthor;

    /**  */
    private String commentAuthorEmail;

    /**  */
    private String commentAuthorUrl;

    /**  */
    private String commentAuthorIp;

    /**  */
    private Date commentDate;

    /**  */
    private Date commentDateGmt;

    /**  */
    private Integer commentKarma;

    /**  */
    private String commentApproved;

    /**  */
    private String commentAgent;

    /**  */
    private String commentType;

    /**  */
    private String commentParent;

    /**  */
    private Long userId;

    /**  */
    private String commentContent;

    public String getCommentId() {
        return getStr("commentId");
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
        set("commentId", commentId);
    }

    public String getCommentPostId() {
        return getStr("commentPostId");
    }

    public void setCommentPostId(String commentPostId) {
        this.commentPostId = commentPostId;
        set("commentPostId", commentPostId);
    }

    public String getCommentAuthor() {
        return getStr("commentAuthor");
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
        set("commentAuthor", commentAuthor);
    }

    public String getCommentAuthorEmail() {
        return getStr("commentAuthorEmail");
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
        set("commentAuthorEmail", commentAuthorEmail);
    }

    public String getCommentAuthorUrl() {
        return getStr("commentAuthorUrl");
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
        set("commentAuthorUrl", commentAuthorUrl);
    }

    public String getCommentAuthorIp() {
        return getStr("commentAuthorIp");
    }

    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp;
        set("commentAuthorIp", commentAuthorIp);
    }

    public Date getCommentDate() {
        return getDate("commentDate");
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
        set("commentDate", commentDate);
    }

    public Date getCommentDateGmt() {
        return getDate("commentDateGmt");
    }

    public void setCommentDateGmt(Date commentDateGmt) {
        this.commentDateGmt = commentDateGmt;
        set("commentDateGmt", commentDateGmt);
    }

    public Integer getCommentKarma() {
        return Integer.parseInt(getStr("commentKarma"));
    }

    public void setCommentKarma(Integer commentKarma) {
        this.commentKarma = commentKarma;
        set("commentKarma", commentKarma);
    }

    public String getCommentApproved() {
        return getStr("commentApproved");
    }

    public void setCommentApproved(String commentApproved) {
        this.commentApproved = commentApproved;
        set("commentApproved", commentApproved);
    }

    public String getCommentAgent() {
        return getStr("commentAgent");
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
        set("commentAgent", commentAgent);
    }

    public String getCommentType() {
        return getStr("commentType");
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
        set("commentType", commentType);
    }

    public Long getCommentParent() {
        return Long.parseLong(getStr("commentParent"));
    }

    public void setCommentParent(String commentParent) {
        this.commentParent = commentParent;
        set("commentParent", commentParent);
    }

    public Long getUserId() {
        return Long.parseLong(getStr("userId"));
    }

    public void setUserId(Long userId) {
        this.userId = userId;
        set("userId", userId);
    }

    public String getCommentContent() {
        return getStr("commentContent");
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
        set("commentContent", commentContent);
    }

}