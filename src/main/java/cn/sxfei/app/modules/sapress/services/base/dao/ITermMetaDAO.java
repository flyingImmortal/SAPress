package cn.sxfei.app.modules.sapress.services.base.dao;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.PostMeta;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.TermMeta;

public interface ITermMetaDAO extends IBaseDao<TermMeta, Long> {
    int deleteByPrimaryKey(Long metaId);

    int insert(TermMeta record);

    int insertSelective(TermMeta record);

    TermMeta selectByPrimaryKey(Long metaId);

    int updateByPrimaryKeySelective(TermMeta record);

    int updateByPrimaryKeyWithBLOBs(TermMeta record);

    int updateByPrimaryKey(TermMeta record);
}