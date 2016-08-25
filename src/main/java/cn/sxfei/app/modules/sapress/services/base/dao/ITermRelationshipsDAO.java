package cn.sxfei.app.modules.sapress.services.base.dao;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.TermRelationships;

public interface ITermRelationshipsDAO extends IBaseDao<TermRelationships, Long>{
    int deleteByPrimaryKey(TermRelationships key);

    int insert(TermRelationships record);

    int insertSelective(TermRelationships record);

    TermRelationships selectByPrimaryKey(TermRelationships key);

    int updateByPrimaryKeySelective(TermRelationships record);

    int updateByPrimaryKey(TermRelationships record);
}