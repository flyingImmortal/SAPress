package cn.sxfei.app.modules.sapress.services.base.dao;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.PostMeta;

public interface IPostMetaDAO  extends IBaseDao<PostMeta, Long> {
    int deleteByPrimaryKey(Long metaId);

    int insert(PostMeta record);

    int insertSelective(PostMeta record);

    PostMeta selectByPrimaryKey(Long metaId);

    int updateByPrimaryKeySelective(PostMeta record);

    int updateByPrimaryKeyWithBLOBs(PostMeta record);

    int updateByPrimaryKey(PostMeta record);
}