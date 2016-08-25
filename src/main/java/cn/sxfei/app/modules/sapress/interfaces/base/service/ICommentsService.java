package cn.sxfei.app.modules.sapress.interfaces.base.service;

import java.util.List;

import cn.sxfei.app.core.base.IBaseService;
import cn.sxfei.app.core.base.exception.BusinessException;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;

/**
 * @author sxfei 2016-07-16
 */
public interface ICommentsService extends IBaseService<Comments, Long> {
    /**
     * 获取某文章最近的评论
     * 
     * @return
     * @throws BusinessException
     */
    public List<Long> getLastComments() throws BusinessException;

    /**
     * 获取所有文章最后评论列表
     * 
     * @return
     * @throws BusinessException
     */
    public List<Long> getAllLastComments() throws BusinessException;

    public List<Comments> getByIds(List<Long> idList) throws BusinessException;

    public List<Long> selectForPage(CommentQueryDTO dto) throws BusinessException;
}
