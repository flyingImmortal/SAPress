package cn.sxfei.app.modules.sapress.web.admin.dto;

import java.util.List;

import cn.sxfei.app.core.base.dto.DTO;

public class Taxonomy extends DTO {
    private Taxonomy taxonomy;
    private String id;
    private String _selected;
    private String title;
    // private String taxonomy;
    private int layer = 0;
    private List<Taxonomy> childList;
    private String content_count;
    private String slug;
    private String text;
    private String parentId;

    private String content_module;
    private String type;
    private String icon;
    private String parent_id;

    public String getLayerString() {
        String layerString = "";
        for (int i = 0; i < layer; i++) {
            layerString += "— ";
        }
        return layerString;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getContent_module() {
        return content_module;
    }

    public void setContent_module(String content_module) {
        this.content_module = content_module;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent_count() {
        return content_count;
    }

    public void setContent_count(String content_count) {
        this.content_count = content_count;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int tier) {
        this.layer = tier;
    }

    public List<Taxonomy> getChildList() {
        return childList;
    }

    public void setChildList(List<Taxonomy> childList) {
        this.childList = childList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_selected() {
        return _selected;
    }

    public void set_selected(String _selected) {
        this._selected = _selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
