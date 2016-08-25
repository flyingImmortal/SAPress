package cn.sxfei.app.modules.sapress.services.base.dao;

import java.util.List;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.CommentMeta;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;

public interface ICommentMetaDAO extends IBaseDao<CommentMeta, Long> {
    CommentMeta selectByPrimaryKey(QueryDTO dto);

    List<CommentMeta> selectByIds(QueryDTO dto);
}