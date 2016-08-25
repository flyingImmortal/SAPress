package cn.sxfei.app.modules.sapress.interfaces.base.entity;

import cn.sxfei.app.core.base.dto.BaseEntity;

public class Terms extends BaseEntity {
    /**  */
    private Long termId;

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

    public Long getTermTaxonomyId() {
        return termTaxonomyId;
    }

    public void setTermTaxonomyId(Long termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    public Long getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(Long termGroup) {
        this.termGroup = termGroup;
    }
}