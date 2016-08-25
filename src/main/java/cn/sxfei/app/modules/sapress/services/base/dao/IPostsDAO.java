package cn.sxfei.app.modules.sapress.services.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sxfei.app.core.base.IBaseDao;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Posts;

/**
 * 日志
 * 
 * @author sxfei
 * 
 */
@Repository
public interface IPostsDAO extends IBaseDao<Posts, Long> {

    List<Long> selectLastPosts(QueryDTO dto);

    List<Long> selectHotPosts(QueryDTO dto);

    Long selectRandomPosts(QueryDTO dto);

    List<Posts> selectByIds(QueryDTO dto);

    Posts selectByPrimaryKey(QueryDTO dto);

    List<Long> selectByPage(PostQueryDTO dto);

    Long selectNextPost(QueryDTO dto);

    Long selectPrevPost(QueryDTO dto);
}
