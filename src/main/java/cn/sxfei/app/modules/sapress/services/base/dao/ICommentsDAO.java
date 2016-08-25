package cn.sxfei.app.modules.sapress.services.base.dao;

import java.util.List;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;

public interface ICommentsDAO extends IBaseDao<Comments, Long> {
    Comments selectByPrimaryKey(QueryDTO dto);

    List<Long> selectLastComments(QueryDTO dto);

    List<Long> selectAllLastComments(QueryDTO dto);

    List<Comments> selectByIds(QueryDTO dto);

    List<Long> selectForPage(CommentQueryDTO dto);
}