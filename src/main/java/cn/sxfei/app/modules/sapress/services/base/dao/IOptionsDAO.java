package cn.sxfei.app.modules.sapress.services.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Options;

/**
 * 配置表
 * 
 * @author sxfei
 * 
 */
@Repository
public interface IOptionsDAO extends IBaseDao<Options, Integer> {
    List<Options> selectAll(QueryDTO dto);
    Options selectByPrimaryKey(QueryDTO dto);

}
