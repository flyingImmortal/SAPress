package cn.sxfei.app.modules.sapress.services.base.dao;

import java.util.List;
import java.util.Map;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;

public interface ITermsDAO extends IBaseDao<Terms, Long> {
    Terms selectByPrimaryKey(QueryDTO dto);

    List<Map> selectTermAndTaxonomy(QueryDTO dto);
    List<Map> selectTermByPostId(QueryDTO dto);
}