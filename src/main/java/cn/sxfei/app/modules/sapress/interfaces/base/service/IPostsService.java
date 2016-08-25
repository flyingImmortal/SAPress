package cn.sxfei.app.modules.sapress.interfaces.base.service;

import java.util.List;
import java.util.Map;

import cn.sxfei.app.core.base.IBaseService;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.exception.BusinessException;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Posts;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;

public interface IPostsService extends IBaseService<Posts, Long> {
    public List<Long> getLastPosts(QueryDTO queryDto) throws BusinessException;

    public List<Long> getHotPosts() throws BusinessException;

    public List<Posts> getByIds(List<Long> idList) throws BusinessException;

    public Long getRandomPosts() throws BusinessException;

    public Map<Long, List<Terms>> getTermByIds(List<Long> idList) throws BusinessException;

    public List<PostDTO> getResultByIds(List<Long> idList) throws BusinessException;

    public List<PostDTO> getNextById(Long id) throws BusinessException;

    public List<PostDTO> getPrevById(Long id) throws BusinessException;

    public List<PostDTO> getRelatePosts(Long id) throws BusinessException;
}
