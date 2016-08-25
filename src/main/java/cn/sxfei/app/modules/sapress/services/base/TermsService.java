package cn.sxfei.app.modules.sapress.services.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sxfei.app.core.base.BaseService;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.utils.BeanUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;
import cn.sxfei.app.modules.sapress.interfaces.base.service.ITermsService;
import cn.sxfei.app.modules.sapress.services.base.dao.ITermsDAO;

import com.google.common.collect.Lists;

/**
 * 分组分类信息
 * 
 * @author sxfei 2016-07-10
 */
@Service
public class TermsService extends BaseService<Terms, Long> implements ITermsService {
    @Autowired
    private ITermsDAO termsDAO;

    @Override
    public Terms getByPrimaryKey(Long id) {
        QueryDTO dto = new QueryDTO();
        dto.setId(id);
        return termsDAO.selectByPrimaryKey(dto);
    }

    @Override
    public List<Terms> getAllTerm() {
        List<Terms> resultList = Lists.newArrayList();
        PostQueryDTO dto = new PostQueryDTO();
        dto.setPageSize(Integer.MAX_VALUE);
        List<Map> temp = termsDAO.selectTermAndTaxonomy(dto);
        for (Map map : temp) {
            Terms terms = new Terms();
            BeanUtils.mapToObject(terms, map);
            resultList.add(terms);
        }
        return resultList;
    }
}
