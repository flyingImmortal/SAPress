package cn.sxfei.app.modules.sapress.interfaces.base.enums;

public enum ModuleTypeEnum {
    POST("post", "文章"), PAGE("page", "页面");
    private String name;
    private String describe;

    private ModuleTypeEnum(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
