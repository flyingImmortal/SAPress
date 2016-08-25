package cn.sxfei.app.modules.sapress.interfaces.base.entity;

import java.util.Date;

import cn.sxfei.app.core.base.dto.BaseEntity;

public class Comments extends BaseEntity{
    /**  */
    private Long commentId;

    /**  */
    private Long commentPostId;

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
    private Long commentParent;

    /**  */
    private Long userId;

    /**  */
    private String commentContent;
    //////////////
    
    private String commentDateStr;
    
    public String getCommentDateStr() {
        return commentDateStr;
    }

    public void setCommentDateStr(String commentDateStr) {
        this.commentDateStr = commentDateStr;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentPostId() {
        return commentPostId;
    }

    public void setCommentPostId(Long commentPostId) {
        this.commentPostId = commentPostId;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor == null ? null : commentAuthor.trim();
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail == null ? null : commentAuthorEmail.trim();
    }

    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl == null ? null : commentAuthorUrl.trim();
    }

    public String getCommentAuthorIp() {
        return commentAuthorIp;
    }

    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp == null ? null : commentAuthorIp.trim();
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Date getCommentDateGmt() {
        return commentDateGmt;
    }

    public void setCommentDateGmt(Date commentDateGmt) {
        this.commentDateGmt = commentDateGmt;
    }

    public Integer getCommentKarma() {
        return commentKarma;
    }

    public void setCommentKarma(Integer commentKarma) {
        this.commentKarma = commentKarma;
    }

    public String getCommentApproved() {
        return commentApproved;
    }

    public void setCommentApproved(String commentApproved) {
        this.commentApproved = commentApproved == null ? null : commentApproved.trim();
    }

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent == null ? null : commentAgent.trim();
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType == null ? null : commentType.trim();
    }

    public Long getCommentParent() {
        return commentParent;
    }

    public void setCommentParent(Long commentParent) {
        this.commentParent = commentParent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }
}