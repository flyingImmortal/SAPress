package cn.sxfei.app.modules.sapress.services.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.sxfei.app.core.aspect.ViewLog;
import cn.sxfei.app.core.base.BaseService;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.exception.BusinessException;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.interfaces.base.service.ICommentsService;
import cn.sxfei.app.modules.sapress.services.base.dao.ICommentsDAO;

/**
 * 评论信息
 * 
 * @author sxfei 2016-07-16
 */
@Service
public class CommentsService extends BaseService<Comments, Long> implements ICommentsService {
    @Autowired
    private ICommentsDAO commentsDAO;

    @Override
    public Comments getByPrimaryKey(Long id) {
        QueryDTO dto = new QueryDTO();
        dto.setId(id);
        return commentsDAO.selectByPrimaryKey(dto);
    }

    @Override
    @ViewLog
    public List<Long> getLastComments() {
        QueryDTO dto = new QueryDTO();
        return commentsDAO.selectLastComments(dto);
    }

    @Override
    public List<Comments> getByIds(List<Long> idList) {
        QueryDTO dto = new QueryDTO();
        dto.setIdList(idList);
        return commentsDAO.selectByIds(dto);
    }

    @Override
    public List<Long> getAllLastComments() throws BusinessException {
        QueryDTO dto = new QueryDTO();
        return commentsDAO.selectAllLastComments(dto);
    }
    @Override
    public List<Long> selectForPage(CommentQueryDTO dto) throws BusinessException {
        return commentsDAO.selectForPage(dto);
    }
}
