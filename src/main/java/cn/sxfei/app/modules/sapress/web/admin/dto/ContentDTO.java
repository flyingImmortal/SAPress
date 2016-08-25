package cn.sxfei.app.modules.sapress.web.admin.dto;

import java.util.List;

import cn.sxfei.app.core.base.dto.DTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;
import cn.sxfei.app.modules.sapress.interfaces.base.enums.PostTypeEnum;
import cn.sxfei.app.modules.sapress.services.admin.TermsInitService;
import cn.sxfei.app.modules.sapress.services.admin.po.TermTaxonomyPO;

import com.google.common.collect.Lists;

public class ContentDTO extends DTO {
    private String title;
    private String status;
    private String url;
    private String id;
    private String username;
    private String commentCount;
    private String created;
    private List<Taxonomy> taxonomyList = Lists.newArrayList();
    private List<Terms> termsList = Lists.newArrayList();
    // ---------------
    private String slug;// http://
    private String text;
    private String module;// =post
    // -------------
    private String flag;
    private String comment_status;
    private String meta_keywords;
    private String meta_description;
    private String lng;
    private String lat;
    private String remarks;
    private String thumbnail;

    public String getTermsAsString(String type) {
        StringBuilder retBuilder = new StringBuilder();
        if (null != termsList) {
            for (Terms terms : termsList) {
                TermTaxonomyPO po = TermsInitService.termTaxonomyTerms.get(terms.getTermId());
                if (po.getTaxonomy().endsWith(type)) {
                    retBuilder.append(terms.getName()).append(",");
                }
            }
        }
        if (retBuilder.length() > 0) {
            retBuilder.deleteCharAt(retBuilder.length() - 1);
        }
        return retBuilder.toString();
    }
    public String getTaxonomyAsString(String type) {
        StringBuilder retBuilder = new StringBuilder();
        for (Taxonomy taxonomy : taxonomyList) {
            if (taxonomy.getType().equals(type)) {
                retBuilder.append(taxonomy.getTitle()).append(",");
            }

        }
        if (retBuilder.length() > 0) {
            retBuilder.deleteCharAt(retBuilder.length() - 1);
        }
        return retBuilder.toString();
    }
    public List<Terms> getTermsList() {
        return termsList;
    }

    public void setTermsList(List<Terms> termsList) {
        this.termsList = termsList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<Taxonomy> getTaxonomyList() {
        return taxonomyList;
    }

    public void setTaxonomyList(List<Taxonomy> taxonomyList) {
        this.taxonomyList = taxonomyList;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
