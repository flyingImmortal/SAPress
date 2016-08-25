package cn.sxfei.app.modules.sapress.services.base.po;

import java.util.Date;

import cn.sxfei.app.plugins.jfinal.BaseModel;
import cn.sxfei.app.plugins.jfinal.ModelBind;

//Constants.TABLE_PREFIX+
@ModelBind(table = "users")
public class UserPO extends BaseModel<UserPO> {

    private static final long serialVersionUID = 1L;
    public static final UserPO dao = new UserPO();
    /**  */
    private String id;

    /**  */
    private String userLogin;

    /**  */
    private String userPass;

    /**  */
    private String userNicename;

    /**  */
    private String userEmail;

    /**  */
    private String userUrl;

    /**  */
    private Date userRegistered;

    /**  */
    private String userActivationKey;

    /**  */
    private Integer userStatus;

    /**  */
    private String displayName;

    /**
     * @return the id
     */
    public String getId() {
        return getStr("id");
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
        set("id", id);
    }

    /**
     * @return the userLogin
     */
    public String getUserLogin() {
        return getStr("userLogin");
    }

    /**
     * @param userLogin the userLogin to set
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        set("userLogin", userLogin);
    }

    /**
     * @return the userPass
     */
    public String getUserPass() {
        return getStr("userPass");
    }

    /**
     * @param userPass the userPass to set
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
        set("userPass", userPass);
    }

    /**
     * @return the userNicename
     */
    public String getUserNicename() {
        return getStr("userNicename");
    }

    /**
     * @param userNicename the userNicename to set
     */
    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
        set("userNicename", userNicename);
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return getStr("userEmail");
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        set("userEmail", userEmail);
    }

    /**
     * @return the userUrl
     */
    public String getUserUrl() {
        return getStr("userUrl");
    }

    /**
     * @param userUrl the userUrl to set
     */
    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
        set("userUrl", userUrl);
    }

    /**
     * @return the userRegistered
     */
    public Date getUserRegistered() {
        return getDate("userRegistered");
    }

    /**
     * @param userRegistered the userRegistered to set
     */
    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
        set("userRegistered", userRegistered);
    }

    /**
     * @return the userActivationKey
     */
    public String getUserActivationKey() {
        return getStr("userActivationKey");
    }

    /**
     * @param userActivationKey the userActivationKey to set
     */
    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey;
        set("userActivationKey", userActivationKey);
    }

    /**
     * @return the userStatus
     */
    public Integer getUserStatus() {
        return getInt("userStatus");
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        set("userStatus", userStatus);
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return getStr("displayName");
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        set("displayName", displayName);
    }
    
}