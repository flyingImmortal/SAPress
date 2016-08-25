package cn.sxfei.app.modules.sapress.services.admin.po;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "termmeta",key="meta_id")
public class TermMetaPO extends BaseModel<TermMetaPO> {
    /**  */
    private Long metaId;

    /**  */
    private Long termId;

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

    public Long getTermId() {
        return Long.parseLong(getStr("termId"));
    }

    public void setTermId(Long termId) {
        this.termId = termId;
        set("termId", termId);
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