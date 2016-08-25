package cn.sxfei.app.modules.sapress.comm;

import cn.sxfei.app.core.base.BaseController;
import cn.sxfei.app.modules.sapress.comm.util.CookieUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;

public class SapressController extends BaseController {
    public AjaxResult renderAjaxResultForError() {
        return renderAjaxResult("error", 1, null);
    }

    public AjaxResult renderAjaxResultForError(String message) {
        return renderAjaxResult(message, 1, null);
    }

    public AjaxResult renderAjaxResultForSuccess(String message) {
        return renderAjaxResult(message, 0, null);
    }

    public AjaxResult renderAjaxResultForSuccess() {
        return renderAjaxResult("success", 0, null);
    }

    public AjaxResult renderAjaxResult(String message, int errorCode) {
        return renderAjaxResult(message, errorCode, null);
    }

    public AjaxResult renderAjaxResult(String message, int errorCode, Object data) {
        AjaxResult ar = new AjaxResult();
        ar.setMessage(message);
        ar.setErrorCode(errorCode);
        ar.setData(data);
        return ar;
    }

    public void setCookie(String name, String value) {
        setCookie(name, value, Integer.MAX_VALUE);
    }

    public void setCookie(String name, String value, int maxAgeInSeconds) {
        CookieUtils.put(response, name, value, maxAgeInSeconds);
    }

    public String getCookie(String name, String defaultValue) {
        String cookie = CookieUtils.getCookStr(request, name);
        return cookie != null ? cookie : defaultValue;
    }

    public UserDTO getLoginUser() {
        UserDTO user = PressServiceHelper.getSessionUser(request);
        return user;
    }

}
