package cn.sxfei.app.modules.sapress.interfaces.base.enums;

public enum PostStatusEnum {
    DRAFT("draft", "草稿"), INHERIT("inherit", "备份"), PUBLISH("publish", "已发布"), TRASH("trash", "已删除"), FUTURE("future", ""), PENDING(
            "pending", ""), PRIVATE("private", "");
    private String name;
    private String describe;

    private PostStatusEnum(String name, String describe) {
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
