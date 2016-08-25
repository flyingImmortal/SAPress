package cn.sxfei.app.modules.sapress.services.base.po;

import java.math.BigInteger;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

@ModelBind(table = "usermeta", key = "umeta_id")
public class UserMetaPO extends BaseModel<UserMetaPO> {
    private static final long serialVersionUID = 1L;
    public static final UserMetaPO dao = new UserMetaPO();

    /**  */
    private Long umetaId;

    /**  */
    private BigInteger userId;

    /**  */
    private String metaKey;

    /**  */
    private String metaValue;

    public String getUmetaId() {
        return getStr("umetaId");
    }

    public void setMetaId(Long umetaId) {
        this.umetaId = umetaId;
        set("umetaId", umetaId);
    }

    public Long getUserId() {
        return Long.parseLong(getStr("userId"));
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
        set("userId", userId);
    }

    public String getMetaKey() {
        return getStr("metaKey");
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
        set("metaKey", metaKey);
    }

    public String getMetaValue() {
        return getStr("metaValue");
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
        set("metaValue", metaValue);
    }

}