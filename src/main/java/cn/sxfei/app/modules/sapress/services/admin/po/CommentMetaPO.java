package cn.sxfei.app.modules.sapress.services.admin.po;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "commentmeta" ,key="meta_id")
public class CommentMetaPO extends BaseModel<CommentMetaPO> {
    /**  */
    private Long metaId;

    /**  */
    private Long commentId;

    /**  */
    private String metaKey;

    /**  */
    private String metaValue;

    public Long getMetaId() {
        return Long.parseLong(getStr("metaId"));
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
        set("metaId", metaId);
    }

    public Long getCommentId() {
        return Long.parseLong(getStr("commentId"));
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
        set("commentId", commentId);
    }

    public String getMetaKey() {
        return getStr("metaKey");
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
        set("metaKey", metaKey);
    }

    public String getMetaValue() {
        return getStr("metaValue");
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
        set("metaValue", metaValue);
    }

}