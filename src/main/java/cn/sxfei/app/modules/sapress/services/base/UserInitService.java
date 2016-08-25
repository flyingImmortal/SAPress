package cn.sxfei.app.modules.sapress.services.base;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import cn.sxfei.app.core.base.IInitialiseService;
import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;
import cn.sxfei.app.modules.sapress.services.base.po.UserPO;

import com.alibaba.fastjson.JSON;

/**
 * 用户信息初始化信息
 * 
 * @author sxfei 2016-07-10
 */
@Service
public class UserInitService implements IInitialiseService {
    @Override
    public void init() {
        List<UserPO> list = UserPO.dao.findAll();
        for (UserPO user : list) {
            UserDTO userDto = new UserDTO();
            try {
                BeanUtils.copyProperties(userDto, user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            PropertiesHelper.setProperty(Constants.USER_KEY + user.getId(), user.getUserNicename());
            PropertiesHelper.setProperty(Constants.USER_OBJECT + user.getId(), JSON.toJSONString(userDto));
        }
    }
}
