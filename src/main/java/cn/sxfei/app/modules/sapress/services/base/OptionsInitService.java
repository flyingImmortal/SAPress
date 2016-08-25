package cn.sxfei.app.modules.sapress.services.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sxfei.app.core.base.IInitialiseService;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Options;
import cn.sxfei.app.modules.sapress.services.base.dao.IOptionsDAO;

/**
 * 参数初始化信息
 * 
 * @author sxfei 2016-07-10
 */
@Service
public class OptionsInitService implements IInitialiseService {
    @Autowired
    private IOptionsDAO optionsDAO;

    @Override
    public void init() {
        QueryDTO dto = new QueryDTO();
        List<Options> list = optionsDAO.selectAll(dto);
        for (Options opt : list) {
            PropertiesHelper.setProperty(opt.getOptionName(), opt.getOptionValue());
        }
    }
}
