package cn.sxfei.app.modules.sapress.services.admin.po;

import cn.sxfei.app.core.base.dto.BaseEntity;
import cn.sxfei.app.core.utils.StringUtils;
import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "terms", key = "term_id")
public class TermsPO extends BaseModel<TermsPO> {
    private static final long serialVersionUID = 1L;
    public static final TermsPO dao = new TermsPO();

    /**  */
    private String termId;

    /**  */
    private String name;

    /**  */
    private String slug;

    /**  */
    private Long termGroup;

    // /////termTaxonomyId/////////
    /**  */
    private Long termTaxonomyId;

    /**  */
    private String taxonomy;

    /**  */
    private Long parent;

    /**  */
    private Long count;

    /**  */
    private String description;

    public Long getTermId() {
        String temp =getStr("termId");
        if(StringUtils.isNullString(temp)){
            return null;
        }
        return Long.parseLong(temp);
    }

    public void setTermId(String termId) {
        this.termId = termId;
        set("termId", termId);
    }

    public String getName() {
        return getStr("name");
    }

    public void setName(String name) {
        this.name = name;
        set("name", name);
    }

    public String getSlug() {
        return getStr("slug");
    }

    public void setSlug(String slug) {
        this.slug = slug;
        set("slug", slug);
    }

    public Long getTermGroup() {
        return Long.parseLong(getStr("termGroup"));
    }

    public void setTermGroup(Long termGroup) {
        this.termGroup = termGroup;
        set("termGroup", termGroup);
    }

    public Long getTermTaxonomyId() {
        return Long.parseLong(getStr("termTaxonomyId"));
    }

    public void setTermTaxonomyId(Long termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
        set("termTaxonomyId", termTaxonomyId);
    }

    public String getTaxonomy() {
        return getStr("taxonomy");
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
        set("taxonomy", taxonomy);
    }

    public Long getParent() {
        return Long.parseLong(getStr("parent"));
    }

    public void setParent(Long parent) {
        this.parent = parent;
        set("parent", parent);
    }

    public Long getCount() {
        return Long.parseLong(getStr("count"));
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