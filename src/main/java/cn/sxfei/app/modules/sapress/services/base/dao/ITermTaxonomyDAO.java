package cn.sxfei.app.modules.sapress.services.base.dao;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.TermTaxonomy;

public interface ITermTaxonomyDAO extends IBaseDao<TermTaxonomy, Long>{
    int deleteByPrimaryKey(Long termTaxonomyId);

    int insert(TermTaxonomy record);

    int insertSelective(TermTaxonomy record);

    TermTaxonomy selectByPrimaryKey(Long termTaxonomyId);

    int updateByPrimaryKeySelective(TermTaxonomy record);

    int updateByPrimaryKeyWithBLOBs(TermTaxonomy record);

    int updateByPrimaryKey(TermTaxonomy record);
}