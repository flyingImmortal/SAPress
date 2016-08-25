package cn.sxfei.app.modules.sapress.services.admin.po;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "options")
public class OptionsPO extends BaseModel<OptionsPO> {
    private static final long serialVersionUID = 1L;
    public static final OptionsPO dao = new OptionsPO();

    /**  */
    private String optionId;

    /**  */
    private String optionName;

    /**  */
    private String autoload;

    /**  */
    private String optionValue;

    public String getOptionId() {
        return getStr("optionId");
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
        set("optionId", optionId);
    }

    public String getOptionName() {
        return getStr("optionName");
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
        set("optionName", optionName);
    }

    public String getAutoload() {
        return getStr("autoload");
    }

    public void setAutoload(String autoload) {
        this.autoload = autoload;
        set("autoload", autoload);
    }

    public String getOptionValue() {
        return getStr("optionValue");
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
        set("optionValue", optionValue);
    }

}