package cn.sxfei.app.modules.sapress.services.admin.po;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "term_taxonomy", key = "term_taxonomy_id")
public class TermTaxonomyPO extends BaseModel<TermTaxonomyPO> {
    private static final long serialVersionUID = 1L;
    public static final TermTaxonomyPO dao = new TermTaxonomyPO();
    /**  */
    private Long termTaxonomyId;

    /**  */
    private Long termId;

    /**  */
    private String taxonomy;

    /**  */
    private String parent;

    /**  */
    private Long count;

    /**  */
    private String description;

    public Long getTermTaxonomyId() {
        return Long.valueOf(getStr("termTaxonomyId"));
    }

    public void setTermTaxonomyId(Long termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
        set("termTaxonomyId", termTaxonomyId);
    }

    public Long getTermId() {
        return Long.valueOf(getStr("termId"));
    }

    public void setTermId(Long termId) {
        this.termId = termId;
        set("termId", termId);
    }

    public String getTaxonomy() {
        return getStr("taxonomy");
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
        set("taxonomy", taxonomy);
    }

    public String getParent() {
        return getStr("parent");
    }

    public void setParent(String parent) {
        this.parent = parent;
        set("parent", parent);
    }

    public Long getCount() {
        return Long.valueOf(getStr("count"));
    }

    public void setCount(Long count) {
        this.count = count;
        set("count", count);
    }

    public String getDescription() {
        return getStr("description");
    }

    public void setDescription(String description) {
        this.description = description;
        set("description", description);
    }

}
