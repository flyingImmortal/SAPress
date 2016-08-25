package cn.sxfei.app.modules.sapress.interfaces.base.enums;

public enum CommentStatusEnum {
    PENDING("0", "待审"), APPROVED("1", "已批准"), SPAM("spam", "垃圾"), TRASH("trash", "回收站");
    
    private String name;
    private String describe;

    private CommentStatusEnum(String name, String describe) {
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
