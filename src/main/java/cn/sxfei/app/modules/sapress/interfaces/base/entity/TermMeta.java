package cn.sxfei.app.modules.sapress.interfaces.base.entity;

import cn.sxfei.app.core.base.dto.BaseEntity;

public class TermMeta extends BaseEntity{
    /**  */
    private Long metaId;

    /**  */
    private Long termId;

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

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
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