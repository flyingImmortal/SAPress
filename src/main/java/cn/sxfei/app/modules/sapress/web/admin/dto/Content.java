package cn.sxfei.app.modules.sapress.web.admin.dto;

import cn.sxfei.app.core.base.dto.DTO;

public class Content extends DTO {
    private ContentDTO content;

    private String username;
    private String meta__meta1;
    private String[] _category;
    private String _tag;

    public ContentDTO getContent() {
        return content;
    }

    public void setContent(ContentDTO content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMeta__meta1() {
        return meta__meta1;
    }

    public void setMeta__meta1(String meta__meta1) {
        this.meta__meta1 = meta__meta1;
    }

    public String[] get_category() {
        return _category;
    }

    public void set_category(String[] _category) {
        this._category = _category;
    }

    public String get_tag() {
        return _tag;
    }

    public void set_tag(String _tag) {
        this._tag = _tag;
    }

}