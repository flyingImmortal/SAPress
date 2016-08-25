package cn.sxfei.app.modules.sapress.web.admin.dto;

import java.util.List;

public class TplTaxonomyType {

    public static final String TYPE_INPUT = "input";
    public static final String TYPE_SELECT = "select";

    private String title;
    private String name;
    private String formType = TYPE_SELECT;
    private TplModule module;
    private List<TplMetadata> metadatas;

    public List<TplMetadata> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(List<TplMetadata> metadatas) {
        this.metadatas = metadatas;
    }

    public void setModule(TplModule module) {
        this.module = module;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TplModule getModule() {
        return module;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Override
    public String toString() {
        return "TaxonomyType [title=" + title + ", name=" + name + ", formType=" + formType + "]";
    }

}