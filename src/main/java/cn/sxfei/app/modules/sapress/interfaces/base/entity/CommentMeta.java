package cn.sxfei.app.modules.sapress.interfaces.base.entity;

import cn.sxfei.app.core.base.dto.BaseEntity;

public class CommentMeta extends BaseEntity{
    /**  */
    private Long metaId;

    /**  */
    private Long commentId;

    /**  */
    private String metaKey;

    /**  */
    private String metaValue;

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey == null ? null : metaKey.trim();
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue == null ? null : metaValue.trim();
    }
}