package cn.sxfei.app.modules.sapress.services.admin.po;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "term_relationships", key = "object_id,term_taxonomy_id")
public class TermRelationshipsPO extends BaseModel<TermRelationshipsPO> {
    private static final long serialVersionUID = 1L;
    public static final TermRelationshipsPO dao = new TermRelationshipsPO();

    /**  */
    private Integer termOrder;
    /**  */
    private String objectId;

    /**  */
    private Long termTaxonomyId;

    public Integer getTermOrder() {
        return Integer.parseInt(getStr("termOrder"));
    }

    public void setTermOrder(Integer termOrder) {
        this.termOrder = termOrder;
        set("termOrder", termOrder);
    }

    public String getObjectId() {
        return  getStr("objectId");
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
        set("objectId", objectId);
    }

    public Long getTermTaxonomyId() {
        return Long.parseLong(getStr("termTaxonomyId"));
    }

    public void setTermTaxonomyId(Long termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
        set("termTaxonomyId", termTaxonomyId);
    }

}