package cn.sxfei.app.modules.sapress.interfaces.base.entity;

import cn.sxfei.app.core.base.dto.BaseEntity;

public class TermRelationships extends BaseEntity{
    /**  */
    private Integer termOrder;
    /**  */
    private Long objectId;

    /**  */
    private Long termTaxonomyId;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getTermTaxonomyId() {
        return termTaxonomyId;
    }

    public void setTermTaxonomyId(Long termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
    }

    public Integer getTermOrder() {
        return termOrder;
    }

    public void setTermOrder(Integer termOrder) {
        this.termOrder = termOrder;
    }
}