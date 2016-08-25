package cn.sxfei.app.modules.sapress.interfaces.base.entity;

import cn.sxfei.app.core.base.dto.BaseEntity;

public class Options extends BaseEntity {
    /**  */
    private Long optionId;

    /**  */
    private String optionName;

    /**  */
    private String autoload;

    /**  */
    private String optionValue;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public String getAutoload() {
        return autoload;
    }

    public void setAutoload(String autoload) {
        this.autoload = autoload == null ? null : autoload.trim();
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue == null ? null : optionValue.trim();
    }
}