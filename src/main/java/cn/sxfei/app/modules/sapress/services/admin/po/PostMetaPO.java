package cn.sxfei.app.modules.sapress.services.admin.po;

import java.math.BigInteger;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "postmeta", key = "meta_id")
public class PostMetaPO extends BaseModel<PostMetaPO> {
    private static final long serialVersionUID = 1L;
    public static final PostMetaPO dao = new PostMetaPO();

    /**  */
    private Long metaId;

    /**  */
    private BigInteger postId;

    /**  */
    private String metaKey;

    /**  */
    private String metaValue;

    public String getMetaId() {
        return getStr("metaId");
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
        set("metaId", metaId);
    }

    public Long getPostId() {
        return Long.parseLong(getStr("postId"));
    }

    public void setPostId(BigInteger postId) {
        this.postId = postId;
        set("postId", postId);
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