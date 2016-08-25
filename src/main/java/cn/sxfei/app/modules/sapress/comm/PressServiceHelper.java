package cn.sxfei.app.modules.sapress.comm;

import javax.servlet.http.HttpServletRequest;

import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.modules.sapress.comm.util.CookieUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;

import com.alibaba.fastjson.JSON;

public class PressServiceHelper {

    public static UserDTO getSessionUser(HttpServletRequest request) {
        String userId = CookieUtils.getUserId(request);
        if (userId != null && !"".equals(userId)) {
            String userStr = PropertiesHelper.getProperty(Constants.USER_OBJECT + userId);
            if (StringUtil.isNotBlank(userStr)) {
                return JSON.parseObject(userStr, UserDTO.class);
            }
        }
        return null;
    }

}
