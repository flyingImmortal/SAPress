package cn.sxfei.app.modules.sapress.interfaces.base.enums;

public enum TermTypeEnum {
    NAV_MENU_ITEM("nav_menu_item", ""), PAGE("page", ""), POST("post", ""), REVISION("revision","");
    private String name;
    private String describe;

    private TermTypeEnum(String name, String describe) {
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
